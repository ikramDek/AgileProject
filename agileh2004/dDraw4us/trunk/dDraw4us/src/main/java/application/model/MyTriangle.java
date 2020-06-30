package application.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class MyTriangle extends SingleShape {

	public abstract void createShape(boolean isUsingCenter, boolean isUsingMagnetism, GridPane gridPane);

	public abstract void updateFields();

	public abstract void enlarge();

	public abstract SingleShape emptyClone();

	public abstract SingleShape clone1();

	public Shape createShapeClone(Shape c) {
		Polygon x = new Polygon(((Polygon) c).getPoints().get(0), ((Polygon) c).getPoints().get(1),
				((Polygon) c).getPoints().get(2), ((Polygon) c).getPoints().get(3), ((Polygon) c).getPoints().get(4),
				((Polygon) c).getPoints().get(5));

		x.setLayoutX(((Polygon) c).getLayoutX());
		x.setLayoutY(((Polygon) c).getLayoutY());
		x.setRotate(((Polygon) c).getRotate());
		x.setFill(c.getFill());
		return x;
	}

	protected void createShapeForImport() {
		createShape(false, false, null);
	}

	public void dragOnPressed(double x, double y) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);

		orgSceneX = x;
		orgSceneY = y;
	}

	@Override
	public void moveShape(int x, int y) {
		Polygon hTriangle = (Polygon) shape;
		hTriangle.setLayoutX(hTriangle.getLayoutX() + x);
		hTriangle.setLayoutY(hTriangle.getLayoutY() + y);
		updateFields();

	}

	public void resizeOnPressed(double x, double y) {
		dragOnPressed(x, y);
	}

	public void dragOnDragged(double x, double y, Shape shape) {

		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		if (this.isflipedX) {
			offsetX = -offsetX;
		}
		if (this.isflipedY) {
			offsetY = -offsetY;
		}

		Polygon hTriangle = (Polygon) shape;
		hTriangle.getPoints().set(0, hTriangle.getPoints().get(0) + offsetX);
		hTriangle.getPoints().set(1, hTriangle.getPoints().get(1) + offsetY);
		hTriangle.getPoints().set(2, hTriangle.getPoints().get(2) + offsetX);
		hTriangle.getPoints().set(3, hTriangle.getPoints().get(3) + offsetY);
		hTriangle.getPoints().set(4, hTriangle.getPoints().get(4) + offsetX);
		hTriangle.getPoints().set(5, hTriangle.getPoints().get(5) + offsetY);
		orgSceneX = x;
		orgSceneY = y;
	}

}