package pe.edu.utp.workshop;

import org.junit.Test;
import pe.edu.utp.workshop.support.ASCIIReport;
import pe.edu.utp.workshop.support.HTMLReport;
import pe.edu.utp.workshop.support.PdfFormat;
import pe.edu.utp.workshop.support.PngFormat;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class PatternMatchingSwitchTest {

    @Test
    public void PrintServiceTest(){

        ASCIIReport textReport = new ASCIIReport("info","utf-8");
        HTMLReport htmlReport = new HTMLReport("<html></html>","alice");

        assertTrue("Precio textReport incorrecto",PrintService.getPrice(textReport) == 0.20f);
        assertTrue("Precio htmlReport incorrecto",PrintService.getPrice(htmlReport) == 0.70f);
        assertTrue("Precio otros objetos incorrecto",PrintService.getPrice(new String("hi")) == 0.0f);

    }

    @Test
    public void ConsoleRequestTest(){

        String[] inputYesList = {"y","Y","yes","Si","SI","s","YES","YeS","YeS","sI","YEs","yES"};

        // Test Answer YES
        for (String input : inputYesList) {
            assertTrue(String.format("Respuesta a comando '%s' incorrecto",input),
                    ConsoleRequest.getResponse(input)== ConsoleRequest.Answer.YES );
        }

        String[] inputNoList = {"n","N","NO","no","No","nO"};

        // Test Answer NO
        for (String input : inputNoList) {
            assertTrue(String.format("Respuesta a comando '%s' incorrecto",input),
                    ConsoleRequest.getResponse(input)== ConsoleRequest.Answer.NO );
        }

    }

    @Test
    public void EnglishGradeGuardTest(){

        /*
         * GRADES
         *  1. Excellent si está entre 90 - 100
         *  2. VeryGood si está entre 70 - 89
         *  3. Satisfying si está entre 50 y 69
         *  4. Sufficient si está entre 30 y 49
         *  5. Unsatisfactory si está entre 0 y 29
         * */

        for (int i = 0; i <= 100; i++) {
            if (i>=0 && i<=29) assertTrue( String.format("%d no es Unsatisfactory",i),
                    EnglishGrade.result(i) == EnglishGrade.Definition.Unsatisfactory );
            if (i>=30 && i<=49) assertTrue( String.format("%d no es Sufficient",i),
                    EnglishGrade.result(i) == EnglishGrade.Definition.Sufficient );
            if (i>=50 && i<=69) assertTrue( String.format("%d no es Satisfying",i),
                    EnglishGrade.result(i) == EnglishGrade.Definition.Satisfying );
            if (i>=70 && i<=89) assertTrue( String.format("%d no es VeryGood",i),
                    EnglishGrade.result(i) == EnglishGrade.Definition.VeryGood );
            if (i>=90 && i<=100) assertTrue( String.format("%d no es Excelent",i),
                    EnglishGrade.result(i) == EnglishGrade.Definition.Excellent);
        }

    }

    @Test
    public void DocFormatTest(){

        File file1 = new File("my-file.txt");

        assertTrue("PNG format must be false", ConvertDoc.convert(new PngFormat(),file1).equals("png error") );
        assertTrue("PDF format must be true", ConvertDoc.convert(new PdfFormat(),file1).equals("pdf ok") );

    }

}
