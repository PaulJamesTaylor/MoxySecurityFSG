import java.io.FileWriter;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.Console;

public class MoxySecurityMatrix {
	 
	MoxySecurityData moxySecurityData;  // this is where the data is
	MatrixCell[][] moxySecurityGrid;  // the grid of cells
	private int matrixRowSize;
	private int matrixColumnSize;
	int currentRow;
	int currentColumn;
	
	
	public MoxySecurityMatrix(MoxySecurityData data){  // constructor

		moxySecurityData = data;
		System.out.println("In constructor");
		matrixRowSize = moxySecurityData.getFunctionGroupList().size() + moxySecurityData.getFunctionList().size() + 3;
		matrixColumnSize = moxySecurityData.getNumberOfFSG() + 1;
		System.out.println("The number of FSG is: " + matrixColumnSize);
		moxySecurityGrid = new MatrixCell[matrixRowSize][matrixColumnSize];
		currentRow = 0;
		currentColumn = 0;
	}
	
	public int getCurrentRow() {
		return currentRow;
	}
	
	
	public int getCurrentColumn() {
		return currentColumn;
	}

	public void setCurrentRow(int i) {
		currentRow = i;
	}

	
	public void setCurrentColumn(int i) {
		currentColumn = i;
	}

	public void incrementRow() {
		currentRow++;
	}


	public void incrementColumn() {
		currentColumn++;
	}

	public int getRowSize() {

		return matrixRowSize;
	}
	
	
	public int getColumnSize() {

		return matrixColumnSize;
	}
	
	public void addCell(MatrixCell cell) {

		moxySecurityGrid[currentRow][currentColumn] = cell;
	}

	
	public boolean hasCell(int row, int column) {

		MatrixCell cell = moxySecurityGrid[row][column];
		if(cell  == null)
			return false;
		else
			return true;
	}
	
	public String getCellValue(int row, int column) {

		MatrixCell cell = moxySecurityGrid[row][column];
		return cell.getCellString();
	}

	public int getCellType(int row, int column) {

		MatrixCell cell = moxySecurityGrid[row][column];
		return cell.getCellType();
	}

	public MatrixCell getCell(int row, int column) {
		
		return	moxySecurityGrid[row][column];
	}
  

	public void printFirstMatrixColumn() {

		for(int i=0;i<matrixRowSize;i++) {
			//System.out.println("In the printFirstMatrixColumn loop: " + i);
			if(moxySecurityGrid[i][0] != null)
				System.out.println(moxySecurityGrid[i][0].getCellString());
		}
	}

	
	public void printSecondMatrixColumn() {

		for(int i=0;i<246;i++) {
			//System.out.println("In the printFirstMatrixColumn loop: " + i);
			if(moxySecurityGrid[i][2] != null)
				System.out.println(moxySecurityGrid[i][1].getCellString());
		}
	}

	public void addFSGRowToMatrix() {
		
		int column = 1;
		this.currentRow = 1;
		
		Map<Integer, MoxyFSG> fsgList = new TreeMap<Integer, MoxyFSG>(moxySecurityData.getFSGList());
	  	Set<Map.Entry<Integer, MoxyFSG>> fsgSet = fsgList.entrySet();
		for(Map.Entry<Integer, MoxyFSG> entryFSG : fsgSet) {
			MoxyFSG fsg = entryFSG.getValue();
			//System.out.println("In addaccessrights.  FSG is: " + fsg.getMoxyFSGName() + " Function is: " + function.getFunctionID()); 
			String fsgName = fsg.getMoxyFSGName(); 
			MatrixCell cell;
            cell = new MatrixCell(fsgName, 7);
			//System.out.println("Current row is: " + currentRow + " Current column is: " + column);
            moxySecurityGrid[currentRow][column] = cell;
			//System.out.println("row: " + currentRow + " column: " + column + " value of cell is: " + moxySecurityGrid[currentRow][column].getCellString());
			column++;
        }
		this.currentRow++;
	}
	
	public void addAccessRightsToMatrix(MoxyFunction function) {
		int column = 1;
		Map<Integer, MoxyFSG> fsgList = new TreeMap<Integer, MoxyFSG>(moxySecurityData.getFSGList());
	  	Set<Map.Entry<Integer, MoxyFSG>> fsgSet = fsgList.entrySet();
		for(Map.Entry<Integer, MoxyFSG> entryFSG : fsgSet) {
			MoxyFSG fsg = entryFSG.getValue();
			//System.out.println("In addaccessrights.  FSG is: " + fsg.getMoxyFSGName() + " Function is: " + function.getFunctionID()); 
			if( (fsg.getFunctionAccessRight(function.getFunctionID())) != null) {
				String right = fsg.getFunctionAccessRight(function.getFunctionID()); 
				MatrixCell cell;
				cell = new MatrixCell(right, 8);
				//System.out.println("Current row is: " + currentRow + " Current column is: " + column);
				moxySecurityGrid[currentRow][column] = cell;
				//System.out.println("row: " + currentRow + " column: " + column + " value of cell is: " + moxySecurityGrid[currentRow][column].getCellString());
			}
			else  {
				String right = "U"; 
				MatrixCell cell;
				cell = new MatrixCell(right, 8);
				//System.out.println("Current row is: " + currentRow + " Current column is: " + column);
				moxySecurityGrid[currentRow][column] = cell;
				//System.out.println("row: " + currentRow + " column: " + column + " value of cell is: " + moxySecurityGrid[currentRow][column].getCellString());
			}
			column++;
        }
	}
	
	public static void main(String args[]) {
	
		MoxySecurityTree tree;
		MoxySecurityConnect moxyConnect = new MoxySecurityConnect();
		MoxySecurityData dataTest;
		MoxySecurityMatrix matrix;

		moxyConnect.setServerIP("10.2.145.20");
		moxyConnect.setDBName("Moxy");
		moxyConnect.setUserID("sa");
		moxyConnect.setUserPassword("!Advent1");
		
		moxyConnect.startConnection();
		
		dataTest = moxyConnect.getMoxySecurityData();
		tree = new MoxySecurityTree(dataTest);
		tree.buildMoxySecurityTree();
		tree.buildMatrix();
		matrix = tree.getMatrix();
		matrix.printSecondMatrixColumn();
	}
}



