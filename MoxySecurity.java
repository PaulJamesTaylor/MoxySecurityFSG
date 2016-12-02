import java.io.FileWriter;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.Console;

public class MoxySecurity {
	 
    MoxySecurityMatrix moxySecurityMatrix; 
	MoxySecurityData moxySecurityData;
    MoxySecurityConnect moxyConnect; 
	XSSFWorkbook workbook;
	
	public MoxySecurity(){  // constructor
		

	}

		
	public void queryMoxySecurityData(String serverIP, String moxyDatabaseName, String login, String password) {
		
		moxyConnect = new MoxyConnect(serverIP, moxyDatabaseName, Login, password);
        moxySecurityData = moxyConnect.getSecurityData();
        
        
        
        moxyConnect = new MoxySecurityConnect();
		
		System.out.println(serverIP);

		moxyConnect.setServerIP(serverIP);
		moxyConnect.setDBName(moxyDatabaseName);
		moxyConnect.setUserID(login);
		moxyConnect.setUserPassword(password);
		
		moxyConnect.buildSecurityData();  // this populates MoxySecurityData
		// moxyConnect.printSecurityData();
		moxySecurityData = moxyConnect.getMoxySecurityData();  // the variable moxySecurityData holds the data
		
	}

	public void buildSecurityGrid() {
		// build the grid here
	}

		
	
	public static void main(String args[]) {
	
		Console console = System.console();
		console.printf("This program populates an Excel file with Moxy Functional Access Group Rights \n");
		console.printf("It requires that you input the Server IP address, the Moxy database name, and valid SQL credentials \n");

/*
		String serverIP = console.readLine("Please enter the Server IP address: ");
		String moxyDatabaseName = console.readLine("Please enter the Moxy database name: ");
		String login = console.readLine("Please enter the SQL login id: ");
		String password = console.readLine("Please enter the login password: ");
		String excelFilePath = console.readLine("Please enter the name of the Excel Spreadsheet to be populated: ");
*/
		MoxySecurityMatrix matrix = new MoxySecurityMatrix();

//		matrix.queryMoxyDatabase(serverIP, moxyDatabaseName, login, password);
		matrix.queryMoxySecurityData("10.2.145.20", "Moxy", "sa", "!Advent1");

//		String excelFilePath = "MoxyPermissions.xlsx";
//		System.out.println("Entered MoxyPermissions spreadsheet name");
		String excelFilePath = new String("MoxyPermissions.xlsx");
		matrix.updateExcelFile(excelFilePath);
					
	}
}