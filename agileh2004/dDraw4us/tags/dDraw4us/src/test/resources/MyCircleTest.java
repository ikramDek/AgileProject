
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.controller.CanevasController;
import application.model.ColorFiller;
import application.model.MyCircle;
import application.model.SingleShape;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

class MyCircleTest {
	
	private MyCircle circle;
	
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	@BeforeEach
	public void buildUp() {
		circle= new MyCircle();
		assertNotNull(circle);
		circle.setStartX(2.1);
		circle.setStartY(2.1);
		circle.setEndX(1.1);
		circle.setEndY(1.1);
		circle.setFiller(new ColorFiller(Color.BLACK, circle));
		circle.setStrokeSize(1.1);
		circle.createShape(false);
		circle.createShape(true);
	}
	@AfterEach
	public void tearDown() {
		circle=null;
		assertNull(circle);
	}
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}
	
	
	@Test
	void resizeOnReleasedTest() {
		
		circle.resizeOnReleased(Color.BLACK, 1.1);
		assertEquals(StrokeLineCap.BUTT, circle.getShape().getStrokeLineCap());
	}
	
	@Test
	void resizeOnPressedTest() {
		
		circle.resizeOnPressed(1.1, 1.1);
		assertEquals(Color.RED, circle.getShape().getStroke());
		assertEquals(5, circle.getShape().getStrokeWidth());
	}
	
	@Test
	void resizeOnDraggedTest() {
		
		circle.resizeOnDragged(1.1, 1.1, circle.getShape());
		assertEquals(StrokeLineCap.BUTT, circle.getShape().getStrokeLineCap());
	}
	
	@Test
	void Clonetest() {
		SingleShape r = circle.clone();
		assertNotNull(r);
	}
	
	@Test
	void MoveShapeTest() {
		
		Circle c = (Circle) (circle.getShape());
		double r = c.getCenterX();
		
		circle.moveShape(1, 1);
		assertEquals(r+1, c.getCenterX());
	}

	
	@Test
	void enlargeTest() {
		
		Circle c = (Circle) (circle.getShape());
		double r = c.getRadius();
		
		circle.enlarge();;
		assertEquals(r+5, c.getRadius());
	}
	
	@Test
	void dragOnPressedTest() {
		
		circle.dragOnPressed(1.1, 1.1);
		assertEquals(Color.RED, circle.getShape().getStroke());
		assertEquals(5, circle.getShape().getStrokeWidth());
	}
	
	@Test
	void dragOnDraggedTest() {
		
		circle.dragOnDragged(1.1, 1.1, circle.getShape());
		//assertEquals(StrokeLineCap.BUTT, rectangle.getShape().getStrokeLineCap());
	}
	
	@Test
	void dragOnReleasedTest() {
		
		circle.dragOnReleased(Color.BLACK, 1.1);
		assertEquals(Color.BLACK, circle.getShape().getStroke());
	}
	
	@Test
	void toStringTest() {
		assertNotNull(circle.toString());
		
	}
	

}
