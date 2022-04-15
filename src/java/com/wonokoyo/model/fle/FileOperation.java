/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.fle;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jakarta.servlet.ServletContext;


/**
 *
 * @author Bayu
 */
public class FileOperation {
    public static String readFile(String filePath,ServletContext context){        
        InputStream is =  context.getResourceAsStream("/"+filePath);        
        String text="";        
        if (is != null) {
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                while ((line = reader.readLine()) != null) {
                    text = text + line;
                }
            } catch (IOException ex) {
                System.out.println("Error membaca file "+filePath+" : "+ ex);
            }

        }else{
            System.out.println("Error file "+filePath+" tidak ditemukan! ");
        }
            
        
        return text;
    }
}
