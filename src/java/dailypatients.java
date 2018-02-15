import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/dailypatients"})
public class dailypatients extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // return patients for the day and their time
        String doctor = "test", name = "test";
        try {
            doctor = helper.getUser();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dailypatients.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String month1 = Integer.toString(month);
        String day1 = Integer.toString(day);
        String year1 = Integer.toString(year);
        
        String date = month1 + "-" + day1 + "-" + year1;
        String test_date = " ", test_doctor = " ";
        ArrayList dailyPatients = new ArrayList();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt1 = conn.createStatement();
            ResultSet rset1 = stmt1.executeQuery("SELECT * FROM hcs_db.appointments WHERE Date='"+date+"' and Doctor='"+doctor+"';");
            
            while(rset1 != null){
                // store patient names in array list and give option to choose which one???
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dailypatients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
