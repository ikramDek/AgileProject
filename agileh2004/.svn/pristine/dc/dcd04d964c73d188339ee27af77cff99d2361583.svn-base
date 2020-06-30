package application.model;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyEllipse extends SingleShape {

	double orgSceneX, orgSceneY;

	@Override
	public void createShape(boolean isUsingCenter) {
		double radiusX = 0, radiusY = 0, centerX = 0, centerY = 0;
		if(isUsingCenter) {
			radiusX = (double) Math.abs(this.startX - this.endX);
			
			radiusY = (double) Math.abs(this.startY - this.endY);
	
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
			
			radiusX = (double) (Math.abs(this.startX - this.endX) / 2);
	
			radiusY = (double) (Math.abs(this.startY - this.endY) / 2);
	
			centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			if (this.startX < this.endX) {
				centerX = this.startX + (Math.abs(this.startX - this.endX) / 2);
			}
	
			centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			if (this.startY < this.endY) {
				centerY = this.startY + (Math.abs(this.startY - this.endY) / 2);
			}
		}

		shape = new Ellipse(centerX, centerY, radiusX, radiusY);
		shape.setStroke(strokeColor);
		shape.setStrokeWidth(strokeSize);
		filler.fill();
		applyRotation();
	}	
	

	@Override
	public SingleShape clone() {
		MyEllipse c = new MyEllipse();
		return c;
	}


	@Override
	public void moveShape(int x, int y) {
		Ellipse c = (Ellipse) (shape);

		c.setCenterX(c.getCenterX() + x);
		c.setCenterY(c.getCenterY() + y);

		updateFields();
	}

	@Override
	public void enlarge() {
		Ellipse c = (Ellipse) (shape);
		c.setRadiusX(c.getRadiusX() + ENLARGE_CONST);
		c.setRadiusY(c.getRadiusY() + ENLARGE_CONST);
		updateFields();
	}
 
	@Override
	public void updateFields() {
		Ellipse c = (Ellipse) (shape);
		this.startX = (c.getCenterX() - c.getRadiusX());
		this.startY = (c.getCenterY() - c.getRadiusY());
		this.endX = (c.getCenterX() + c.getRadiusX());
		this.endY = (c.getCenterY() + c.getRadiusY());
		this.strokeColor = (Color) c.getStroke();
		this.strokeSize = c.getStrokeWidth() ;
		this.rotate = c.getRotate();
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

		Ellipse c = (Ellipse) shape;

		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		c.setCenterX(c.getCenterX() + offsetX);
		c.setCenterY(c.getCenterY() + offsetY);

		c.setRadiusX(c.getRadiusX() - offsetX);
		c.setRadiusY(c.getRadiusY() - offsetY);

		orgSceneX = x;
		orgSceneY = y;
		
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
	public void dragOnPressed(double x, double y) {		
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);
		
		orgSceneX = x;
		orgSceneY = y;
	}

	@Override
	public void dragOnDragged(double x, double y, Shape shape) {
		double offsetX = x-orgSceneX;
		double offsetY = y-orgSceneY;

		Ellipse c = (Ellipse) (shape);

		c.setCenterX(c.getCenterX() + offsetX);
		c.setCenterY(c.getCenterY() + offsetY);

		
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
	
	
}
