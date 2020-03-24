package com.play.poc.strings;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringEncodingClient {

   public static void main(String[] args){
       try{

           String textwithaccent="Amélie";
           String textwithletter="Th�s �s a text with accent";
           System.out.println("Original Text :: "+textwithaccent);

           String text1 = new String(textwithaccent.getBytes(StandardCharsets.UTF_8));
           String text2 = new String(textwithletter.getBytes(StandardCharsets.UTF_8));

           System.out.println("Text1 :: "+text1);
           System.out.println("Text2 :: "+text2);


           byte[] bText1 = textwithaccent.getBytes(StandardCharsets.UTF_8);
           String r1 = new String(bText1);
           System.out.println(">>> R1 :: "+r1);



       }catch(Exception ex){
          ex.printStackTrace();
       }
   }
}
