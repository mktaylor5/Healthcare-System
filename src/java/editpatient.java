import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/editpatient"})
public class editpatient extends HttpServlet {

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
        String name = (String)request.getSession().getAttribute("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String insurance = request.getParameter("insurance");
        String SSN = "test";

        try {
            SSN = helper.getSSN(name);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editsubmitted.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //String sql = "SELECT * FROM hcs_db.patient_info WHERE Name='"+name+"';";
            //ResultSet rs = stmt.executeQuery(sql);
            //rs.first();
            
            PreparedStatement ps;
            
            if(!address.equals("")){
                ps = conn.prepareStatement("UPDATE hcs_db.patient_info SET Address = ? WHERE Name='"+name+"';");
                ps.setString(1,address);
                ps.executeUpdate();
            }
            if(!phone.equals("")){
                ps = conn.prepareStatement("UPDATE hcs_db.patient_info SET Phone = ? WHERE Name='"+name+"';");
                ps.setString(1,phone);
                ps.executeUpdate();
            }
            if(!email.equals("")){
                ps = conn.prepareStatement("UPDATE hcs_db.patient_info SET Email = ? WHERE Name='"+name+"';");
                ps.setString(1,email);
                ps.executeUpdate();
                //rs.updateString("Email", email);
                //rs.updateRow();
            }
            if(!insurance.equals("")){
                ps = conn.prepareStatement("UPDATE hcs_db.patient_info SET Insurance = ? WHERE Name='"+name+"';");
                ps.setString(1,insurance);
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editpatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("address", address);
        request.getSession().setAttribute("phone", phone);
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("insurance", insurance);
        response.sendRedirect("reviewpatientchanges.jsp");
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
