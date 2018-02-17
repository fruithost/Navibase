package fruithost;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class MainController extends Controller {	
	@FXML private Parent menu;
	@FXML private SplitPane content;
	@FXML private ListView<?> sidebar_connection;
	@FXML private BorderPane sidebar_cloud;
	@FXML private MenuController menuController;
	@FXML private Button status_connection;
	@FXML private Button status_cloud;
	
	private Double connection_divider	= 0.0;
	private Double cloud_divider		= 0.0;
	
	public Parent getMenu() {
		return menu;
	}	

	public void init(Main main) {
		this.main = main;
		
		menuController.init(this.main);
		menuController.updateConnectionViewItem(true);
		menuController.updateCloudViewItem(true);
	}

	public void toggleSidebar(String string) {
		switch(string) {
			case "connection":
				if(content.getItems().contains(sidebar_connection)) {
					connection_divider = content.getDividerPositions()[0];
					content.getItems().remove(sidebar_connection);
					status_connection.getStyleClass().remove("active");
					menuController.updateConnectionViewItem(false);
				} else {
					status_connection.getStyleClass().add("active");
					content.getItems().add(0, sidebar_connection); 
					content.setDividerPosition(0, connection_divider);
					menuController.updateConnectionViewItem(true);
				}
			break;
			case "cloud":
				if(content.getItems().contains(sidebar_cloud)) {
					if(content.getItems().contains(sidebar_connection)) {
						cloud_divider = content.getDividerPositions()[1];
					} else {
						cloud_divider = content.getDividerPositions()[0];						
					}
					
					content.getItems().remove(sidebar_cloud);
					status_cloud.getStyleClass().remove("active");
					menuController.updateCloudViewItem(false);
				} else {
					content.getItems().add(content.getItems().size(), sidebar_cloud); 
					status_cloud.getStyleClass().add("active");
					
					if(content.getItems().contains(sidebar_connection)) {
						content.setDividerPosition(1, cloud_divider);
					} else {
						content.setDividerPosition(0, cloud_divider); 
					}
					menuController.updateCloudViewItem(true);
				}
			break;
		}
		
		content.requestLayout();
	}
}
