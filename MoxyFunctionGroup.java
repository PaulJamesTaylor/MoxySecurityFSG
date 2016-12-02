import java.util.*;

public class MoxyFunctionGroup {
	 
	
	private int moxyFunctionGroupID;
	private int moxyFunctionGroupParentID;
	private String moxyFunctionGroupName;
	private String moxyFunctionGroupDescription;
	private int generation;
    
	 		   
	public MoxyFunctionGroup(){};
		   
	public MoxyFunctionGroup(int id, String functionGroupName, int parentID,  String functionGroupDescription) {
			   
		moxyFunctionGroupID = id;
		moxyFunctionGroupParentID = parentID;
		moxyFunctionGroupName = functionGroupName;
		moxyFunctionGroupDescription = functionGroupDescription;
		
	}
	
	public int getMoxyFunctionGroupID() {

		return moxyFunctionGroupID;
	}	
    	
	public int getMoxyFunctionGroupParentID() {

		return moxyFunctionGroupParentID;
	}	
    
	public String getMoxyFunctionGroupName() {
	    
		return moxyFunctionGroupName;
	}

    public String getMoxyFunctionGroupDescription() {
	    
		return moxyFunctionGroupName;
	}

	public int getMoxyFunctionGroupGeneration() {
	    
		return generation;
	}

	public void setGeneration(int g) {
		generation = g;
	}

	
	
	public void printFunctionGroupData() {
			
		System.out.println("The Moxy Function Group id is " + moxyFunctionGroupID);
		System.out.println("The Moxy Function Group Name is: " + moxyFunctionGroupName);
	}
			

	
	
		   
	public static void main(String args[]) {
			   
		System.out.println("test1 is ");
		//String accessRight = test.getFunctionAccessRight(functionName);
	}
	
}