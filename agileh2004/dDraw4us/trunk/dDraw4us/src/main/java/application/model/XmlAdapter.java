package application.model;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlAdapter implements XmlConverter {

	public Node getNode(String absolutePath) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = null;

		dom = db.parse(absolutePath);
		
		Element doc = dom.getDocumentElement();
		Node x = doc.getFirstChild();
		
		return x ;
	}

	public Document getCustomShapeDocument(ComposedShape comp) throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.newDocument();
		Element rootEle = dom.createElement("root");
		Element sh = dom.createElement(comp.getClass().getSimpleName());
		sh = ((MyShape) comp).createNode(sh, dom);
		rootEle.appendChild(sh);
		dom.appendChild(rootEle);
		return dom ;
	}



}
