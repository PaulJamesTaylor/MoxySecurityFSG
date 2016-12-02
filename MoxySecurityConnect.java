

// Import the SQL Server JDBC Driver classes 
import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MoxySecurityConnect
{  
        private String serverIP;
		private String dbName; 
		private String userID;
		private String userPassword;
		private String sqlQuery; 
		private Connection connection;	
		private MoxySecurityData moxySecurityData;	
			   	
		public MoxySecurityConnect() {
		
			moxySecurityData = new MoxySecurityData();
		}

		
		public void setServerIP(String IP) {
			
			serverIP = IP;
		}
		
		public void setDBName(String name) {
			
			dbName = name;
		}
		
		public void setUserID(String ID) {
			
			userID = ID;
		}
	
		public void setUserPassword(String password) {
			
			userPassword = password;
		}

		public MoxySecurityData getMoxySecurityData() {

			this.startConnection();
			this.buildFunctionData();
			this.buildFunctionGroupData();
			this.buildFSGList();
			this.buildFunctionAccessRights();
			return moxySecurityData;
		}
		
		public void startConnection() {

			String connectionURL = "jdbc:sqlserver://" + serverIP + ":1433;" + 
						 "database=" + dbName + ";" + 
						 "user=" + userID  + ";" + 
						 "password=" + userPassword;

			try  
			{ 
				// Load the SQLServerDriver class, build the 
				// connection string, and get a connection 
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
				connection = DriverManager.getConnection(connectionURL); 
				System.out.println("Connected.");
			  }  
		   catch(Exception e)  
		   { 
				System.out.println("Problem connecting to SQL Server, please check the IP and database");
				System.out.println(e.getMessage()); 
				System.exit(0);  
		   }
		}
		
		private void buildFunctionData() {

			sqlQuery = "SELECT * from moxyfunctions";
			try {
										
				Statement stmt = connection.createStatement();  
				ResultSet rs = stmt.executeQuery(sqlQuery);
				
				// Iterate through the data in the result set and display it.  
				while (rs.next())  
				{   
					MoxyFunction function = new MoxyFunction(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
					moxySecurityData.addFunction(function);
								
				}
			}
			catch(Exception e)  { 
				System.out.println("Problem connecting to SQL Server, please check the IP and database");
				System.out.println(e.getMessage()); 
				System.exit(0);  
		   }
		}

		private void buildFunctionGroupData() {

			sqlQuery = "SELECT * from moxyfunctiongroups";
			try {						
				Statement stmt = connection.createStatement();  
				ResultSet rs = stmt.executeQuery(sqlQuery);
				
				// Iterate through the data in the result set and display it.  
				while (rs.next())  
				{   
					MoxyFunctionGroup fg;
					fg = new MoxyFunctionGroup(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
					moxySecurityData.addFunctionGroup(fg);
				}
				moxySecurityData.addFunctionGroupGeneration();
			}
			catch(Exception e)  { 
				System.out.println("Problem connecting to SQL Server, please check the IP and database");
				System.out.println(e.getMessage()); 
				System.exit(0);  
		   }
		}
		
		private void buildFSGList() {

			sqlQuery = "SELECT * from moxyfunctionalaccessgroups";						
			try {	
				Statement stmt = connection.createStatement();  
				ResultSet rs = stmt.executeQuery(sqlQuery);
				
				// Iterate through the data in the result set and display it.  
				while (rs.next())  
				{   
					MoxyFSG fsg;
					fsg = new MoxyFSG(rs.getInt(1),rs.getString(2));
					moxySecurityData. addFSG(fsg);
				}
			}
			catch(Exception e)  { 
				System.out.println("Problem connecting to SQL Server, please check the IP and database");
				System.out.println(e.getMessage()); 
				System.exit(0);  
		   }
		}

		private void buildFunctionAccessRights() {

			sqlQuery = "SELECT * from MoxyFunctionalAccessRights";						
			try {
				Statement stmt = connection.createStatement();  
				ResultSet rs = stmt.executeQuery(sqlQuery);
				
				// Iterate through the data in the result set and display it.  
				while (rs.next())  
				{   
					MoxyFunctionAccessRight functionRight;
					functionRight = new MoxyFunctionAccessRight(rs.getInt(1),rs.getInt(2),rs.getString(3));
					moxySecurityData.addFunctionAccessRight(functionRight);
				}
			}
			catch(Exception e)  { 
				System.out.println("Problem connecting to SQL Server, please check the IP and database");
				System.out.println(e.getMessage()); 
				System.exit(0);  
		   }
		}

 
		
	public static void main(String args[]) {  
       
		MoxySecurityConnect moxyConnect = new MoxySecurityConnect();
		MoxySecurityData dataTest;

		moxyConnect.setServerIP("10.2.145.20");
		moxyConnect.setDBName("Moxy");
		moxyConnect.setUserID("sa");
		moxyConnect.setUserPassword("!Advent1");
		
		moxyConnect.startConnection();
		
		dataTest = moxyConnect.getMoxySecurityData();
		dataTest.printFunctionData();
		
	} 
}