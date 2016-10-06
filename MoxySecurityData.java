
import java.util.*;

public class MoxySecurityData {
		   
		private ArrayList<MoxyFSG> fsgList;
		   
		public MoxySecurityData(){
			fsgList = new ArrayList<MoxyFSG>();
		};
		   
		   		   
		public void addFSG(MoxyFSG fsg) {
			   
			fsgList.add(fsg);
		}
		   
		public MoxyFSG getMoxyFSG(int i) {
			
			return fsgList.get(i);
		}
		
		public int getNumberOfFSG() {
			
			return fsgList.size();
		}
		
		public MoxyFSG getMoxyFSG(String fsgName) {
			
			//System.out.println("Inside getMoxyFSG, the fsgName is: " + fsgName);
			
			for(MoxyFSG test : fsgList) {
				//System.out.println("Inside the for loop.  The name is of the test iterator fsg is: " + test.getMoxyFSGName());
				if(fsgName.replace(" ", "").equalsIgnoreCase(test.getMoxyFSGName().replace(" ", ""))) 
					return test;
			}
			
			return null;
		}
		
		public void printFSGList() {
			//System.out.println("This list of FSG");
			for(MoxyFSG test : fsgList) {
				System.out.println("The Moxy FSg is: " + test.getMoxyFSGName());
			}
		}
		
		   
		public ArrayList<MoxyFSG> getFSGList() {
			return fsgList;
		}      
		   
	    public MoxyFSG fsgExists(String fsgName) {
			
			
			
			// this assumes the FSG exists
			for(MoxyFSG test : fsgList) {
				if(fsgName.equals(test.getMoxyFSGName())) {
					
					return test;
				}	
			
			}
			
			return null;
		}


		
		public void addFunctionToFSG(String fsgName, int groupID, MoxyFunction function) {
			
			MoxyFSG test;
			test = this.fsgExists(fsgName);
			
			// if FSG exists then a pointer will be returned, meaning not null and it doesn't need to be added only the function needs to be added
			// if the FSG does not exist a null will be returned and it needs to be created
			if(test!= null) {  //fsg exists, need to add it to the list
				
				test.addFunctionToFSG(groupID, function);
			}
			
			else {  //FSG does not exists 
				
				MoxyFSG newFSG = new MoxyFSG(fsgName);
				//System.out.println("The FSG is created");
				newFSG.addFunctionToFSG(groupID, function);
				//System.out.println("The function is added");
				this.addFSG(newFSG);
			}
		}
		
		public void insertSecurityRecord(String fsgName, int moxyFunctionGroupID, String moxyFunctionName, String description, String accessRight) {
			
			// assume no duplicate functions returned from database			
			MoxyFunction function = new MoxyFunction(moxyFunctionName, description, accessRight);
			this.addFunctionToFSG(fsgName, moxyFunctionGroupID, function);
		}
		
		
		public void printSecurityData() {
			
			for(MoxyFSG fsg : fsgList) 
				fsg.printFSGData();
			
			System.out.println("There are " + fsgList.size() + " FSGs in the list");
			
			for(MoxyFSG fsg : fsgList) 
				System.out.println("FSG: " + fsg.getMoxyFSGName());
		
		}
		
		
		public String getAccessRight(String fsgString, String function) {
			//System.out.println("Do I get to MoxySecurityData.getAccessRight()?" + " The fsgString var is: " + fsgString);
			MoxyFSG fsgObject = this.getMoxyFSG(fsgString);
			//this.printFSGList();
			if(fsgObject == null) 
				System.out.println("fsgObject is null");
			//else 
					
				//System.out.println("fsgObject is not null");
				//System.out.println("Test: " + fsgObject.getMoxyFSGName());
				//System.out.println("Do we get into MoxySecurityData");
				//System.out.println("The FSG is returned by MSD.getAccessRight - the name is " + fsgObject.getMoxyFSGName());
			
			if(fsgObject != null)
				return fsgObject.getFunctionAccessRight(function);
			else
				return null;
		}
		
		public static void main(String args[]) {
			   
			String fsgName = new String("System Defaults");
			int groupID = 2;
			String functionName = new String("Approve Own Trades");
			String functionDescription = new String("Controls whether users can approve orders");
			String accessRight = new String("Y");
						
			String fsgName2 = new String("System Administrator");
			
			MoxySecurityData moxySecurityData = new MoxySecurityData();
			moxySecurityData.insertSecurityRecord(fsgName, groupID, functionName, functionDescription, accessRight);
			moxySecurityData.insertSecurityRecord(fsgName2, groupID, functionName, functionDescription, accessRight);
			
			String test = moxySecurityData.getAccessRight("System Defaults", "Approve Own Trades");
			
			moxySecurityData.printSecurityData();
			System.out.println("The access right is " + test);
			
		}
}