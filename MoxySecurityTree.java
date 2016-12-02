// Moxy Security Tree

import java.util.*;

public class MoxySecurityTree {
		   
		// this will build the first column of the excel file which should show the function groups and functions

	private MoxyTreeNode root;
	MoxySecurityData moxySecurityData;
	MoxySecurityMatrix matrix;

	public MoxySecurityTree(MoxySecurityData data){

		//root = new MoxyTreeNode(0);  //creating the root node
		moxySecurityData = data;
		matrix = new MoxySecurityMatrix(moxySecurityData);
	}

	public void buildMoxySecurityTree() {

		Map<Integer, MoxyFunction> functionList = new TreeMap<Integer, MoxyFunction>(moxySecurityData.getFunctionList());
		Map<Integer, MoxyFunctionGroup> functionGroupList = new TreeMap<Integer, MoxyFunctionGroup>(moxySecurityData.getFunctionGroupList());
		Set<Map.Entry<Integer, MoxyFunction>> functionSet = functionList.entrySet();
		Set<Map.Entry<Integer, MoxyFunctionGroup>> functionGroupSet = functionGroupList.entrySet();

		for(Map.Entry<Integer, MoxyFunctionGroup> entryFunctionGroup : functionGroupSet) {
			MoxyFunctionGroup functionGroup = entryFunctionGroup.getValue();
			MoxyTreeNode node = new MoxyTreeNode(functionGroup);
			for(Map.Entry<Integer, MoxyFunction> entryFunction : functionSet) {  // need to loop through the functions in the group so they can be listed
					MoxyFunction function = entryFunction.getValue();
					if(function.getFunctionGroupID() == functionGroup.getMoxyFunctionGroupID()) 
						node.addFunction(function);
			}	
			if(root == null)
				root = node;	
			else
				root.insertNode(node);
		}
	}

	public void printTree() {
		root.printTree(root);
	}

	public void buildMatrix() {

		matrix.addFSGRowToMatrix();
		root.buildMatrix(matrix, root, 0);
	}

		
	public MoxySecurityMatrix getMatrix() {

		return matrix;
	}

	public void printMatrix() {
		matrix.printFirstMatrixColumn();
	}
	
			

	 public static void main(String args[]) {
		
		MoxySecurityTree tree;
		MoxySecurityConnect moxyConnect = new MoxySecurityConnect();
		MoxySecurityData dataTest;

		moxyConnect.setServerIP("10.2.145.20");
		moxyConnect.setDBName("Moxy");
		moxyConnect.setUserID("sa");
		moxyConnect.setUserPassword("!Advent1");
		
		moxyConnect.startConnection();
		
		dataTest = moxyConnect.getMoxySecurityData();
		tree = new MoxySecurityTree(dataTest);
		tree.buildMoxySecurityTree();
		tree.printTree();
		tree.buildMatrix();
	}
}