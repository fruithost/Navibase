package fruithost;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuController extends Controller {
	@FXML
	private CheckMenuItem view_connection;
	
	@FXML
	private CheckMenuItem view_cloud;	
	
	@FXML
	private Menu file_connection;
	
	@FXML
	private Menu tools_monitor;
	
	public void addMenu(Connector inst) {
		MenuItem item;
		
		// Connection Menu
		item = new MenuItem(inst.getName() + "...");
		item.setId("file_connection_" + inst.getName().toLowerCase());
		item.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onClick(event);
			}
		});
		file_connection.getItems().add(item);
		
		// Monitor
		if(inst.hasMonitor()) {
			item = new MenuItem(inst.getName() + "...");
			item.setId("tools_monitor_" + inst.getName().toLowerCase());
			item.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onClick(event);
				}
			});
			tools_monitor.getItems().add(item);
		}
	}
	
	public void updateConnectionViewItem(boolean state) {
		this.view_connection.setSelected(state);
	}
	
	public void updateCloudViewItem(boolean state) {
		this.view_cloud.setSelected(state);		
	}

	public void init(Main main) {
		this.main = main;
		
		for(Entry<String, Connector> temp : main.getConnectors().entrySet()) {
			this.addMenu(temp.getValue());
		}
	}
}
