/**
 *{{IS_NOTE
 *	Purpose:
 *		
 *	Description:
 *		
 *	History:
 *		Sep 8, 2008, Created by Dennis.Chen
 *}}IS_NOTE
 *
 *Copyright (C) 2007 Potix Corporation. All Rights Reserved.
 *
 *{{IS_RIGHT
 *	This program is distributed under GPL Version 2.0 in the hope that
 *	it will be useful, but WITHOUT ANY WARRANTY.
 *}}IS_RIGHT
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

/**
 * @author Dennis.Chen
 *
 */
public class XMLs {

	static private XPathFactory xPathFactory = XPathFactory.newInstance();
	static private DocumentBuilderFactory docFactory  = DocumentBuilderFactory.newInstance();
	
	static public Node toXMLNode(Document owner, String str){
		Node node = null;
		try{
			node = toXMLInstruction(owner,str);
		}catch(Exception x){};
		if(node==null){
			try{
				node =toXMLElement(owner,str);
			}catch(Exception x){}
		}
		return node;
	}
	
	static public Element toXMLElement(Document owner, String str) throws Exception{
		try {
			Document document = toDocument(str);
			Element src = document.getDocumentElement();
			Element dest = owner.createElement(src.getTagName());
			
			cloneElement(src,dest);
			
			
			return dest;
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}
	}
	
	
	
	public static ProcessingInstruction toXMLInstruction(Document owner, String str) throws Exception{
		try {
			str = str.trim();
			if(!(str.startsWith("<?") && str.endsWith("?>"))){ //$NON-NLS-1$ //$NON-NLS-2$
				throw new IllegalArgumentException("not a ProcessingInstruction string"); //$NON-NLS-1$
			}
			str = str+"<dummy-body/>";//need a body, or can't be a document. //$NON-NLS-1$
			Document doc = toDocument(str);
			NodeList nl = doc.getChildNodes();
			for(int i=0;i<nl.getLength();i++){
				Node n = nl.item(i);
				if(n instanceof ProcessingInstruction){
					ProcessingInstruction pi = owner.createProcessingInstruction(
							((ProcessingInstruction)n).getTarget(), 
							((ProcessingInstruction)n).getData());
					return pi;
				}
			}
			return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}
		
	}
	
	
	static private void cloneElement(Element src,Element dest){
		Document doc = dest.getOwnerDocument();
		
		NodeList nl = src.getChildNodes();
		int size = nl.getLength();
		Node ns,nd;
		for(int i=0;i<size;i++){
			nd = null;
			ns = nl.item(i);
			
			if(ns instanceof Element){
				nd  = doc.createElement(((Element)ns).getTagName());
				cloneElement((Element)ns,(Element)nd);
			}else if(ns instanceof CDATASection){//CDATA first
				nd  = doc.createCDATASection(((CDATASection)ns).getData());
			}else if(ns instanceof Text){
				nd  = doc.createTextNode(((Text)ns).getData());
			}else if(ns instanceof Comment){
				nd  = doc.createComment(((Comment)ns).getData());
			}
			if(nd!=null){
				dest.appendChild(nd);
			}
		}
		
		NamedNodeMap nnm = src.getAttributes();
		size = nnm.getLength();
		for(int i=0;i<size;i++){
			nd = null;
			ns = nnm.item(i);
			if(ns instanceof Attr){
				nd  = doc.createAttribute(((Attr)ns).getName());
				((Attr)nd).setValue(((Attr)ns).getValue());
				dest.setAttributeNode((Attr)nd);
			}
		}
		
	}

	static public void main(String[] args){
		try {

			String file = "D:/ZK/zk studio/eclipse/org.zkoss.eclipse.zks/src/org/zkoss/eclipse/zks/template/config/def.xml"; //$NON-NLS-1$
			
			Document doc = toDocument(new File(file));
			
			System.out.println("1.XML:\n"+toXMLString(doc)); //$NON-NLS-1$
			NodeList nl = queryNodeList(doc.getDocumentElement(),"/template-metainfo/wizards/wizard"); //$NON-NLS-1$
			for(int i=0;i<nl.getLength();i++){
				System.out.println("=====\n"+toXMLString(nl.item(i))); //$NON-NLS-1$
			}
			Element elm = queryElement(doc.getDocumentElement(),"/template-metainfo/processors"); //$NON-NLS-1$
			System.out.println("=====\n"+toXMLString(elm)); //$NON-NLS-1$
			
			Node node = queryNode(elm,"processor[@id=\"out\"]/text()"); //$NON-NLS-1$
			System.out.println("=====\n"+node.getNodeValue()); //$NON-NLS-1$
			node = queryNode(elm,"processor[@id=\"out\"]/@class"); //$NON-NLS-1$
			System.out.println("=====\n"+node.getNodeValue()); //$NON-NLS-1$
			
			System.out.println("=====\n"+queryText(elm, "processor[@id=\"h1\"]")); //$NON-NLS-1$ //$NON-NLS-2$
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Document toDocument(File file) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuffer sb = new StringBuffer();
		try{
			String line = reader.readLine();
			while(line!=null){
				sb.append(line);
				line = reader.readLine();
				if(line!=null){
					sb.append("\n"); //$NON-NLS-1$
				}
			}
		}finally{
			reader.close();
		}
		return toDocument(sb.toString());
	}
	
	public static Document toDocument(String xml) throws Exception{
		DocumentBuilder builder;
		builder = docFactory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));
		return document;
	}
	
	
	public static Document toDocument(Reader reader) throws Exception{
		StringBuffer sb = new StringBuffer();
		BufferedReader r = new BufferedReader(reader);
		String line;
		while((line=r.readLine())!=null){
			sb.append(line).append("\n"); //$NON-NLS-1$
		}
		
		return toDocument(sb.toString());
	}
	
	public static String toXMLString(Node doc, String headerCharEncoding) {
		if (doc instanceof Document)
			doc = ((Document) doc).getDocumentElement();
		return toXMLString(doc.cloneNode(true), headerCharEncoding, true);
	}

	public static String toXMLString(Node doc) {
		if(doc==null) return "null"; //$NON-NLS-1$
		if (doc instanceof Document)
			doc = ((Document) doc).getDocumentElement();
		return toXMLString(doc.cloneNode(true), null, true);
	}
	
	private static String toXMLString(Node doc, String headerCharEncoding,
			boolean reform) {
		try {
			if (reform) {
				removeEmptyTextNode(doc);
				appendTab(doc, 4);
			}
			javax.xml.transform.Source source = new DOMSource(doc);
			StringWriter sw = new StringWriter();
			javax.xml.transform.Result result = new StreamResult(sw);
			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
			if (headerCharEncoding != null)
				xformer.setOutputProperty("encoding", headerCharEncoding); //$NON-NLS-1$
			
			xformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			
			
			
			xformer.transform(source, result);
			return sw.toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	private static void removeEmptyTextNode(Node n) throws Exception {
		if (n instanceof Document)
			n = ((Document) n).getFirstChild();
		if (n instanceof Element) {
			Element e = (Element) n;
			NodeList nl = e.getChildNodes();
			int l = nl.getLength();
			Vector toremove = new Vector();
			for (int i = 0; i < l; i++) {
				Node cn = nl.item(i);
				if (cn instanceof Text) {
					Text t = (Text) cn;
					String text = t.getData().trim();
					if (text.equals("")) { //$NON-NLS-1$
						toremove.addElement(cn);
					} else {
						String rtext = t.getData();
						rtext = replaceIllegalXMLCharacter(rtext, ' ');
						t.setNodeValue(rtext);
					}
				} else {
					removeEmptyTextNode(cn);
				}
			}

			l = toremove.size();
			for (int i = 0; i < l; i++){
				e.removeChild((Node) toremove.elementAt(i));
			}

		}
	}
	
	private static String replaceIllegalXMLCharacter(String rtext, char replace) {
		char chars[] = rtext.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '\t' || c == '\n' || c == '\r')
				sb.append(c);
			else if (c >= ' ' && c <= '\uD7FF')
				sb.append(c);
			else if (c >= '\uE000' && c <= '\uFFF0')
				sb.append(c);
			else
				sb.append(replace);
		}

		return sb.toString();
	}
	
	private static void appendTab(Node n, int tabspace) throws Exception {
		appendTab(n, tabspace, 1);
	}

	private static void appendTab(Node n, int tabspace, int leveloftab)
			throws Exception {
		if (n instanceof Document)
			n = ((Document) n).getFirstChild();
		if (n instanceof Element) {
			Element e = (Element) n;
			NodeList nl = e.getChildNodes();
			int l = nl.getLength();
			int elechildren = 0;
			Node cns[] = new Node[l];
			for (int i = 0; i < l; i++)
				cns[i] = nl.item(i);

			for (int i = 0; i < l; i++)
				if (cns[i] instanceof Element) {
					e.removeChild(cns[i]);
					e.appendChild(createNewLineText(e, tabspace * leveloftab));
					e.appendChild(cns[i]);
					appendTab(cns[i], tabspace, leveloftab + 1);
					elechildren++;
				} else {
					e.removeChild(cns[i]);
					e.appendChild(cns[i]);
				}

			if (elechildren > 0)
				e
						.appendChild(createNewLineText(e, tabspace
								* (leveloftab - 1)));
		}
	}
	
	private static Text createNewLineText(Node node, int space)
			throws Exception {
		Document doc = node.getOwnerDocument();
		StringBuffer sb = new StringBuffer();
		sb.append("\n"); //$NON-NLS-1$
		for (int i = 0; i < space; i++)
			sb.append(" "); //$NON-NLS-1$

		return doc.createTextNode(sb.toString());
	}
	
	public static Document newDocument() {
		try {
			Document doc = docFactory.newDocumentBuilder().newDocument();
			return doc;
		} catch (Exception x) {
			throw new RuntimeException(x.getMessage(),x);
		}
	}
	
	public static Element appendElement(Element element,String tag){
		Document doc = element.getOwnerDocument();
		Element elm = doc.createElement(tag);
		element.appendChild(elm);
		return elm;
	}
	public static Element appendElement(Element element,String tag,String text){
		Document doc = element.getOwnerDocument();
		Element elm = doc.createElement(tag);
		element.appendChild(elm);
		appendText(elm,text);
		return elm;
	}
	
	public static Attr setAttribute(Element element,String name,String value){
		Attr attr = element.getAttributeNode(name);
		if(attr==null){
			attr = element.getOwnerDocument().createAttribute(name);
			element.appendChild(attr);
		}
		attr.setValue(value);
		return attr;
	}
	
	public static Text appendText(Element element,String text){
		if(text.contains("<") || text.contains(">")){ //$NON-NLS-1$ //$NON-NLS-2$
			return appendCDText(element,text);
		}
		Text tx = element.getOwnerDocument().createTextNode(text);
		element.appendChild(tx);
		return tx;
	}
	public static CDATASection appendCDText(Element element,String text){
		CDATASection tx = element.getOwnerDocument().createCDATASection(text);
		element.appendChild(tx);
		return tx;
	}
	
	public static Text setText(Element element,String text){
		if(text.contains("<") || text.contains(">")){ //$NON-NLS-1$ //$NON-NLS-2$
			return setCDText(element,text);
		}
		NodeList nl = element.getChildNodes();
		ArrayList<Node> remove = new ArrayList<Node>();
		int size = nl.getLength();
		for(int i=0;i<size;i++){
			Node n = nl.item(i);
			if(n instanceof Attr) continue;
			remove.add(n);
		}
		for(Node n:remove){
			element.removeChild(n);
		}
		return appendText(element,text);
	}
	
	public static CDATASection setCDText(Element element,String text){
		NodeList nl = element.getChildNodes();
		ArrayList<Node> remove = new ArrayList<Node>();
		int size = nl.getLength();
		for(int i=0;i<size;i++){
			Node n = nl.item(i);
			if(n instanceof Attr) continue;
			remove.add(n);
		}
		for(Node n:remove){
			element.removeChild(n);
		}
		return appendCDText(element,text);
	}
	
	public static NodeList queryNodeList(Element element,String query) throws XPathExpressionException {
		XPathExpression path = xPathFactory.newXPath().compile(query);
		NodeList nodeList = (NodeList)path.evaluate(element,XPathConstants.NODESET);
		return nodeList;
	}
	
	public static Node queryNode(Element element,String query)throws XPathExpressionException {
		XPathExpression path = xPathFactory.newXPath().compile(query);
		Node node = (Node)path.evaluate(element,XPathConstants.NODE);
		return node;
	}
	
	public static Element queryElement(Element element,String query)throws XPathExpressionException {
		XPathExpression path = xPathFactory.newXPath().compile(query);
		Node node = (Node)path.evaluate(element,XPathConstants.NODE);
		return (node instanceof Element)?(Element) node:null;
	}
	
	/**
	 * Never return null
	 */
	public static String queryText(Element element)throws XPathExpressionException {
		NodeList nl = queryNodeList(element,"text()"); //$NON-NLS-1$
		int size = nl.getLength();
		StringBuffer text = new StringBuffer();
		for(int i=0;i<size;i++){
			Node n = nl.item(i);
			if(n instanceof Text){
				text.append(((Text)n).getData());
			}
		}
		return text.toString();
	}
	
	/**
	 * Never return null
	 */
	public static String queryText(Element element,String query)throws XPathExpressionException {
		Element elm = queryElement(element,query);
		if(elm==null) return ""; //$NON-NLS-1$
		NodeList nl = elm.getChildNodes();
		int size = nl.getLength();
		StringBuffer text = new StringBuffer();
		for(int i=0;i<size;i++){
			Node n = nl.item(i);
			if(n instanceof Text){
				if(text.length()>0) text.append("\n"); //$NON-NLS-1$
				text.append(((Text)n).getData());
			}
		}
		return text.toString();
	}
}
