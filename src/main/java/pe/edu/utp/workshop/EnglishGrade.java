package pe.edu.utp.workshop;

public class EnglishGrade {

    public enum Definition {Excellent, VeryGood, Satisfying, Sufficient, Unsatisfactory};

    public static Definition result(Integer grade){
        Definition res = Definition.Unsatisfactory;

        /*
         * TODO
         *  Utilizar (Guard Condition) con Matching Pattern Switch para devolver un valor para Definition
         *  1. Excellent si está entre 90 - 100
         *  2. VeryGood si está entre 70 - 89
         *  3. Satisfying si está entre 50 y 69
         *  4. Sufficient si está entre 30 y 49
         *  5. Unsatisfactory si está entre 0 y 29
         * */

        return res;
    }

}
