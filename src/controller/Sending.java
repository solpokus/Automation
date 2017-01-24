package controller;

import Encaps.Encapsulation;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

public class Sending {

    Encapsulation encaps = new Encapsulation();
    koneksi kon = new koneksi();

    public void Send() throws Exception {
        //
//        encaps.setFactory(DocumentBuilderFactory.newInstance());
//        encaps.setBuilder(encaps.getFactory().newDocumentBuilder());
//        encaps.setDoc(encaps.getBuilder().newDocument());
//        encaps.setResults(encaps.getDoc().createElement("Results"));
//        encaps.getDoc().appendChild(encaps.getResults());
        ///
        //build document
        try {
            encaps.setDbFactory(DocumentBuilderFactory.newInstance());
            encaps.setDocBuilder(encaps.getDbFactory().newDocumentBuilder());
            encaps.setFileMapping(new File("src/map/mappinguser.xml"));
            encaps.setDataDoc(encaps.getDocBuilder().newDocument());
            encaps.setMapDoc(encaps.getDocBuilder().parse(encaps.getFileMapping()));
            encaps.setNewDoc(encaps.getDocBuilder().newDocument());
        } catch (Exception e) {
            System.out.println("Problem creating document: " + e.getMessage());
            e.printStackTrace();
        }

        encaps.setMapRoot(encaps.getMapDoc().getDocumentElement());
        encaps.setDataRoot(encaps.getDataDoc().createElement("data"));

        //start connection
        encaps.setConn(kon.connDb());
        Date tgl = new Date();
        DateFormat df = new DateFormat();
        encaps.setTgl(df.dateFormat(tgl));
        try {
            //encaps.setSql("select * from `case` where client='10' and haf_doc_completed_date like '%" + encaps.getTgl() + "%'");
            encaps.setSql("select * from `case` where client='10'");
            encaps.setStatement(encaps.getConn().prepareStatement(encaps.getSql()));
            encaps.setResultSet(encaps.getStatement().executeQuery());
            encaps.setResultSetMetaData(encaps.getResultSet().getMetaData());
            int numcols = encaps.getResultSetMetaData().getColumnCount();

            while (encaps.getResultSet().next()) {
                encaps.setRowEl(encaps.getDataDoc().createElement("row"));
                for (int i = 1; i <= numcols; i++) {
                    encaps.setColName(encaps.getResultSetMetaData().getColumnName(i));
                    encaps.setColVal(encaps.getResultSet().getString(i));
                    if (encaps.getResultSet().wasNull()) {
                        encaps.setColVal("");
                    }
                    encaps.setDataEl(encaps.getDataDoc().createElement(encaps.getColName()));
                    encaps.getDataEl().appendChild(encaps.getDataDoc().createTextNode(encaps.getColVal()));
                    encaps.getRowEl().appendChild(encaps.getDataEl());
                }
                encaps.getDataRoot().appendChild(encaps.getRowEl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        encaps.getDataDoc().appendChild(encaps.getDataRoot());
        //
        encaps.setNewRootInfo((Element) encaps.getMapRoot().getElementsByTagName("root").item(0));
        encaps.setNewRootName(encaps.getNewRootInfo().getAttribute("name"));
        encaps.setNewRowName(encaps.getNewRootInfo().getAttribute("rowName"));

        //node list
        encaps.setNewNodesMap(encaps.getMapRoot().getElementsByTagName("element"));
        encaps.setNewNodesB(encaps.getMapRoot().getElementsByTagName("id"));

        encaps.setNewRootElement(encaps.getNewDoc().createElement(encaps.getNewRootName()));
        encaps.setOldRows(encaps.getDataRoot().getElementsByTagName("row"));

        //
        for (int i = 0; i < encaps.getOldRows().getLength(); i++) {
            encaps.setThisRow((Element) encaps.getOldRows().item(i));

            encaps.setNewRow(encaps.getNewDoc().createElement(encaps.getNewRowName()));

            for (int j = 0; j < encaps.getNewNodesMap().getLength(); j++) {
                encaps.setThisElement((Element) encaps.getNewNodesMap().item(j));
                encaps.setNewElementName(encaps.getThisElement().getAttribute("name"));

                encaps.setOldElement((Element) encaps.getThisElement().getElementsByTagName("content").item(0));
                encaps.setOldField(encaps.getOldElement().getFirstChild().getNodeValue());

                encaps.setOldValueElement((Element) encaps.getThisRow().getElementsByTagName(encaps.getOldField()).item(0));
                encaps.setOldValue(encaps.getOldValueElement().getFirstChild().getNodeValue());

                encaps.setNewElement(encaps.getNewDoc().createElement(encaps.getNewElementName()));
                encaps.getNewElement().appendChild(encaps.getNewDoc().createTextNode(encaps.getOldValue()));

                encaps.getNewRow().appendChild(encaps.getNewElement());
            }
            encaps.setThisElementB((Element) encaps.getNewNodesB().item(0));
            encaps.setNewElementNameB(encaps.getThisElementB().getAttribute("name"));

            encaps.setOldElementB((Element) encaps.getThisElementB().getElementsByTagName("content").item(0));
            encaps.setOldFieldB(encaps.getOldElementB().getFirstChild().getNodeValue());

            encaps.setOldValueElementB((Element) encaps.getThisRow().getElementsByTagName(encaps.getOldFieldB()).item(0));
            encaps.setOldValueB(encaps.getOldValueElementB().getFirstChild().getNodeValue());

            encaps.setNewElementB(encaps.getNewDoc().createElement(encaps.getNewElementNameB()));
            encaps.getNewElementB().appendChild(encaps.getNewDoc().createTextNode(encaps.getOldValueB()));

            encaps.getNewRootElement().appendChild(encaps.getNewRow());
        }
        //add the new root to the document
        encaps.getNewRootElement().appendChild(encaps.getNewElementB());

        //add batch id
        encaps.setScan(encaps.getNewElementB().getParentNode());
        encaps.getNewElementB().getParentNode().insertBefore(encaps.getNewElementB(), encaps.getScan().getFirstChild());
        encaps.getNewDoc().appendChild(encaps.getNewRootElement());

        //
        encaps.setDomSource(new DOMSource(encaps.getNewDoc()));
        encaps.setTf(encaps.getTf().newInstance());
        encaps.setTransformer(encaps.getTf().newTransformer());
        encaps.getTransformer().setOutputProperty(OutputKeys.METHOD, "xml");
        encaps.getTransformer().setOutputProperty(OutputKeys.ENCODING, "utf-8");
        encaps.setSw(new StringWriter());
        encaps.setSr(new StreamResult(encaps.getSw()));
        encaps.getTransformer().transform(encaps.getDomSource(), encaps.getSr());
        System.out.println(encaps.getSw().toString());

        //close conn
        //encaps.getConn().close();
    }

    public void createFile() throws TransformerException {
        Date tgl = new Date();
        DateFormat df = new DateFormat();
        
        encaps.setTgl(df.dateFormat(tgl));
        encaps.setFileInput(new File("aia.xml"));
        encaps.setResult(new StreamResult(encaps.getFileInput()));
        encaps.getTransformer().transform(encaps.getDomSource(), encaps.getResult());
        encaps.setConsoleResult(new StreamResult(System.out));
        //encaps.getTransformer().transform(encaps.getDomSource(), encaps.getConsoleResult());
        
    }
}
