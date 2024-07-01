/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author dungt
 */
public class UpdateOrderController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            if (account != null) {
                String orderStatusTxt = request.getParameter("orderStatuss");
                if (orderStatusTxt != null && !orderStatusTxt.isEmpty()) {
                    int orderStatus = Integer.parseInt(orderStatusTxt);
                    int orderId = Integer.parseInt(request.getParameter("orderId"));
                    if (orderStatus == 1) {
                        boolean check = new OrderDAO().updateOrderStatus(orderId, 3);
                        if (check) {
                            request.setAttribute("MSG_SUCCESS", "Cancel order successfully!");
                            String url = (String) session.getAttribute("urlOrderHistory");
                            request.getRequestDispatcher(url).forward(request, response);
                        } else {
                            request.setAttribute("MSG_ERROR", "Cancel order fail!");
                            String url = (String) session.getAttribute("urlOrderHistory");
                            request.getRequestDispatcher(url).forward(request, response);
                        }
                    } else if (orderStatus == 3) {
                        boolean check = new OrderDAO().updateOrderStatus(orderId, 1);
                        if (check) {
                            request.setAttribute("MSG_SUCCESS", "Order again successfully!");
                            String url = (String) session.getAttribute("urlOrderHistory");
                            request.getRequestDispatcher(url).forward(request, response);
                        } else{
                            request.setAttribute("MSG_ERROR", "Order again fail!");
                            String url = (String) session.getAttribute("urlOrderHistory");
                            request.getRequestDispatcher(url).forward(request, response);
                        }
                    }
                }
            } else {
                response.sendRedirect("HomeController");
            }
        } catch (Exception e) {
            log("Error at UpdateOrderController: " + e.toString());
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
