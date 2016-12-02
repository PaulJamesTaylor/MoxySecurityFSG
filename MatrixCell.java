
import java.util.*;

public class MatrixCell {
		   
        private String cellString;
        private int cellType;

/*
        These are cell types for the Excel spreadsheet
        They are indicators of how the cell should be formatted for display
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
            
            
        public MatrixCell(){};
            
        public MatrixCell(String cell, int type) {
                
            cellString = cell;
            cellType = type;
        }
            
        public String getCellString() {
            return cellString;
        }
		   
		public int getCellType() {
            return cellType;
        }

          
		public static void main(String args[]) {
			
			System.out.println("Test");
		}
}