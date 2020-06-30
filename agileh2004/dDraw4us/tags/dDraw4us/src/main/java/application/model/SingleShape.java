package application.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

public abstract class SingleShape implements MyShape{
	protected static final int ENLARGE_CONST = 5;
	protected static final int ROTATE_CONST = 5;

	Shape shape;

	boolean isSelected = false ;

	
	public boolean isPattern() {
		return isPattern;
	}

	public void setPattern(boolean isPattern) {
		this.isPattern = isPattern;
	}

	public void setFiller(Filler filler) {
		this.filler = filler;
	}
	protected ComposedShape root;
	protected Double startX ;
	protected Double startY;
	protected Double endX;
	protected Double endY;
	protected Color strokeColor;
	protected Double strokeSize;
	protected Double rotate;
	protected Translate translate;
	protected boolean isPattern;
	protected Filler filler;
	
	public abstract void createShape(boolean isUsingCenter);

	public abstract void resizeOnPressed(double x, double y);

	public abstract void resizeOnDragged(double x, double y, Shape c);

	public abstract void resizeOnReleased(Paint color, double str);

	public abstract void dragOnPressed(double x, double y);

	public abstract void dragOnDragged(double x, double y, Shape c);

	public abstract void dragOnReleased(Paint color, double str);

	public Shape getShape() {
		return shape;
	}

	public void disableDraggable() {
		shape.setOnMousePressed((t) -> {

		});
		shape.setOnMouseDragged((t) -> {

		});
	}

	public void setProperties(
			Double startX, 
			Double startY, 
			Double endX, 
			Double endY, 
			Color strokeColor,
			Double strokeSize,
			Filler filler,
			Double rotate
			) 
	{
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		this.filler=filler;
		this.strokeSize = strokeSize;
		this.strokeColor=strokeColor;
		this.rotate= rotate;
	}

	public abstract SingleShape clone() ;

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void rotateShape() {
		shape.setRotate(shape.getRotate()+ROTATE_CONST);
		updateFields();
	}
	public abstract void moveShape(int x, int y);
	public abstract void enlarge();

	public abstract void updateFields();

	public Map<String, Double> getProperties(){

		Map<String, Double> properties = new HashMap<String, Double>();

		properties.put("startPositionX", (double)startX);
		properties.put("startPositionY", (double)startY);

		properties.put("endPositionX", (double)endX);
		properties.put("endPositionY", (double)endY);

		properties.put("topLeftX", (double)startX);
		properties.put("topLeftY", (double)startY);


		properties.put("strokeSize", (double) shape.getStrokeWidth());
		if(!filler.isColor()) {
			String pattern = ((PatternFiller) filler).getImageUrl().substring(((PatternFiller)filler).getImageUrl().lastIndexOf("/") + 1, ((PatternFiller)filler).getImageUrl().lastIndexOf('.'));
			properties.put("imageUrl", (double) Integer.parseInt(pattern));
		}
		else {
			properties.put("fillR", ((ColorFiller) filler).getColor().getRed());
			properties.put("fillG", ((ColorFiller) filler).getColor().getGreen());
			properties.put("fillB", ((ColorFiller) filler).getColor().getBlue());
			properties.put("fillOpacity", ((ColorFiller) filler).getColor().getOpacity());
		}
		properties.put("strockR", strokeColor.getRed());
		properties.put("strockG", strokeColor.getGreen());
		properties.put("strockB", strokeColor.getBlue());

		properties.put("rotateValue", shape.getRotate());

		//

		return properties;
	}

	public void setProperties(HashMap<String, Double> properties) {
		startX = properties.get("startPositionX");
		startY = properties.get("startPositionY");

		endX = properties.get("endPositionX");
		endY = properties.get("endPositionY");
		
		strokeSize = (Double) properties.get("strokeSize");

		rotate = properties.get("rotateValue");

		Double strockR,strockG,strockB,fillR,fillG,fillB, fillOpacity;
		strockR = (Double) properties.get("strockR");
		strockG = (Double) properties.get("strockG");
		strockB = (Double) properties.get("strockB");

		strokeColor = Color.color(strockR,strockG, strockB);

		if(properties.containsKey("imageUrl")) {
			filler = new PatternFiller("src/main/resources/" + properties.get("imageUrl").intValue() + ".jpg", this);
		}
		else {
			fillR = (Double) properties.get("fillR");
			fillG = (Double) properties.get("fillG");
			fillB = (Double) properties.get("fillB");
			fillOpacity = (Double) properties.get("fillOpacity");
			filler = new ColorFiller(Color.color(fillR, fillG, fillB, fillOpacity), this);
		}

	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(Double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(Double startY) {
		this.startY = startY;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(Double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(Double endY) {
		this.endY = endY;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public double getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(double strokeSize) {
		this.strokeSize = strokeSize;
	}

	public Filler getFiller() {
		return filler;
	}

	public void setFiller(ColorFiller colorFiller) {
		filler = colorFiller;
	}
	public void applyRotation(){
		shape.setRotate(this.rotate);
	}

	public void changeStrokeWidth(int x) {
		shape.setStrokeWidth(x);
		updateFields();
	}
	
	public void changeStrokeColor(Color value) {
		shape.setStroke(value);
		updateFields();
	}
	
	@Override
	public void draw(ObservableList<Node> children) {
		// TODO Auto-generated method stub
		children.add(getShape());
	}
	
	@Override
	public void setOnMouseDraggedListener() {
		// TODO Auto-generated method stub
		getShape().setOnMouseDragged((t) -> {
			
			ComposedShape compShape = this.getRoot();
			if(compShape == null)
				resizeOnDragged(t.getSceneX(), t.getSceneY(), (Shape)t.getSource());
			else {
				for (MyShape shape : compShape.getList()) {
					shape.resizeOnDragged(t.getSceneX(), t.getSceneY(), ((SingleShape)shape).getShape());
				}
			}
		});
	}
	
	@Override
	public void setOnMousePressedListener() {
		// TODO Auto-generated method stub
		getShape().setOnMousePressed((t) -> {
			ComposedShape compShape = this.getRoot();
			if(compShape == null)
				resizeOnPressed(t.getSceneX(), t.getSceneY());
			else {
				for (MyShape shape : compShape.getList()) {
					shape.resizeOnPressed(t.getSceneX(), t.getSceneY());
				}
			}
		});
	}
	
	@Override
	public void setOnMouseReleasedListener() {
		// TODO Auto-generated method stub
		getShape().setOnMouseReleased((t) -> {			
			ComposedShape compShape = this.getRoot();
			if(compShape == null) {
				double str = getStrokeSize();
				Color color=(Color) getStrokeColor();
				resizeOnReleased(color, str);

			}
			else {
				for (MyShape shape : compShape.getList()) {
					double str = ((SingleShape) shape).getStrokeSize();
					Color color=(Color) ((SingleShape) shape).getStrokeColor();
					shape.resizeOnReleased(color, str);
				}
			}
		});
	}

	@Override
	public void setOnMouseDraggedListenerDrag() {
		// TODO Auto-generated method stub
		getShape().setOnMouseDragged((t) -> {
			ComposedShape compShape = this.getRoot();
			if(compShape == null)
				dragOnDragged(t.getSceneX(), t.getSceneY(), (Shape)t.getSource());
			else {
				for (MyShape shape : compShape.getList()) {
					shape.dragOnDragged(t.getSceneX(), t.getSceneY(), ((SingleShape)shape).getShape());
				}
			}
		});
	}
	
	@Override
	public void setOnMousePressedListenerDrag() {
		// TODO Auto-generated method stub
		getShape().setOnMousePressed((t) -> {
			ComposedShape compShape = this.getRoot();
			System.out.println(compShape);
			if(compShape == null)
				dragOnPressed(t.getSceneX(), t.getSceneY());
			else {
				for (MyShape shape : compShape.getList()) {
					shape.dragOnPressed(t.getSceneX(), t.getSceneY());
				}
			}
		});
	}
	
	private ComposedShape getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public void setOnMouseReleasedListenerDrag() {
		// TODO Auto-generated method stub
		getShape().setOnMouseReleased((t) -> {
			ComposedShape compShape = this.getRoot();
			if(compShape == null) {
				double str = getStrokeSize();
				Color color=(Color) getStrokeColor();
				dragOnReleased(color, str);

			}
			else {
				for (MyShape shape : compShape.getList()) {
					double str = ((SingleShape) shape).getStrokeSize();
					Color color=(Color) ((SingleShape) shape).getStrokeColor();
					shape.dragOnReleased(color, str);
				}
			}

		});
	}
	
	@Override
	public void setFill(Paint pattern) {
		// TODO Auto-generated method stub
		getShape().setFill(pattern);
	}
	
	@Override
	public void setRoot(ComposedShape compShape) {
		// TODO Auto-generated method stub
		root = compShape;
	}

	@Override
	public boolean isInside(Double startX, Double startY, Double currentX, Double currentY) {
		return FindPoint(startX, startY, currentX, currentY, this.startX, this.startY)
	 || FindPoint(startX, startY, currentX, currentY, this.endX, this.endY)
	|| FindPoint(startX, startY, currentX, currentY, this.startX, this.endY)
	|| FindPoint(startX, startY, currentX, currentY, this.endX, this.startY);

	}
	
	public boolean FindPoint(double x1, double y1, double x2, double y2, double x, double y) { 
		if (x > x1 && x < x2 && y > y1 && y < y2) 
			return true; 

		return false; 
	} 

	public ArrayList<MyShape> getList(){
		ArrayList<MyShape> list = new ArrayList<MyShape>();
		list.add(this);
		return list;
	}

}
