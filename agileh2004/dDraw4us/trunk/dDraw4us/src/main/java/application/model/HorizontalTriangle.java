package application.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;

public class HorizontalTriangle extends MyTriangle {
	private double widthTriangle;
	private int i = 2;

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
		widthTriangle = width;
		shape = new Polygon(startX + ((width) / 2), startY, endX - width, endY, endX, endY);
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		shape.setRotate(getRotate());
		updateFields();

	}

	@Override
	public void updateFields() {
		Polygon c = (Polygon) shape;
		this.startX = c.getPoints().get(0) - widthTriangle / 2;
		this.startY = c.getPoints().get(1);
		this.endX = c.getPoints().get(4);
		this.endY = c.getPoints().get(5);
		this.strokeColor = (Color) c.getStroke();
		this.strokeSize = c.getStrokeWidth();
		this.rotate = c.getRotate();
	}

	@Override
	public void enlarge() {
		Double width = Math.abs(this.startX - this.endX);
		Polygon hTriangle = (Polygon) shape;
		setEndX(getEndX() + ENLARGE_CONST);
		setEndY(getEndY() + ENLARGE_CONST);

		hTriangle.getPoints().setAll(startX + (widthTriangle / 2), startY, endX - width - i * ENLARGE_CONST, endY, endX,
				endY);
		i = i + 1;
		updateFields();
	}

	@Override
	public SingleShape clone1() {
		return new HorizontalTriangle();
	}

	@Override
	public SingleShape emptyClone() {
		return new HorizontalTriangle();
	}

	@Override
	public String toString() {
		return "HorizontalTriangle";
	}

	public void resizeOnDragged(double x, double y, Shape shape) {
		shape.setStrokeLineCap(StrokeLineCap.BUTT);
		Double width = Math.abs(this.startX - this.endX);
		shape.getStrokeDashArray().addAll(10d, 5d);
		Polygon c = (Polygon) (shape);
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;
		setStartX(startX + offsetX);
		setStartY(startY + offsetY);
		if (startX > endX) {
			c.getPoints().setAll(startX + ((width) / 2), startY, startX + 2 * width, endY, endX, endY);
		}

		else {
			c.getPoints().setAll(startX + ((width) / 2), startY, endX - width, endY, endX, endY);
		}

		orgSceneX = x;
		orgSceneY = y;
	}

}
