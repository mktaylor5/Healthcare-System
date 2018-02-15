import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/verifypayment"})
public class verifypayment extends HttpServlet {

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
    
    public boolean validExpDate(int month, int year){
        // funcion that checks if date entered (MM/YY) is in the future
        Calendar calendar = Calendar.getInstance();
        int todayM = Calendar.getInstance().get(Calendar.MONTH);
        int todayY = Calendar.getInstance().get(Calendar.YEAR);

        if(todayY > year){
            return true;
        }
        // !- year is now less than or equal to current year
        else if(todayM == month){
            return false;
        }
        else{
            return false;
        }
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
        //String invoiceNumber = (String)request.getSession().getAttribute("invoiceNum");
        int invoice = (int) request.getSession().getAttribute("invoiceNum");
        //String amount = (String)request.getSession().getAttribute("totalCost");
        int cost = (int) request.getSession().getAttribute("totalCost");
        
        String cardHolder = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNum");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        int expMonth = parseInt(month);
        int expYear = parseInt(year);
        String expDate = month + "/" + year;
        String CVV = request.getParameter("CVV");
        
        /*boolean verified = false;
        
        if((cardNumber.length() == 16) && (CVV.length() == 3) && (helper.validExpDate(expMonth, expYear))){
            verified = true;
        }*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO hcs_db.online_payments (InvoiceNum, CCName, CCNum, ExpDate, CVV, Amount) VALUES(?,?,?,?,?,?);");
            stmt.setInt(1, invoice);
            stmt.setString(2, cardHolder);
            stmt.setString(3, cardNumber);
            stmt.setString(4, expDate);
            stmt.setString(5, CVV);
            stmt.setInt(6, cost);
            stmt.executeUpdate();        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getSession().setAttribute("name", cardHolder);
        request.getSession().setAttribute("invoice", invoice);
        request.getSession().setAttribute("amount", cost);
        
        response.sendRedirect("onlinereceipt.jsp");
        
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
