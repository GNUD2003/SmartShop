/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sync;

import dao.PlantDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Plant;

/**
 *
 * @author dungt
 */
public class ViewAllController extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            final int PAGE_SIZE = 6;
            
            HttpSession session = request.getSession();
            session.setAttribute("destPage", "plant");
            
            String category = request.getParameter("category");
            String pagenumber = request.getParameter("pagenumber");
            int page = 1;
            if (pagenumber != null) {
                page = Integer.parseInt(pagenumber);
            }

            PlantDAO dao = new PlantDAO();
            if (category == null || category.isEmpty()) {
                List<Plant> listPlants = dao.getAllPlantsWithPaging(page, PAGE_SIZE);
                int totalPlants = dao.getTotalPlants();
                int totalPage = totalPlants / PAGE_SIZE;
                if (totalPlants % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listPlants", listPlants);
                session.setAttribute("urlHistory", "ViewAllController"
                                                 + ((pagenumber != null) ? ("?pagenumber=" + pagenumber) : ""));
                request.getRequestDispatcher("product.jsp").forward(request, response);
            } else {
                List<Plant> listPlants = dao.getPlantsByCategoryWithPagging(Integer.parseInt(category), page, PAGE_SIZE);
                if (!listPlants.isEmpty()) {
                    int totalPlants = dao.getTotalPlantsByCategory(Integer.parseInt(category));
                    int totalPage = totalPlants / PAGE_SIZE;
                    if (totalPlants % PAGE_SIZE != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("category", category);
                    request.setAttribute("listPlants", listPlants);
                    session.setAttribute("urlHistory", "ViewAllController?category=" + category
                                                 + ((pagenumber != null) ? ("&pagenumber=" + pagenumber) : ""));
                    request.getRequestDispatcher("product.jsp").forward(request, response);
                } else {
                    request.setAttribute("category", category);
                    request.setAttribute("NO_PRODUCT", "There are currently no products in this category");
                    session.setAttribute("urlHistory", "ViewAllController?category=" + category);
                    request.getRequestDispatcher("product.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            log("Error at ViewAllController: " + e.toString());
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
