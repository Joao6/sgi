/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

/**
 *
 * @author William
 */
public class GSONConverter {

   private static GsonBuilder gsonBuilder;

   private static GsonBuilder gsonBuilderWithHour;

   private static Gson gson;

   private static Gson gsonWithHour;

   private static GsonBuilder getGsonBuilder() {
      if (gsonBuilder == null) {
         gsonBuilder = new GsonBuilder();
         gsonBuilder.setDateFormat("yyyy-MM-dd");
      }
      return gsonBuilder;
   }

   private static GsonBuilder getGsonBuilderWithHour() {
      if (gsonBuilderWithHour == null) {
         gsonBuilderWithHour = new GsonBuilder();
         gsonBuilderWithHour.setDateFormat("yyyy-MM-dd");
      }
      return gsonBuilderWithHour;
   }

   private static Gson getGson() {
      if (gson == null) {
         gson = getGsonBuilder().create();
      }
      return gson;
   }

   private static Gson getGsonWithHour() {
      if (gsonWithHour == null) {
         gsonWithHour = getGsonBuilderWithHour().create();
      }
      return gsonWithHour;
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public static Object convert(String jsonData, Class classe) {
      try {
         return getGsonWithHour().fromJson(corrigeJsonData(jsonData), classe);
      } catch (Exception e) {
         return getGson().fromJson(corrigeJsonData(jsonData), classe);
      }
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public static Object convert(String jsonData, Type type) {
      try {
         return getGsonWithHour().fromJson(corrigeJsonData(jsonData), type);
      } catch (Exception e) {
         return getGson().fromJson(corrigeJsonData(jsonData), type);
      }
   }

   private static String corrigeJsonData(String jsonData) {
      return jsonData.replace(":\"\"", ":null");
   }

}