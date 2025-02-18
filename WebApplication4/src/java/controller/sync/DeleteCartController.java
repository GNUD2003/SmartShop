/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sync;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

/**
 *
 * @author dungt
 */
public class DeleteCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int pid = Integer.parseInt(request.getParameter("pid"));
            HttpSession session = request.getSession();
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            if (carts == null) {
                carts = new LinkedHashMap<>();
            }
            if (carts.containsKey(pid)) {
                carts.remove(pid);
            }
            if (carts.size() > 0) {
                session.setAttribute("carts", carts);
                // Save cookie contain list cart to client
                Cookie[] cookies = request.getCookies();
                // Remove old cookie "cart" from client if it existed
                Cookie cartCookie = null;
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("cart")) {
                        cartCookie = cooky;
                        cartCookie.setMaxAge(0);
                        response.addCookie(cartCookie);
                    }
                    if (cartCookie != null) {
                        break;
                    }
                }
                // Convert carts to string for save it to cookie
                String cart = "";
                for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                    int plantId = entry.getKey();
                    Cart cartEl = entry.getValue();
                    if (cart.equals("")) {
                        cart = Integer.toString(plantId) + ":" + Integer.toString(cartEl.getQuantity());
                    } else {
                        cart += "-" + Integer.toString(plantId) + ":" + Integer.toString(cartEl.getQuantity());
                    }
                }
                Cookie cookieCart = new Cookie("cart", cart);
                cookieCart.setMaxAge(60 * 60 * 24);
                response.addCookie(cookieCart);
            } else {
                session.removeAttribute("carts");
                Cookie[] cookies = request.getCookies();
                // Remove old cookie "cart" from client if it existed
                Cookie cartCookie = null;
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("cart")) {
                        cartCookie = cooky;
                        cartCookie.setMaxAge(0);
                        response.addCookie(cartCookie);
                    }
                    if (cartCookie != null) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at DeleteCartController: " + e.toString());
        } finally {
            response.sendRedirect("CartController");
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
