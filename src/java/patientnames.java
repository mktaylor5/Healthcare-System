import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/patientnames"})
public class patientnames extends HttpServlet {

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
        String name = "None";
        String date = " ";
        String doctor = " ";
        try {
            doctor = helper.getUser();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(patientnames.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM hcs_db.patient_info WHERE Doctor='"+doctor+"';";
        //ArrayList patientList = new ArrayList();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcs_db?SSL=false", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            // need to store in list; name is only set to one name each time
            // also doesn't get names associated with a specific doctors
            // rset.first();
            // CHANGE BACK TO WHILE LATER
            while(rset.next()){
                name = (String)rset.getString("Name");
                //patientList.add(name);
                //rset.next();
        }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(patientnames.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("doctor", doctor);
        //request.getSession().setAttribute("patientList", patientList);
        
        //RequestDispatcher rd = request.getRequestDispatcher("treatmentcontent.jsp");
        //rd.forward(request, response);
        response.sendRedirect("treatmentcontent.jsp");
        //this.getServletContext().getRequestDispatcher("/treatmentcontent.jsp").forward(request, response);
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
