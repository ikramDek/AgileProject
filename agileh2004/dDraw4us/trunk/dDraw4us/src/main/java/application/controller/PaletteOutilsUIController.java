package application.controller;

import application.model.HorizontalTriangle;
import application.model.MyCircle;
import application.model.MyEllipse;
import application.model.MyLine;
import application.model.MyRectangle;
import application.model.MyRoundedRectangle;
import application.model.MyShape;
import application.model.VerticalTriangle;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class PaletteOutilsUIController {

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
	private Button selectBtn;
	@FXML
	private Button resizeBtn;
	@FXML
	private Button zoomBtn;
	@FXML
	private Button cursorBtn;
	@FXML
	private Button vTriangle;
	@FXML
	private Button hTriangle;

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Pane pane;
	@FXML
	private VBox vBox;

	@FXML
	private Button flipHor;

	@FXML
	private Button flipVert;

	private CanevasController canevasController;

	////////////////////////////////////////////////////////
	// these methods are executed when we click on buttons//
	////////////////////////////////////////////////////////
	@FXML
	public void circleBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new MyCircle());
	}

	@FXML
	void horBtnClicked(javafx.event.ActionEvent eg) {

		MyShape selectedShape;
		selectedShape = canevasController.getSelectedShape();

		if (selectedShape != null) {
			this.canevasController.getChangesController().getRedoList().clear();
			this.canevasController.getChangesController().saveStateToUndo();
			selectedShape.flipShape(false);
			selectedShape.updateFields();
		}

	}

	@FXML
	void vertBtnClicked(javafx.event.ActionEvent eg) {

		MyShape selectedShape;
		selectedShape = canevasController.getSelectedShape();

		if (selectedShape != null) {
			this.canevasController.getChangesController().getRedoList().clear();
			this.canevasController.getChangesController().saveStateToUndo();
			selectedShape.flipShape(true);
			selectedShape.updateFields();
		}

	}

	@FXML
	void selectButClicked(ActionEvent event) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(null);
		canevasController.setSelectionMode(true);
	}

	@FXML
	public void ellipseBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setCurrentShape(new MyEllipse());
	}

	@FXML
	public void rectBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setCurrentShape(new MyRectangle());
		canevasController.setSelectionMode(false);
		canevasController.setDraggableMode(false);
	}

	@FXML
	public void roundedRectBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new MyRoundedRectangle());
	}

	@FXML
	public void lineBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new MyLine());
	}

	@FXML
	public void vTriangleBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new VerticalTriangle());
	}

	@FXML
	public void hTriangleBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setDraggableMode(false);
		canevasController.setSelectionMode(false);
		canevasController.setCurrentShape(new HorizontalTriangle());
	}

	@FXML
	public void cursorBtnClicked(javafx.event.ActionEvent eg) {
		canevasController.setCurrentShape(null);
		canevasController.setSelectionMode(false);
		canevasController.setDraggableMode(true);
	}

	@FXML
	public void resizeBtnClicked(javafx.event.ActionEvent eg) {

		canevasController.setCurrentShape(null);
		canevasController.setResizeMode(true);
		canevasController.setSelectionMode(false);

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
		layout.getChildren().setAll(slider);

		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if (Boolean.FALSE.equals(isNowFocused)) {
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
		slider.setMinHeight(Region.USE_PREF_SIZE);

		slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging,
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

	public Region getVBox() {
		// TODO Auto-generated method stub
		return vBox;
	}

}
