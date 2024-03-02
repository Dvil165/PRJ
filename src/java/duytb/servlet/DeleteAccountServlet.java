/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.servlet;

import duytb.users.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author baodu
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "errors.html";

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
        //1. get all user info param
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String url = ERROR_PAGE;
        try {
            //2. call model
            //   new dao obj
            UsersDAO dao = new UsersDAO();
            //   call method of that dao
            boolean result = dao.deleteAccount(username);
            //3. process result
            if (result) {
                // if ok, call pre function by url rewriting
                url = "DispatchServlet"
                        + "?btnAction=Search"
                        + "&txtSearchValue=" +searchValue;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            //forward ko dc dung
            /* vi tham so se trung => array => goi thang dau tien, ai ma biet
            no la cai gi?*/
            response.sendRedirect(url);
            
            
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
