package pe.edu.utp.workshop.support;

public class ASCIIReport {
    public String data;
    public String encoding;

    public ASCIIReport(){}

    public ASCIIReport(String data, String encoding) {
        this.data = data;
        this.encoding = encoding;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
