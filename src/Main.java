import com.grooveshark.GSMethods;
import com.http.util.Constants;

public class Main {

	public static void main(String [] args) throws Exception{
		Constants.initializeConstants();
		GSMethods method = new GSMethods();
		method.startSession();
		//method.registerUser("rushabhone1@gmail.com", "1012sp12", "Rsshroff", method.getSessionId());

		//method.authenticate("shroffrushabh@gmail.com", 
		//		"", method.getSessionId());
		//method.logout(method.getSessionId());
		method.getCountry();
		method.getStreamKeyStreamServer(method.getSessionId(),33031232);
		method.markStreamKeyOver30Secs();
		
	}	
}
