/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sync;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Order;

/**
 *
 * @author dungt
 */
public class SearchOrderController extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession(true);
            Account account = (Account) session.getAttribute("LOGIN_USER");
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            List<Order> orderList = new OrderDAO().
                    getOrdersByPeriodTime(account.getAccId(), from, to);
            if (orderList != null && orderList.size() > 0) {
                request.setAttribute("orderList", orderList);
                request.setAttribute("from", from);
                request.setAttribute("to", to);
                session.setAttribute("urlOrderHistory", "SearchOrderServlet?from=" + from + "&to=" + to);
                request.getRequestDispatcher("order.jsp").forward(request, response);
            } else {
                request.setAttribute("from", from);
                request.setAttribute("to", to);
                request.setAttribute("NO_ORDER", "You don't have any order from " + from + " to " + to);
                request.getRequestDispatcher("order.jsp").forward(request, response);
            }
        } catch (Exception e) {
            log("Error at SearchOrderController: " + e.toString());
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
