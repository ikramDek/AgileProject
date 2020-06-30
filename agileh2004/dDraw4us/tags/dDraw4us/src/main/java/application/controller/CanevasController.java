package application.controller;

import java.io.FileInputStream;
import java.net.URL;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.ColorFiller;
import application.model.ComposedShape;
import application.model.Filler;
import application.model.MyRectangle;
import application.model.MyShape;
import application.model.SingleShape;
import application.model.PatternFiller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.input.ScrollEvent;

 

public class CanevasController implements Initializable {

	int prefHeight = 500;
	int prefWidth = 770;

	private static final double DEFAULT_ROTATION = 0;

	private static final double MAX_SCALE = 10.0d;
	private static final double MIN_SCALE = .1d;


	@FXML
	private Group root;
	
    @FXML
    private AnchorPane anchorPane;

	@FXML
	private AnchorPane canvas;// The canvas on which the image is drawn.

	@FXML
	private ScrollPane scrollPane;

	private boolean isFilled=false;

	private boolean isStrokeColored=false;

	private AnchorPane overlay;// A transparent canvas that lies on top of

	private Double startX, startY; // start point of drag
	
	private Double prevX, prevY; // previous mouse location during a drag
	
	private Double currentX, currentY;// current mouse position during a drag

	private SingleShape currentShape = null; // The current drawing shape.

	private ArrayList<MyShape> shapesList;
	
	private SingleShape lastDrawnShape;

	private ColorPicker fillColorPicker;

	private ColorPicker strokeColorPicker;

	private double strokeSize=1;

	private String selectedPatternUrl = "";
	
	private boolean isUsingCenter = true;
	
	private boolean isSelectionMode;
	private ArrayList<Integer> selectedList;

	public ColorPicker getStrokeColorPicker() {
		return strokeColorPicker;
	}

	public void setStrokeColorPicker(ColorPicker strokeColorPicker) {
		this.strokeColorPicker = strokeColorPicker;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		shapesList = new ArrayList<MyShape>();
		canvas.setOnMouseDragged(e -> mouseDragged(e));
		canvas.setOnMousePressed(e -> mousePressed(e));
		canvas.setOnMouseReleased(e -> mouseReleased(e));

		//canvas.setOnScroll(onScrollEventHandler);


		overlay = new AnchorPane();

		overlay.scaleXProperty().bind(canvas.scaleXProperty());
		overlay.scaleYProperty().bind(canvas.scaleYProperty());
		//canvas.prefHeightProperty().bind(overlay.prefHeightProperty());
		//canvas.prefWidthProperty().bind(overlay.prefWidthProperty());


	}

	void mousePressedSelection(MouseEvent evt) {

		this.clearSelection();

		startX = prevX = currentX = evt.getX();

		startY = prevY = currentY = evt.getY();
		setCurrentShape(new MyRectangle());
	}


	void mousePressed(MouseEvent evt) {

		this.clearSelection();

		startX = prevX = currentX = evt.getX();

		startY = prevY = currentY = evt.getY();
		if (currentShape==null) {
			//do nothing or write with pen ( en fonction du mode de selection )
		}
	}
	public void mouseDragged(MouseEvent evt) {
		currentX = evt.getX();
		currentY = evt.getY();
		if(currentShape==null ) {


		}
		else {
			//the shapes that have to be drew on the overlay canvas
			overlay.getChildren().clear();
			if(!isFilled) {
				fillColorPicker.setValue(Color.TRANSPARENT);
			}
			currentShape.setProperties(startX, startY, currentX, currentY, strokeColorPicker.getValue(), getStrokeSize(), getCurrentFiller(),DEFAULT_ROTATION);
			if(isSelectionMode)
				currentShape.createShape(false);
			else
				currentShape.createShape(isUsingCenter);
			if (root.getChildren().size()<2) { //means it's the first time
				root.getChildren().add(overlay);
			}
			overlay.getChildren().add(currentShape.getShape());

		}
		prevX = currentX;
		prevY = currentY;
	}
	public void mouseDraggedSelection(MouseEvent evt) {
		currentX = evt.getX();
		currentY = evt.getY();
		if(currentShape==null ) {


		}
		else {
			//the shapes that have to be drew on the overlay canvas
			overlay.getChildren().clear();
			fillColorPicker.setValue(Color.TRANSPARENT);
			currentShape.setProperties(startX, startY, currentX, currentY, strokeColorPicker.getValue(), getStrokeSize(), getCurrentFiller(),DEFAULT_ROTATION);
			if(isSelectionMode)
				currentShape.createShape(false);
			else
				currentShape.createShape(isUsingCenter);
			if (root.getChildren().size()<2) { //means it's the first time
				root.getChildren().add(overlay);
			}
			overlay.getChildren().add(currentShape.getShape());

		}
		prevX = currentX;
		prevY = currentY;
	}



	private Filler getCurrentFiller() {
		Filler filler;

		if(selectedPatternUrl == "") {
			filler = new ColorFiller(fillColorPicker.getValue(), currentShape);
		}
		else
			filler = new PatternFiller(selectedPatternUrl, currentShape);

		return filler;
	}

	public void mouseReleased(MouseEvent evt) {
		if (root.getChildren().size()>1) { //means it's the first time
			root.getChildren().remove(root.getChildren().size()-1);
			currentShape.setProperties(startX, startY, currentX, currentY, strokeColorPicker.getValue(), getStrokeSize(), getCurrentFiller(), DEFAULT_ROTATION);
			if(isSelectionMode)
				currentShape.createShape(false);
			else
				currentShape.createShape(isUsingCenter);
			lastDrawnShape=currentShape;
			shapesList.add(lastDrawnShape);
			currentShape= currentShape.clone();
			canvas.getChildren().add(((SingleShape)(shapesList.get(shapesList.size()-1))).getShape());

		}


	}

	public void mouseReleasedSelection(MouseEvent evt) {
		if (root.getChildren().size()>1) { //means it's the first time
			root.getChildren().remove(root.getChildren().size()-1);
			selectedList = new ArrayList<Integer>();
			for(int i = shapesList.size() - 1; i >= 0; i--) {
				if(shapesList.get(i).isInside(startX, startY, currentX, currentY))
						selectedList.add(i);
			}
			System.out.println("selected shapes = " + selectedList.size());
		}


	}



	public static double clamp( double value, double min, double max) {

		if( Double.compare(value, min) < 0)
			return min;

		if( Double.compare(value, max) > 0)
			return max;

		return value;
	}


	private javafx.event.EventHandler<ScrollEvent> onScrollEventHandler = new javafx.event.EventHandler<ScrollEvent>() {

		@Override
		public void handle(ScrollEvent event) {

			double delta = 1.2;

			double scale = canvas.getScaleY(); // currently we only use Y, same value is used for X
			double oldScale = scale;

			if (event.getDeltaY() < 0)
				scale /= delta;
			else
				scale *= delta;

			scale = clamp( scale, MIN_SCALE, MAX_SCALE);

			double f = (scale / oldScale)-1;

			double dx = (event.getSceneX() - (canvas.getBoundsInParent().getWidth()/2 + canvas.getBoundsInParent().getMinX()));
			double dy = (event.getSceneY() - (canvas.getBoundsInParent().getHeight()/2 + canvas.getBoundsInParent().getMinY()));

			canvas.scaleXProperty().set(scale);
			canvas.scaleYProperty().set(scale);
			overlay.scaleXProperty().set(scale);
			overlay.scaleYProperty().set(scale);


			event.consume();

		}

	};

	public ArrayList<MyShape> getShapesList() {
		return shapesList;
	}

	public void setShapesList(ArrayList<MyShape> shapesList) {
		this.shapesList = shapesList;
	}

	public SingleShape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(SingleShape currentShape) {
		this.currentShape = currentShape;
	}

	public void setFillColorPicker(ColorPicker colorPicker) {
		this.fillColorPicker = colorPicker;
	}

	public void setDraggableMode(boolean value) {
		if(value==true) {
			for (MyShape shape : shapesList) {

				shape.setOnMousePressedListenerDrag();
				shape.setOnMouseDraggedListenerDrag();
				shape.setOnMouseReleasedListenerDrag();

			}
		}
		else {
			for (MyShape shape : shapesList) {
				shape.setSelected(false);
				shape.disableDraggable();
			}
		}
	}

	public boolean isFilled() {
		return isFilled;
	}
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public void clearAll() {
		this.getShapesList().clear();
		this.canvas.getChildren().clear();

	}

	public void clearSelection() {
		for (MyShape myShape : shapesList) {
			myShape.setSelected(false);
		}
	}

	public MyShape getSelectedShape() {
		for (MyShape myShape : shapesList) {
			if (myShape.isSelected()) {
				System.out.println(myShape.getClass());
				return myShape;

			}
		}
		return null ;
	}

	public double getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(double strokeSize) {
		this.strokeSize = strokeSize;
	}

	public void moveSelectedToBack() {
		int i =-1;
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		if (i!= -1 && i > 0) {
			MyShape tmp = shapesList.get(i);
			shapesList.remove(i);
			shapesList.add(0, tmp); 
			
			this.canvas.getChildren().clear();
			for (MyShape myShape : shapesList) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	public void moveSelectedToFront() {
		int i =-1;
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		if (i!= -1 && i < shapesList.size()-1) {
			MyShape tmp = shapesList.get(i);
			shapesList.remove(i);
			shapesList.add(tmp);
			
			this.canvas.getChildren().clear();
			for (MyShape myShape : shapesList) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public Stage getStage() {

		return (Stage) scrollPane.getScene().getWindow();				// ceci est la mienne (Ayoub)	
	}

	public void refresh(ArrayList<MyShape> newList) {

		clearAll();
		shapesList.addAll(newList);


		for(int i=0;i<newList.size();i++){
			newList.get(i).draw(this.canvas.getChildren());
		}

	}



	public void setResizeMode(boolean value) {
		if (value == true) {
			for (MyShape shape : shapesList) {
	
				/*
				shape.getShape().setOnMouseReleased((t) -> {

					double str= shape.getStrokeSize();
					Color color=(Color) shape.getStrokeColor();
					shape.resizeOnReleased(color, str);
				});*/
				
				shape.setOnMousePressedListener();
				shape.setOnMouseDraggedListener();
				shape.setOnMouseReleasedListener();

			}
		} else {
			for (MyShape shape : shapesList) {
				shape.setSelected(false);
				shape.disableDraggable();
			}
		}
	}

	public void setImagePattern(String selectedPatternUrl) {
		this.selectedPatternUrl = selectedPatternUrl;
	}
	public String getImagePattern() {
		return selectedPatternUrl;
	}
	public AnchorPane getCanvas() {
		return canvas;
	}

	public void setCanvas(AnchorPane canvas) {
		this.canvas = canvas;
	}

	private int getSelectedShapeIndex() {
		for (int i = 0; i < shapesList.size(); i++) {
			if (shapesList.get(i).isSelected()) {
				return i;

			}
		}
		return -1 ;
	}
	

	void moveForward() {
		int i =-1;
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		if (i!= -1 && i < shapesList.size()-1) {
			MyShape tmp = shapesList.get(i);
			shapesList.set(i, shapesList.get(i+1)) ;
			shapesList.set(i+1, tmp) ;
			
			this.canvas.getChildren().clear();
			for (MyShape myShape : shapesList) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	void moveBackward() {
		int i =-1;
		if(getSelectedShape()!=null) {
			i = getSelectedShapeIndex();
		}
		if (i!= -1 && i > 0) {
			MyShape tmp = shapesList.get(i);
			shapesList.set(i, shapesList.get(i-1)) ;
			shapesList.set(i-1, tmp) ;
			
			this.canvas.getChildren().clear();
			for (MyShape myShape : shapesList) {
				myShape.draw(this.canvas.getChildren());
			}
		}
	}

	
	public void resize(double parseDouble) {
		double delta = 1.2;

		double scale = parseDouble / 100.0; // currently we only use Y, same value is used for X        
System.out.println(scale);
		canvas.scaleXProperty().set(scale);
		canvas.scaleYProperty().set(scale);
		
		
	}

	public void setUsingCenter(boolean b) {
		isUsingCenter = b;
	}
	public boolean isUsingCenter() {
		return isUsingCenter;
	}

	public void fillSelectedShapeWithColor(Color value) {
		getSelectedShape().setFill(value);
		getSelectedShape().setFiller(new ColorFiller(value, getSelectedShape()));
		getSelectedShape().setPattern(false);
		getSelectedShape().updateFields();
	}

	public void changeSelectedStrokeColor(Color value) {
		getSelectedShape().changeStrokeColor(value);
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void setSelectionMode(boolean b) {
		isSelectionMode = b;
		// TODO Auto-generated method stub
		if(b) {
			canvas.setOnMouseDragged(e -> mouseDraggedSelection(e));
			canvas.setOnMousePressed(e -> mousePressedSelection(e));
			canvas.setOnMouseReleased(e -> mouseReleasedSelection(e));	
		}
		else {
			canvas.setOnMouseDragged(e -> mouseDragged(e));
			canvas.setOnMousePressed(e -> mousePressed(e));
			canvas.setOnMouseReleased(e -> mouseReleased(e));
		}
	}

	public void groupSelection() {
		// TODO Auto-generated method stub
		ComposedShape compShape = new ComposedShape();
		ArrayList<MyShape> list = new ArrayList<MyShape>();
		for (Integer index : selectedList) {
			shapesList.get(index).setRoot(compShape);
			list.add(shapesList.get(index));
			shapesList.remove(index.intValue());
		}
		compShape.setList(list);
		shapesList.add(compShape);
	}

	public void ungroupSelection() {
		// TODO Auto-generated method stub
		System.out.println(shapesList.size());
		for (Integer index : selectedList) {
			ArrayList<MyShape> list = shapesList.get(index).getList();
			for (MyShape myShape : list) {
				myShape.setRoot(null);
				shapesList.add(myShape);
			}
			shapesList.remove(index.intValue());
		}
		System.out.println(shapesList.size());
	}


}


