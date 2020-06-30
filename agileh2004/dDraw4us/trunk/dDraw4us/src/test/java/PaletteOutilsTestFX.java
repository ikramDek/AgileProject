import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PaletteOutilsTestFX extends TestFXBase {

	@Test
	void testDrawingCircle() {

		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

		// on donne l'element sur lequel on veut cliquer
		clickOn("#circleBtn");

		pressDragRelease();

		// vous pouvez acceder au different controleur � partir du mainController
		assertFalse(mainController.getCanevasController().getAllShapes().isEmpty());
		assertTrue(mainController.getCanevasController().getAllShapes().size() == 1);
	}

	@Test
	void testDrawingEllipse() {
		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

		clickOn("#ellipseBtn");

		pressDragRelease();

		assertFalse(mainController.getCanevasController().getAllShapes().isEmpty());
		assertTrue(mainController.getCanevasController().getAllShapes().size() == 1);

	}

	@Test
	void testDrawingRectangle() {
		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

		clickOn("#rectBtn");

		pressDragRelease();

		assertFalse(mainController.getCanevasController().getAllShapes().isEmpty());
		assertTrue(mainController.getCanevasController().getAllShapes().size() == 1);

	}

	@Test
	void testDrawingRoundedRectangle() {
		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

		clickOn("#roundedRectBtn");

		pressDragRelease();

		assertFalse(mainController.getCanevasController().getAllShapes().isEmpty());
		assertTrue(mainController.getCanevasController().getAllShapes().size() == 1);

	}
	@Test
	void testVerticalFlip() {

		drawFirstShapeAndSelectIt();

		clickOn("#flipVert");
		
		assertFalse(mainController.getCanevasController().getLayoutsList().isEmpty());
	}
	
	@Test
	void testHorizontalFlip() {

		drawFirstShapeAndSelectIt();
		

		clickOn("#flipHor");
		
		
		assertFalse(mainController.getCanevasController().getLayoutsList().isEmpty());
	}
	
	@Test
	void testDrawingLine() {
		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

		clickOn("#lineBtn");

		pressDragRelease();

		assertFalse(mainController.getCanevasController().getAllShapes().isEmpty());
		assertTrue(mainController.getCanevasController().getAllShapes().size() == 1);

	}

	@Test
	void testResizing() {

		Shape shapeDessine = drawFirstShapeAndSelectIt();

		clickOn("#resizeBtn");
		moveTo(shapeDessine);

		drag();

		moveBy(-10, -10);
		double finalWidth = ((Rectangle) shapeDessine).getWidth();

		assertTrue(finalWidth>5);
	}

	@Test
	void testZoom() {
		clickOn("#zoomBtn");
		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

	}

	@Test
	void testSelection() {
		clickOn("#selectBtn");

		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

	}

	@Test
	void testCursor() {
		clickOn("#cursorBtn");

		assertTrue(mainController.getCanevasController().getAllShapes().isEmpty());

	}

	private void pressDragRelease() {
		// Deplacer la souris de +200 sur x et +50 sur y et pressMouse
		moveBy(200, 50);

		// DragMouse
		drag();

		// Deplacer la souris de +50 sur x et +50 sur y et pressMouse
		moveBy(50, 50);

		// DragMouse
		drop();

	}

}
