/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sync;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import model.Account;
import model.Cart;

/**
 *
 * @author dungt
 */
public class CheckOutController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            if (carts != null && !carts.isEmpty()) {
                if (account == null) {
                    session.setAttribute("destPage", "BookingController");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else{
                    // Tinh tong tien
                    double totalMoney = 0;
                    for (Map.Entry<Integer, Cart> entry : carts.entrySet()) {
                        Integer pid = entry.getKey();
                        Cart cart = entry.getValue();

                        totalMoney += cart.getQuantity() * cart.getPlant().getPrice();
                    }
                    request.setAttribute("totalMoney", totalMoney);
                    session.setAttribute("destPage", "");
                    request.getRequestDispatcher("checkout.jsp").forward(request, response);
                }
            } else {
                session.setAttribute("destPage", "home");
                response.sendRedirect("HomeController");
            }
        } catch (Exception e) {
            log("Error at CheckOutController: " + e.toString());
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

   
    @Override
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String note = request.getParameter("note").trim();
            if (note == null || note.isEmpty() || note.equals("")) {
                note = "No notes";
            }

            Account account = (Account) session.getAttribute("LOGIN_USER");

            // Save all to database
            Map<Integer, Cart> carts = (Map<Integer, Cart>) session.getAttribute("carts");
            if (carts != null && !carts.isEmpty()) { // Cart is not empty
                if (account != null) { // User had login
                    boolean result = new OrderDAO().insertOrder(account.getAccId(), carts, name, phone, address, note);
                    if (result) {
                        session.removeAttribute("carts");

                        // Clear cookie name "cart" from client
                        Cookie[] cookies = request.getCookies();
                        for (Cookie cooky : cookies) {
                            if (cooky.getName().equals("cart")) {
                                cooky.setMaxAge(0);
                                response.addCookie(cooky);
                            }
                        }
                        try {
                            String subject = "Your order has been processing";
                            String message = "<!DOCTYPE html>\n"
                                    + "<html lang=\"en\">\n"
                                    + "\n"
                                    + "<head>\n"
                                    + "</head>\n"
                                    + "\n"
                                    + "<body>\n"
                                    + "    <h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                                    + "\n"
                                    + "</body>\n"
                                    + "\n"
                                    + "</html>";
//                            SendMailUtils.send(account.getEmail(), subject, message);
                        } catch (Exception e) {
                            log("Error occur when send mail to user after place order sucessfully!");
                        }
                        request.setAttribute("MSG_SUCCESS", "Your order has been successfully placed!");
                        request.getRequestDispatcher("carts.jsp").forward(request, response);
                    } else {
                        request.setAttribute("MSG_ERROR", "These products are out of stock!");
                        request.getRequestDispatcher("carts.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("HomeController");
                }
            } else {
                response.sendRedirect("HomeController");
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
        }
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
