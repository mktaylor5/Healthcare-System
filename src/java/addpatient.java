import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/addpatient"})
public class addpatient extends HttpServlet {

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
        
        String patientName = request.getParameter("patient");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String insurance = request.getParameter("insurance");
        String SSN = request.getParameter("SSN");
        String doctor = request.getParameter("doctor");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO hcs_db.patient_info VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, SSN);
            stmt.setString(2, patientName);
            stmt.setString(3, address);
            stmt.setString(4, phone);
            stmt.setString(5, email);
            stmt.setString(6, insurance);
            stmt.setString(7, doctor);
            stmt.executeUpdate();
            //String values = "('" + SSN + "', '" + patientName + "', '" + address + "', '" + phone + "', '" + email + "', '" + insurance + "')";
            //String sql = "INSERT INTO hcs_db.patient_info (SSN, Name, Address, Phone, Email, Insurance) VALUES" + values;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("patient", patientName);
        response.sendRedirect("patientadded.jsp");
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
