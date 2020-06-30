package application.model;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public interface XmlConverter {
	Node getNode(String s) throws SAXException, IOException, ParserConfigurationException;
	
	Document getCustomShapeDocument(ComposedShape comp) throws ParserConfigurationException;
	
}
