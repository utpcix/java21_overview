package pe.edu.utp.features.switchp;

public final class LinuxCommand implements OSCommand{

    private String user;

    public LinuxCommand(String user) {
        this.user = user;
    }

    @Override
    public void exec(String cmd) {
        System.out.println(String.format("preparing ssh shell for %s",cmd));
    }

    @Override
    public String output() {
        return "linux output";
    }
}
