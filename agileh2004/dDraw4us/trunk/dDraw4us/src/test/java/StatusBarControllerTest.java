import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import  application.controller.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

class StatusBarControllerTest {
	
	
	private CanevasController canevasController;
	private  StatusBarController statusBarController;
	
	private AnchorPane statusBarAnchor;
	private Label shapeWidth;
	private Label shapeHeight;
	 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("TEST StatusBarController  STARTED");

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		System.out.println("TEST StatusBarController  STOPED");

	}

	@BeforeEach
	void setUp() throws Exception {
		
	//
		//statusBarController =new StatusBarController();
	//	canevasController = new CanevasController();
	//	statusBarAnchor = new AnchorPane();
		
		
		
	//	assertNotNull(statusBarController);
	//	assertNotNull(canevasController);
	//	assertNotNull(statusBarAnchor);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
	//	statusBarController.setCanevasController(canevasController);
		
		
	
	//	assertEquals(canevasController.getClass(), statusBarController.getCanevasController().getClass());
		
		
		
		
	}
	

}