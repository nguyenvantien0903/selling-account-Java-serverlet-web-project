/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.PlansDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Plan;

/**
 *
 * 
 */
public class CartServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession();
        String planIndex_raw = request.getParameter("plan");
        String duration_raw = request.getParameter("dur");
        PlansDAO pd = new PlansDAO();
        List<Plan> list = pd.getAllPlans();
        
        int planIndex, duration;
            
        try {
            planIndex = Integer.parseInt(planIndex_raw) - 1;
            duration = Integer.parseInt(duration_raw);
            Plan x = list.get(planIndex);
            List<Item> listItems = (List<Item>) session.getAttribute("itemsInCart");
            if(listItems == null)
                listItems = new ArrayList<>();
            // in ra loi
//            if(listItems.size()==2){
//                pr.println("<script type=\"text/javascript\">");
//                pr.println("alert('Too much');");
//                pr.println("</script>");
//                
////                request.getRequestDispatcher("/plans").forward(request, response);
////                response.sendRedirect("./plans");
//                return;
//            }
            listItems.add(new Item(x, duration));
            session.setAttribute("itemsInCart", listItems);
            request.setAttribute("itemsInCart", listItems);
        } catch (Exception e) {
            System.err.println(e);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
