/**
	dDraw4us
    @autor Yassine HAMMADI 
	Created on 29 Jan 2020 at 18:36:04
 */
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author yassine
 *
 */
public class StatusBarController implements Initializable {

	@FXML
	private Slider zoomSlider;
	
    @FXML
    private AnchorPane statusBarAnchor;
    
    @FXML
    private Pane pane;


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
				//canevasController.resize((double) newValue);
				canevasController.getCanvas().scaleXProperty().set((double) newValue/100.0);
				canevasController.getCanvas().scaleYProperty().set((double) newValue/100.0);
			}
		});

	}
	
	public AnchorPane getStatusBarAnchor() {
		return statusBarAnchor;
	}
	
	public Slider getZoomSlider() {
		return zoomSlider;
	}

	public Pane getPane() {
		return pane;
	}	
	public void setWidth(double width) {
		statusBarAnchor.setPrefWidth(width);
		
	}

}
