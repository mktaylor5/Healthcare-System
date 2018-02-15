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

@WebServlet(urlPatterns = {"/makeappointment"})
public class makeappointment extends HttpServlet {

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
        /*
            Make appointment adds new row into appointments database
            If date and time in DB: take to error-makeappt jsp page
            Otherwise: add information to db
            CHECK IF NAME IN DB???
        */
        String name = request.getParameter("patientname");
        String doctor = request.getParameter("doctor");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String year = request.getParameter("year");
        String date = month + "-" + day + "-" + year;
        String hour = request.getParameter("hour");
        String minutes = request.getParameter("minutes");
        String time = hour + ":" + minutes;
        String test_date="test", test_time="test", test_name="test", test_doctor="test", SSN = "test";
        String cleared = "N";
        boolean conflict = false;
        
        try {
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
                if(test_date.equals(date)){
                    if(test_time.equals(time)){
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
        try{
            SSN = helper.getSSN(name);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{    // no conflict = insert appt
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            if(!conflict){
                String sql = "INSERT INTO hcs_db.appointments (Date, Time, Cleared, PatientSSN, Doctor) VALUES(?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, date);
                stmt.setString(2, time);
                stmt.setString(3, cleared);
                stmt.setString(4, SSN);
                stmt.setString(5, doctor);
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("patientName", name);
        request.getSession().setAttribute("SSN", SSN);
        request.getSession().setAttribute("date", date);
        request.getSession().setAttribute("time", time);
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
