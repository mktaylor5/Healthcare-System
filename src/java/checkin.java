import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/checkin"})
public class checkin extends HttpServlet {

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
        String patient = request.getParameter("patientName");
        String doctor = request.getParameter("doctor");
        String hour = request.getParameter("hour");
        String min = request.getParameter("minutes");
        String time = hour + ":" + min;
        
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String date = month + "-" + day + "-" + year;
        String test_date, test_doctor, test_time, sql, SSN = "test";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();
            ResultSet rset1 = stmt1.executeQuery("SELECT Date FROM hcs_db.appointments");
            ResultSet rset2 = stmt2.executeQuery("SELECT Time FROM hcs_db.appointments");
            ResultSet rset3 = stmt3.executeQuery("SELECT Doctor FROM hcs_db.appointments");
            
            rset1.first();
            rset2.first();
            rset3.first();
            while(rset1 != null){
                test_date = rset1.getString("Date");
                test_time = rset2.getString("Time");
                test_doctor = rset3.getString("Doctor");
                if(test_date.equals(date)){
                    if(test_time.equals(time)){
                        if(test_doctor.equals(doctor)){
                            SSN = helper.getSSN(patient);
                            break;
                        }
                    }
                }
                rset1.next();
                rset2.next();
                rset3.next();
            }
            /* ONLY WORKS FOR APPOINTMENTS ON WHATEVER DAY IT IS!!! */
            sql = "UPDATE hcs_db.appointments SET Cleared='Y' WHERE PatientSSN='"+SSN+"' and Time='"+time+"' and Date='"+date+"';";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("name", patient);
        request.getSession().setAttribute("time", time);
        request.getSession().setAttribute("date", date);
        request.getSession().setAttribute("SSN", SSN);
        response.sendRedirect("verifycheckin.jsp");
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
