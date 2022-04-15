/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model.utilities.validation;

/**
 *
 * @author king peter
 */
public class NumberField {

    public NumberField(String fieldname, float value) {
        this.fieldname = fieldname;
        this.value = value;
    }
    
    public String fieldname;
    public float value;
}
