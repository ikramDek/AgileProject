

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyShape;
import application.model.VerticalTriangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class VerticalTriangleTest {
	VerticalTriangle verticalTriangle;
	
	@BeforeAll
	public static void init() {
		System.out.println("TEST STARTED");
	}
	@AfterAll
	public static void destroy() {
		System.out.println("TEST STOPPED");
	}
	@BeforeEach
	public void buildUp() {
		verticalTriangle = new VerticalTriangle();
		assertNotNull(verticalTriangle);
		verticalTriangle.setStrokeSize(2.1);
		verticalTriangle.setStartX(2.1);
		verticalTriangle.setStartY(2.1);
		verticalTriangle.setEndX(1.1);
		verticalTriangle.setEndY(1.1);
		verticalTriangle.setFiller(new ColorFiller(Color.BLACK, verticalTriangle));
		verticalTriangle.createShape(false, false, new GridPane());
		verticalTriangle.createShape(true, true, new GridPane());
		verticalTriangle.createShape(true, false, new GridPane());
		assertNotEquals(verticalTriangle.getStartX(), 2.1);
		assertNotEquals(verticalTriangle.getStartY(), 2.1);
		assertNotEquals(verticalTriangle.getEndX(), 1.1);
		assertNotEquals(verticalTriangle.getEndY(), 1.1);
		
	}
	
	@AfterEach
	public void tearDown() {
		verticalTriangle=null;
		assertNull(verticalTriangle);
	}


	@Test
	void enlargeTest() {
		
		Polygon c = (Polygon) (verticalTriangle.getShape());
		double r = c.getBoundsInLocal().getWidth();
		
		verticalTriangle.enlarge();
		assertNotEquals(r, c.getBoundsInLocal().getWidth());
	}
	@Test
	void Clonetest() {
		MyShape r = verticalTriangle.emptyClone();
		MyShape shape= verticalTriangle.clone1();
		assertNotNull(r);
		assertNotNull(shape);
	}
	@Test
	void toStringTest() {
		assertEquals("VerticalTriangle", verticalTriangle.toString());
	}
	
}
