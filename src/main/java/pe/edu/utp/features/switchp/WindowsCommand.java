package pe.edu.utp.features.switchp;

public final class WindowsCommand implements OSCommand{

    private String user;

    public WindowsCommand(String user) {
        this.user = user;
    }

    @Override
    public void exec(String cmd) {
        System.out.println(String.format("preparing cmd shell for %s",cmd));
    }

    @Override
    public String output() {
        return "cmd output";
    }


}
