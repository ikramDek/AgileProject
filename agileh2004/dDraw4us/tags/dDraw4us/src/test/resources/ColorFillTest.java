import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.ColorFiller;
import application.model.MyEllipse;
import application.model.MyRectangle;
import application.model.SingleShape;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

class ColorFillTest {
	
	ColorFiller colorFiller;
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	
	 
	@BeforeEach
	public void buildUp() {
		colorFiller= new ColorFiller(Color.BLACK, new MyEllipse() );
		assertNotNull(colorFiller);
	}
	
	@AfterEach
	public void tearDown() {
		colorFiller=null;
		assertNull(colorFiller);
	}
	
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}


	@Test
	void isColortest() {
		assertTrue(colorFiller.isColor());
		assertEquals(Color.BLACK, colorFiller.getColor());
		
		
		
	}

}
