/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.servlet;

import duytb.users.UsersCreateError;
import duytb.users.UsersDAO;
import duytb.users.UsersDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "CreateAccServlet", urlPatterns = {"/CreateAccServlet"})
public class CreateAccServlet extends HttpServlet {

    private final String ERROR_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        //1. get all client's info aka all param
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPass");
        String confirmPassword = request.getParameter("txtConfirmPass");
        String fullname = request.getParameter("txtfullname");
        boolean foundErr = false;
        UsersCreateError errors = new UsersCreateError();
        String url = ERROR_PAGE;
        try {
            //2. check all user's errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLengErr("User name len is not ok");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setPassLengErr("pass len is not ok");
            } else if (!confirmPassword.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmPassNotMatched("pass not matched");
            }
            if (fullname.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengErr("full name len is not ok");
            }
            if (foundErr) {
                // catching a specific attribute then go to error page to show
                request.setAttribute("CREATE_ERRORS", errors);

            } else {//no error
                //3. check all server's errors if have to
                //4. call model
                UsersDAO dao = new UsersDAO();
                UsersDTO dto = new UsersDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }// no error

            //   process result
        } catch (SQLException e) {
            String msg = e.getMessage();
            log("CreateAccServlet _ SQL: " + msg);
            if (msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + "is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException e) {
            log("CreateAccServlet _ Naming: " + e.getMessage());
        } finally {
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
