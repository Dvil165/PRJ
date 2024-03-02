/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
// De y dong nay, khi nao nen @ khi nao nen web.xml
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACC_CONTROLLER = "DeleteAccountServlet";
    private final String STARTUP_CONTROLLER = "StartUpServlet";
    private final String ADD_TO_CART_CONTROLER = "AddToCartServlet";
    private final String REMOVE_FROM_CART_CONTROLER = "RemoveFromCartServlet";
    
    private final String VIEW_CART_PAGE = "viewCart.jsp";

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
        //1. wich btn user clicked?
        String button = request.getParameter("btnAction");
        String url = LOGIN_PAGE;
        
        try {
            if(button == null){ //first time request and app starts
                // chuyen sang trang login
                // chekc cookies to determine which page to transfer
                url = STARTUP_CONTROLLER;
            } else if (button.equals("Login")){
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")){
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("delete")){
                url = DELETE_ACC_CONTROLLER;
            } else if (button.equals("Add to cart")){
                url = ADD_TO_CART_CONTROLER;
            } else if (button.equals("View your cart")){
                url = VIEW_CART_PAGE;
            } else if (button.equals("Remove selected itmes")){
                url = REMOVE_FROM_CART_CONTROLER;
            }
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
