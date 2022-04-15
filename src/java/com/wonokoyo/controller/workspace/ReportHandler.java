/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.controller.workspace;
import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.utilities.report.RekapRealisasiSewaRuang;
import com.wonokoyo.model.utilities.report.Report;
import com.wonokoyo.model.utilities.report.r_RekapReservasi;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *
 * @author king peter
 */
public class ReportHandler {
    public static void doFilter(HttpServletRequest request,HttpServletResponse response,DBConnection dBConnection,ServletContext context){
        if(request.getParameter("reportname").equalsIgnoreCase("m_jenis")){
            Report.showReport(request, response,dBConnection,context);
        }
        else if(request.getParameter("reportname").equalsIgnoreCase("Reservasi")){
            r_RekapReservasi.showReport(request, response,dBConnection,context);
        }
        else if(request.getParameter("reportname").equalsIgnoreCase("RekapRealisasiSewaRuang")){
            RekapRealisasiSewaRuang.showReport(request, response,dBConnection,context);
        }
    }
}
