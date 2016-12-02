// FunctionAcessRight
import java.io.FileWriter;

public class MoxyFunctionAccessRight {
	 
	int fsgID;
	int functionID;
	String accessRight;

	 		   
	public MoxyFunctionAccessRight(){};
	
	public MoxyFunctionAccessRight(int groupID, int fID, String right) {
		// need to add group id
		fsgID = groupID;
		functionID = fID;
		accessRight = right;
	}
		   
	public int getFSGID() {
	    
		return fsgID;
	}
	
	public int getFunctionID() {
	    
		return functionID;
	}


	public String getAccessRight() {
	    
		return accessRight;
	}
	
	public void setFunctionID(int id) {
			   
		functionID = id;
			   
    }

	public void setAccessRight(String right) {
			   
		accessRight = right;
			   
    }
		   
	public static void main(String args[]) {
		
		System.out.println("Test");
	}
	
}