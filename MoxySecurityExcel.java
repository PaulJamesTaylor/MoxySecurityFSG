import java.io.FileWriter;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.Console;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class MoxySecurityExcel {
	
	
	MoxySecurityMatrix matrix;
	private String filePath;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFCellStyle cellStyle1;
	private XSSFCellStyle cellStyle2;
	private XSSFCellStyle cellStyle3;
	private XSSFCellStyle cellStyle4;
	private XSSFCellStyle cellStyle5;
	private XSSFCellStyle cellStyle6;
	private XSSFCellStyle cellStyle7;
	private XSSFCellStyle cellStyle8;
	private XSSFCellStyle cellStyle9;
	private XSSFCellStyle cellStyle10;
	private XSSFCellStyle cellStyle11;
	private XSSFCellStyle cellStyle12;
	private XSSFFont cellFont;
	private XSSFRow currentRow;
	
	public MoxySecurityExcel(MoxySecurityMatrix matrixArg, String path){  // constructor
        
		matrix = matrixArg;
		filePath = path;
	}

	public void buildExcelFile() {
		
		
		System.out.println("The path of the excel file is: " + filePath);
	
		
		try {
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(excelFile);
			sheet = workbook.getSheetAt(0);
			this.setCellStyle();

			cellFont = workbook.createFont();
			for(int row = 0 ; row < matrix.getRowSize(); row++) {  
            	currentRow = sheet.createRow(row);
                for(int column = 0; column < matrix.getColumnSize(); column++) {
					if(matrix.hasCell(row, column)) {
						XSSFCell cell = currentRow.createCell(column);
                    	if (cell.getCellType() == Cell.CELL_TYPE_BLANK) 
							cell.setCellType(Cell.CELL_TYPE_STRING);
						int type = matrix.getCellType(row, column);
						String value = matrix.getCellValue(row,column);
						cell.setCellValue(value);
						cell.setCellStyle(this.getCellStyle(type, value));
					}
                }     
            }    
			  // end inner for loop
			sheet.autoSizeColumn(5);
			
			excelFile.close();
			FileOutputStream excelFileOutput = new FileOutputStream(new File(filePath));
			workbook.write(excelFileOutput);
			excelFileOutput.close();
			workbook.close();
        }

		catch(Exception e)
		{ 
				e.printStackTrace();
                System.out.println("Problem updating the excel file");
				System.out.println(e.getMessage()); 
				System.exit(0);   
		}
	}

	private void setCellStyle() {

		short indent1 = 1;
		short indent2 = 2;
		short indent3 = 3;
		short indent4 = 4;
		short indent5 = 5;
		short indent6 = 6;
		short indent7 = 7;

		cellStyle1 = workbook.createCellStyle();
        Font font1 = workbook.createFont();
        font1.setBold(true);
		font1.setColor(IndexedColors.GREEN.getIndex());
        cellStyle1.setFont(font1);

		cellStyle2 = workbook.createCellStyle();
        Font font2 = workbook.createFont();
        font2.setColor(IndexedColors.RED.getIndex());
        cellStyle2.setFont(font2);
	
		cellStyle3 = workbook.createCellStyle();
        Font font3 = workbook.createFont();
        font3.setBold(true);
		font3.setUnderline(XSSFFont.U_SINGLE);
        cellStyle3.setFont(font3);
		
		cellStyle4 = workbook.createCellStyle();
		Font font4 = workbook.createFont();
        font4.setBold(true);
		font4.setUnderline(XSSFFont.U_SINGLE);
        cellStyle4.setFont(font4);

		cellStyle5 = workbook.createCellStyle();
        cellStyle5.setIndention(indent2);
		Font font5 = workbook.createFont();
        font5.setBold(true);
		font5.setUnderline(XSSFFont.U_SINGLE);
        cellStyle5.setFont(font5);
		
		cellStyle6 = workbook.createCellStyle();
        cellStyle6.setIndention(indent3);
		Font font6 = workbook.createFont();
        font6.setBold(true);
		font6.setUnderline(XSSFFont.U_SINGLE);
        cellStyle6.setFont(font6);
		
		
		cellStyle7 = workbook.createCellStyle();
        cellStyle7.setIndention(indent4);
		Font font7 = workbook.createFont();
        font7.setBold(true);
		font7.setUnderline(XSSFFont.U_SINGLE);
        cellStyle7.setFont(font7);

		
		cellStyle8 = workbook.createCellStyle();
        cellStyle8.setIndention(indent5);
		Font font8 = workbook.createFont();
        font8.setBold(true);
		font8.setUnderline(XSSFFont.U_SINGLE);
        cellStyle8.setFont(font8);
		
		cellStyle9 = workbook.createCellStyle();
        cellStyle9.setIndention(indent6);
		Font font9 = workbook.createFont();
        font9.setBold(true);
		font9.setUnderline(XSSFFont.U_SINGLE);
        cellStyle9.setFont(font9);

		cellStyle10 = workbook.createCellStyle();
        cellStyle10.setIndention(indent7);

		
		cellStyle11 = workbook.createCellStyle();
        Font font11 = workbook.createFont();
        font11.setBold(true);
		font11.setColor(IndexedColors.DARK_YELLOW.getIndex());
        cellStyle11.setFont(font11);

			
		cellStyle12 = workbook.createCellStyle();
		cellStyle12.setWrapText(true);
		//cellStyle12.setRotation((short)90);
        Font font12 = workbook.createFont();
        font12.setBold(true);
        cellStyle12.setFont(font12);

	} 

	private XSSFCellStyle getCellStyle(int type, String value) {
/*
    	0: function group root
        1: function group first descendant
        2: function group second descendent
        3: function group third descendent
        4: function group fourth descendent
        5: function group fifth descendent
        6: function group sixth descendent
        7: FSG
        8: access right
        9: function
*/
		XSSFCellStyle style;

		switch(type) {
		
			case 0:
				style = cellStyle3;
				break;
			case 1:
				style = cellStyle4;
				break;
			case 2:
				style = cellStyle5;
				break;
			case 3:
				style = cellStyle6;
				break;
			case 4:
				style = cellStyle7;
				break;
			case 5:
				style = cellStyle8;
				break;
			case 6:
				style = cellStyle9;
				break;
			case 7:
				style =  cellStyle12;
				break;
			case 8:
				if(value.equals("Y")) 
					style = cellStyle1;
				else if (value.equals("N"))
					style = cellStyle2;
				else if (value.equals("U"))
					style = cellStyle11;
				else
					style = cellStyle1;
				break; 
			case 9:
				style =  cellStyle10;
				break;
			default:
				style = cellStyle1;
		}
		return style;
	}
	
	public static void main(String args[]) {
	
        MoxySecurityExcel excel;
		MoxySecurityMatrix matrix;
		MoxySecurityConnect moxyConnect; 
		MoxySecurityData moxySecurityData;
		MoxySecurityTree tree;
		
		Console console = System.console();
		console.printf("This program populates an Excel file with Moxy Functional Access Group Rights \n");
		console.printf("It requires that you input the Server IP address, the Moxy database name, and valid SQL credentials \n");

		String serverIP = console.readLine("Please enter the Server IP address: ");
		String moxyDatabaseName = console.readLine("Please enter the Moxy database name: ");
		String login = console.readLine("Please enter the SQL login id: ");
		String password = console.readLine("Please enter the login password: ");
		String excelFilePath = console.readLine("Please enter the name of the Excel Spreadsheet to be populated: ");

		moxyConnect = new MoxySecurityConnect();
		moxyConnect.setServerIP(serverIP);
		moxyConnect.setDBName(moxyDatabaseName);
		moxyConnect.setUserID(login);
		moxyConnect.setUserPassword(password);
		
		
		/*
		moxyConnect = new MoxySecurityConnect();
		moxyConnect.setServerIP("10.2.144.86");
		moxyConnect.setDBName("Moxy");
		moxyConnect.setUserID("sa");
		moxyConnect.setUserPassword("!Advent1");
		*/
		moxyConnect.startConnection();
		
		moxySecurityData = moxyConnect.getMoxySecurityData();
		tree = new MoxySecurityTree(moxySecurityData);
		tree.buildMoxySecurityTree();
		//tree.printTree();
		tree.buildMatrix();

		matrix = tree.getMatrix();
		//matrix.printSecondMatrixColumn();
        excel = new MoxySecurityExcel(matrix, excelFilePath);
		//excel = new MoxySecurityExcel(matrix, "test.xlsx");
        excel.buildExcelFile();
		//excel.testExcelFile();
	}
}

