/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.servlet;

import duytb.users.UsersDAO;
import duytb.users.UsersDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author baodu
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
    private final String SEARCH_PAGE = "search.html";
    private final String RESULT_PAGE = "search.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String searchValue = request.getParameter("txtSearchValue");
        String url = SEARCH_PAGE;
        try {
            if (!searchValue.trim().isEmpty()) {
                UsersDAO dao = new UsersDAO();
                // call DAO
                // new DAO
                // call method of DAO
                dao.searchLastname(searchValue);
                List<UsersDTO> result = dao.getAccounts();
                url = RESULT_PAGE;
                request.setAttribute("SEARCH_RESULT", result);
                // process result
            }//end of user typed a valid value
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // neu dung sendredirect se xoa bo ket qua cua minh nen dung forward
            // dung sendredi trong case khac
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
        }
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
