import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import application.controller.CanevasController;
import application.controller.VerticalRulerUIControler;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

class VerticalRulerTest {
	
	VerticalRulerUIControler verticalRuler;
	Canvas rulerCanvas;
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	@BeforeEach
	public void buildUp() {
		verticalRuler= new VerticalRulerUIControler();
		assertNotNull(verticalRuler);
		rulerCanvas= new Canvas();
		assertNotNull(rulerCanvas);
	}
	
	@AfterEach
	public void tearDown() {
		verticalRuler= null;
		assertNull(verticalRuler);
		rulerCanvas= null;
		assertNull(rulerCanvas);
	}
	
	@AfterAll
	public static void destroy() {
		System.out.println("TEST SHAPE STOPED");
	}
	

	@Test
	void testCanvasControler() {
		CanevasController canvasControler= new CanevasController();
		verticalRuler.setCanevasController(canvasControler);
		assertEquals(canvasControler, verticalRuler.getCanevasController());
		
	}
	
	@Test
	void testRulerAnchorPane() {
		AnchorPane anchorPane = new AnchorPane();
		verticalRuler.setAnchorPane(anchorPane);;
		assertEquals(anchorPane, verticalRuler.getAnchorPane());
		
	}
	
	@Test
	void testRulerCanvas() {
		verticalRuler.setRulerCanvas(rulerCanvas);
		assertEquals(rulerCanvas, verticalRuler.getRulerCanvas());
		verticalRuler.rulerResize();
		
	}
	
	@Test
	void testDrawRules() {
		verticalRuler.setUnits(100, 10, 50);
		verticalRuler.setRulerCanvas(rulerCanvas);
		verticalRuler.drawRules();
		assertTrue(verticalRuler.isRulerExisted());
		
	}
	@Test
	void rulerResizeTest() {
		verticalRuler.setRulerCanvas(rulerCanvas);
		verticalRuler.setRulerExisted(true);
		verticalRuler.rulerResize();	
		assertTrue(verticalRuler.isRulerExisted());
	}
	
	/*@Test
	void testDrawRuler() {
		verticalRuler.setUnits(100, 10, 50);
		verticalRuler.setRulerCanvas(rulerCanvas);
		verticalRuler.drawRuler(verticalRuler.getRulerCanvas().getGraphicsContext2D(), 30 , 20, 30,(int) rulerCanvas.getHeight(),true);
	}*/
	
	

}
