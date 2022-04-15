/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller;

import com.wonokoyo.controller.workspace.TreeMenuHandler;
import com.wonokoyo.model.MMenuDb;
import com.wonokoyo.model.fle.FileOperation;
import com.wonokoyo.model.utilities.report.Report;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Bayu
 */
public class ServletTesting extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       // response.setContentType("text/xml;charset=UTF-8");
         //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletTesting</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletTesting at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            //System.out.println(menudb.getAllMenuToXMLforTreepanel());
            //ServletContext context = getServletContext();

            //out.println(FileOperation.readFile("form/master/m_jenis.html",context));
        //} finally {
            
            //out.close();
        //}
        


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
