import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.controller.CanevasController;
import application.controller.HorizontalRulerUIControler;
import javafx.scene.canvas.Canvas;

class HorizontalRulerTest {
	HorizontalRulerUIControler horizontalRuler;
	Canvas rulerCanvas;
	@BeforeAll
	public static void init() {
		System.out.println("TEST SHAPE STARTED");
	}
	@BeforeEach
	public void buildUp() {
		horizontalRuler= new HorizontalRulerUIControler();
		assertNotNull(horizontalRuler);
		rulerCanvas= new Canvas();
		assertNotNull(rulerCanvas);
	}
	
	@AfterEach
	public void tearDown() {
		horizontalRuler= null;
		assertNull(horizontalRuler);
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
		horizontalRuler.setCanevasController(canvasControler);
		assertEquals(canvasControler, horizontalRuler.getCanevasController());
		
	}
	
	@Test
	void testRulerAnchorPane() {
		assertEquals(null, horizontalRuler.getAnchorPane());
	}
	
	@Test
	void testRulerCanvas() {
		horizontalRuler.setRulerCanvas(rulerCanvas);
		assertEquals(rulerCanvas, horizontalRuler.getRulerCanvas());
		horizontalRuler.rulerResize();
	}
	
	@Test
	void testDrawRules() {
		horizontalRuler.setUnits(100, 10, 50);
		horizontalRuler.setRulerCanvas(rulerCanvas);
		horizontalRuler.drawRules();
		assertTrue(horizontalRuler.isRulerExisted());
		
	}
	@Test
	void rulerResizeTest() {
		horizontalRuler.setRulerCanvas(rulerCanvas);
		horizontalRuler.setRulerExisted(true);
		horizontalRuler.rulerResize();	
		assertTrue(horizontalRuler.isRulerExisted());
	}
	
	

}
