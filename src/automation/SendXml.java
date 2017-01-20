package automation;

import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SendXml {

    public static void main(String args[]) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element results = doc.createElement("Results");
        doc.appendChild(results);

        //add
        Document mapDoc = null, dataDoc = null, newDoc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docbuilder = dbFactory.newDocumentBuilder();
            mapDoc = docbuilder.parse("src/map/mappinguser.xml");
            dataDoc = docbuilder.newDocument();
            dataDoc = docbuilder.newDocument();
            newDoc = docbuilder.newDocument();
        } catch (Exception e) {
            System.out.println("Problem creating document: " + e.getMessage());
            e.printStackTrace();
        }

        Element mapRoot = mapDoc.getDocumentElement();
        //end

        //
        //add
        Connection conn = (Connection) controller.koneksi.connDb();
        PreparedStatement statement = null;
        ResultSet resultset = null;
        ResultSetMetaData resultmetadata = null;
        Element dataRoot = dataDoc.createElement("data");
        try {
            //statement = conn.prepareStatement("select * from `case`  where id='849'");
            statement = conn.prepareStatement("select * from `case`  where client='10'");
            resultset = statement.executeQuery();
            resultmetadata = resultset.getMetaData();
            int numcols = resultmetadata.getColumnCount();

            while (resultset.next()) {
                Element rowEl = dataDoc.createElement("row");
                for (int i = 1; i <= numcols; i++) {
                    String colName = resultmetadata.getColumnName(i);
                    String colVal = resultset.getString(i);
                    if (resultset.wasNull()) {
                        colVal = "and up";
                    }
                    Element dataEl = dataDoc.createElement(colName);
                    dataEl.appendChild(dataDoc.createTextNode(colVal));
                    rowEl.appendChild(dataEl);
                }
                dataRoot.appendChild(rowEl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //end
        //

        dataDoc.appendChild(dataRoot);
        //Retrieve the root element (also called "root")
        Element newRootInfo = (Element) mapRoot.getElementsByTagName("root").item(0);
        //newRootInfo.setAttribute("xmlns:xsi", "http");
        //newRootInfo.appendChild(newDoc.createTextNode("xmlns:xsi"));
        //dataRoot.appendChild(newRootInfo);

        //Retrieve the root and row information
        String newRootName = newRootInfo.getAttribute("name");
        String newRowName = newRootInfo.getAttribute("rowName");
        //Retrieve information on elements to be built in the new document
        NodeList newNodesMap = mapRoot.getElementsByTagName("element");

        //add
        NodeList newNodesB = mapRoot.getElementsByTagName("id");
        //end add

        //Create the final root element with the name from the mapping file
        Element newRootElement = newDoc.createElement(newRootName);

        //Retrieve all rows in the old document
        NodeList oldRows = dataRoot.getElementsByTagName("row");
        //add
        Element newElementB = null;
        //end add

        for (int i = 0; i < oldRows.getLength(); i++) {

            //Retrieve each row in turn
            Element thisRow = (Element) oldRows.item(i);

            //Create the new row
            Element newRow = newDoc.createElement(newRowName);
            Element newElement = null;
            for (int j = 0; j < newNodesMap.getLength(); j++) {

                //For each node in the new mapping, retrieve the information 			 
                //First the new information...  
                Element thisElement = (Element) newNodesMap.item(j);
                String newElementName = thisElement.getAttribute("name");

                //Then the old information
                Element oldElement = (Element) thisElement.getElementsByTagName("content").item(0);
                String oldField = oldElement.getFirstChild().getNodeValue();

                //Get the original values based on the mapping information
                Element oldValueElement = (Element) thisRow.getElementsByTagName(oldField).item(0);
                String oldValue = oldValueElement.getFirstChild().getNodeValue();

                //Create the new element
                newElement = newDoc.createElement(newElementName);
                newElement.appendChild(newDoc.createTextNode(oldValue));

                //Add the new element to the new row
                newRow.appendChild(newElement);

            }

            //add
            Element thisElementB = (Element) newNodesB.item(0);
            String newElementNameB = thisElementB.getAttribute("name");
            //Then the old information
            Element oldElementB = (Element) thisElementB.getElementsByTagName("content").item(0);
            String oldFieldB = oldElementB.getFirstChild().getNodeValue();

            //Get the original values based on the mapping information
            Element oldValueElementB = (Element) thisRow.getElementsByTagName(oldFieldB).item(0);
            String oldValueB = oldValueElementB.getFirstChild().getNodeValue();

            //Create the new element
            newElementB = newDoc.createElement(newElementNameB);
            newElementB.appendChild(newDoc.createTextNode(oldValueB));
            //end add

            //Add the new row to the root
            newRootElement.appendChild(newRow);
        }
        //Add the new root to the document
        newRootElement.appendChild(newElementB);
       
        //Add Batch Id
        Node scan = newElementB.getParentNode();
        newElementB.getParentNode().insertBefore(newElementB, scan.getFirstChild());
        newDoc.appendChild(newRootElement);

        DOMSource domSource = new DOMSource(newDoc);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
//        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);

        //add to create new file
//        StreamResult result = new StreamResult(new File("aia.xml"));
//        transformer.transform(domSource, result);
//        StreamResult consoleResult =  new StreamResult(System.out);
//        transformer.transform(domSource, consoleResult);
        //end add
        
        System.out.println(sw.toString());

        conn.close();
    }
}
