
import java.util.*;

public class MoxyTreeNode {
    
    private int id;
    private int parentID;
    private ArrayList<MoxyTreeNode> children;
    private MoxyTreeNode parent;
    Map<Integer, MoxyFunction> functionList;
    MoxyFunctionGroup functionGroup;

    public MoxyTreeNode(int id) {
        this.id = id;
        children = new ArrayList<MoxyTreeNode>();
        this.parent = null;
        functionList = new TreeMap<Integer, MoxyFunction>();
    }

    public MoxyTreeNode(int id, int parent) {
        this.id = id;
        this.parentID = parent;
        children = new ArrayList<MoxyTreeNode>();
        this.parent = null;
        functionList = new TreeMap<Integer, MoxyFunction>();
    }

    public MoxyTreeNode(MoxyFunctionGroup group) {

        functionGroup = group;
        this.id = functionGroup.getMoxyFunctionGroupID();
        this.parentID = functionGroup.getMoxyFunctionGroupParentID();
        children = new ArrayList<MoxyTreeNode>();
        this.parent = null;
        functionList = new TreeMap<Integer, MoxyFunction>();
    }

    public MoxyFunctionGroup getMoxyFunctionGroup() {

        return functionGroup;
    }
    
    public void addChild(MoxyTreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addFunction(MoxyFunction function) {
        this.functionList.put(function.getFunctionID(), function);
    }

    public MoxyFunction getFunction(int i) {
        return functionList.get(i);
    }


    /*
    public void addChildren(ArrayList<MoxyTreeNode> children) {
        for(MoxyTreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }
    */

    public ArrayList<MoxyTreeNode> getChildren() {
        return children;
    }

    public int getID() {
        return id;
    }

    private void setParent(MoxyTreeNode parent) {
        this.parent = parent;
    }

    public MoxyTreeNode getParent() {
        return parent;
    }

    public int getParentID() {
        return parentID;
    }

    public void insertNode(MoxyTreeNode child) {

        MoxyTreeNode pointer = this.getNode(child.getParentID());
        pointer.addChild(child);
    }
    
    MoxyTreeNode getNode(int id) {

        // System.out.println("the value of id is: " + this.id);
        if(this.id == id)
            return this;
        
        for(MoxyTreeNode test: this.getChildren()) {
            MoxyTreeNode temp = test.getNode(id);
            if(temp != null)
                return temp;
        }
        return null;
    }

    public void printFunctionList() {
        	
        Set<Map.Entry<Integer, MoxyFunction>> functionSet = functionList.entrySet();
		for(Map.Entry<Integer, MoxyFunction> entryFunction : functionSet) {
			MoxyFunction function = entryFunction.getValue();
            System.out.println("The function is: " + function.getFunctionName());
        }
    }
    
    public void addFunctionToMatrix(MoxySecurityMatrix matrix) {

        Set<Map.Entry<Integer, MoxyFunction>> functionSet = functionList.entrySet();
		for(Map.Entry<Integer, MoxyFunction> entryFunction : functionSet) {
			MoxyFunction function = entryFunction.getValue();
            //System.out.println("The function is: " + function.getFunctionName() + " The function id is: " + function.getFunctionID());
            MatrixCell cell;
            cell = new MatrixCell(function.getFunctionName(), 9);
            matrix.addCell(cell);
            matrix.addAccessRightsToMatrix(function);
            matrix.incrementRow();
        }
    }

    public void buildMatrix(MoxySecurityMatrix matrix, MoxyTreeNode node, int depth) {
        
        MatrixCell cell;
        cell = new MatrixCell(node.getMoxyFunctionGroup().getMoxyFunctionGroupName(), depth);
        matrix.addCell(cell);
        matrix.incrementRow();

        if(node.getChildren().size() == 0)
           ;
        else 
            for(MoxyTreeNode test: node.getChildren()) {
               // System.out.println("In the for loop the id of the node is: " + test.getID());
                this.buildMatrix(matrix, test, depth+1);
            }
         
        node.addFunctionToMatrix(matrix);
        
    }
   
    public void printTree(MoxyTreeNode node) {

        System.out.println("Node id is: " + node.getMoxyFunctionGroup().getMoxyFunctionGroupName() + " Parent id is: " + node.getParentID());
        
        if(node.getChildren().size() == 0)
           ;
        else
            for(MoxyTreeNode test: node.getChildren()) {
               // System.out.println("In the for loop the id of the node is: " + test.getID());
                this.printTree(test);
        }
        node.printFunctionList();
    }
        
    public static void main(String args[]) {
			   
        MoxyTreeNode root = new MoxyTreeNode(0);
        MoxyTreeNode child1 = new MoxyTreeNode(1,0);
        MoxyTreeNode child2 = new MoxyTreeNode(2,1);
        
        root.insertNode(child1);
        root.insertNode(child2);

        MoxyFunction function1 = new MoxyFunction(1, 1, "test1", "test1description");
        MoxyFunction function2 = new MoxyFunction(2, 1, "test2", "test2description");

        child1.addFunction(function1);
        child1.addFunction(function2);

        root.printTree(root);
    }
}