/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata;

/**
 *
 * @author Bayu
 *
 */

/**
 *Contoh bentuk XML nya : <br/> 
        {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
        {@code <sewaruang> } 
            &nbsp;&nbsp;&nbsp;{@code <level1 titleLevel1="" url="" daun="#daun" id="#id"
                            userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                                pprint="#pprint"> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <level2 titleLevel2="" url="" daun="#daun" id="#id"
                            userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                             pprint="#pprint"> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code </level2> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <level2 titleLevel2="" url="" daun="#daun" id="#id"
                        userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                            pprint="#pprint"> } <br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <level3 titleLevel3="" url="" daun="#daun" id="#id"
                            userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                            pprint="#pprint"> } <br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code </level3> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code </level2> } <br/>
            &nbsp;&nbsp;&nbsp;{@code </level1> } <br/>
            &nbsp;&nbsp;&nbsp;{@code <level1 titleLevel1="" url="" daun="#daun" id="#id"
                        userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                            pprint="#pprint"> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <level2 titleLevel2=""  url="" daun="#daun" id="#id"
                           userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                                pprint="#pprint"> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code </level2> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <level2 titleLevel2=""  url="" daun="#daun" id="#id"
                               userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                                    pprint="#pprint"> } <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code </level2> } <br/>
            &nbsp;&nbsp;&nbsp;{@code </level1> } <br/>            
        {@code </sewaruang> } <br/>
 *
 */
public class TreepanelXMLFormat {
    /**
     * berisi elemen: <br/>
     *     {@code <?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><sewaruang> }
     */
    public static final String rootElAwal = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<sewaruang>";

    /**
     * berisi elemen: {@code </sewaruang> }
     */
    public static final String rootElAkhir = "</sewaruang>";

    /**
     * berisi elemen : <br/>
     * {@code <level1 titleLevel1="#title" url="#url" daun="#daun" id="#id"
               userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                    pprint="#pprint" >}
     * <br/>replace #title dengan string nilainya
     */
    public static final String level1ElAwal = "<level1 titleLevel1=\"#title\" url=\"#url\" daun=\"#daun\" id=\"#id\" "+
                    "userid=\"#userid\" pview=\"#pview\" padd=\"#padd\" pupdate=\"#pupdate\" pdelete=\"#pdelete\" "+
                    "pprint=\"#pprint\" batal_trans=\"#batal_trans\" >";

    /**
     * berisi elemen: {@code </level1> }
     */
    public static final String level1ElAkhir = "</level1>";

    /**
     * berisi elemen : <br/>
     * {@code <level2 titleLevel2=\"#title\"  url=\"#url\" daun="#daun" id="#id"
               userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                    pprint="#pprint" >}
     * <br/>replace #title dan #url dengan string nilainya
     */
    public static final String level2ElAwal = "<level2 titleLevel2=\"#title\"  url=\"#url\" daun=\"#daun\" id=\"#id\" "+
                    "userid=\"#userid\" pview=\"#pview\" padd=\"#padd\" pupdate=\"#pupdate\" pdelete=\"#pdelete\" "+
                    "pprint=\"#pprint\" batal_trans=\"#batal_trans\" >";

    /**
     * berisi elemen: {@code </level2> }
     */
    public static final String level2ElAkhir = "</level2>";

    /**
     * berisi elemen : <br/>
     * {@code <level3 titleLevel3=\"#title\"  url=\"#url\" daun="#daun" id="#id"
               userid="#userid" pview="#pview" padd="#padd" pupdate="#pupdate" pdelete="#pdelete"
                    pprint="#pprint" > }
     * <br/>replace #title dan #url dengan string nilainya
     */
    public static final String level3ElAwal = "<level3 titleLevel3=\"#title\"  url=\"#url\" daun=\"#daun\" id=\"#id\" "+
                    "userid=\"#userid\" pview=\"#pview\" padd=\"#padd\" pupdate=\"#pupdate\" pdelete=\"#pdelete\" "+
                    "pprint=\"#pprint\" batal_trans=\"#batal_trans\" >";

    /**
     * berisi elemen: {@code </level3> }
     */
    public static final String level3ElAkhir = "</level3>";
}
