import java.io.FileWriter;

public class MoxyFunction {
	 
	int functionID;
	int functionGroupID;
	String functionName;
    String functionDescription;
	String functionStringID;
	int displayOrder;

	 		   
	public MoxyFunction(){};
	
	public MoxyFunction(int id, int groupID, String name, String description) {
		// need to add group id
		functionID = id;
		functionGroupID = groupID;
		functionName = name;
		functionDescription = description;
	}
		   
	public int getFunctionID() {
	    
		return functionID;
	}

	public int getFunctionGroupID() {
	    
		return functionGroupID;
	}

	public String getFunctionName() {
	    
		return functionName;
	}
	
	public String getMoxyFunctionDescription() {
			   
	    return functionDescription; 
	}
	
	public void setFunctionID(int id) {
			   
		functionID = id;
	}

	public void setFunctionGroupID(int groupID) {
			   
		functionGroupID = groupID;
	}
	
	public void setFunctionName(String name) {
			   
		functionName = name;
	}
		   
    public void setMoxyFunctionDescription(String description) {
			   
	    functionDescription = description; 
	}
			
	void printFunctionData() {
		
		System.out.println("Function id is: " + functionID);
		System.out.println("Function Group ID is: " + functionGroupID);	
		System.out.println("Function Name is: " +  functionName + ", " );
		System.out.println("Function description is: " + functionDescription + ", ");
	}
	
	
	public static void main(String args[]) {
			   
	  
		System.out.println("End of test");
	}
	
}