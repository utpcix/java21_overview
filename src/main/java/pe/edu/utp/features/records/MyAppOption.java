package pe.edu.utp.features.records;

import java.net.URL;
import java.nio.file.Path;

public sealed interface MyAppOption {
    record ShowHelp() implements MyAppOption {}
    record SaveImage(URL url) implements MyAppOption {}
    record SaveImageInDir(URL url, Path path) implements MyAppOption {}
    record NoOption() implements MyAppOption {}
}
