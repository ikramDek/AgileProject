package application;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EntryPoint extends Application{

	String PATH_MAIN = "src/main/java/application/view/MainUI.fxml";
	@Override
	public void start(Stage stage) throws Exception {
		Parent root;
		try {
			URL URLMain = new File(PATH_MAIN).toURI().toURL();
			root = FXMLLoader.load(URLMain);
			root.prefHeight(600);
			root.prefWidth(800);
			Scene scene = new Scene(root);
			stage.setTitle("dDraw4Us");
			stage.setScene(scene);
			
			//stage.setResizable(false);

			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
