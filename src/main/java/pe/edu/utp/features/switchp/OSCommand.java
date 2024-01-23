package pe.edu.utp.features.switchp;

public sealed interface OSCommand permits LinuxCommand, WindowsCommand {
    public void exec(String cmd);
    public String output();
}
