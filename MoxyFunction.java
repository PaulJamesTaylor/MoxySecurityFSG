import java.io.FileWriter;

public class MoxyFunction {
	 
	String functionName;
    String functionDescription;
	String accessRight;
	 		   
	public MoxyFunction(){};
	
	public MoxyFunction(String name, String description, String right) {
		// need to add group id
		functionName = name;
		functionDescription = description;
		accessRight = right;
	}
		   
	public String getFunctionName() {
	    
		return functionName;
	}
	
	public String getMoxyFunctionDescription() {
			   
	    return functionDescription; 
	}
	
		
	public void setFunctionName(String name) {
			   
		functionName = name;
			   
    }
		   
    public void setMoxyFunctionDescription(String description) {
			   
	    functionDescription = description; 
	}
	
	public void setMoxyAccessRight(String right) {
	
		accessRight = right;
	}
	
	public String getMoxyAccessRight() {
	
		return accessRight;
	}
		
	void printFunctionData() {
		
	
		System.out.println("Function Name is: " +  functionName + ", " );
		System.out.println("Function description is: " + functionDescription + ", ");
		System.out.println("Function access is: " + accessRight);
	
	}
	
	
	public static void main(String args[]) {
			   
	    MoxyFunction mf = new MoxyFunction();
		mf.setFunctionName("Approve Own Trades");
		mf.setMoxyFunctionDescription("Controls whether users can approve orders they create. ");
		mf.setMoxyAccessRight("N");
		
		System.out.println("Testing MoxyFunction Class");
		
		String test = mf.getMoxyAccessRight();
		
		System.out.println("The Moxy access right is: " + test);
		
		mf.printFunctionData();
		
		System.out.println("End of test");
	}
	
}