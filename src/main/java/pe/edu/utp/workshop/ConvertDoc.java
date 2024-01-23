package pe.edu.utp.workshop;

import pe.edu.utp.workshop.support.DocFormat;
import pe.edu.utp.workshop.support.PdfFormat;
import pe.edu.utp.workshop.support.PngFormat;

import java.io.File;

public class ConvertDoc {

    public static String convert(DocFormat document, File file){

        /*
         * TODO
         *  Utilizando Matching Pattern Switch devolver un texto según
         *  1. "png ok" si el formato es Png y la función convert devuelve true
         *  2. "png error" si el formato es Png y la función convert devuelve false
         *  3. "pdf ok" si el formato es Pdf y la función convert devuelve true
         *  4. "pdf error" si el formato es Pdf y la función convert devuelve false
         * */

        return switch (document){
            case PngFormat png -> {
                boolean res = png.convert(file);
                if (res == true){
                    yield "png ok";
                }else{
                    yield "png error";
                }
            }
            case PdfFormat pdf -> {
                boolean res = pdf.convert(file);
                if (res == true){
                    yield "pdf ok";
                }else{
                    yield "pdf error";
                }
            }
        };
        //return null;
    }

}
