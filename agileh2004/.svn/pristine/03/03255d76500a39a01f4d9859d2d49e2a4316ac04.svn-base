package application.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class VerticalTriangle extends MyTriangle {
	int i = 2;

	@Override
	public void createShape(boolean isUsingCenter, boolean isUsingMagnetism, GridPane gridPane) {
		initCircles();
		if (isUsingMagnetism) {
			drawWithMagnetism(gridPane);
		}
		if (isUsingCenter && !isUsingMagnetism) {
			double centerX = startX;
			double centerY = startY;

			startX = 2 * centerX - endX;
			startY = 2 * centerY - endY;
		}

		arrangeStartAndEndXY();

		Double width = Math.abs(this.startX - this.endX);
		Double height = Math.abs(this.startY - this.endY);
		shape = new Polygon(startX, startY, startX + width, endY - (height / 2), startX, endY);
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		shape.setRotate(this.rotate);
		updateFields();
	}

	@Override
	public void updateFields() {
		Polygon c = (Polygon) shape;
		this.startX = c.getBoundsInLocal().getMinX();
		this.startY = c.getBoundsInLocal().getMinY();
		this.endX = c.getBoundsInLocal().getMaxX();
		this.endY = c.getBoundsInLocal().getMaxY();
		this.strokeColor = (Color) c.getStroke();
		this.strokeSize = c.getStrokeWidth();
		this.rotate = c.getRotate();

	}

	@Override
	public void enlarge() {
		Double height = Math.abs(this.startY - this.endY);
		Polygon vTriangle = (Polygon) shape;
		vTriangle.getPoints().setAll(startX, startY, endX + ENLARGE_CONST, endY - ENLARGE_CONST - (height / 2), startX,
				endY + ENLARGE_CONST);

		updateFields();

	}

	@Override
	public SingleShape clone1() {
		return new VerticalTriangle();
	}

	@Override
	public SingleShape emptyClone() {
		return new VerticalTriangle();
	}

	@Override
	public String toString() {
		return "VerticalTriangle";
	}

	public void resizeOnDragged(double x, double y, Shape shape) {
		shape.setStrokeLineCap(StrokeLineCap.BUTT);
		// Double width = Math.abs(this.startX - this.endX);
		Double height = Math.abs(this.startY - this.endY);
		shape.getStrokeDashArray().addAll(10d, 5d);
		Polygon c = (Polygon) (shape);
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		setStartX(startX + offsetX);
		setStartY(startY + offsetY);

		if (startY > endY) {
			c.getPoints().setAll(startX, startY, endX, endY - (height / 2), startX, endY - 2 * height);

		}

		else {
			c.getPoints().setAll(startX, startY, endX, endY - (height / 2), startX, startY + height);

		}

		orgSceneX = x;
		orgSceneY = y;
		i++;
	}

}
