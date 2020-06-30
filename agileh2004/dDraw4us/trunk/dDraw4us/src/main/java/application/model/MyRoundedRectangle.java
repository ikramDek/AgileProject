package application.model;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MyRoundedRectangle extends MyRectangle {

	private static final int ARC_ANGLE = 30;

	@Override
	public void createShape(boolean isUsingCenter, boolean isUsingMagnetism, GridPane gridPane) {
		initCircles();
		super.createShape(isUsingCenter, isUsingMagnetism, gridPane);
		((Rectangle) (shape)).setArcHeight(ARC_ANGLE);
		((Rectangle) (shape)).setArcWidth(ARC_ANGLE);
	}

	@Override
	public Shape createShapeClone(Shape c) {
		initCircles();
		Rectangle r = (Rectangle) super.createShapeClone(c);
		r.setArcHeight(ARC_ANGLE);
		r.setArcWidth(ARC_ANGLE);
		return r;
	}

	@Override
	public SingleShape emptyClone() {
		return new MyRoundedRectangle();
	}

	@Override
	protected void createShapeForImport() {
		createShape(false, false, null);
	}

	@Override
	public SingleShape clone1() {
		return new MyRoundedRectangle();
	}

	@Override
	public String toString() {
		return "Rounded Rectangle";
	}
}
