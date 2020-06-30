package application.model;


import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyRoundedRectangle extends SingleShape {
	
	private static final int ARC_ANGLE = 30;
	
	double orgSceneX, orgSceneY;

	@Override
	public void createShape(boolean isUsingCenter) {

		if(isUsingCenter) {	
			double centerX = 0, centerY = 0;
			centerX = startX;
	
			centerY = startY;
			
			startX = 2 * centerX - endX;
			startY = 2 * centerY - endY;
		}

		
		if (startX>endX) {
			Double tmp = startX;
			startX=endX;
			endX=tmp;
		}
		if (startY>endY) {
			Double tmp = startY;
			startY=endY;
			endY=tmp;
		}


		Double width = Math.abs(this.startX-this.endX);
		Double height =Math.abs(this.startY-this.endY);
		Rectangle r= new Rectangle(this.startX,this.startY,width,height);
		r.setArcHeight(ARC_ANGLE);
		r.setArcWidth(ARC_ANGLE);
		r.setStroke(strokeColor);
		shape= r;
		filler.fill();
		shape.setStrokeWidth(strokeSize);
		applyRotation();

	}



	
	
	
	@Override
	public SingleShape clone() {
		MyRoundedRectangle rr= new MyRoundedRectangle();
		return rr;
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
		c.setWidth(c.getWidth()+ENLARGE_CONST);
		c.setHeight(c.getHeight()+ENLARGE_CONST);
		updateFields();
	}
	
	@Override
	public void updateFields() {
		Rectangle c = (Rectangle) (shape);
		this.startX = c.getX();
		this.startY =  c.getY();
		this.endX = (c.getX() + c.getWidth());
		this.endY = (c.getY() + c.getHeight());
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
		
		Rectangle c = (Rectangle) (shape);

		
		double offsetX =  x - orgSceneX;
		double offsetY = y - orgSceneY;
		
		c.setX(c.getX()+offsetX);
		c.setY(c.getY()+offsetY);
		
		c.setHeight(c.getHeight()-offsetY);
		c.setWidth(c.getWidth()-offsetX);
	 
		//this.startX = c.getX();
		//this.startY = c.getY();

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
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

		Rectangle c = (Rectangle) (shape);


		c.setX(c.getX() + offsetX);
		c.setY(c.getY() + offsetY);
		
		orgSceneX = x;
		orgSceneY = y;
	}



	@Override
	public void dragOnReleased(Paint color, double str) {
		this.isSelected = true ;
		
		this.shape.setStroke(color);
		this.shape.setStrokeWidth(str);

		updateFields();

	}

}
