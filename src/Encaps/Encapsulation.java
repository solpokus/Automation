package Encaps;

import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Encapsulation {

    DocumentBuilderFactory factory, dbFactory;
    DocumentBuilder builder, docBuilder;
    Document doc, mapDoc = null, dataDoc = null, newDoc = null;

    ////
    Element results, mapRoot, dataRoot, rowEl, dataEl, newRootInfo,
            newRootElement, thisRow, newRow, newElement, thisElement,
            oldElement, oldValueElement, thisElementB, oldElementB,
            oldValueElementB, newElementB;

    NodeList newNodesMap, newNodesB, oldRows;

    Node scan;

    String sql, colName, colVal, newRootName, newRowName, newElementName,
            oldField, oldValue, newElementNameB, oldFieldB, oldValueB;

    String tgl;
    ////
    Connection conn;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    ResultSetMetaData resultSetMetaData = null;

    ////
    DOMSource domSource;
    TransformerFactory tf;
    Transformer transformer;
    StringWriter sw;
    StreamResult sr, result, consoleResult;

    ////
    File fileMapping, fileInput, fileOutput;

    public DocumentBuilderFactory getFactory() {
        return factory;
    }

    public void setFactory(DocumentBuilderFactory factory) {
        this.factory = factory;
    }

    public DocumentBuilderFactory getDbFactory() {
        return dbFactory;
    }

    public void setDbFactory(DocumentBuilderFactory dbFactory) {
        this.dbFactory = dbFactory;
    }

    public DocumentBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(DocumentBuilder builder) {
        this.builder = builder;
    }

    public DocumentBuilder getDocBuilder() {
        return docBuilder;
    }

    public void setDocBuilder(DocumentBuilder docBuilder) {
        this.docBuilder = docBuilder;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Document getMapDoc() {
        return mapDoc;
    }

    public void setMapDoc(Document mapDoc) {
        this.mapDoc = mapDoc;
    }

    public Document getDataDoc() {
        return dataDoc;
    }

    public void setDataDoc(Document dataDoc) {
        this.dataDoc = dataDoc;
    }

    public Document getNewDoc() {
        return newDoc;
    }

    public void setNewDoc(Document newDoc) {
        this.newDoc = newDoc;
    }

    public Element getResults() {
        return results;
    }

    public void setResults(Element results) {
        this.results = results;
    }

    public Element getMapRoot() {
        return mapRoot;
    }

    public void setMapRoot(Element mapRoot) {
        this.mapRoot = mapRoot;
    }

    public Element getDataRoot() {
        return dataRoot;
    }

    public void setDataRoot(Element dataRoot) {
        this.dataRoot = dataRoot;
    }

    public Element getRowEl() {
        return rowEl;
    }

    public void setRowEl(Element rowEl) {
        this.rowEl = rowEl;
    }

    public Element getDataEl() {
        return dataEl;
    }

    public void setDataEl(Element dataEl) {
        this.dataEl = dataEl;
    }

    public Element getNewRootInfo() {
        return newRootInfo;
    }

    public void setNewRootInfo(Element newRootInfo) {
        this.newRootInfo = newRootInfo;
    }

    public Element getNewRootElement() {
        return newRootElement;
    }

    public void setNewRootElement(Element newRootElement) {
        this.newRootElement = newRootElement;
    }

    public Element getThisRow() {
        return thisRow;
    }

    public void setThisRow(Element thisRow) {
        this.thisRow = thisRow;
    }

    public Element getNewRow() {
        return newRow;
    }

    public void setNewRow(Element newRow) {
        this.newRow = newRow;
    }

    public Element getNewElement() {
        return newElement;
    }

    public void setNewElement(Element newElement) {
        this.newElement = newElement;
    }

    public Element getThisElement() {
        return thisElement;
    }

    public void setThisElement(Element thisElement) {
        this.thisElement = thisElement;
    }

    public Element getOldElement() {
        return oldElement;
    }

    public void setOldElement(Element oldElement) {
        this.oldElement = oldElement;
    }

    public Element getOldValueElement() {
        return oldValueElement;
    }

    public void setOldValueElement(Element oldValueElement) {
        this.oldValueElement = oldValueElement;
    }

    public Element getThisElementB() {
        return thisElementB;
    }

    public void setThisElementB(Element thisElementB) {
        this.thisElementB = thisElementB;
    }

    public Element getOldElementB() {
        return oldElementB;
    }

    public void setOldElementB(Element oldElementB) {
        this.oldElementB = oldElementB;
    }

    public Element getOldValueElementB() {
        return oldValueElementB;
    }

    public void setOldValueElementB(Element oldValueElementB) {
        this.oldValueElementB = oldValueElementB;
    }

    public Element getNewElementB() {
        return newElementB;
    }

    public void setNewElementB(Element newElementB) {
        this.newElementB = newElementB;
    }

    public NodeList getNewNodesMap() {
        return newNodesMap;
    }

    public void setNewNodesMap(NodeList newNodesMap) {
        this.newNodesMap = newNodesMap;
    }

    public NodeList getNewNodesB() {
        return newNodesB;
    }

    public void setNewNodesB(NodeList newNodesB) {
        this.newNodesB = newNodesB;
    }

    public NodeList getOldRows() {
        return oldRows;
    }

    public void setOldRows(NodeList oldRows) {
        this.oldRows = oldRows;
    }

    public Node getScan() {
        return scan;
    }

    public void setScan(Node scan) {
        this.scan = scan;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColVal() {
        return colVal;
    }

    public void setColVal(String colVal) {
        this.colVal = colVal;
    }

    public String getNewRootName() {
        return newRootName;
    }

    public void setNewRootName(String newRootName) {
        this.newRootName = newRootName;
    }

    public String getNewRowName() {
        return newRowName;
    }

    public void setNewRowName(String newRowName) {
        this.newRowName = newRowName;
    }

    public String getNewElementName() {
        return newElementName;
    }

    public void setNewElementName(String newElementName) {
        this.newElementName = newElementName;
    }

    public String getOldField() {
        return oldField;
    }

    public void setOldField(String oldField) {
        this.oldField = oldField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewElementNameB() {
        return newElementNameB;
    }

    public void setNewElementNameB(String newElementNameB) {
        this.newElementNameB = newElementNameB;
    }

    public String getOldFieldB() {
        return oldFieldB;
    }

    public void setOldFieldB(String oldFieldB) {
        this.oldFieldB = oldFieldB;
    }

    public String getOldValueB() {
        return oldValueB;
    }

    public void setOldValueB(String oldValueB) {
        this.oldValueB = oldValueB;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ResultSetMetaData getResultSetMetaData() {
        return resultSetMetaData;
    }

    public void setResultSetMetaData(ResultSetMetaData resultSetMetaData) {
        this.resultSetMetaData = resultSetMetaData;
    }

    public DOMSource getDomSource() {
        return domSource;
    }

    public void setDomSource(DOMSource domSource) {
        this.domSource = domSource;
    }

    public TransformerFactory getTf() {
        return tf;
    }

    public void setTf(TransformerFactory tf) {
        this.tf = tf;
    }

    public Transformer getTransformer() {
        return transformer;
    }

    public void setTransformer(Transformer transformer) {
        this.transformer = transformer;
    }

    public StringWriter getSw() {
        return sw;
    }

    public void setSw(StringWriter sw) {
        this.sw = sw;
    }

    public StreamResult getSr() {
        return sr;
    }

    public void setSr(StreamResult sr) {
        this.sr = sr;
    }

    public StreamResult getResult() {
        return result;
    }

    public void setResult(StreamResult result) {
        this.result = result;
    }

    public StreamResult getConsoleResult() {
        return consoleResult;
    }

    public void setConsoleResult(StreamResult consoleResult) {
        this.consoleResult = consoleResult;
    }

    public File getFileMapping() {
        return fileMapping;
    }

    public void setFileMapping(File fileMapping) {
        this.fileMapping = fileMapping;
    }

    public File getFileInput() {
        return fileInput;
    }

    public void setFileInput(File fileInput) {
        this.fileInput = fileInput;
    }

    public File getFileOutput() {
        return fileOutput;
    }

    public void setFileOutput(File fileOutput) {
        this.fileOutput = fileOutput;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

}
