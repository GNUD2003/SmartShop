/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.PlantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author dungt
 */
public class UpdatePlantController extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            if (action != null) {
                String name = request.getParameter("name");
                String imgPath = request.getParameter("imgPath");
                int price = Integer.parseInt(request.getParameter("price"));
                String description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateId = Integer.parseInt(request.getParameter("cateId"));
                switch (action) {
                    case "updatePlant":
                        int pid = Integer.parseInt(request.getParameter("pid"));
                        boolean check1 = new PlantDAO().updatePlantInfo(pid, name, imgPath, price, description, status, cateId);
                        if (check1) {
                            request.setAttribute("MSG_SUCCESS", "You have successfully updated the plant information!");
                        } else {
                            request.setAttribute("MSG_ERROR", "You have failed to update plant information!");
                        }
                        break;
                    case "createPlant":
                        boolean check2 = new PlantDAO().insertNewPlant(name, imgPath, price, description, status, cateId);
                        if (check2) {
                            request.setAttribute("MSG_SUCCESS", "You have successfully created new plant!");
                        } else {
                            request.setAttribute("MSG_ERROR", "You have failed to create new plant!");
                        }
                        System.out.println("1");
                        break;
                        
//                    case "deletePlant":
//                        String id = request.getParameter("pid");
//                    
//                       System.out.println("1");
//                        boolean check3 = new PlantDAO().delete(id);
//                       if (check3) {
//                           request.setAttribute("MSG_SUCCESS", "You have successfully deleted the plant!");
//                        } else {
//                            request.setAttribute("MSG_ERROR", "You have failed to delete the plant!");                        }
//
//                        
//                        break;
                }
            } else {
                request.setAttribute("MSG_ERROR", "Oops, something went wrong! Try later!");
            }
        } catch (Exception e) {
            log("Error at UpdatePlantController: " + e.toString());
        } finally {
            request.getRequestDispatcher("AdminManagePlantController").forward(request, response);
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
