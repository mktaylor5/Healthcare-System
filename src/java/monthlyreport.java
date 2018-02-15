import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet(urlPatterns = {"/monthlyreport"})
public class monthlyreport extends HttpServlet {

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
        String month1 = request.getParameter("month");
        int month = Integer.parseInt(month1);
        String month2 = " ";
        String dbdate = " ";
        int test_month = 0;
        int p1=0, p2=0, p3=0, p4=0, p5=0;
        int i1=0, i2=0, i3=0, i4=0, i5=0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.daily_reports;");
            rset.next();
            while(rset != null){
                dbdate = rset.getString("Date");
                month2 = String.valueOf(dbdate.charAt(0));
                test_month = Integer.parseInt(month2);
                if(test_month == month){
                    p1 += rset.getInt("Doc1P");
                    p2 += rset.getInt("Doc2P");
                    p3 += rset.getInt("Doc3P");
                    p4 += rset.getInt("Doc4P");
                    p5 += rset.getInt("Doc5P");
                    i1 += rset.getInt("Doc1I");
                    i2 += rset.getInt("Doc2I");
                    i3 += rset.getInt("Doc3I");
                    i4 += rset.getInt("Doc4I");
                    i5 += rset.getInt("Doc5I");
                }
                rset.next();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("month", "May");
        request.getSession().setAttribute("p1", p1);
        request.getSession().setAttribute("p2", p2);
        request.getSession().setAttribute("p3", p3);
        request.getSession().setAttribute("p4", p4);
        request.getSession().setAttribute("p5", p5);
        request.getSession().setAttribute("i1", i1);
        request.getSession().setAttribute("i2", i2);
        request.getSession().setAttribute("i3", i3);
        request.getSession().setAttribute("i4", i4);
        request.getSession().setAttribute("i5", i5);
        response.sendRedirect("viewmonthlyreport.jsp");
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
