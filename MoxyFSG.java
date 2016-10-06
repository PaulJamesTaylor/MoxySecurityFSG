
import java.util.*;

public class MoxyFSG {
		   
		   private String MoxyFSGName;
		   private ArrayList<MoxyFunctionGroup> functionGroupList;
		   
		   public MoxyFSG(){};
		   
		   public MoxyFSG(String name) {
			   
			   MoxyFSGName = name;
			   functionGroupList = new ArrayList<MoxyFunctionGroup>();
		   }
		   
		   public String getMoxyFSGName() {
			   return MoxyFSGName;
			   
		   }
		   
		   public String getFunctionAccessRight(String functionName) {
			   
				//System.out.println("the function passed to MoxyFSG.getFunctionAccessRight is: " + functionName);
			
				for(MoxyFunctionGroup test : functionGroupList) {
					//System.out.println("Now in the MoxyFSG.getFunctionAccessRight loop");
					//System.out.println("This is an FSG with function groups.  The number of function groups in this fsg is: " + functionGroupList.size());
					//System.out.println("The function group in this list is: " + test.getMoxyFunctionGroupName());
					String accessRight = test.getFunctionAccessRight(functionName);	
					
					//System.out.println("Inside MoxyFSG.getFunctionAccessRight, The access right is: " + accessRight);
					
					if(accessRight != null) 
						return accessRight;
					//System.out.println("Do I make it here?");
				}
				
			   return null;
		   }
		   
		   public MoxyFunctionGroup functionGroupExists(int GroupID) {
			
			
			for(MoxyFunctionGroup test : functionGroupList) {
				if(test.getMoxyFunctionGroupNameByID(GroupID).equals(test.getMoxyFunctionGroupName())); 
					return test;
			}
			
			return null;  // no group of that id exists
		}


		
		public void addFunctionToFSG(int groupID, MoxyFunction function) {
			
			MoxyFunctionGroup test;
			test = this.functionGroupExists(groupID);
			
			if(test!= null) {  //function group exists within this fsg, need to add function to the group
				test.addMoxyFunction(function);
			}
			
			else {  //MoxyFunctionGroup does not exists 
			
				MoxyFunctionGroup newMoxyFunctionGroup = new MoxyFunctionGroup(groupID);   // create the function group
				newMoxyFunctionGroup.addMoxyFunction(function);     // add the function to the function group
				this.addFunctionGroup(newMoxyFunctionGroup);   // add the function group to the fsg
			}
		}
		   
		   
		   
		   
		   public void addFunctionGroup(MoxyFunctionGroup functionGroup) {
			   // need to check if exists
			
				boolean exist = false;
			
				for(MoxyFunctionGroup test : functionGroupList) {
					if(functionGroup.getMoxyFunctionGroupName().equals(test.getMoxyFunctionGroupName())) { 
						exist = true;
					}
				}
			
				if(!exist) {  //FSG does not exists 
			
					functionGroupList.add(functionGroup);
				}
			}

			
		   public void addMoxyFunction(MoxyFunction function, String groupName) {
				
				for(MoxyFunctionGroup test : functionGroupList) {
					if(test.getMoxyFunctionGroupName().equals(groupName)) { 
						test.addMoxyFunction(function);
					}
				}	
		   }
		   
		   
		   
		   
		   
		   public MoxyFunctionGroup getFunctionGroup(int i) {
			   
			   return functionGroupList.get(i);
		   }
		   
		   public ArrayList<MoxyFunctionGroup> getFunctionGroupList() {
			   return functionGroupList;
		   }
		   
		   public void printFSGData() {
			
				for(MoxyFunctionGroup functionGroup : functionGroupList) {
					
					System.out.println("The FSG is: " + MoxyFSGName);
					functionGroup.printFunctionGroupData();
				}
					
			}
		   
		   
		public static void main(String args[]) {
			   
			// 1) create 3 moxy functions
			// 2) create 2 Moxy function groups
			// 3) add the moxy functions to the function groups - 2 to the first group and one to the second
			// 4) create a MoxyFSG
			// 5) add the two function groups to the FSG
			// 6) get the access right by passing the FSG the function name
			
			MoxyFunction mf = new MoxyFunction();
			MoxyFunction mf2 = new MoxyFunction();
			MoxyFunction mf3 = new MoxyFunction();
			
			MoxyFunctionGroup testFunctionGroup; 
			
			mf.setFunctionName("Approve Own Trades");
			mf.setMoxyFunctionDescription("Controls ");
			mf.setMoxyAccessRight("Y");
			
			mf2.setFunctionName("Create Strategies");
			mf2.setMoxyFunctionDescription("The ability .");
			mf2.setMoxyAccessRight("Y");
			
			mf3.setFunctionName("Close Orders");
			mf3.setMoxyFunctionDescription("Place and Fill Orders");
			mf3.setMoxyAccessRight("N");
			
			MoxyFunctionGroup mfg = new MoxyFunctionGroup(2);
			MoxyFunctionGroup mfg2 = new MoxyFunctionGroup(3);
			
			mfg.addMoxyFunction(mf);
			mfg.addMoxyFunction(mf2);
			mfg2.addMoxyFunction(mf3);
			
			MoxyFSG fsg = new MoxyFSG("System Defaults");
			fsg.addFunctionGroup(mfg);
			fsg.addFunctionGroup(mfg2);
			
			// fsg.addMoxyFunction(mf2, mfg.getMoxyFunctionGroupName());  //testing adding function in MoxyFSG
			
			System.out.println("The FSG name is: " + fsg.getMoxyFSGName());
			//testFunctionGroup = fsg.getFunctionGroup(0);
			
		
			System.out.println("Testing returning the array list");
			ArrayList<MoxyFunctionGroup> testArray;
			testArray = fsg.getFunctionGroupList();
			System.out.println("size of array is " + testArray.size());
			
						
			fsg.printFSGData(); 
			
			String accessRight = fsg.getFunctionAccessRight("Close Orders");
			System.out.println("The access right returned is " + accessRight);
			
		}
}