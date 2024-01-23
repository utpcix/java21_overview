package pe.edu.utp.features.records;

import org.apache.commons.cli.*;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class MiniWget {

    /*
    *
    * App CLI Demo para descargar imagenes desde internet
    * Para usarlo se debe pasar argumentos en la línea de comandos
    *
    * */

    public static void main(String[] args) throws ParseException, IOException {

        // MiniWGet [OPTIONS] URL
        // MiniWGet [option1 value1 option2 value2 ...] argument1
        // MiniWGet   -d     /home                      https://image-site.com/img.png

        // Build CommandLine Options
        Options options = new Options();
        options.addOption("h","help",false,"Show this help");       // Boolean Option
        //Option dirOption = Option.builder().option("d").longOpt("dir").hasArg().optionalArg(true).desc("Directory for files").build();
        options.addOption("d","dir", true,"Directory for files");   // Option with Arg
        //options.addOption(dirOption);
        CommandLine cmd = new DefaultParser().parse(options,args);

        // Test with https://i.pinimg.com/originals/4c/33/02/4c33020023bb139b964dae546b32615f.jpg

        // Make App Options
        MyAppOption opt = new MyAppOption.NoOption();
        URL url = null;
        boolean callHelp = cmd.hasOption("h");
        boolean hasURL = cmd.getArgList().size() > 0;  // there are arguments
        boolean hasDir = cmd.hasOption("d");
        boolean hasNoOptions = (hasURL == false) && (hasDir == false);
        // Show Help in case "h" are present in arguments or there are no url/dir args
        if (callHelp || hasNoOptions){
            opt = new MyAppOption.ShowHelp();
        }else {
            if (hasURL) {
                url = new URL(cmd.getArgList().getFirst());
                System.out.println("url = " + url);
            } else {
                throw new IllegalArgumentException("URL required");
            }
            String dirPath = null;
            if (hasDir) {
                dirPath = cmd.getOptionValue("d");
                File file = new File(dirPath);
                if (!file.isDirectory()) {
                    throw new IllegalArgumentException("Dir cannot be a File");
                }
                System.out.println("dirPath = " + dirPath);
                opt = new MyAppOption.SaveImageInDir(url, Path.of(dirPath));
            } else {
                opt = new MyAppOption.SaveImage(url);
            }
        }

        // Process Cli App Option
        switch (opt){
            case MyAppOption.NoOption() -> showMyAppHelp(options);
            case MyAppOption.ShowHelp() -> showMyAppHelp(options);
            case MyAppOption.SaveImage(URL src_url) -> saveImageFromURL(src_url);
            case MyAppOption.SaveImageInDir(URL filename, Path path)
                    -> saveImageFromURL(filename,path);
        };


    }

    private static void showMyAppHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("MiniWGet [OPTIONS] URL",options);
        System.exit(0);
    }

    private static void saveImageFromURL(URL url) throws IOException {
        saveImageFromURL(url, Path.of("."));
    }

    private static void saveImageFromURL(URL url, Path path) throws IOException {
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        String strFilename = new File(url.getPath()).getName();
        String file = path.toString() + FileSystems.getDefault().getSeparator() + strFilename;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }

}
