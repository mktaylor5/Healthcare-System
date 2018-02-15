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

import static java.lang.Integer.parseInt;

@WebServlet(urlPatterns = {"/authorization"})
public class authorization extends HttpServlet {

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
        String test_user = "test user";
        int test_pin = 0;
        String classification = "class";
        String username = request.getParameter("username");
        int pin = parseInt(request.getParameter("pin"));
        boolean auth = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();
            ResultSet rset1 = stmt1.executeQuery("SELECT Username FROM hcs_db.authorization;");
            ResultSet rset2 = stmt2.executeQuery("SELECT PIN FROM hcs_db.authorization;");
            ResultSet rset3 = stmt3.executeQuery("SELECT * FROM hcs_db.authorization;");
            
            rset1.first();
            rset2.first();
            rset3.first();
            while(rset1 != null){
                test_user = rset1.getString("Username");
                test_pin = rset2.getInt("PIN");
                if(test_user.equals(username)){
                    test_user = rset1.getString("Username");
                    if(test_pin == pin){
                        auth = true;
                        classification = rset3.getString(2);
                        break;
                    }
                }
                rset1.next();
                rset2.next();
                rset3.next();
            }
            
            // add username to user db to use for authorization
            Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            String sql = "UPDATE hcs_db.user SET user = '" + username + "' WHERE pk = 1";
            PreparedStatement stmt4 = conn2.prepareStatement(sql);
            stmt4.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // name of attribute (you decide), String value you're passing to it
        request.getSession().setAttribute("user", test_user);
        request.getSession().setAttribute("pinnum", test_pin);
        request.getSession().setAttribute("employeeType", classification);
        // tells servlet where to send attribute information
        if(auth){
            if(classification.equals("CEO")){
                response.sendRedirect("ceohome.jsp");
            }
            else if(classification.equals("Staff")){
                response.sendRedirect("staffhome.jsp");
            }
            else if(classification.equals("Doctor")){
                response.sendRedirect("doctorhome.jsp");
            }
            else if(classification.equals("Nurse")){
                response.sendRedirect("nursehome.jsp");
            }
        }
        else{
            response.sendRedirect("notauthorized.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Authorize users";
    }// </editor-fold>

}
