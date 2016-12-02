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

public class ExcelTest3 {
	 

	XSSFWorkbook workbook;

	
	public ExcelTest3(){}
		
	public void readExcelFile(String filePath) {
		
		System.out.println("The path of the excel file is: " + filePath);
		
		try {
		
				FileInputStream fis = new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook (fis);
				XSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				System.out.println("Marker 1");
				int rowCounter = 0;
				while(rowIterator.hasNext()){
					System.out.println("Marker 2");
					Row row = rowIterator.next();
					Cell cell1 = row.getCell(0); 
					if(rowCounter > 10) {
						Cell cell2 = row.createCell(2);
						cell2.setCellValue("Y");
					}
					rowCounter++;
				}
				fis.close();
				FileOutputStream outFile =new FileOutputStream(new File(filePath));
				workbook.write(outFile);
				outFile.close();
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
			   
	    ExcelTest3 test = new ExcelTest3();
		
		String excelFilePath = ".\\MoxyPermissions.xlsx";
			
		test.readExcelFile(excelFilePath);
		System.out.println("Do I make it out of the readExcelFile method?");
				
	}
}