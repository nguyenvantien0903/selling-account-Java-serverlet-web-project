/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BillingsDAO;
import dal.PlansDAO;
import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Billing;
import model.Plan;
import model.User;

/**
 *
 * 
 */
public class AdminBillingServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminBillingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminBillingServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        out.println(session.getAttribute("role"));
        try {
            if(session.getAttribute("role") == null)
                response.sendRedirect("../../account/index.jsp");
            String userRole = session.getAttribute("role").toString();
            if(userRole.equals("0")){
                BillingsDAO ud = new BillingsDAO();
                PlansDAO pd = new PlansDAO();
                String sort;

                try{
                    sort = request.getParameter("sort").trim();
                }catch(Exception e){
                    sort = "";
                }
                
                List<Billing> list ;
                list = ud.getAllBillings();
                if(sort.equals("")){

                }else {
                    if(session.getAttribute("sort")!=null){
                        if(sort.equalsIgnoreCase("date")){
                            list=ud.sortListBy(list, 0);
                        }
                        if(sort.equalsIgnoreCase("duration")){
                            list=ud.sortListBy(list, 1);
                        }
                        if(sort.equalsIgnoreCase("price")){
                            list=ud.sortListBy(list, 2);
                        }
                        session.setAttribute("sort", null);
                    }else{
                        if(sort.equalsIgnoreCase("date")){
                            list=ud.revSortListBy(list, 0);
                        }
                        if(sort.equalsIgnoreCase("duration")){
                            list=ud.revSortListBy(list, 1);
                        }
                        if(sort.equalsIgnoreCase("price")){
                            list=ud.revSortListBy(list, 2);
                        }
                        session.setAttribute("sort", sort);
                    }
                }
                
                String plan;
                try{
                    plan = request.getParameter("pro_id");
                }catch(Exception e){
                    plan = "";
                }
                if(plan==null)plan="";
                if(plan.equals("0")||plan.equals("")){
                    
                }else {
                    list=ud.choseOnly(list, plan);
               }
                
                int numPs = list.size();
                int numperPage = 20;
                int numpage = numPs/numperPage + (numPs%numperPage == 0 ? 0 : 1);
                int start, end;
                int page;
    
                String tpage = request.getParameter("page");
                try{
                    page = Integer.parseInt(tpage);
                }catch(NumberFormatException e){
                    page = 1;
                }
                start = (page-1)*numperPage;
                if(page*numperPage > numPs)
                    end = numPs;
                else
                    end = page*numperPage;
                List<Billing> billList = ud.getListByPage(list, start, end);
                request.setAttribute("bill", billList);
                request.setAttribute("num", numpage);
                request.setAttribute("curPage", page);
                request.setAttribute("sort", sort);
                request.getRequestDispatcher("billing.jsp").forward(request, response);
            }
            else
                response.sendRedirect("../../account/index.jsp");
        } catch (Exception e) {
            response.sendRedirect("../../account/login");
        }
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {
            if(session.getAttribute("role") == null)
                response.sendRedirect("../index.jsp");
            String userRole = session.getAttribute("role").toString();
            if(userRole.equals("0")){
                PlansDAO pd = new PlansDAO();
                BillingsDAO ud = new BillingsDAO();
                String key = request.getParameter("key1");
                List<Billing> list;
                if(key.equals("")){
                    list = ud.getAllBillings();
                }else{
                    list = ud.getBillingsByKey(key);
                }
                int numPs = list.size();
                int numperPage = 20;
                int numpage = numPs/numperPage+(numPs%numperPage==0?0:1);
                int start, end;
                int page;
                
                PrintWriter out = response.getWriter();
                String tpage = request.getParameter("page");
                
                try{
                    page = Integer.parseInt(tpage);
                }catch(NumberFormatException e){
                    page = 1;
                }
                start = (page-1)*numperPage;
                if(page*numperPage > numPs)
                    end = numPs;
                else
                    end = page*numperPage;
                List<Billing> billList = ud.getListByPage(list, start, end);
                request.setAttribute("bill", billList);
                request.setAttribute("num", numpage);
                request.setAttribute("curPage", page);
                request.getRequestDispatcher("/admin/billing.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("../login");
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
