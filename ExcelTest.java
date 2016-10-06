import java.io.FileWriter;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelTest {
	 

	XSSFWorkbook workbook;

	
	public ExcelTest(){}
		
	public void readExcelFile(String filePath) {
		
		System.out.println("The path of the excel file is: " + filePath);
		
		try {
		
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			System.out.println("Am I able to open the file?");
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = workbook.getSheetAt(0);
					
			Row row = sheet.getRow(5);
			Cell cell = row.getCell(1);	
			System.out.print(cell.toString() +"  ");
								
			excelFile.close();
			//System.out.println("Do I make it to the line after closing the Excel file?");
				
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
			   
	    ExcelTest test = new ExcelTest();
		
		String excelFilePath = ".\\MoxyPermissions.xlsx";
			
		test.readExcelFile(excelFilePath);
		System.out.println("Do I make it out of the readExcelFile method?");
				
	}
}