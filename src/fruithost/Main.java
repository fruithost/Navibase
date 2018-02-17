package fruithost;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private HashMap<String, Connector> connector = new HashMap<String, Connector>();
	private MainController controller;

	@Override
	public void start(Stage primaryStage) {
		this.loadConnectors();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Main.fxml"));
			Scene scene = new Scene((Parent) loader.load(), 800, 600);
			scene.getStylesheets().add("/ui/ui.css");
			controller = loader.getController();
			controller.init(this);
			primaryStage.setScene(scene);
			primaryStage.setTitle("fruithost");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadConnectors() {
		File folder = new File("connector");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile() && Pattern.compile("([^\\s]+(\\.(?i)(config))$)").matcher(file.getName()).matches()) {
				Connector inst = new Connector(file);
				connector.put(inst.getName().toLowerCase(), inst);
			}
		}
	}
	
	Connector getConnector(String name) {
		return connector.get(name.toLowerCase());
	}

	public static void main(String[] args) {
		launch(args);
	}

	public HashMap<String, Connector> getConnectors() {
		return connector;
	}

	public void toggleSidebar(String string) {
		controller.toggleSidebar(string);
	}

	public Controller openWindow(String file, String title, int width, int height, boolean resizable) {
		Controller c = null;
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/" + file + ".fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			c = fxmlLoader.getController();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			root1.getStylesheets().add("/ui/ui.css");
			stage.setTitle(title);
			Scene a = new Scene(root1, width, height);
			stage.setScene(a);
			//ScenicView.show(a);
			stage.initStyle(StageStyle.UTILITY);
			stage.setResizable(resizable);
			stage.show();
		} catch (Exception e) {

		}
		
		return c;
	}
}
