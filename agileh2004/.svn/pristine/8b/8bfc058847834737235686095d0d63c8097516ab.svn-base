import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.HorizontalTriangle;
import application.model.MyShape;
import application.model.VerticalTriangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class HorizontalTriangleTest {

HorizontalTriangle horizontalTriangle;
	
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
		horizontalTriangle = new HorizontalTriangle();
		assertNotNull(horizontalTriangle);
		horizontalTriangle.setStrokeSize(2.1);
		horizontalTriangle.setStartX(2.1);
		horizontalTriangle.setStartY(2.1);
		horizontalTriangle.setEndX(1.1);
		horizontalTriangle.setEndY(1.1);
		horizontalTriangle.setFiller(new ColorFiller(Color.BLACK, horizontalTriangle));
		horizontalTriangle.createShape(false, false, new GridPane());
		horizontalTriangle.createShape(true, true, new GridPane());
		horizontalTriangle.createShape(true, false, new GridPane());
		assertNotEquals(horizontalTriangle.getStartX(), 2.1);
		assertNotEquals(horizontalTriangle.getStartY(), 2.1);
		assertNotEquals(horizontalTriangle.getEndX(), 1.1);
		assertNotEquals(horizontalTriangle.getEndY(), 1.1);
		
	}
	
	@AfterEach
	public void tearDown() {
		horizontalTriangle=null;
		assertNull(horizontalTriangle);
	}


	@Test
	void enlargeTest() {
		
		Polygon c = (Polygon) (horizontalTriangle.getShape());
		double r = c.getBoundsInLocal().getWidth();
		
		horizontalTriangle.enlarge();
		assertNotEquals(r, c.getBoundsInLocal().getWidth());
	}
	@Test
	void Clonetest() {
		MyShape r = horizontalTriangle.emptyClone();
		MyShape shape= horizontalTriangle.clone1();
		assertNotNull(r);
		assertNotNull(shape);
	}
	@Test
	void toStringTest() {
		assertEquals("HorizontalTriangle", horizontalTriangle.toString());
	}
	

}
