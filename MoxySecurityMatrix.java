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
	 
	MoxySecurityConnect moxyConnect; 
	XSSFWorkbook workbook;
    MoxySecurityData moxySecurityData;
	
	public MoxySecurityMatrix(){
		
	}

		
	public void queryMoxyDatabase(String serverIP, String moxyDatabaseName, String login, String password) {
		
		moxyConnect = new MoxySecurityConnect();
		
		System.out.println(serverIP);


		moxyConnect.setServerIP(serverIP);
		moxyConnect.setDBName(moxyDatabaseName);
		moxyConnect.setUserID(login);
		moxyConnect.setUserPassword(password);
		
		moxyConnect.queryDatabase();  // this also populates MoxySecurityData
		// moxyConnect.printSecurityData();
		moxySecurityData = moxyConnect.getMoxySecurityData();
		
	}
/*	
	public void queryMoxyDatabase() {
		
		moxyConnect = new MoxySecurityConnect();
		
		
		moxyConnect.setServerIP("10.2.145.28");
		moxyConnect.setDBName("MoxyTest");
		moxyConnect.setUserID("sa");
		moxyConnect.setUserPassword("!Advent1");
		
		moxyConnect.queryDatabase();  // this also populates MoxySecurityData
		// moxyConnect.printSecurityData();
		moxySecurityData = moxyConnect.getMoxySecurityData();
		
	}
*/	


	public void updateExcelFile(String filePath) {
		
		System.out.println("The path of the excel file is: " + filePath);
		
		try {
		
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			//System.out.println("Am I able to open the file?");
			workbook = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			// need to go through a loop and populate the matrix row by row
			// right now the grid is from 4 to 229
			
			XSSFRow fsgRow = sheet.getRow(1);  //fsgRow is the second row that contains the Functional Security Groups
			//System.out.println("Can I get the second row?");
			//System.out.print("Printing out the fsg row" + fsgRow.toString());
			//System.out.println("Printing out the fsg row");
			
			for(int rowIterator=4; rowIterator<229; rowIterator++){  // the functions start at row 4
				//System.out.println("In the loop in MoxySecurityMatrix");			
				XSSFRow currentRow = sheet.getRow(rowIterator);
				
				
				XSSFCell functionCell = currentRow.getCell(0);  // this is the cell of the function
				String functionString = functionCell.toString();
				//System.out.println("Pulling function from the spreadsheet, the function is: " + functionString);
				
				for(int columnIterator = 1; columnIterator < 8 ;columnIterator++) {
					
					XSSFCell cell2 = currentRow.createCell(columnIterator);
					String fsgString = fsgRow.getCell(columnIterator).toString();  // this gets the fsg from the first row in the current column 
					//System.out.println("The functional security group is: " + fsgString);
					String accessRight = moxySecurityData.getAccessRight(fsgString, functionString);
					//System.out.println("the accessright is: " + accessRight);
					cell2.setCellValue(accessRight);
					
				}  // end inner for loop

				
				
			}	// end outer for loop	 
			
			excelFile.close();
			
			FileOutputStream excelFileOutput = new FileOutputStream(new File(filePath));
			workbook.write(excelFileOutput);
			excelFileOutput.close();
			workbook.close();
		}  // end try
		
		catch(Exception e)
		{ 
				System.out.println("Problem updating the excel file please make sure the path is correct");
				System.out.println(e.getMessage()); 
				System.exit(0);  
		}
	}
		
	
	public static void main(String args[]) {
	
		Console console = System.console();
		console.printf("This program populates an Excel file with Moxy Functional Access Group Rights \n");
		console.printf("It requires that you input the Server IP address, the Moxy database name, and valid SQL credentials \n");

		String serverIP = console.readLine("Please enter the Server IP address: ");
		String moxyDatabaseName = console.readLine("Please enter the Moxy database name: ");
		String login = console.readLine("Please enter the SQL login id: ");
		String password = console.readLine("Please enter the login password: ");
		String excelFilePath = console.readLine("Please enter the name of the Excel Spreadsheet to be populated: ");

		MoxySecurityMatrix matrix = new MoxySecurityMatrix();

		matrix.queryMoxyDatabase(serverIP, moxyDatabaseName, login, password);
		

//		String excelFilePath = "MoxyPermissions.xlsx";
//		System.out.println("Entered MoxyPermissions spreadsheet name");
		
		matrix.updateExcelFile(excelFilePath);
					
	}
}