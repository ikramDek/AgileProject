import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import application.model.ColorFiller;
import application.model.ComposedShape;
import application.model.FileManager;
import application.model.HorizontalTriangle;
import application.model.MyCircle;
import application.model.MyEllipse;
import application.model.MyLine;
import application.model.MyRectangle;
import application.model.MyRoundedRectangle;
import application.model.MyShape;
import application.model.SingleShape;
import application.model.VerticalTriangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

class FileMangerTest {
	
	private FileManager fileManager;
	private ArrayList<MyShape> l;
	private ComposedShape comp;

	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	 
	@BeforeEach
	public void buildUp() {
		fileManager= new FileManager();
		assertNotNull(fileManager);
		ArrayList<MyShape> compList= new ArrayList<MyShape>();
		SingleShape rectangle= new MyRectangle();
		assertNotNull(rectangle);
		rectangle.setStrokeSize(2.1);
		rectangle.setStartX(2.1);
		rectangle.setStartY(2.1);
		rectangle.setEndX(1.1);
		rectangle.setEndY(1.1);
		rectangle.setFiller(new ColorFiller(Color.BLACK, rectangle));
		rectangle.setLayoutName("Layout1");
		rectangle.setStrokeColor(Color.BLACK);
		GridPane gp = new GridPane();
		rectangle.createShape(false, false, gp);
		
		SingleShape rectangle2= new MyRectangle();
		assertNotNull(rectangle2);
		rectangle2.setStrokeSize(2.1);
		rectangle2.setStartX(2.1);
		rectangle2.setStartY(2.1);
		rectangle2.setEndX(1.1);
		rectangle2.setEndY(1.1);
		rectangle2.setFiller(new ColorFiller(Color.BLACK, rectangle));
		rectangle2.setLayoutName("Layout1");
		rectangle2.setStrokeColor(Color.BLACK);
		rectangle2.createShape(false, false, gp);
		
		comp = new ComposedShape();
		compList.add(rectangle);
		compList.add(rectangle2);
		comp.setList(compList);
		
		l = new ArrayList<MyShape>();
		l.add(comp);
		
		SingleShape rectangle3= new MyRectangle();
		assertNotNull(rectangle3);
		rectangle3.setStrokeSize(2.1);
		rectangle3.setStartX(2.1);
		rectangle3.setStartY(2.1);
		rectangle3.setEndX(1.1);
		rectangle3.setEndY(1.1);
		rectangle3.setFiller(new ColorFiller(Color.BLACK, rectangle));
		rectangle3.setLayoutName("Layout1");
		rectangle3.setStrokeColor(Color.BLACK);
		rectangle3.createShape(false, false, gp);
		
		l.add(rectangle3);
		
		SingleShape rrectangle= new MyRoundedRectangle();
		assertNotNull(rrectangle);
		rrectangle.setStrokeSize(2.1);
		rrectangle.setStartX(2.1);
		rrectangle.setStartY(2.1);
		rrectangle.setEndX(1.1);
		rrectangle.setEndY(1.1);
		rrectangle.setFiller(new ColorFiller(Color.BLACK, rectangle));
		rrectangle.setLayoutName("Layout1");
		rrectangle.setStrokeColor(Color.BLACK);
		rrectangle.createShape(false, false, gp);
		
		l.add(rrectangle);
		
		SingleShape circle= new MyCircle();
		assertNotNull(circle);
		circle.setStrokeSize(2.1);
		circle.setStartX(2.1);
		circle.setStartY(2.1);
		circle.setEndX(1.1);
		circle.setEndY(1.1);
		circle.setFiller(new ColorFiller(Color.BLACK, rectangle));
		circle.setLayoutName("Layout1");
		circle.setStrokeColor(Color.BLACK);
		circle.createShape(false, false, gp);
		
		l.add(circle);
		
		SingleShape ellipse= new MyEllipse();
		assertNotNull(ellipse);
		ellipse.setStrokeSize(2.1);
		ellipse.setStartX(2.1);
		ellipse.setStartY(2.1);
		ellipse.setEndX(1.1);
		ellipse.setEndY(1.1);
		ellipse.setFiller(new ColorFiller(Color.BLACK, rectangle));
		ellipse.setLayoutName("Layout1");
		ellipse.setStrokeColor(Color.BLACK);
		ellipse.createShape(false, false, gp);
		
		l.add(ellipse);
		
		SingleShape line= new MyLine();
		assertNotNull(line);
		line.setStrokeSize(2.1);
		line.setStartX(2.1);
		line.setStartY(2.1);
		line.setEndX(1.1);
		line.setEndY(1.1);
		line.setFiller(new ColorFiller(Color.BLACK, rectangle));
		line.setLayoutName("Layout1");
		line.setStrokeColor(Color.BLACK);
		line.createShape(false, false, gp);
		
		l.add(line);
		
		SingleShape vTriangle= new VerticalTriangle();
		assertNotNull(vTriangle);
		vTriangle.setStrokeSize(2.1);
		vTriangle.setStartX(2.1);
		vTriangle.setStartY(2.1);
		vTriangle.setEndX(1.1);
		vTriangle.setEndY(1.1);
		vTriangle.setFiller(new ColorFiller(Color.BLACK, rectangle));
		vTriangle.setLayoutName("Layout1");
		vTriangle.setStrokeColor(Color.BLACK);
		vTriangle.createShape(false, false, gp);
		
		l.add(vTriangle);
		
		SingleShape hTriangle= new HorizontalTriangle();
		assertNotNull(hTriangle);
		hTriangle.setStrokeSize(2.1);
		hTriangle.setStartX(2.1);
		hTriangle.setStartY(2.1);
		hTriangle.setEndX(1.1);
		hTriangle.setEndY(1.1);
		hTriangle.setFiller(new ColorFiller(Color.BLACK, rectangle));
		hTriangle.setLayoutName("Layout1");
		hTriangle.setStrokeColor(Color.BLACK);
		hTriangle.createShape(false, false, gp);
		
		l.add(hTriangle);
		
		
		assertNotNull(l);
	}
	
	@AfterEach
	public void tearDown() {
		fileManager=null;
		assertNull(fileManager);
	}
	
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}

	
	
	
	@Test
	void testSaveTextToFile() {
		try {
			fileManager.saveTextToFile("file.txt", l);
			assertNotNull(null, l);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testParseFromFile() {
		try {fileManager.saveTextToFile("file.txt", l);
			fileManager.parseFromFile(l, new File("file.txt"));;
			assertNotNull(null, l);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testSaveCustomShape() {
			fileManager.saveCustomShape(comp, "club");
			assertNotNull(null, l);
	}
	
	@Test
	void testImportFrom() {
		try {
			fileManager.saveTextToFile("file.txt", l);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MyShape> list = fileManager.importFrom("file.txt");
		assertNotNull(null, list);
	}

}
