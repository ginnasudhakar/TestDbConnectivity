import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBOracleConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("java.security.egd", "file:///dev/urandom");
		String dbip = args[0];
		String dbport = args[1];
		String dbsid = args[2];
		String dbuser = args[3];
		String dbpass = args[4];
		
		System.out.println("Database IP - " + args[0] + "\t" +
				"Database Port - " + args[1] + "\t" +
				"Database SID - " + args[2] + "\t"+
				"Database user - " + args[3] + "\t"+
				"Database pass - " + args[4] + "\t");
		
		  try {

	            Class.forName("oracle.jdbc.driver.OracleDriver");

	        } catch (ClassNotFoundException e) {

	            System.out.println("Where is your Oracle JDBC Driver?");
	            e.printStackTrace();
	            return;

	        }

	        System.out.println("Oracle JDBC Driver Registered!");

	        Connection connection = null;

	        try {

	        	 System.out.println("Trying to get the Connection");
		           
	            connection = DriverManager.getConnection(
	                    "jdbc:oracle:thin:@"+dbip+":"+dbport+":"+dbsid, dbuser, dbpass);

	        } catch (SQLException e) {

	            System.out.println("Connection Failed! Check output console");
	            e.printStackTrace();
	            return;

	        }

	        if (connection != null) {
	            System.out.println("Connection Successful, Trying to get the System Time on Database");
	            
	            Statement stmt = null;
	            String query = "SELECT SYSDATE DTTM FROM DUAL";
	            try {
	                stmt = connection.createStatement();
	                ResultSet rs = stmt.executeQuery(query);
	                while (rs.next()) {
	                    String coffeeName = rs.getString("DTTM");
	                  
	                    System.out.println("Database Time is --> " + coffeeName);
	                }
	            } catch (SQLException e ) {
	                e.printStackTrace();
	            } finally {
	                if (stmt != null) { try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} }
	            }
	            
	            try {
	            	System.out.println("Trying to Close the Connection with " + dbsid);
	     	          
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            System.out.println("Connection Closed");
	     	      
	            
	        } else {
	            System.out.println("Failed to make connection!");
	        }
	        
	        
	        
		
	}

}
