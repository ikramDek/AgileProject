package application.controller;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import application.model.FileManager;
import application.model.MyShape;
import application.model.SingleShape;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;


public class MenuUIController {

	private CanevasController canevasController;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Pane pane;
	@FXML
	private MenuBar menu;
	@FXML
	private MenuItem unPoint;
	@FXML
	private MenuItem deuxPoints;
	@FXML
	private MenuItem quatrePoints;
	@FXML
	private MenuItem sixPoints;
	@FXML
	private MenuItem huitPoints;
	@FXML
	private MenuItem dixPoints;
	@FXML
	private CheckMenuItem activate;
	@FXML
	private CheckMenuItem desactivate;

	private FileManager fileManager;

	private String openedFile;

	public MenuUIController() {
		fileManager = new FileManager();
		openedFile = "";
	}

	@FXML
	void selectedPen(ActionEvent event) {

		unPoint.setOnAction(event1);
		deuxPoints.setOnAction(event1);
		quatrePoints.setOnAction(event1);
		sixPoints.setOnAction(event1);
		huitPoints.setOnAction(event1);
		dixPoints.setOnAction(event1);

	}

	EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {

			String s = ((MenuItem) e.getSource()).getText();

			String[] separated = s.split(" ");

			int val = Integer.parseInt(separated[0]);

			canevasController.setStrokeSize(val);

			if (canevasController.getSelectedShape() != null) {

				canevasController.getSelectedShape().changeStrokeWidth(val);
				canevasController.setStrokeSize(val);

			}
		}
	};

	@FXML
	void rotateSelected(ActionEvent event) {
		canevasController.getSelectedShape().rotateShape();
	}

	@FXML
	void clearCanevas(ActionEvent event) {
		canevasController.clearAll();
	}

	@FXML
	void activate(ActionEvent event) {
		String id = ((CheckMenuItem) event.getSource()).getId();
		if(id.equals("activate")) {
			canevasController.setUsingCenter(true);
			activate.setSelected(true);
			desactivate.setSelected(false);
		}
		else {
			canevasController.setUsingCenter(false);
			activate.setSelected(false);
			desactivate.setSelected(true);
		}

	}

	private ArrayList<MyShape> myList = new ArrayList<MyShape>();
	private HashMap m;


	@FXML
	void moveToFront(ActionEvent event) {
		canevasController.moveSelectedToFront();
	}

	@FXML
	void moveToBack(ActionEvent event) {
		canevasController.moveSelectedToBack();
	}

	public CanevasController getCanevasController() {
		return canevasController;
	}

	public void setCanevasController(CanevasController canevasController) {
		this.canevasController = canevasController;
	}



	@FXML
	void saveAsFile(ActionEvent event) throws ParserConfigurationException {
		FileChooser fileChooser = new FileChooser();

		//Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		//Show save file dialog
		File file = fileChooser.showSaveDialog(canevasController.getStage());
		fileManager.saveTextToFile(file.getAbsolutePath(), canevasController.getShapesList());
	}


	@FXML
	void newPage(ActionEvent event) throws ParserConfigurationException {
		if(canevasController.getShapesList().size() != 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Your changes will be lost if you do not save them.");
			alert.setContentText("do you want to save the changes?");

			ButtonType buttonTypeOne = new ButtonType("Yes");
			ButtonType buttonTypeTwo = new ButtonType("No");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne){
				saveFile(null);
			} else {
				if (result.get() == buttonTypeTwo){
					canevasController.clearAll();
				} else {
					return;
				}
			}
		}
		canevasController.clearAll();
	}
	@FXML
	void saveFile(ActionEvent event) throws ParserConfigurationException {
		if(openedFile.equals("")) {
			FileChooser fileChooser = new FileChooser();

			//Set extension filter for text files
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			//Show save file dialog
			File file = fileChooser.showSaveDialog(canevasController.getStage());
			fileManager.saveTextToFile(file.getAbsolutePath(), canevasController.getShapesList());
			openedFile = file.getAbsolutePath();
		}
		else
			fileManager.saveTextToFile(openedFile, canevasController.getShapesList());
	}


	@FXML
	void importFile(ActionEvent event) {
		try {
			fileManager.importFromFile(canevasController.getStage(), m, myList);
			ArrayList<MyShape> newList = new ArrayList<MyShape>();
			newList.addAll(canevasController.getShapesList());
			newList.addAll(myList);
			
			canevasController.refresh(newList);

		}
		catch (Exception e) {
			System.out.println(e);	}
	}


	@FXML
	void openFile(ActionEvent event) throws SAXException, ParserConfigurationException, IOException{


		if(canevasController.getShapesList().size() != 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Your changes will be lost if you do not save them.");
			alert.setContentText("do you want to save the changes?");

			ButtonType buttonTypeOne = new ButtonType("Yes");
			ButtonType buttonTypeTwo = new ButtonType("No");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne){
				saveFile(null);
			} else {
				if (result.get() == buttonTypeTwo){
					canevasController.clearAll();
				} else {
					return;
				}
			}

		}

		myList = new ArrayList<MyShape>();

		fileManager.importFromFile(canevasController.getStage(), m, myList);
		canevasController.refresh(myList);

	}



	@FXML
	void saveAsAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		//Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().add(extFilter);

		//Show save file dialog
		File file = fileChooser.showSaveDialog(canevasController.getStage());

		if(file != null){
			try {
				WritableImage writableImage = new WritableImage(770,540);
				canevasController.getCanvas().snapshot(null, writableImage);
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
				ImageIO.write(renderedImage, "png", file);
			} catch (IOException ex) {
            	System.out.println(ex);
            }
        }
    }

	public void setWidth(double width) {
		// TODO Auto-generated method stub
		pane.setPrefWidth(width);
		menu.setPrefWidth(width);
	    anchorPane.setPrefWidth(width);
	}
	
    @FXML
    void moveForward(ActionEvent event) {
    	canevasController.moveForward();
    }

    @FXML
    void groupSelection(ActionEvent event) {
    	canevasController.groupSelection();
    }
    
    @FXML
    void ungroupSelection(ActionEvent event) {
    	canevasController.ungroupSelection();
    }


    @FXML
    void moveBackward(ActionEvent event) {
    	canevasController.moveBackward();
    }

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public Pane getPane() {
		return pane;
	}

	public MenuBar getMenu() {
		return menu;
	}

    


}
