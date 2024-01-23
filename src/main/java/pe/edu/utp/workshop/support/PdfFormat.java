package pe.edu.utp.workshop.support;

import java.io.File;

public final class PdfFormat implements DocFormat{

    @Override
    public boolean convert(File file) {
        return true;
    }
}
