
import java.util.*;

public class MoxySecurityData {

// MoxySecurityData will hold the database data
// This class will not organize the data (see MoxySecurityMatrix)

		private HashMap<Integer, MoxyFunction> functionList;
		private HashMap<Integer, MoxyFunctionGroup> functionGroupList;
		private HashMap<Integer, MoxyFSG> fsgList;
				
		public MoxySecurityData(){
			
			fsgList = new HashMap<Integer, MoxyFSG>();
			functionGroupList = new HashMap<Integer, MoxyFunctionGroup>();
			functionList = new HashMap<Integer, MoxyFunction>();
		};
		   
		public void addFunctionAccessRight(MoxyFunctionAccessRight functionRight) {
			
			// need to get the proper FSG
			//System.out.println("Adding the function right.  The function id is: " + functionRight.getFunctionID() );
			MoxyFSG fsg = this.getMoxyFSG(functionRight.getFSGID());
			fsg.addFunctionRight(functionRight);
		}
		   		   
		public void addFunction(MoxyFunction function) {

			if(function.getFunctionID() != -1)
				functionList.put(function.getFunctionID(), function);
		}

		
		public void addFunctionGroup(MoxyFunctionGroup functionGroup) {

			functionGroupList.put(functionGroup.getMoxyFunctionGroupID(), functionGroup);
		}
		
		public void addFSG(MoxyFSG fsg) {
			   
			fsgList.put(fsg.getMoxyFSGID(),fsg);
			System.out.println("Adding FSG: " + fsg.getMoxyFSGName());
		}
		   
		public HashMap<Integer, MoxyFunction> getFunctionList() {
			return functionList;
		}
		
		public HashMap<Integer, MoxyFunctionGroup> getFunctionGroupList() {
			return functionGroupList;
		}
		
		public MoxyFSG getMoxyFSG(int id) {
			
			return fsgList.get(id);
		}
		
		public int getNumberOfFSG() {
			
			return fsgList.size();
		}
		
				   
		public HashMap<Integer, MoxyFSG> getFSGList() {
			return fsgList;
		}      

		public void addFunctionGroupGeneration() {
			// assumes max 3 generations - probably should implement recursion for this
			Set<Map.Entry<Integer, MoxyFunctionGroup>> functionGroupSet = functionGroupList.entrySet();
			for(Map.Entry<Integer, MoxyFunctionGroup> entryFunctionGroup : functionGroupSet) {
				int parent = entryFunctionGroup.getValue().getMoxyFunctionGroupParentID();
				if(parent == 0)
					entryFunctionGroup.getValue().setGeneration(0);  // 1st generation
				else {
					parent = entryFunctionGroup.getValue().getMoxyFunctionGroupParentID(); 
					if(parent == 0)
						entryFunctionGroup.getValue().setGeneration(1); // 2nd generation 
				 	else {
						parent = entryFunctionGroup.getValue().getMoxyFunctionGroupParentID();
						if(parent == 0)
							entryFunctionGroup.getValue().setGeneration(2);  //3rd generation
						else 
							entryFunctionGroup.getValue().setGeneration(3);  // 4th generation
				 	}
				}
			}
		}

		
		
		public void printFunctionData() {

			Map<Integer, MoxyFunction> functionListTree = new TreeMap<Integer, MoxyFunction>(functionList);
			Set<Map.Entry<Integer, MoxyFunction>> functionSet = functionListTree.entrySet();
			for(Map.Entry<Integer, MoxyFunction> entryFunction : functionSet) {
					MoxyFunction test = entryFunction.getValue();
					System.out.println("Function id: " + test.getFunctionID() + " Function Name: " + test.getFunctionName() );
			}
		}

		public void printFunctionGroupData() {

			System.out.println("FunctionGroupSize is " + functionGroupList.size());
			Set<Map.Entry<Integer, MoxyFunctionGroup>> functionGroupSet = functionGroupList.entrySet();

			for(Map.Entry<Integer, MoxyFunctionGroup> entryFunctionGroup : functionGroupSet) {
			
					MoxyFunctionGroup test = entryFunctionGroup.getValue();
					System.out.println("Function Group id: " + test.getMoxyFunctionGroupID() + " Fuctnion Name: " + test.getMoxyFunctionGroupName() );
			}
		}		

		public void printFSGData() {

			Set<Map.Entry<Integer, MoxyFSG>> set = fsgList.entrySet();
			
			for(Map.Entry<Integer, MoxyFSG> entry : set) {
				// MoxyFSG test = fsgList.get(i);
				System.out.println("FSG id: " + entry.getKey() + " FSG Name: " + entry.getValue());
			}
		}

		public int getMinFunctionKey() {
			
			Integer min;
			
			min = Collections.min(functionList.keySet());
			return min.intValue();
		}

		
		public int getMaxFunctionKey() {
			
			Integer max;
			
			max = Collections.min(functionList.keySet());
			return max.intValue();
		}

		public int getMinFunctionGroupKey() {
			
			Integer min;
			
			min = Collections.min(functionGroupList.keySet());
			return min.intValue();
		}

		
		public int getMaxFunctionGroupKey() {
			
			Integer max;
			
			max = Collections.min(functionGroupList.keySet());
			return max.intValue();
		}

		public static void main(String args[]) {

		MoxySecurityConnect moxyConnect = new MoxySecurityConnect();
		MoxySecurityData dataTest;

		moxyConnect.setServerIP("10.2.145.20");
		moxyConnect.setDBName("Moxy");
		moxyConnect.setUserID("sa");
		moxyConnect.setUserPassword("!Advent1");
		
		moxyConnect.startConnection();
		
		dataTest = moxyConnect.getMoxySecurityData();
		dataTest.printFunctionData();


		}
}