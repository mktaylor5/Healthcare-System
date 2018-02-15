import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class helper {
    public static String getSSN(String patient) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");            
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.patient_info;");
        String test_name = " ", SSN = " ";
        
        rset.first();
        while(rset != null){
            test_name = rset.getString(2);
            if(test_name.equals(patient)){
                SSN = rset.getString(1);
                break;
            }
            rset.next();
        }
        conn.close();
        stmt.close();
        rset.close();
        return SSN;
    }
    
    public static String getUser() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");            
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?useSSL=false", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.user");
        String username = "error";

        if(rset.next()){
            username = rset.getString("user");
        }
        conn.close();
        stmt.close();
        rset.close();
        return username;
    }
    
    public static String getEmail(String patient) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");            
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.patient_info;");
        String test_name = " ", email = " ";
        
        rset.first();
        while(rset != null){
            test_name = rset.getString(2);
            if(test_name.equals(patient)){
                email = rset.getString(5);
                break;
            }
            rset.next();
        }
        conn.close();
        stmt.close();
        rset.close();
        return email;
    }
    
    public static String getDoctor(String patient, String date, String time) throws ClassNotFoundException, SQLException{
        String SSN = getSSN(patient);
        Class.forName("com.mysql.jdbc.Driver");            
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.appointments WHERE patientSSN='"+SSN+"' and Date='"+date+"' and Time='"+time+"';");
        String test_SSN = " ", doctor = " ", test_date = " ", test_time = " ";
        
        rset.first();
        while(rset != null){
            test_SSN = rset.getString(5);
            test_date = rset.getString(2);
            test_time = rset.getString(3);
            if(test_SSN.equals(SSN)){
                doctor = rset.getString(6);
                break;
            }
            rset.next();
        }
        conn.close();
        stmt.close();
        rset.close();
        return doctor;
    }
    
    public static ArrayList patientList(){
        String name = "None";
        String date = " ";
        String doctor = " ";
        try {
            doctor = helper.getUser();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(patientnames.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM hcs_db.patient_info WHERE Doctor='"+doctor+"';";
        ArrayList<String> patientList = new ArrayList<String>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            // need to store in list; name is only set to one name each time
            // also doesn't get names associated with a specific doctors
            // rset.first();
            // CHANGE BACK TO WHILE LATER
            while(rset.next()){
                name = rset.getString("Name");
                patientList.add(name);
                //rset.next();
        }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(patientnames.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patientList;
    }
    
    public static boolean validExpDate(int month, int year){
        // funcion that checks if date entered (MM/YY) is in the future
        Calendar calendar = Calendar.getInstance();
        int todayM = Calendar.getInstance().get(Calendar.MONTH) + 1;
        System.out.println(todayM);
        int todayY = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(todayY);
        
        if(todayY < year){
            return true;
        }
        else if(todayM == month){
            return false;
        }
        else{
            return false;
        }
    }
    
    //MAIN METHOD FOR TESTING PURPOSES
     /* public static void main(String[] args) throws ClassNotFoundException, SQLException{
        String result = getDoctor("Megan Taylor", "5-5-2017", "12:30");
        System.out.println(result);
      }*/
}