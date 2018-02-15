import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/changeappointment"})
public class changeappointment extends HttpServlet {

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
        String name = request.getParameter("name");
        String SSN = " ";
        try {
            SSN = helper.getSSN(name);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(changeappointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        // OLD APPOINTMENT INFO
        String prevMonth = request.getParameter("prevMonth");
        String prevDay = request.getParameter("prevDay");
        String prevYear = request.getParameter("prevYear");
        String prevDate = prevMonth + "-" + prevDay + "-" + prevYear;
        String prevHour = request.getParameter("prevHour");
        String prevMinutes = request.getParameter("prevMinutes");
        String prevTime = prevHour + ":" + prevMinutes;
        
        String doctor = " ";
        try{
            doctor = helper.getDoctor(name, prevDate, prevTime);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(changeappointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // NEW APPOINTMENT INFO
        String newMonth = request.getParameter("newMonth");
        String newDay = request.getParameter("newDay");
        String newYear = request.getParameter("newYear");
        String newDate = newMonth + "-" + newDay + "-" + newYear;
        String newHour = request.getParameter("newHour");
        String newMinutes = request.getParameter("newMinutes");
        String newTime = newHour + ":" + newMinutes;
        
        String test_date="test", test_time="test", test_name="test", test_doctor="test";
        String cleared = "N";
        boolean conflict = false;
        
        try{    // checking for conflict with new time
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();
            ResultSet rset1 = stmt1.executeQuery("SELECT Date FROM hcs_db.appointments;");
            ResultSet rset2 = stmt2.executeQuery("SELECT Time FROM hcs_db.appointments;");
            ResultSet rset3 = stmt3.executeQuery("SELECT Doctor FROM hcs_db.appointments;");
            
            rset1.next();
            rset2.next();
            rset3.next();
            while(rset1 != null){
                test_date = rset1.getString("Date");
                test_time = rset2.getString("Time");
                test_doctor = rset3.getString("Doctor");
                if(test_date.equals(newDate)){
                    if(test_time.equals(newTime)){
                        if(test_doctor.equals(doctor)){
                            conflict = true;
                        }
                    }
                }
                rset1.next();
                rset2.next();
                rset3.next();
            }
                        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{    // no conflict = update appt
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            if(!conflict){
                String sql1 = "UPDATE hcs_db.appointments SET Time='"+newTime+"' WHERE PatientSSN='"+SSN+"' and Doctor='"+doctor+"';";
                String sql2 = "UPDATE hcs_db.appointments SET Date='"+newDate+"' WHERE PatientSSN='"+SSN+"' and Doctor='"+doctor+"';";
                PreparedStatement pstmt = conn.prepareStatement(sql1);
                pstmt.executeUpdate();
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("patientName", name);
        request.getSession().setAttribute("SSN", SSN);
        request.getSession().setAttribute("date", newDate);
        request.getSession().setAttribute("time", newTime);
        request.getSession().setAttribute("doctor", doctor);
        if(conflict){
            response.sendRedirect("apptconflict.jsp");
        }
        else{
            response.sendRedirect("verifyappt.jsp");
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
