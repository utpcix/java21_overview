package pe.edu.utp.features.switchp;

public class PatternSwitch {


    public static String showDataType(Object object){
        // Pattern Matching for data types
        return switch (object){
            case Integer i -> String.format("int %d",i);
            case String s -> String.format("String %s",s);
            case null -> String.format("Null Info");
            case Ticket t -> String.format("Ticket %d:%s", t.id(), t.event());
            default -> String.format("Unknown");
        };
    }

    public static String rubrica(Integer nota){
        // Using Guards Condition
        return switch (nota){
            case Integer n when (n>= 0 && n<=5) -> "Muy Bajo";
            case Integer n when (n> 5 && n<=10) -> "Bajo";
            case Integer n when (n> 10 && n<=15) -> "Regular";
            case Integer n when (n> 15 && n<=20) -> "Alto";
            default -> "Fuera del rango";
        };
    }

    public static String ExecOSCommand(OSCommand cmd){
        // Exhausting Switch with Sealing Class
        return switch (cmd){
            case LinuxCommand linux -> {
                System.out.println("Linux command exec...");
                linux.exec("ls");
                yield "linux cmd done";
            }
            case WindowsCommand win -> {
                System.out.println("Windows command exec...");
                win.exec("dir");
                yield "windows cmd done";
            }
            // No need for 'default'
        };
    }


    public static void main(String[] args) {

        String html = "<html><body>App</body></html>";
        int edad = 19;
        String nullString = null;
        Ticket ticket = new Ticket(1,"Webinar Java 21");

        System.out.println(showDataType(ticket));

        for (int i = 0; i <= 20; i++) {
            System.out.println(rubrica(Integer.valueOf(i)));
        }

        ExecOSCommand(new LinuxCommand("student"));

    }

}
