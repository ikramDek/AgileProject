package application.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileManager {


	public void saveTextToFile(String absolutePath, ArrayList<MyShape> arrayList) throws ParserConfigurationException {
	/*	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.newDocument();
		Element rootEle = dom.createElement("root");
		Map<String, Double> m;
		for(int i=0;i<arrayList.size();i++){
			m = arrayList.get(i).getProperties();
			Element sh = dom.createElement(arrayList.get(i).getClass().getSimpleName());
			for (Map.Entry<String, Double> entry : m.entrySet()) {
				String key = entry.getKey();
				Double value = entry.getValue();
				sh.setAttribute(key, value.toString());
			}
			rootEle.appendChild(sh);
		}
		dom.appendChild(rootEle);


		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			// send DOM to file
			tr.transform(new DOMSource(dom), 
					new StreamResult(new FileOutputStream(absolutePath)));

		} catch (Exception e) {
			System.out.println("Failed to save the xml document");
			return;
		}
*/
	}

	public void importFromFile(Stage stage, HashMap<String, Double> m, ArrayList<MyShape> myList) throws ParserConfigurationException {
		/*FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(stage);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = null;
		try{
			dom = db.parse(file.getAbsolutePath());
		}
		catch(Exception e){};
		Element doc = dom.getDocumentElement();

		Node x = doc.getFirstChild();
		x = x.getNextSibling();

		try{
			do{
				if(x.getNodeName().equals("#text")){x=x.getNextSibling();continue;}
				NamedNodeMap tempm = x.getAttributes();
				m = new HashMap<String,Double>();
				for(int i=0;i<tempm.getLength();i++){
					addToMap(tempm.item(i).toString(), m);
				}
				copyMapToList(x.getNodeName(), m, myList);
				x = x.getNextSibling();
			}while(!x.getNextSibling().equals(null));


		}catch(Exception e){}
			*/
	}

	
	private void copyMapToList(String type, HashMap<String, Double> m, ArrayList<SingleShape> myList){
		SingleShape temp = null;
		switch(type){
		case"MyCircle": temp = new MyCircle();break;
		case"MyEllipse": temp = new MyEllipse();break;
		case"MyRectangle": temp = new MyRectangle();break;
		case"MyLine": temp = new MyLine();break;
		case"MyRoundedRectangle": temp = new MyRoundedRectangle();break;
		}
		temp.setProperties(m);
		temp.createShape(false);
		myList.add(temp);

	}

	private void addToMap(String s, HashMap<String, Double> m){
		int ind = s.indexOf('=');
		String key = s.substring(0, ind);
		double val = Double.parseDouble(s.substring(ind+2, s.length()-1));
		m.put(key, val);
	}
}
