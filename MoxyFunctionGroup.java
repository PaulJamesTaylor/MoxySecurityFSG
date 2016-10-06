import java.util.*;

public class MoxyFunctionGroup {
	 
	
	int moxyFunctionGroupID;
	String moxyFunctionGroupName;
    ArrayList<MoxyFunction> functionList;
	 		   
	public MoxyFunctionGroup(){};
		   
	public MoxyFunctionGroup(int id) {
			   
		moxyFunctionGroupID = id;
		this.setMoxyFunctionGroupName(id);
		functionList = new ArrayList<MoxyFunction>();
	}
	
	public ArrayList<MoxyFunction> getFunctionList() {
			   return functionList;
		   }
		
    private void setMoxyFunctionGroupName(int id) {
		
		switch(id) {
			case 2: moxyFunctionGroupName = "Create and Approve";
					break;
			case 3: case 4: moxyFunctionGroupName = "Trade";
					break;
			case 5: case 6: case 7: moxyFunctionGroupName = "Modify";
					break;
			case 8: case 9: case 10: moxyFunctionGroupName = "Allocate";
					break;
			case 11: case 12: moxyFunctionGroupName = "Portfolio Access";
					break;
			case 14: moxyFunctionGroupName = "New Orders";
					break;
			case 15: moxyFunctionGroupName = "Order Out of Balance";
					break;
			case 16: moxyFunctionGroupName = "Broker Conflicts";
					break;
			case 17: moxyFunctionGroupName = "Miscellaneious";
					break;
			case 18: case 22: case 23: case 24: case 25: case 26: case 27: case 28: moxyFunctionGroupName = "Restriction Checking";
					break;
			case 30: case 31: case 32: case 33: moxyFunctionGroupName = "Ticket Reports";
					break;
			case 34: moxyFunctionGroupName = "Order Reports";
					break;
			case 36: case 37: moxyFunctionGroupName = "Internal Reports";
					break;
			case 38: moxyFunctionGroupName = "Portfolio Reports";
					break;
			case 39: moxyFunctionGroupName = "Audit Reports";
					break;
			case 41: case 42: case 43: case 44: case 45: case 46: moxyFunctionGroupName = "System Reports";
					break;
			case 47: moxyFunctionGroupName = "Administrator";
					break;
			case 48: case 49: case 50: case 51: moxyFunctionGroupName = "System Tables";
					break;
			default: moxyFunctionGroupName = "invalid";
					break;
		}
	}		
	
	public String getMoxyFunctionGroupNameByID(int id) {
		
		String mfgName;
		
		switch(id) {
			case 2: mfgName = new String("Create and Approve");
					break;
			case 3: case 4: mfgName = new String("Trade");
					break;
			case 5: case 6: case 7: mfgName = new String("Modify");
					break;
			case 8: case 9: case 10: mfgName = new String("Allocate");
					break;
			case 11: case 12: mfgName = new String("Portfolio Access");
					break;
			case 14: mfgName = new String("New Orders");
					break;
			case 15: mfgName = new String("Order Out of Balance");
					break;
			case 16: mfgName = new String("Broker Conflicts");
					break;
			case 17: mfgName = new String("Miscellaneious");
					break;
			case 18: case 22: case 23: case 24: case 25: case 26: case 27: case 28: mfgName = new String("Restriction Checking");
					break;
			case 30: case 31: case 32: case 33: mfgName = new String("Ticket Reports");
					break;
			case 34: mfgName = new String("Order Reports");
					break;
			case 36: case 37: mfgName = new String("Internal Reports");
					break;
			case 38: mfgName = new String("Portfolio Reports");
					break;
			case 39: mfgName = new String("Audit Reports");
					break;
			case 41: case 42: case 43: case 44: case 45: case 46: mfgName = new String("System Reports");
					break;
			case 47: mfgName = new String("Administrator");
					break;
			case 48: case 49: case 50: case 51: mfgName = new String("System Tables");
					break;
			default: mfgName = new String("invalid");
					break;
		}
		
		return mfgName;
	}
	
	public String getMoxyFunctionGroupName() {
	    
		return moxyFunctionGroupName;
	}
	
	 public void addMoxyFunction(MoxyFunction function) {
		
		//ArrayList<MoxyFunction> functionList = new ArrayList<MoxyFunction>();	
		
		functionList.add(function);
			   
    }
		   
    public MoxyFunction getMoxyFunction(int i) {
			   
	    return functionList.get(i);
	}
	
	public String getFunctionAccessRight(String functionName) {
		
		//System.out.println("Now in MoxyFunctionGroup.getFunctionAccessRight.  The function name is: " + functionName + "test");
		//this.printFunctionGroupData();
		
		for(MoxyFunction functionIterator : functionList)  {
			//System.out.println("Now in the MoxyFunctionGroup.getFunctionAccessRight for loop.  Name is: " + functionIterator.getFunctionName());
			
			if(functionIterator.getFunctionName().replace(" ", "").equalsIgnoreCase(functionName.replace(" ", ""))) {  // need to put a regular expression here
				return functionIterator.getMoxyAccessRight();
			}
		}	
		
		return null;  //no function found with that name
	}
	
	public void printFunctionGroupData() {
			
		for(MoxyFunction function : functionList) {
			
			System.out.println("The Moxy Function Group is " + moxyFunctionGroupName);
			System.out.println(" ");
			function.printFunctionData();
			
		}
			
	}
	
	
		   
	public static void main(String args[]) {
			   
	    MoxyFunction mf = new MoxyFunction();
		MoxyFunction mf2 = new MoxyFunction();
		
		MoxyFunction testFunction;
		
		mf.setFunctionName("Approve Own Trades ");
		mf.setMoxyFunctionDescription("Controls whether users can approve orders they create.  When disabled, orders must be approved by an independent user.");
		mf.setMoxyAccessRight("N");
		
		mf2.setFunctionName("Create Strategies");
		mf2.setMoxyFunctionDescription("The ability to create orders using an Allocation Strategy or Modeling.");
		mf2.setMoxyAccessRight("Y");
		
		MoxyFunctionGroup mfg = new MoxyFunctionGroup(3);
		mfg.addMoxyFunction(mf);
		mfg.addMoxyFunction(mf2);
		
		System.out.println("Testing MoxyFunctionGroup");
		System.out.println("The name of the Moxy Function Group is: " + mfg.getMoxyFunctionGroupName());
		System.out.println("The name of the Moxy Function Group by id is: " + mfg.getMoxyFunctionGroupNameByID(3));
		
		mfg.printFunctionGroupData();

		String test1 = mfg.getFunctionAccessRight("Create Strategies");
		String test2 = mfg.getFunctionAccessRight("test");
		String test3 = mfg.getFunctionAccessRight("Approve Own Trades");
		
		System.out.println("test1 is " + test1);
		System.out.println("test2 is " + test2);
		System.out.println("test3 is " + test3);
		
		//String accessRight = test.getFunctionAccessRight(functionName);
	}
	
}