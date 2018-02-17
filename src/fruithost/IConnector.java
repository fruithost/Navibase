package fruithost;
import java.sql.Driver;
import java.util.HashMap;

public interface IConnector {
	public void setConnectionData(ConnectionController controller);
	public boolean checkConnection(Driver driver, HashMap<String, Object> data) throws Exception;
}
