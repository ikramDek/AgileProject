package application.controller;

import application.model.MyCircle;
import application.model.MyEllipse;
import application.model.MyLine;
import application.model.MyRectangle;
import application.model.MyRoundedRectangle;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class PaletteOutilsUIController {

	// the buttons created on the palette
	@FXML
	private Button circleBtn;
	@FXML
	private Button ellipseBtn;
	@FXML
	private Button rectBtn;
	@FXML
	private Button roundedRectBtn;
	@FXML
	private Button lineBtn;
	@FXML
	private Button arrowBtn;
	
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;
    @FXML
    private VBox vBox;

	private CanevasController canevasController;

	public void resizeBtnClicked(javafx.event.ActionEvent eg) {

		canevasController.setCurrentShape(null);
		canevasController.setResizeMode(true);
		canevasController.setSelectionMode(false);

	}
	
	
	////////////////////////////////////////////////////////
	// these methods are executed when we click on buttons//
	////////////////////////////////////////////////////////
	public void circleBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new MyCircle());
		System.out.println("circle button clicked");
	}

	public void ellipseBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new MyEllipse());
		System.out.println("ellipse button clicked");
	}

	public void rectBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setCurrentShape(new MyRectangle());
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		System.out.println("rectangle button clicked");
	}

	public void roundedRectBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new MyRoundedRectangle());
		// canevasController.setCurrentShape(new RoundedRectangle());
		System.out.println("rounded rectangle button clicked");
	}

	public void lineBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new MyLine());
		System.out.println("line button clicked");
	}

	public void arrowBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(null);
		System.out.println("arrow button clicked");
	}

	public void cursorBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setCurrentShape(null);
		canevasController.setDraggableMode(true);
		canevasController.setSelectionMode(false);
	}
	
	@FXML
    void selectButClicked(ActionEvent event) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(null);
		canevasController.setSelectionMode(true);
    }

	public CanevasController getCanevasController() {
		return canevasController;
	}

	public void setCanevasController(CanevasController canevasController) {
		this.canevasController = canevasController;
	}

	@FXML
    void zoom(ActionEvent event) {

		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Scaling");
		dialog.setHeaderText("Scaling");
			
		
        Slider slider = createMonitoredSlider();

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().setAll(
                slider
        );
        
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                stage.hide();
            }
        });
        
       
        stage.setScene(new Scene(layout));
        stage.show();
    }

    private Slider createMonitoredSlider() {
        final Slider slider = new Slider(0, 200, canevasController.getCanvas().getScaleY() * 100);
        slider.setMajorTickUnit(20);
        slider.setMinorTickCount(0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMinHeight(Slider.USE_PREF_SIZE);

        slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {
                Double valueString = slider.getValue();
                canevasController.resize(valueString);
                
            }

		
        });
        return slider;
    }

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public Pane getPane() {
		return pane;
	}

	public VBox getVBox() {
		return vBox;
	}
    

}
