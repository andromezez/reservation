/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model.utilities.xpath;
import com.sun.org.apache.xpath.internal.NodeSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.xml.sax.InputSource;
/**
 *
 * @author Bayu
 */
public class XpathProcessing {
    public static NodeList doProcess (String xmlString,String xpathExpr){
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder=null;
        Document doc=null;
        Object result=null;
        try {
            builder = domFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.err.println(ex);
            return null;
        }    
        
        try {           
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlString));
            doc = builder.parse(is);
        } catch (SAXException ex) {
             System.err.println(ex);
            return null;
        } catch (IOException ex) {
             System.err.println(ex);
            return null;
        }
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();                
        try {
            //XPathExpression expr = xpath.compile("//book[author='Neal Stephenson']/title/text()");
            //result = expr.evaluate(doc, XPathConstants.NODESET);
            result = xpath.evaluate(xpathExpr, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
             System.err.println(ex);
            return null;
        }
        NodeList nodes = (NodeList) result;
        /*for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue()); 
        }*/
        return nodes;
    }
    
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
           CharacterData cd = (CharacterData) child;
           return cd.getData();
        }
        return "";
    }
}
