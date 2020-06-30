package application.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MyLine extends SingleShape {

	private double orgSceneX, orgSceneY;

	@Override
	public void createShape(boolean isUsingCenter) {

		if(isUsingCenter) {
			double centerX = 0, centerY = 0;
			centerX = startX;
	
			centerY = startY;
			
			startX = 2 * centerX - endX;
			startY = 2 * centerY - endY;	
		}

		shape = new Line(startX,startY, endX, endY);
		shape.setStrokeWidth(strokeSize);
		shape.setStroke(strokeColor);
		filler.fill();
		applyRotation();

	}
		

	@Override
	public SingleShape clone() {
		MyLine l= new MyLine();
		return l ;
	}


	@Override
	public void moveShape(int x, int y) {
		Line l = (Line) (shape);

		l.setLayoutX(l.getLayoutX()+x);
		l.setLayoutY(l.getLayoutY()+y);


		updateFields();
	}

	@Override
	public void enlarge() {

		Line l = (Line) (shape);
		double pente =(l.getEndY()-l.getStartY())/(l.getEndX()-l.getStartX());
		double b= l.getStartY()- pente*l.getStartX();
		if(pente<0  ) {
			if(l.getEndY()>l.getStartY()) {
				l.setEndX(l.getEndX()-ENLARGE_CONST);
			}
			else {
				l.setEndX(l.getEndX()+ENLARGE_CONST);
			}
		}
		else if(pente>0 ) {
			if(l.getEndY()<l.getStartY()) {
				l.setEndX(l.getEndX()-ENLARGE_CONST);
			}
			else {
				l.setEndX(l.getEndX()+ENLARGE_CONST);
			}
		}
		else if(pente ==0) {
			if(l.getEndX()>l.getStartX()) {
				l.setEndX(l.getEndX()+ENLARGE_CONST);
			}
			else {
				l.setEndX(l.getEndX()-ENLARGE_CONST);
			}
		}
		l.setEndY(pente*l.getEndX()+b);
		updateFields();
	}

	@Override
	public void updateFields() {
		Line l = (Line) (shape);

		this.startX=l.getStartX();
		this.startY= l.getStartY();
		this.endX= l.getEndX();
		this.endY= l.getEndY();
		this.strokeColor= (Color) l.getStroke();
		this.strokeSize = l.getStrokeWidth() ;
		this.rotate = l.getRotate();

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
		
		Line l = (Line) shape;
		
		double offsetX = x - orgSceneX;
		double offsetY = y - orgSceneY;

				
		l.setStartX(l.getStartX()+offsetX);
		l.setStartY(l.getStartY()+offsetY);

	//	l.setLayoutX(l.getLayoutX()+offsetX);
	//	l.setLayoutY(l.getLayoutY()+offsetY);


		orgSceneX = x;
		orgSceneY = y;
	}

	@Override
	public void resizeOnReleased(Paint color, double str) {
		this.isSelected = true ;
		
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
		this.shape.setStroke(Color.RED);
		this.shape.setStrokeWidth(5);
		
		double offsetX = x- orgSceneX;
		double offsetY = y- orgSceneY;

		Line l = (Line) (shape);



		l.setStartX(l.getStartX()+offsetX);
		l.setStartY(l.getStartY()+offsetY);

		l.setEndX(l.getEndX()+offsetX);
		l.setEndY(l.getEndY()+offsetY);

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
