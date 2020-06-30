package application.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileManager {

	private XmlConverter xmlAdapter;

	public FileManager() {
		this.xmlAdapter = new XmlAdapter();
	}
	
	public void saveTextToFile(String absolutePath, List l) throws ParserConfigurationException {
		try {
			
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.newDocument();
			Element rootEle = dom.createElement("root");
			for (int i = 0; i < l.size(); i++) {
				System.out.println(l.get(i).getClass().getSimpleName());
				Element sh = dom.createElement(l.get(i).getClass().getSimpleName());
				sh = ((MyShape) l.get(i)).createNode(sh, dom);
				System.out.println(sh);
				rootEle.appendChild(sh);
			}
			dom.appendChild(rootEle);

			
			TransformerFactory factory = TransformerFactory.newInstance();
			
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			Transformer tr = factory.newTransformer();
			
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			// send DOM to file
			tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(absolutePath)));

		} catch (Exception e) {
			Logger logger = Logger.getGlobal();
			logger.log(Level.SEVERE, "Exception during saving file");
		}
	}

	public void importFromFile(Stage stage, List<MyShape> myList) throws ParserConfigurationException {
		try {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(stage);

			parseFromFile(myList, file);


		} catch (Exception e) {
			Logger logger = Logger.getGlobal();
			logger.log(Level.SEVERE, "Exception");
		}
	}

	public void parseFromFile(List<MyShape> myList, File file)
			throws SAXException, IOException, ParserConfigurationException {
		if(file!=null) {
			Node node = xmlAdapter.getNode (file.getAbsolutePath());

			node= node.getNextSibling();
			parse(myList, node);
		}
	}

	private void parse(List<MyShape> myList, Node x) {

		do {
			if (x.getNodeName().equals("#text")) {
				x = x.getNextSibling();
				continue;
			}
			if (x.getNodeName().equals("ComposedShape")) {
				parseComposedShape(myList, x);
			} else {
				parseSingleShape(myList, x);
			}
			x = x.getNextSibling();
		} while (x.getNextSibling() != null);
	}

	private void parseSingleShape(List<MyShape> myList, Node x) {
		NamedNodeMap tempm = x.getAttributes();
		if (tempm.getLength() == 0) {
			Node y = x.getFirstChild();
			while (tempm.getLength() == 0) {
				if (y.getNodeName().equals("#text")) {
					y = y.getNextSibling();
					continue;
				}
				tempm = y.getAttributes();
			}

		}
		HashMap<String, Double> m = new HashMap<>();
		for (int i = 0; i < tempm.getLength(); i++) {
			addToMap(tempm.item(i).toString(), m);
		}
		copyMapToList(x.getNodeName(), m, myList);
	}

	private void parseComposedShape(List<MyShape> myList, Node x) {
		ComposedShape composedShape = new ComposedShape();
		ArrayList<MyShape> list = new ArrayList<>();
		parse(list, x.getFirstChild());
		for (MyShape myShape : list) {
			myShape.setRoot(composedShape);
		}
		composedShape.setList(list);
		composedShape.setLayout(list.get(0).getLayout());
		myList.add(composedShape);
	}

	private void copyMapToList(String type, HashMap<String, Double> m, List<MyShape> myList) {
		MyShape temp = null;
		try {
			switch (type) {
			case "MyCircle":
				temp = new MyCircle();
				break;
			case "MyEllipse":
				temp = new MyEllipse();
				break;
			case "MyRoundedRectangle":
				temp = new MyRoundedRectangle();
				break;
			case "MyRectangle":
				temp = new MyRectangle();
				break;
			case "MyLine":
				temp = new MyLine();
				break;
			case "VerticalTriangle":
				temp = new VerticalTriangle();
				break;
			case "HorizontalTriangle":
				temp = new HorizontalTriangle();
				break;
			default:
				temp = new MyCircle();
				break;
			}
			((SingleShape) temp).setProperties(m);
			((SingleShape) temp).createShapeForImport();
		} catch (Exception e) {
			Logger logger = Logger.getGlobal();
			logger.log(Level.SEVERE, "Exception");
		}

		myList.add(temp);
	}

	private void addToMap(String s, HashMap<String, Double> m) {
		int ind = s.indexOf('=');
		String key = s.substring(0, ind);
		double val = Double.parseDouble(s.substring(ind + 2, s.length() - 1));
		m.put(key, val);
	}

	public void saveCustomShape(ComposedShape comp, String string) {
		try {
			
			Document dom = xmlAdapter.getCustomShapeDocument(comp);
	
			
			TransformerFactory factory = TransformerFactory.newInstance();
			
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	
			Transformer tr = factory.newTransformer();
			
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	
			// send DOM to file
			tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream("customShapes" + File.separator + string + ".txt")));
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<MyShape> importFrom(String substring) {
		try {
			File file = new File(substring);
	
			if(file!=null) {
				Node node = xmlAdapter.getNode (file.getAbsolutePath());
				node = node.getNextSibling();
				ArrayList<MyShape> list = new ArrayList<MyShape>();
				parse(list, node);
				return list;
			}
		}
		catch (Exception e) {

		}
		return null;

	}
}
