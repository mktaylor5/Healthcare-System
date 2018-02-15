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

@WebServlet(urlPatterns = {"/nursetreatmentrecord"})
public class nursetreatmentrecord extends HttpServlet {

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
        String re = " ";
        String wt = " ";
        String ht = " ";
        String tc = " ";
        String pr = " ";
        String bp = " ";
        
        String name = request.getParameter("name");
        String date = " ";
        String SSN = " ";
        try {
            SSN = helper.getSSN(name);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(viewtreatmentcontent.class.getName()).log(Level.SEVERE, null, ex);
        }
        String err = "None Found";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt = conn.createStatement();
            // add date parameter later
            ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.treatment_record WHERE SSN='"+SSN+"';");
            rset.first();
            date=rset.getString(4);
            re=rset.getString(3);
            wt=rset.getString(5);
            ht=rset.getString(6);
            bp=rset.getString(7);
            tc=rset.getString(8);
            pr=rset.getString(9);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(viewtreatmentcontent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // name of attribute (you decide), String value you're passing to it
        // then use getAttribute on print.jsp to get that string
        
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("date", date);
        request.getSession().setAttribute("re", re);
        request.getSession().setAttribute("wt", wt);
        request.getSession().setAttribute("ht", ht);
        request.getSession().setAttribute("tc", tc);
        request.getSession().setAttribute("pr", pr);
        request.getSession().setAttribute("bp", bp);
        
        // tells servlet where to send attribute information
        response.sendRedirect("nurseviewtreatmentcontent.jsp");
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
        processRequest(request, response);
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
