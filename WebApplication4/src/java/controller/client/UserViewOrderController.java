/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.client;

import dao.OrderDAO;
import dao.OrderStatusDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Order;

/**
 *
 * @author dungt
 */
public class UserViewOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            HttpSession session = request.getSession();
            session.setAttribute("destPage", "order");
            Account account = (Account) session.getAttribute("LOGIN_USER");

            Map<Integer, String> listOrderStatus = new OrderStatusDAO().getOrderStatus();
            session.setAttribute("listOrderStatus", listOrderStatus);

            String orderStatus = request.getParameter("orderStatus");

            OrderDAO orderDAO = new OrderDAO();
            if (orderStatus == null || orderStatus.isEmpty()) {
                List<Order> orderList = orderDAO.getOrders(account.getAccId());
                if (orderList.isEmpty()) {
                    request.setAttribute("NO_ORDER", "Don't have any order!");
                    session.setAttribute("urlOrderHistory", "UserViewOrderController");
                    request.getRequestDispatcher("order.jsp").forward(request, response);
                } else {
                    request.setAttribute("orderList", orderList);
                    request.setAttribute("orderStatus", 0);
                    session.setAttribute("urlOrderHistory", "UserViewOrderController");
                    request.getRequestDispatcher("order.jsp").forward(request, response);
                }
            } else {
                int orderStatusNum = Integer.parseInt(orderStatus);
                List<Order> orderList = orderDAO.getOrderByStatus(account.getAccId(), orderStatusNum);
                if (orderList.isEmpty()) {
                    request.setAttribute("NO_ORDER", "Don't have any " + listOrderStatus.get(orderStatusNum) + " order!");
                    request.setAttribute("orderStatus", orderStatus);
                    session.setAttribute("urlOrderHistory", "UserViewOrderController?orderStatus=" + orderStatus);
                    request.getRequestDispatcher("order.jsp").forward(request, response);
                } else {
                    request.setAttribute("orderList", orderList);
                    request.setAttribute("orderStatus", orderStatus);
                    session.setAttribute("urlOrderHistory", "UserViewOrderController?orderStatus=" + orderStatus);
                    request.getRequestDispatcher("order.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            log("Error at UserViewOrderController: " + e.toString());
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
