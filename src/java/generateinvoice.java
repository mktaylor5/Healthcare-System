import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/generateinvoice"})
public class generateinvoice extends HttpServlet {

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
        String SSN = "test";
        try {
            SSN = helper.getSSN(name);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(generateinvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        String doctor = request.getParameter("doctor");
        String cost = request.getParameter("cost");
        String service = request.getParameter("service");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String year = request.getParameter("year");
        String date = month + "-" + day + "-" + year;
        int invoiceNum = 1;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            String sql = "INSERT INTO hcs_db.invoices (SSN, Service, Cost, Date, Doctor) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, SSN);
            stmt.setString(2, service);
            stmt.setString(3, cost);
            stmt.setString(4, date);
            stmt.setString(5, doctor);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(generateinvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            String sql = "SELECT InvoiceID FROM hcs_db.invoices WHERE SSN='"+SSN+"' and Date='"+date+"';";
            Statement stmt1 = conn1.prepareStatement(sql);
            ResultSet rset = stmt1.executeQuery(sql);
            rset.first();
            if(rset != null){
                invoiceNum = rset.getInt("InvoiceID");
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(generateinvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("invoiceNum", invoiceNum);
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("SSN", SSN);
        request.getSession().setAttribute("date", date);
        request.getSession().setAttribute("service", service);
        request.getSession().setAttribute("cost", cost);
        response.sendRedirect("displayinvoice.jsp");
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
