package application.controller;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.model.PatternFiller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class PalettePatternsUIController {

	@FXML
	private ColorPicker colorPicker;

	@FXML
	private CheckBox filled;

	@FXML
	private ColorPicker strokeColor;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private HBox hBox;

	@FXML
	private Pane pane;

	private CanevasController canevasController;

	@FXML
	private Button image1;
	@FXML
	private Button image2;
	@FXML
	private Button image3;
	@FXML
	private Button image4;
	@FXML
	private Button image5;
	@FXML
	private Button image6;
	
	@FXML
	private Button forwardPalette;
	@FXML
	private Button backwardPalette;
	@FXML
	private Button undoPalette;
	@FXML
	private Button redoPalette;
	@FXML
	private Button historyPalette;
	
	

	@FXML
	public void strokShapeColor(ActionEvent event) {
		if (canevasController.getSelectedShape() != null) {
			canevasController.changeSelectedStrokeColor(strokeColor.getValue());
		}
	}

	@FXML
	public void pickerShapeColor(ActionEvent event) {
		if (canevasController.getSelectedShape() != null) {
			canevasController.fillSelectedShapeWithColor(colorPicker.getValue());
			canevasController.setImagePattern("");
		}
	}

	public void setCanevasController(CanevasController canevasController) {
		canevasController.setFillColorPicker(colorPicker);
		canevasController.setStrokeColorPicker(strokeColor);
		this.canevasController = canevasController;
		this.strokeColor.setValue(Color.BLACK);
	}


	public CanevasController getCanevasController() {

		return this.canevasController;
	}

	public void filledChecked() {
		canevasController.setFilled(filled.isSelected());
		canevasController.setImagePattern("");
	}

	@FXML
	public void imageClicked(ActionEvent event) {

		String id = ((Control) event.getSource()).getId();
		if (canevasController.getSelectedShape() != null) {

			try {
				getCanevasController().getChangesController().getRedoList().clear();
				getCanevasController().getChangesController().saveStateToUndo();
				canevasController.getSelectedShape()
				.setFill(new ImagePattern(new Image(new FileInputStream(getImagePath(id)))));
				canevasController.getSelectedShape()
				.setFiller(new PatternFiller(getImagePath(id), canevasController.getSelectedShape()));
				canevasController.getSelectedShape().setPattern(true);
			} catch (Exception e) {
				Logger logger = Logger.getGlobal();
				logger.log(Level.SEVERE, "Exception");
			}
		} else {
			this.filled.setSelected(false);
			canevasController.setImagePattern(getImagePath(id));
		}
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public Pane getPane() {
		return pane;
	}

	public HBox getHBox() {
		return hBox;
	}

	public String getImagePath(String id) {
		return "src/main/resources/" + id.charAt(5) + ".jpg";
	}


	@FXML
	void moveSelectedBackward(ActionEvent event) {
		if (this.canevasController.getSelectedShape() != null) {
			canevasController.moveBackward();
			canevasController.getMenuUIController().getHistoryController().refreshHistoryTable();
		}
	}

	@FXML
	void moveSelectedForward(ActionEvent event) {
		if (this.canevasController.getSelectedShape() != null) {
			canevasController.moveForward();
			canevasController.getMenuUIController().getHistoryController().refreshHistoryTable();
		}
	}
	
	@FXML
	void historyToggle(ActionEvent event) {
		if (!canevasController.getMenuUIController().getHistoryMenuItem().isSelected()) {
			if (canevasController.getMenuUIController().getHistoryStage() != null && canevasController.getMenuUIController().getHistoryStage().isShowing()) {
				canevasController.getMenuUIController().getHistoryController().refreshHistoryTable();
			} else {
				canevasController.getMenuUIController().setHistoryStage(canevasController.getMenuUIController().getHistoryController().initialiseHistoryStage());
			}
			canevasController.getMenuUIController().getHistoryStage().setOnHiding(e -> canevasController.getMenuUIController().getHistoryMenuItem().setSelected(false));
			canevasController.getMenuUIController().getHistoryMenuItem().setSelected(true);
		} else {
			canevasController.getMenuUIController().getHistoryStage().hide();
			canevasController.getMenuUIController().getHistoryMenuItem().setSelected(false);
		}
	}
	
	@FXML
	void undo(ActionEvent event) {
		canevasController.getChangesController().undo();
		canevasController.getMenuUIController().updateMenuItemState();
		canevasController.setCurrentShape(null);
		canevasController.setSelectionMode(false);
		canevasController.setDraggableMode(true);
		canevasController.getMenuUIController().getHistoryController().setLayoutsList(canevasController.getLayoutsList());
		canevasController.getMenuUIController().getHistoryController().refreshHistoryTable();
	}
	
	@FXML
	void redo(ActionEvent event) {
		canevasController.getChangesController().redo();
		canevasController.getMenuUIController().updateMenuItemState();
		canevasController.setCurrentShape(null);
		canevasController.setSelectionMode(false);
		canevasController.setDraggableMode(true);
		canevasController.getMenuUIController().getHistoryController().setLayoutsList(canevasController.getLayoutsList());
		canevasController.getMenuUIController().getHistoryController().refreshHistoryTable();
	}
}