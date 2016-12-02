
import java.util.*;

public class MoxyFSG {
		   
		   private int MoxyFSGID;
		   private String MoxyFSGName;
		   private HashMap<Integer, MoxyFunctionAccessRight> functionRightList;
		   
		   public MoxyFSG(){};
		   
		   public MoxyFSG(int id, String name) {
			   
			   MoxyFSGID = id;
			   MoxyFSGName = name;
			   functionRightList = new HashMap<Integer, MoxyFunctionAccessRight>();
		   }
		   
		   public int getMoxyFSGID() {
			   return MoxyFSGID;
		   }
		   
		   public String getMoxyFSGName() {
			   return MoxyFSGName;
			   
		   }
		   
		public String getFunctionAccessRight(int functionID) {
							
			MoxyFunctionAccessRight accessRight;
			
			if(functionRightList.containsKey(functionID)) {
				accessRight = functionRightList.get(functionID);
				return accessRight.getAccessRight();
			}
			else
				return null;
		}
		   
				
		public void addFunctionRight(MoxyFunctionAccessRight functionRight) {
			
			functionRightList.put(functionRight.getFunctionID(), functionRight);
		}
		   		   
		public boolean hasFunction(int id) {

			if(functionRightList.containsKey(id))
				return true;
			else
				return false;
		}  
		public MoxyFunctionAccessRight getMoxyFunction(int i) {
			   
			   return functionRightList.get(i);
		}
		   
		   
		public int getfunctionRightListSize() {

			return functionRightList.size();

		}
		
		
		   
		   
		public static void main(String args[]) {
			   
			// 1) create 3 moxy functions
			// 2) create 2 Moxy function groups
			// 3) add the moxy functions to the function groups - 2 to the first group and one to the second
			// 4) create a MoxyFSG
			// 5) add the two function groups to the FSG
			// 6) get the access right by passing the FSG the function name
			
			
			
			System.out.println("Test " );
			
		}
}