package application;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.controller.MainUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EntryPoint extends Application {

	private static final String PATH_MAIN = "src/main/java/application/view/MainUI.fxml";
 
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		try {
			URL urlMain = new File(PATH_MAIN).toURI().toURL();
			FXMLLoader fxmlLoaderEntry = new FXMLLoader(urlMain);
			root.getChildren().add((Node) fxmlLoaderEntry.load());
			MainUIController mainController = fxmlLoaderEntry.getController();

			root.prefHeight(600);
			root.prefWidth(800);
			Scene scene = new Scene(root);
			stage.setTitle("dDraw4Us");
			stage.setScene(scene);

			stage.show();

			stage.setOnHidden(e -> {
				mainController.getStateController().saveState();
				mainController.getMenuController().getHistoryStage().hide();
			});

		} catch (Exception e) {
			Logger logger = Logger.getGlobal();
			logger.log(Level.SEVERE, "Exception");
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
