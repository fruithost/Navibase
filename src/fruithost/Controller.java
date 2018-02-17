package fruithost;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;

public class Controller implements Initializable {
	public Main main;
	
	public Main getMain() {
		return this.main;
	}
	
	@FXML
	protected void onClick(ActionEvent event) {
		Object element	= event.getSource();
		String id		= null;
		String submenu;
		
		if(element instanceof Button) {
			id = ((Button) element).getId();
		} else if(element instanceof RadioMenuItem) {
			id = ((RadioMenuItem) element).getId();
		} else if(element instanceof MenuItem) {
			id = ((MenuItem) element).getId();
		}
		
		
		try {
			StringTokenizer section	= new StringTokenizer(id, "_");
			String menu				= section.nextToken();
			
			switch(menu) {
				case "file":
					submenu = section.nextToken();
					
					switch(submenu) {
						case "connection":
							String name = section.nextToken();
							
							switch(name) {
								case "open":
								case "close":
									// @ToDo
								break;
								default:
									Connector connector				= this.getMain().getConnector(name);
									ConnectionController controller	= (ConnectionController) this.getMain().openWindow("Connection", "Connection", 480, 475, false);
									controller.setConnector(connector);
									connector.getInstance().setConnectionData(controller);
								break;
							}
						break;
						case "export":
						
						break;
						case "cloud":
						
						break;
						case "exit":
							System.exit(-1);
						break;
						default:
							System.err.println("Cant find " +  submenu);
						break;
					}
				break;
				case "view":
					submenu = section.nextToken();
					
					switch(submenu) {
						case "list":
						
						break;
						case "detail":
							
						break;
						case "diagram":
							
						break;
						case "flatten":
							// SUBDATA
							// connection
							// objects
						break;
						case "onlyactive":
							
						break;
						case "sort":
							// SUBDATA
							// name
							// group
							// ai
							// format
							// date > modified, created
							// index
							// data
							// type
							// rows
							// max
							// free
							// time
							// collation
							// options
							// comment
						break;
						case "columns":
							
						break;
						case "connection":
							this.getMain().toggleSidebar("connection");
						break;
						case "information":
							this.getMain().toggleSidebar("information");
						break;
						case "cloud":
							this.getMain().toggleSidebar("cloud");
						break;
						case "table":
							
						break;
						case "view":
							
						break;
						case "function":
							
						break;
						case "event":
							
						break;
						case "query":
							
						break;
						case "report":
							
						break;
						case "backup":
							
						break;
						case "schedule":
							
						break;
						case "model":
							
						break;
						case "user":
							
						break;
						default:
							System.err.println("Cant find " +  submenu);
						break;
					}
				break;
				case "favorites":
					
				break;
				case "tools":
					submenu = section.nextToken();
					
					switch(submenu) {
						case "transfer":
							
						break;
						case "syncronization":
							// SUBDATA
							// data
							// structure
						break;
						case "console":
							
						break;
						case "monitor":
							// SUBDATA
							// connector name
						break;
						case "find":
							
						break;
						case "log":
							
						break;
						case "options":
							this.getMain().openWindow("Options", "Options", 800, 600, false);
						break;
						default:
							System.err.println("Cant find " +  submenu);
						break;
					}
				break;
				case "window":
					submenu = section.nextToken();
					
					switch(submenu) {
						case "next":
							
						break;
						default:
							System.err.println("Cant find " +  submenu);
						break;
					}
				break;
				case "help":
					submenu = section.nextToken();
					
					switch(submenu) {
						case "help":
						
						break;
						case "update":
						
						break;
						case "about":
							
						break;
						default:
							System.err.println("Cant find " +  submenu);
						break;
					}
				break;
				case "status":
					submenu = section.nextToken();
					
					switch(submenu) {
						case "connection":
							this.getMain().toggleSidebar("connection");
						break;
						case "information":
							this.getMain().toggleSidebar("information");
						break;
						case "cloud":
							this.getMain().toggleSidebar("cloud");
						break;
						default:
							System.err.println("Cant find " +  submenu);
						break;
					}
				break;
				default:
					System.err.println("Cant find " +  menu);
				break;
			}
		} catch(Exception e) {
			/* Do Nothing */
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
