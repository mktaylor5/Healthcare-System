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

@WebServlet(urlPatterns = {"/dailyreport"})
public class dailyreport extends HttpServlet {

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
        String date = request.getParameter("date");
        int patient1=0, patient2=0, patient3=0, patient4=0, patient5=0;
        
        // Find number of patients
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.appointments WHERE Date='"+date+"';");
            rset.first();
            while(rset != null){
                if(rset.getString("Doctor").equals("doc1") && rset.getString("Cleared").equals("Y")){
                    patient1++;
                }
                if(rset.getString("Doctor").equals("doc2") && rset.getString("Cleared").equals("Y")){
                    patient2++;
                }
                if(rset.getString("Doctor").equals("doc3") && rset.getString("Cleared").equals("Y")){
                    patient3++;
                }
                if(rset.getString("Doctor").equals("doc4") && rset.getString("Cleared").equals("Y")){
                    patient4++;
                }
                if(rset.getString("Doctor").equals("doc5") && rset.getString("Cleared").equals("Y")){
                    patient5++;
                }
                rset.next();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Find health care service income
        int income1=0, income2=0, income3=0, income4=0, income5=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM hcs_db.invoices WHERE Date='"+date+"';");
            rset.first();
            String cost1 = " ";
            int cost = 0;
            while(rset != null){
                if(rset.getString("Doctor").equals("doc1")){
                    cost1 = rset.getString("Cost");
                    cost = Integer.parseInt(cost1);
                    income1+=cost;
                }
                if(rset.getString("Doctor").equals("doc2")){
                    cost1 = rset.getString("Cost");
                    cost = Integer.parseInt(cost1);
                    income2+=cost;
                }
                if(rset.getString("Doctor").equals("doc3")){
                    cost1 = rset.getString("Cost");
                    cost = Integer.parseInt(cost1);
                    income3+=cost;
                }
                if(rset.getString("Doctor").equals("doc4")){
                    cost1 = rset.getString("Cost");
                    cost = Integer.parseInt(cost1);
                    income4+=cost;
                }
                if(rset.getString("Doctor").equals("doc5")){
                    cost1 = rset.getString("Cost");
                    cost = Integer.parseInt(cost1);
                    income5+=cost;
                }
                rset.next();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{    // insert daily report into db to pull for monthly
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            String sql = "INSERT INTO hcs_db.daily_reports (Date, Doc1P, Doc1I, Doc2P, Doc2I, Doc3P, Doc3I, Doc4P, Doc4I, Doc5P, Doc5I) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, date);
            stmt.setInt(2, patient1);
            stmt.setInt(3, income1);
            stmt.setInt(4, patient2);
            stmt.setInt(5, income2);
            stmt.setInt(6, patient3);
            stmt.setInt(7, income3);
            stmt.setInt(8, patient4);
            stmt.setInt(9, income4);
            stmt.setInt(10, patient5);
            stmt.setInt(11, income5);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getSession().setAttribute("date", date);
        request.getSession().setAttribute("patient1", patient1);
        request.getSession().setAttribute("patient2", patient2);
        request.getSession().setAttribute("patient3", patient3);
        request.getSession().setAttribute("patient4", patient4);
        request.getSession().setAttribute("patient5", patient5);
        request.getSession().setAttribute("income1", income1);
        request.getSession().setAttribute("income2", income2);
        request.getSession().setAttribute("income3", income3);
        request.getSession().setAttribute("income4", income4);
        request.getSession().setAttribute("income5", income5);
        response.sendRedirect("viewdailyreport.jsp");
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
