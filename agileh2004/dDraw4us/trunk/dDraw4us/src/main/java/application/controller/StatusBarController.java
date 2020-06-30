package application.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import application.model.MyShape;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StatusBarController implements Initializable {

	@FXML
	private Slider zoomSlider;

	@FXML
	private AnchorPane statusBarAnchor;

	@FXML
	private Pane pane;

	@FXML
	private Label shapeSelected;


	private CanevasController canevasController;

	public void setCanevasController(CanevasController canevasController) {
		this.canevasController = canevasController;

	}

	public CanevasController getCanevasController() {

		return canevasController;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				canevasController.updateCanvasScale((double) newValue);
			}
		});
	} 

	public void setStatusBarAnchor(AnchorPane statusBarAnchor) {
		this.statusBarAnchor =statusBarAnchor;
	}
	 
	public AnchorPane getStatusBarAnchor() {
		return this.statusBarAnchor;
	}
 
	public Slider getZoomSlider() {
		return zoomSlider;
	}

	public Pane getPane() {
		return pane; 
	}
	
	public Double getWidth() {
		
		return statusBarAnchor.getPrefWidth();
	}

	public void setWidth(double width) {
		statusBarAnchor.setPrefWidth(width);
	}

	public void setSelectedText(MyShape shape) {
		if(shape != null)
			shapeSelected.setText("Selected shape : " + shape.toString() +
					", TOPLEFT("+new DecimalFormat("#.##").format(shape.getStartX())+";"+new DecimalFormat("#.##").format(shape.getStartY())
					+"), BOTTOMRIGHT("+new DecimalFormat("#.##").format(shape.getEndX())+";"+new DecimalFormat("#.##").format(shape.getEndY())+")");
		else
			shapeSelected.setText("No shape selected");
	}

} 
