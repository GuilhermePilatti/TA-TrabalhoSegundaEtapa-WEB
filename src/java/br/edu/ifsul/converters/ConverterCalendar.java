package br.edu.ifsul.converters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ws
 */
@FacesConverter(value = "converterCalendar")
public class ConverterCalendar implements Converter, Serializable {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Calendar retorno = Calendar.getInstance();
        try {
            retorno.setTime(sdf.parse(value));
            return retorno;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Calendar obj = (Calendar) value;
        return sdf.format(obj.getTime());
    }
    
}
