package pe.edu.utp.workshop.support;

import java.io.File;

public final class PngFormat implements DocFormat{

    @Override
    public boolean convert(File file) {
        return false;
    }
}
