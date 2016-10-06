

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
		
		private ArrayList<String> securityMatrix;
		MoxySecurityData moxySecurityData;
	   	
		public MoxySecurityConnect() {
			
			moxySecurityData = new MoxySecurityData();
			sqlQuery = "SELECT FunctionalAccessGroupName, MoxyFunctionGroupID, MoxyFunctionName, FunctionDescription, AccessRight  " + 
                         "FROM MoxyFunctionalAccessRights FAR " +
                         "JOIN MoxyFunctionalAccessGroups AG ON FAR.FunctionalAccessGrpID = AG.FunctionalAccessGrpID " +
                         "JOIN MoxyFunctions F ON FAR.MoxyFunctionID = F.MoxyFunctionID " +
                         "ORDER BY AG.FunctionalAccessGrpID, F.MoxyFunctionGroupID";
		
			securityMatrix = new ArrayList<String>();
		
		}
		
		

		public MoxySecurityData getMoxySecurityData() {
			
			return moxySecurityData;
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
		
		public String getServerIP() {
			
			return serverIP;
		}
		
		public String getDBName() {
			
			return dbName;
		}
		
		public String getUserID() {
			
			return userID;
		}
	
		public String getUserPassword() {
			
			return userPassword;
		}
		
		
		public String getSQLQuery() {
			return sqlQuery;
		}
		
		public void queryDatabase() {
			
			try  
			{ 
				// Load the SQLServerDriver class, build the 
				// connection string, and get a connection 
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
				String connectionUrl = "jdbc:sqlserver://" + this.getServerIP() + ":1433;" + 
										"database=" + this.getDBName() + ";" + 
										"user=" + this.getUserID()  + ";" + 
										"password=" + this.getUserPassword(); 
				
				
				System.out.println("connection string is: " + connectionUrl);
				
				Connection con = DriverManager.getConnection(connectionUrl); 
				
				System.out.println("Connected.");

				// Create and execute an SQL statement that returns some data.  
								
				Statement stmt = con.createStatement();  
				ResultSet rs = stmt.executeQuery(sqlQuery);

				
				// Iterate through the data in the result set and display it.  
				while (rs.next())  
				{   
					moxySecurityData.insertSecurityRecord(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				}
			
				
		   }  
		   catch(Exception e)  
		   { 
				System.out.println("Problem connecting to SQL Server, please check the IP and database");
				System.out.println(e.getMessage()); 
				System.exit(0);  
		   }
			
		}
		
		public void printSecurityData() {
			
			moxySecurityData.printSecurityData();
		}
		
		
	public static void main(String args[]) {  
       
		MoxySecurityConnect moxyConnect = new MoxySecurityConnect();
		
		moxyConnect.setServerIP("10.2.145.28");
		moxyConnect.setDBName("MoxyTest");
		moxyConnect.setUserID("sa");
		moxyConnect.setUserPassword("!Advent1");
		
		moxyConnect.queryDatabase();
		moxyConnect.printSecurityData();
		//moxyConnect.writeOutputToFile("Test.txt");
		
		//MoxySecurityData moxySecurityData;
		//moxySecurityData = moxyConnect.getMoxySecurityData();
		
		//moxySecurityData.printSecurityData();
		
	} 
}