/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.login;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MUserDb;
import com.wonokoyo.model.formatdata.url.loginaccount.URLParameterFormatLogin;
import com.wonokoyo.model.strukturdata.M_User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Eddy
 */
public class FilterLogin implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterLogin() {
    } 

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	if (debug) log("FilterLogin:DoBeforeProcessing");

	// Write code here to process the request and/or response before
	// the rest of the filter chain is invoked.

	// For example, a logging filter might log items on the request object,
	// such as the parameters.
	/*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
	*/        
    } 

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	if (debug) log("FilterLogin:DoAfterProcessing");

	// Write code here to process the request and/or response after
	// the rest of the filter chain is invoked.
	
	// For example, a logging filter might log the attributes on the
	// request object after the request has been processed. 
	/*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
	*/

	// For example, a filter might append something to the response.
	/*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
	*/        
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

	if (debug) log("FilterLogin:doFilter()");
        DBConnection dBConnection = new DBConnection();
	doBeforeProcessing(request, response);
        MUserDb userDb = new MUserDb(dBConnection);
	HttpSession session = ((HttpServletRequest) request).getSession(true);
        if(session != null){
            if(session.isNew()==true){
                System.out.println("session baru telah terbuat");
                if((request.getParameter(URLParameterFormatLogin.parameterUserId) == null) && (request.getParameter(URLParameterFormatLogin.parameterPass)==null)){
                    session.invalidate();
                    System.out.println("    parameter login masih kosong");
                    dBConnection.closeConn();
                    ((HttpServletResponse) response).sendRedirect("auth.htm");                    
                    return;
                }
                
                M_User user = new M_User();
                user = userDb.selectOneRow(request.getParameter(URLParameterFormatLogin.parameterUserId), request.getParameter(URLParameterFormatLogin.parameterPass));
                if (user.USERID!=null){//((request.getParameter("username").equals("welovebayu"))&&(request.getParameter("pass").equals("0534010163"))){
                    session.setMaxInactiveInterval(60*60);
                    session.setAttribute("userdetil", user);
                    System.out.println("    user id dan password cocok");
                    //response.sendRedirect(response.encodeRedirectURL("maincontroller"));
                    //return;
                }else{
                    session.invalidate();
                    dBConnection.closeConn();
                    ((HttpServletResponse) response).sendRedirect("loginerror.html");
                    System.out.println("    user id dan password tidak cocok");
                    return;
                }
            }else{
                System.out.println("session sudah ada sebelumnya");
                M_User userdetail = (M_User)session.getAttribute("userdetil");
                
                M_User user = userDb.selectOneRow(userdetail.USERID,userdetail.PASS);
                if (user.USERID!=null){//(acc.getUsername().equals("welovebayu") && acc.getPass().equals("0534010163")){
                    System.out.println("    user id dan password cocok");
                    if(request.getParameter("logout") != null){
                        if(request.getParameter("logout").equals("true")){
                            System.out.println("        logout sukses");
                            session.invalidate();
                            dBConnection.closeConn();
                            ((HttpServletResponse) response).sendRedirect("maincontroller");
                            return;
                        }else{
                            System.out.println("        logout error");
                            response.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = response.getWriter();
                            try {
                                 //TODO output your page here
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Servlet MainController</title>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1>Erorr saat logout.</h1>");
                                out.println("<br/>Silahkan <a href=\""+ ((HttpServletResponse) response).encodeURL("maincontroller?logout=true")  +"\">Logout</a> ulang. ");
                                out.println("</body>");
                                out.println("</html>");
                            } finally {
                                out.close();
                            }
                            return;
                        }
                    }
                    /*response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    try {
                         //TODO output your page here
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet MainController</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>selamat, username " + acc.getUsername() + " dengan password "+acc.getPass()+" telah berhasil login.</h1>");
                        out.println("<br/>Silahkan <a href=\""+ response.encodeURL("MainController?logout=true")  +"\">Logout</a> ");
                        out.println("</body>");
                        out.println("</html>");
                    } finally {
                        out.close();
                    }*/
                }
                else{
                    System.out.println("    user id dan password tidak cocok");
                    session.invalidate();
                    dBConnection.closeConn();
                    ((HttpServletResponse) response).sendRedirect("loginerror.html");
                    return;
                }
            }
        }
        dBConnection.closeConn();

	Throwable problem = null;
	try {
	    chain.doFilter(request, response);
	}
	catch(Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
	    // we still want to execute our after processing, and then
	    // rethrow the problem after that.
	    problem = t;
	    t.printStackTrace();
	}

	doAfterProcessing(request, response);

	// If there was a problem, we want to rethrow it if it is
	// a known type, otherwise log it.
	if (problem != null) {
	    if (problem instanceof ServletException) throw (ServletException)problem;
	    if (problem instanceof IOException) throw (IOException)problem;
	    sendProcessingError(problem, response);
	}
    }
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    public void destroy() { 
    }

    /**
     * Init method for this filter 
     */
    public void init(FilterConfig filterConfig) { 
	this.filterConfig = filterConfig;
	if (filterConfig != null) {
	    if (debug) { 
		log("FilterLogin:Initializing filter");
	    }
	}
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("FilterLogin()");
	StringBuffer sb = new StringBuffer("FilterLogin(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
