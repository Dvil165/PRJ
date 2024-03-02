/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.servlet;

import duytb.cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author baodu
 */
@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/RemoveFromCartServlet"})
public class RemoveFromCartServlet extends HttpServlet {

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
        try  {
            //1. cust goes to his/her cart place
            // o day dung false tai vi o phia server co ss time-out
            // con o phia client chi la static ma thoi
            HttpSession session = request.getSession(false);
            if (session != null){
                //2. cust takes cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                // co cart moi xoa duoc
                if (cart != null){
                    //3. cust takes items
                    Map<String, Integer> items = cart.getItems();
                    if(items != null){
                        //4. cust remove item from items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null){
                            for(String item : selectedItems){
                                cart.removeItemFromCart(item);
                            }// remove action is ok
                            session.setAttribute("CART", cart);
                        }// users must check at least 1 item
                    }
                }
                
            }
        } finally {
            //refresh --> call prev function again using 
            // URL rewriting
            String urlRewrite = "DispatchServlet"
                    + "?btnAction=View your cart";
            // vi trung btnAction nen no se confuse function
            // => sendRedirect
            response.sendRedirect(urlRewrite);
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
