import java.io.*;
import java.util.*;
import java.sql.*;
import java.time.*;


public class createDailyReport{
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/hcs_db?SSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   String date;
   
   public createDailyReport(){}
   
   public createDailyReport(String d) {
  
date=d; 
Connection conn = null;
Statement stmt = null;
   
int c1,c2,c3,c4,c5;//counters for number of patients
c1=c2=c3=c4=c5=0;
int i1,i2,i3,i4,i5;
i1=i2=i3=i4=i5=0;//accumulators for health service income
   
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating table in given database...");
      stmt = conn.createStatement();
      
      String sql = "CREATE TABLE  " +date+
                   " (id INTEGER not NULL AUT0_INCREMENT, " +
                   " name VARCHAR(25), " +  
                   " number INTEGER, " + 
              //no plus here at end of line
                   " health_service_income INTEGER, " +
                   " PRIMARY KEY ( id ))"; 

      stmt.executeUpdate(sql);
      System.out.println("Created table in given database...");
 
 //insert values into the table just created
 String query="insert into "+date+" (name,number,health_service_income) "+" values (?,?,?)";
 PreparedStatement pst=conn.prepareStatement(query);
 // deleted String sql; bc already defined
      sql = "SELECT hcs_db_appointments.Doctor,hcs_db_invoices.Cost FROM hcs_db_appointments INNER JOIN hcs_db_invoices ON hcs_db_appointments.PatientSSN=hcs_db_invoices.SSN WHERE hcs_db_appointments.DATE="+date;
      ResultSet rs = stmt.executeQuery(sql);

         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            
            String doctor= rs.getString("Doctor");
			int cost=rs.getInt("Cost");
			if(doctor.equalsIgnoreCase("doc1")){
				c1++;
				i1+=cost;
			}
			else if(doctor.equalsIgnoreCase("doc2")){
				c2++;
				i2+=cost;
			}
			else if(doctor.equalsIgnoreCase("doc3")){
				c3++;
				i3+=cost;
			}
			else if(doctor.equalsIgnoreCase("doc4")){
				c4++;
				i4+=cost;
			}
			else if(doctor.equalsIgnoreCase("doc5")){
				c5++;
				i5+=cost;
			}
			else ;
					 }
			pst.setString(1,"doc1");
			pst.setInt(2,c1);
			pst.setInt(3,i1);
			pst.execute();
			//pst=conn.prepareStatement(query);
			pst.setString(1,"doc2");
			pst.setInt(2,c2);
			pst.setInt(3,i2);
			pst.execute();
			pst.setString(1,"doc3");
			pst.setInt(2,c3);
			pst.setInt(3,i3);
			pst.execute();
			pst.setString(1,"doc4");
			pst.setInt(2,c4);
			pst.setInt(3,i4);
			pst.execute();
			pst.setString(1,"doc5");
			pst.setInt(2,c5);
			pst.setInt(3,i5);
			pst.execute();
			rs.close();
			 
			   }catch(SQLException se){
				  //Handle errors for JDBC
				  se.printStackTrace();
			   }catch(Exception e){
				  //Handle errors for Class.forName
				  e.printStackTrace();
			   }finally{
				  //finally block used to close resources
				  try{
					 if(stmt!=null)
						conn.close();
				  }catch(SQLException se){
				  }// do nothing
				  try{
					 if(conn!=null)
						conn.close();
				  }catch(SQLException se){
					 se.printStackTrace();
				  }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
}//end main
}//end JDBCExample