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

@WebServlet(urlPatterns = {"/nurseeditsubmitted"})
public class nurseeditsubmitted extends HttpServlet {

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
        String date = (String)request.getSession().getAttribute("date");
        String wt = request.getParameter("wt");
        String ht = request.getParameter("ht");
        String bp = request.getParameter("bp");
        String re = request.getParameter("re");
        String SSN = "test";
        
        try {
            SSN = helper.getSSN(name);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editsubmitted.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM hcs_db.treatment_record WHERE SSN='"+SSN+"' and Date='"+date+"';";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            
            if(!re.equals("")){
                rs.updateString("Reason", re);
                rs.updateRow();
            }
            if(!wt.equals("")){
                rs.updateString("Weight", wt);
                rs.updateRow();
            }
            if(!ht.equals("")){
                rs.updateString("Height", ht);
                rs.updateRow();
            }
            if(!bp.equals("")){
                rs.updateString("BloodPressure", bp);
                rs.updateRow();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(editsubmitted.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        response.sendRedirect("nursetreatmentcontent.jsp");
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
