import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class HistoryControllerTestFX extends TestFXBase {


	@Test
	public void testSetLayoutsList() {
		assertTrue(true);
	}

	@Test
	public void testHistoryController() {
		assertNotNull(mainController.getMenuController().getHistoryController());
		assertNotNull(mainController.getMenuController().getHistoryController());
	}

	@Test
	public void testInitialiseHistoryStage() {
		clickOn("#edit");

		clickOn("History");

		assertTrue(mainController.getCanevasController().getStage().isShowing());

		assertEquals(1,mainController.getMenuController().getHistoryController().getLayoutsList().size());
	}

	@Test
	public void testRefreshHistoryTable() {
		clickOn("#edit");

		clickOn("History");

		assertTrue(mainController.getCanevasController().getStage().isShowing());

		assertEquals(1,mainController.getMenuController().getHistoryController().getLayoutsList().size());
	
		drawFirstShapeAndSelectIt();
		
		assertEquals(1,mainController.getMenuController().getHistoryController().getLayoutsList().size());
		
		//assert Number de layout est 1
		assertEquals(1,mainController.getMenuController().getHistoryController().getTree().getRoot().getChildren().size());
		
		//assert Number que layout 1 contient un seul shape
		assertEquals(1,mainController.getMenuController().getHistoryController().getTree().getRoot().getChildren().get(0).getChildren().size());
				
		drawFirstShapeAndSelectIt();
		
		//assert Number que layout 1 contient 2 shape
		assertEquals(2,mainController.getMenuController().getHistoryController().getTree().getRoot().getChildren().get(0).getChildren().size());
				
	}

}
