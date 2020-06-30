import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import javafx.scene.shape.Shape;

public class PalettePatternsControllerTest extends TestFXBase {

	@Test
	void testMoveForward() {
		Shape shape1 = drawFirstShapeAndSelectIt();
		assertNotNull(shape1);
		Shape shape2 = drawSecondShapeAndSelectIt();
		assertNotNull(shape2);

		clickOn("#forwardPalette");

		assertTrue(mainController.getCanevasController().getAllShapes().get(1).getSingleShapeList().get(0).getShape().equals(shape2));

	}
	
	@Test
	void testMoveBackward() {
		Shape shape1 = drawFirstShapeAndSelectIt();
		assertNotNull(shape1);
		Shape shape2 = drawSecondShapeAndSelectIt();
		assertNotNull(shape2);

		clickOn("#backwardPalette");

		assertTrue(mainController.getCanevasController().getAllShapes().get(0).getSingleShapeList().get(0).getShape().equals(shape2));

	}
	
	@Test
	void testUndoAction() {
		drawFirstShapeAndSelectIt();

		assertTrue(mainController.getMenuController().getCanevasController().getAllShapes().size() == 1);

		clickOn("#undoPalette");

		clickOn("#undoPalette");

		assertTrue(mainController.getMenuController().getCanevasController().getAllShapes().size() == 0);

	}
	
	@Test
	void testHistory() {
		drawFirstShapeAndSelectIt();

		assertTrue(mainController.getMenuController().getCanevasController().getAllShapes().size() == 1);

		clickOn("#historyPalette");

		assertTrue(mainController.getMenuController().getHistoryStage().isShowing());
		
		clickOn("#historyPalette");

		assertFalse(mainController.getMenuController().getHistoryStage().isShowing());
	}
	
	@Test
	void testRedoAction() {
		drawFirstShapeAndSelectIt();

		assertTrue(mainController.getMenuController().getCanevasController().getAllShapes().size() == 1);

		clickOn("#undoPalette");


		clickOn("#redoPalette");

		assertTrue(mainController.getMenuController().getCanevasController().getAllShapes().size() == 1);

	}
	
}
