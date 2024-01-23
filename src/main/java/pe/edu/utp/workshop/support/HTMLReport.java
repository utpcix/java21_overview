package pe.edu.utp.workshop.support;

public class HTMLReport {
    public String html;
    public String author;

    public HTMLReport(){}

    public HTMLReport(String html, String author) {
        this.html = html;
        this.author = author;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
