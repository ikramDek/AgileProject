package application.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyRectangle extends SingleShape {

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
		shape = new Rectangle(this.startX, this.startY, width, height);
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		shape.setRotate(this.rotate);
		updateFields();

	}

	@Override
	public SingleShape emptyClone() {
		return new MyRectangle();
	}

	@Override
	public void moveShape(int x, int y) {
		Rectangle c = (Rectangle) (shape);
		c.setX(c.getX() + x);
		c.setY(c.getY() + y);
		updateFields();
	}

	@Override
	public void enlarge() {
		Rectangle c = (Rectangle) (shape);
		c.setWidth(c.getWidth() + ENLARGE_CONST);
		c.setHeight(c.getHeight() + ENLARGE_CONST);
		updateFields();
	}

	@Override
	public void updateFields() {
		Rectangle c = (Rectangle) (shape);
		this.startX = c.getX();
		this.startY = c.getY();
		this.endX = (c.getX() + c.getWidth());
		this.endY = (c.getY() + c.getHeight());
		this.strokeColor = (Color) c.getStroke();
		this.strokeSize = c.getStrokeWidth();
		this.rotate = c.getRotate();
	}

	@Override
	public void resizeOnDragged(double x, double y, Shape shape) {
		shape.setStrokeLineCap(StrokeLineCap.BUTT);

		shape.getStrokeDashArray().addAll(10d, 5d);

		Rectangle c = (Rectangle) (shape);

		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		c.setX(c.getX() + offsetX);
		c.setY(c.getY() + offsetY);

		c.setWidth(c.getWidth() - offsetX);
		c.setHeight(c.getHeight() - offsetY);

		orgSceneX = x;
		orgSceneY = y;
	}

	@Override
	public void dragOnDragged(double x, double y, Shape shape) {
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		if (this.isflipedX) {
			offsetX = -offsetX;
		}
		if (this.isflipedY) {
			offsetY = -offsetY;
		}

		Rectangle c = (Rectangle) (shape);

		c.setX(c.getX() + offsetX);
		c.setY(c.getY() + offsetY);

		orgSceneX = x;
		orgSceneY = y;
	}

	@Override
	protected void createShapeForImport() {
		createShape(false, false, null);
	}

	@Override
	public Shape createShapeClone(Shape c) {
		Rectangle x = new Rectangle(((Rectangle) c).getX(), ((Rectangle) c).getY(), ((Rectangle) c).getWidth(),
				((Rectangle) c).getHeight());
		x.setLayoutX(((Rectangle) c).getLayoutX());
		x.setLayoutY(((Rectangle) c).getLayoutY());
		x.setRotate(((Rectangle) c).getRotate());
		x.setFill(c.getFill());
		return x;
	}

	@Override
	public void resizeOnPressed(double x, double y) {
		dragOnPressed(x, y);
	}

	@Override
	public void dragOnPressed(double x, double y) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);

		orgSceneX = x;
		orgSceneY = y;
	}

	@Override
	public SingleShape clone1() {
		return new MyRectangle();
	}

	@Override
	public String toString() {
		return "Rectangle";
	}

}
