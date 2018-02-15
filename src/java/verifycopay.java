import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/verifycopay"})
public class verifycopay extends HttpServlet {

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
        String cardNumber = " ", cardHolder = " ", CVV = " ", expDate = " ", checkNum = " ", expMonth2 = " ", expYear2 = " ";
        int expMonth = 0, expYear = 0;
        String type = request.getParameter("type");
        String name = "test";
        name = request.getParameter("patient");
        String SSN = " ";
        try {
            SSN = helper.getSSN(name);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(verifycopay.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(type.equals("Card")){
            cardHolder = request.getParameter("cardName");
            cardNumber = request.getParameter("cardNum");
            expMonth = Integer.parseInt(request.getParameter("month"));
            expYear = Integer.parseInt(request.getParameter("year"));
            expMonth2 = request.getParameter("month");
            expYear2 = request.getParameter("year");
            expDate = expMonth2 + "/" + expYear2;
            CVV = request.getParameter("CVV");
        }
        if(type.equals("Check")){
            checkNum = request.getParameter("checkNum");
        }
        String amount = request.getParameter("amount");
        
        boolean verified = false;
        int payID = 0;
        
        if((type.equals("Card")) && (cardNumber.length() == 16) && (CVV.length() == 3)){
            if (helper.validExpDate(expMonth, expYear)) {
                verified = true;
            }
        }
        
        try{ // for card payments
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            if(verified && type.equals("Card")){
                String sql = "INSERT INTO hcs_db.copay (SSN, Type, CC, CCName, CVV, ExpDate, Amount) VALUES(?,?,?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, SSN);
                stmt.setString(2, type);
                stmt.setString(3, cardNumber);
                stmt.setString(4, cardHolder);
                stmt.setString(5, CVV);
                stmt.setString(6, expDate);
                stmt.setString(7, amount);
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{ // for check payments
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            if(type.equals("Check")){
                String sql = "INSERT INTO hcs_db.copay (SSN, Type, CheckNum, Amount) VALUES(?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, SSN);
                stmt.setString(2, type);
                stmt.setString(3, checkNum);
                stmt.setString(4, amount);
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{ // for cash payments
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            if(type.equals("Cash")){
                String sql = "INSERT INTO hcs_db.copay (SSN, Type, Cash, Amount) VALUES(?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, SSN);
                stmt.setString(2, type);
                stmt.setString(3, "Y");
                stmt.setString(4, amount);
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        String email = "hello";
        try {
            email = helper.getEmail(name);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(verifycopay.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("SSN", SSN);
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("type", type);
        request.getSession().setAttribute("amount", amount);
        
        if(verified && type.equals("Card")){
            response.sendRedirect("receipt.jsp");
        }
        else if(!verified && type.equals("Card")){
            response.sendRedirect("errorpage.jsp");
        }
        else if(type.equals("Check")){
            response.sendRedirect("receipt.jsp");
        }
        else if(type.equals("Cash")){
            response.sendRedirect("emailreceipt");
        }
        else{
            response.sendRedirect("errorpage.jsp");
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
