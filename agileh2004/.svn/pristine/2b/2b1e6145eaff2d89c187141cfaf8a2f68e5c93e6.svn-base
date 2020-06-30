package application.model;


import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyCircle extends SingleShape {

	double orgSceneX, orgSceneY;

	@Override
	public void createShape(boolean isUsingCenter) {
		double radius = 0, centerX = 0, centerY = 0;
		if(isUsingCenter) {
			radius = Math.sqrt(Math.abs(this.startX - this.endX) * Math.abs(this.startX - this.endX)
					+ Math.abs(this.startY - this.endY) * Math.abs(this.startY - this.endY));
	
			centerX = startX;
	
			centerY = startY;
			
			startX = 2 * centerX - endX;
			startY = 2 * centerY - endY;
			
		}
		if (startX > endX) {
			Double tmp = startX;
			startX = endX;
			endX = tmp;
		}

		if (startY > endY) {
			Double tmp = startY;
			startY = endY;
			endY = tmp;
		}
		if(!isUsingCenter) {
			
			radius = Math.sqrt(Math.abs(this.startX - this.endX) * Math.abs(this.startX - this.endX)
					+ Math.abs(this.startY - this.endY) * Math.abs(this.startY - this.endY)) / 2;

			centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			if (this.startX < this.endX) {
				centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			}

			centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			if (this.startY < this.endY) {
				centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			}
		}
		

		shape = new Circle(centerX, centerY, radius);
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		applyRotation();

	}


	@Override
	public void resizeOnReleased(Paint color, double str) {
		this.isSelected = true;
	
		this.shape.setStroke(color);
		this.shape.setStrokeWidth(str);
    	this.shape.setStrokeLineCap(StrokeLineCap.BUTT);
	    this.shape.getStrokeDashArray().clear();
		this.shape.setStrokeDashOffset(str);
		updateFields();

	}
	 
	@Override
	public void resizeOnPressed(double x, double y) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);
		
		orgSceneX = x;
		orgSceneY = y;
	}
	
	@Override
	public void resizeOnDragged(double x, double y, Shape shape) {
		this.shape.setStrokeLineCap(StrokeLineCap.BUTT);

		this.shape.getStrokeDashArray().addAll(10d, 5d);
		

		Circle c = (Circle) shape;

		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		c.setCenterX(c.getCenterX());
		c.setCenterY(c.getCenterY());

		c.setRadius(c.getRadius() - ((offsetX) / 2 + (offsetY) / 2) / 2);

		orgSceneX = x;
		orgSceneY = y;
		//updateFields();
	}

	@Override
	public SingleShape clone() {
		MyCircle c = new MyCircle();
		return c;
	}


	@Override
	public void moveShape(int x, int y) {
		Circle c = (Circle) (shape);

		c.setCenterX(c.getCenterX() + x);
		c.setCenterY(c.getCenterY() + y);
		updateFields();
	}

	@Override
	public void enlarge() {
		Circle c = (Circle) (shape);
		c.setRadius(c.getRadius() + ENLARGE_CONST);
		updateFields();
	}

	@Override
	public void updateFields() {
		Circle c = (Circle) (shape);
		this.startX = (c.getCenterX() - c.getRadius());
		this.startY = (c.getCenterY() - c.getRadius());
		this.endX = (c.getCenterX() + c.getRadius());
		this.endY = (c.getCenterY() + c.getRadius());
		this.strokeColor = (Color) c.getStroke();
		this.strokeSize = c.getStrokeWidth() ;
		this.rotate = c.getRotate();
	}

	@Override
	public void dragOnPressed(double x, double y) {
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);
		
		orgSceneX = x;
		orgSceneY = y;

	}

	@Override
	public void dragOnDragged(double x, double y, Shape shape) {
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		Circle c = (Circle) (shape);

		
		c.setCenterX(c.getCenterX() + offsetX);
		c.setCenterY(c.getCenterY() + offsetY);

		//updateFields();

		orgSceneX = x;
		orgSceneY = y;

	}

	@Override
	public void dragOnReleased(Paint color, double str) {
		this.isSelected = true;
		
		this.shape.setStroke(color);
		this.shape.setStrokeWidth(str);

		updateFields();

	}


	@Override
	public String toString() {
		return "MyCircle [orgSceneX=" + orgSceneX + ", orgSceneY=" + orgSceneY + ", isSelected="
				+ isSelected + ", startX=" + startX + ", startY=" + startY + ", endX=" + endX + ", endY=" + endY
				+ ", strokeColor=" + strokeColor + ", strokeSize=" + strokeSize + ", rotate=" + rotate + ", isPattern="
				+ isPattern + ", filler=" + filler + "]";
	}
	
}