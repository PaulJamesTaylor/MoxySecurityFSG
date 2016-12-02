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

public class ExcelTest2 {
	 

	XSSFWorkbook workbook;

	
	public ExcelTest2(){}
		
	public void readExcelFile(String filePath) {
		
		System.out.println("The path of the excel file is: " + filePath);
		
		try {
		
				FileInputStream fis = new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook (fis);
				XSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> ite = sheet.iterator();
				while(ite.hasNext()){
					Row row = ite.next();
					Iterator<Cell> cite = row.iterator();
					while(cite.hasNext()){
						Cell c = cite.next();
						System.out.print(c.toString() +"  ");
					}
					System.out.println();
				}
				fis.close();
		}
		
		  // end try
		
			catch(Exception e)
			{ 
				System.out.println("Problem updating the excel file please make sure the path is correct");
				System.out.println(e.getMessage()); 
				System.exit(0);  
			}
	}
		
	public static void main(String args[]) {
			   
	    ExcelTest2 test = new ExcelTest2();
		
		String excelFilePath = ".\\MoxyPermissions.xlsx";
			
		test.readExcelFile(excelFilePath);
		System.out.println("Do I make it out of the readExcelFile method?");
				
	}
}