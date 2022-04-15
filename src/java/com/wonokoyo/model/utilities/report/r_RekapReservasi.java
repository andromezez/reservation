/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model.utilities.report;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.strukturdata.M_User;
import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
/**
 *
 * @author Indra
 */
public class r_RekapReservasi extends HttpServlet{
    

    public static void showReport( HttpServletRequest request, HttpServletResponse response,DBConnection con,ServletContext context)
            
    {
        try{

            try {
                                    
                    HttpSession session =  request.getSession(false);
                    M_User user = (M_User)session.getAttribute("userdetil");                              

                    String reportPath = context.getRealPath("/jasperreportxmlfile/"+request.getParameter("reportname"));

                    String imagePath=context.getRealPath("/jasperreportxmlfile/wnky.jpg");

                    String pdfPath=context.getRealPath("/jasperreportxmlfile/");

                    Map param = new HashMap();
                    param.put("User", user.USERID);
                    param.put("gambar", imagePath);
                    param.put("tglAwal", request.getParameter("awal"));
                    param.put("tglAkhir", request.getParameter("akhir"));
                    param.put("namabp", request.getParameter("namabp"));
                    param.put("item", request.getParameter("item"));
                   
                    JasperReport JRpt = JasperCompileManager.compileReport(reportPath+".jrxml");

                    JasperPrint JPrint = JasperFillManager.fillReport(JRpt, param, con.getConnection());
                     response.setContentType("application/pdf");
                     response.addHeader("Content-Disposition","attachment; filename="+request.getParameter("reportname")+".pdf" );

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, JPrint);
			
			OutputStream ouputStream = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

			try 
			{
				exporter.exportReport();
			} 
			catch (JRException e) 
			{
				throw new ServletException(e);
			}
			finally
			{
				if (ouputStream != null)
				{
					try
					{
						ouputStream.close();
					}
					catch (IOException ex)
					{
					}
				}
			}
                } catch (Exception rptexcpt) {
                System.out.println("Report Can’t view because : " + rptexcpt);
                }
           }
          catch(Exception ex){
           System.out.println(ex.toString());
          }
    }
}
