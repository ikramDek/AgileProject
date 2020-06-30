package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainUIController implements Initializable {

	private static final int HEIGHT_OFFSET = 130;
	private static final int WIDTH_OFFSET = 45;

	private static final int MOVING_STEP = 5;
	private static final int NO_STEP = 0;

	private static final String PATH_MENU = "src/main/java/application/view/MenuUI.fxml";
	private static final String PATH_PALETTE_PATTERNS="src/main/java/application/view/PalettePatternsUI.fxml";
	private static final String PATH_PALETTE_OUTILS="src/main/java/application/view/PaletteOutilsUI.fxml";
	private static final String PATH_CANEVAS="src/main/java/application/view/CanevasUI.fxml";
	private static final String PATH_STATUSBAR="src/main/java/application/view/StatusBarUI.fxml";

	//GREAT

	@FXML
	private BorderPane root;

	@FXML
	private AnchorPane menuAnchor;
	@FXML
	private AnchorPane canevasAnchor;
	@FXML
	private AnchorPane paletteOutilsAnchor;   
	@FXML
	private AnchorPane statusbBarAnchor;


	PaletteOutilsUIController paletteOutilsController;
	PalettePatternsUIController palettePatternsController;
	MenuUIController menuController;
	CanevasController canevasController;
	StatusBarController satusbarController;


	@Override
	public void initialize(URL url, ResourceBundle rb) {   

		Platform.runLater(() -> {
			URL URLMenu;
			URL URLPalettePatterns;
			URL URLPaletteOutils;
			URL URLCanevas;
			URL URLStatusBar;
			try {
				//Initialisation de la partie Top de l'interface
				URLMenu = new File(PATH_MENU).toURI().toURL();
				URLPalettePatterns = new File(PATH_PALETTE_PATTERNS).toURI().toURL();
				VBox vbox = new VBox();
				FXMLLoader fxmlLoaderMenu = new FXMLLoader(URLMenu);
				vbox.getChildren().add((Node) fxmlLoaderMenu.load());
				FXMLLoader fxmlLoaderPalettePatterns = new FXMLLoader(URLPalettePatterns);
				vbox.getChildren().add((Node) fxmlLoaderPalettePatterns.load());
				menuAnchor.getChildren().clear();
				menuAnchor.getChildren().add(vbox);

				//Initialisation de la partie Left de l'interface
				URLPaletteOutils = new File(PATH_PALETTE_OUTILS).toURI().toURL();
				paletteOutilsAnchor.getChildren().clear();
				FXMLLoader fxmlLoaderPaletteOutils = new FXMLLoader(URLPaletteOutils);
				paletteOutilsAnchor.getChildren().add((Node) fxmlLoaderPaletteOutils.load());

				//Initialisation de la partie Center de l'interface
				URLCanevas = new File(PATH_CANEVAS).toURI().toURL();
				canevasAnchor.getChildren().clear();
				FXMLLoader fxmlLoaderCanevas = new FXMLLoader(URLCanevas);
				canevasAnchor.getChildren().add((Node) fxmlLoaderCanevas.load());

				//Initialisation de la partie bas de l'interface Staus bar
				URLStatusBar = new File(PATH_STATUSBAR).toURI().toURL();
				statusbBarAnchor.getChildren().clear();
				FXMLLoader fxmlLoaderStatusBar = new FXMLLoader(URLStatusBar);
				statusbBarAnchor.getChildren().add((Node) fxmlLoaderStatusBar.load());




				paletteOutilsController = new PaletteOutilsUIController();
				palettePatternsController = new PalettePatternsUIController();
				menuController =  new MenuUIController();
				canevasController = new CanevasController();
				satusbarController=new StatusBarController();

				paletteOutilsController = (PaletteOutilsUIController) fxmlLoaderPaletteOutils.getController();
				palettePatternsController = (PalettePatternsUIController) fxmlLoaderPalettePatterns.getController();
				menuController = (MenuUIController) fxmlLoaderMenu.getController();
				canevasController = (CanevasController) fxmlLoaderCanevas.getController();
				satusbarController=(StatusBarController)fxmlLoaderStatusBar.getController();

				menuController.setCanevasController(canevasController);
				paletteOutilsController.setCanevasController(canevasController);
				palettePatternsController.setCanevasController(canevasController);
				satusbarController.setCanevasController(canevasController);

				this.bindSizes();



			} catch (Exception e) {
				e.printStackTrace();
			}

		});

	}
	public void keyPressed(KeyEvent e) {
		if(canevasController.getSelectedShape()!=null) {

			if(e.getCode().equals(KeyCode.R) ) {
				canevasController.getSelectedShape().rotateShape();	
			}
			else if(e.getCode().equals(KeyCode.W)) {
				canevasController.getSelectedShape().moveShape(NO_STEP,-MOVING_STEP);
			}
			else if(e.getCode().equals(KeyCode.S)) {
				canevasController.getSelectedShape().moveShape(NO_STEP,MOVING_STEP);
			}
			else if(e.getCode().equals(KeyCode.D)) {
				canevasController.getSelectedShape().moveShape(MOVING_STEP,NO_STEP);
			}
			else if(e.getCode().equals(KeyCode.A)) {
				canevasController.getSelectedShape().moveShape(-MOVING_STEP,NO_STEP);
			}
			else if(e.getCode().equals(KeyCode.E)) {
				canevasController.getSelectedShape().enlarge();
			}
		}



	}

	private void bindSizes() {
		canevasController.getCanvas().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		canevasController.getScrollPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		canevasController.getAnchorPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		paletteOutilsController.getAnchorPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		paletteOutilsController.getPane().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		paletteOutilsController.getVBox().prefHeightProperty().bind(root.getScene().getWindow().heightProperty().subtract(HEIGHT_OFFSET));
		

		canevasController.getCanvas().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		canevasController.getScrollPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		canevasController.getAnchorPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		satusbarController.getStatusBarAnchor().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		satusbarController.getPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		
		menuController.getMenu().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		menuController.getPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		menuController.getAnchorPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		palettePatternsController.getAnchorPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty());
		palettePatternsController.getHBox().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		palettePatternsController.getPane().prefWidthProperty().bind(root.getScene().getWindow().widthProperty().subtract(WIDTH_OFFSET));
		
	}
}
