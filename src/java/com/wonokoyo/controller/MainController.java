/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller;

import com.wonokoyo.controller.workspace.ReportHandler;
import com.wonokoyo.controller.workspace.DB_DirectRequest;
import com.wonokoyo.controller.workspace.FormHandler;
import com.wonokoyo.controller.workspace.TreeMenuHandler;
import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.formatdata.url.form.URLParameterFormatForm;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.URLParameterFormatTableOperation;
import com.wonokoyo.model.formatdata.url.treemenu.URLParameterFormatTreeMenu;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Bayu
 */
public class MainController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String content="";
        DBConnection dBConnection = new DBConnection();
        try {
            if(request.getParameter("reportname") != null){
                ReportHandler.doFilter(request, response, dBConnection,getServletContext());
            }else if (request.getParameter(URLParameterFormatForm.parameterFormMode) != null){
                content = FormHandler.filterForm(request, response,dBConnection);
                PrintWriter out = response.getWriter();                
                out.println(content);
                out.close();
            }else if(request.getParameter(URLParameterFormatTreeMenu.parameterTreeMode) != null){
                content= TreeMenuHandler.filterMenu(request,response,getServletContext(),dBConnection);
                PrintWriter out = response.getWriter();
                out.println(content);
                out.close();
            }else if(request.getParameter(URLParameterFormatTableOperation.parameterTableName) != null){                                
                content= DB_DirectRequest.doProcess(request,response,dBConnection);
                PrintWriter out = response.getWriter();                
                System.out.println();
                
                out.println(content);
                out.close();
            }
            else{
                //response.sendRedirect("index.html");
                //response.sendRedirect(response.encodeRedirectURL("index.html"));
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
            // TODO output your page here
            
            
        } finally {            
            dBConnection.closeConn();
            /*PrintWriter out = response.getWriter();
            out.println(content);
            out.close();*/
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
