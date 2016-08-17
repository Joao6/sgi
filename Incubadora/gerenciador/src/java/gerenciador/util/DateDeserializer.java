/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author William
 */
public class DateDeserializer implements JsonDeserializer<Date>{

   @Override
   public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
      String date = je.getAsString();
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
      try {
         return formatter.parse(date);
      } catch (ParseException e) {
         System.out.println("Falhou ao converter data! " + e);
         return null;
      }
   }
   
}