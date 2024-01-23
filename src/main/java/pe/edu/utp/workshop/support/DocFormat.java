package pe.edu.utp.workshop.support;

import java.io.File;

public sealed interface DocFormat permits PdfFormat, PngFormat {
    public boolean convert(File file);
}
