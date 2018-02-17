package fruithost;

import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConnectionController extends Controller {
	@FXML GridPane form;
	private Connector connector;
	
	public void init(Main main) {
		this.main = main;
	}
	
	public GridPane getForm() {
		return form;
	}
	
	public void addComponent(Node component, int columnIndex, int rowIndex, int columnspan, int rowspan) {
		form.getChildren().add(component);
		GridPane.setConstraints(component, columnIndex, rowIndex);
	}
	
	public HashMap<String, Object> getData() {
		ObservableList<Node> childrens	= this.getForm().getChildren();
		HashMap<String, Object> data	= new HashMap<String, Object>();
		
	    for(Node node : childrens) {
	    	if(node.getId() == null) {
	    		continue;
	    	}
	    	
	    	Object value = null;
	    	
	    	if(node instanceof TextField) {
	    		value = ((TextField) node).getText();
	    	} else if(node instanceof PasswordField) {
	    		value = ((PasswordField) node).getText();
	    	} else if(node instanceof CheckBox) {
	    		value = ((CheckBox) node).isSelected();
	    	}
	    	
	    	data.put(node.getId(), value);
	    }
	    
		return data;
	}
	
	@FXML
	protected void testConnection(ActionEvent event) {
		Alert alert;
		
		try {
			if(this.connector.getInstance().checkConnection(this.connector.getDriver(), this.getData())) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Connection Successfull.");
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setContentText("Access denied XXX");
			}
		} catch (Exception e) {
			alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
		}

		alert.setHeaderText(null);
		alert.showAndWait();
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}
}
