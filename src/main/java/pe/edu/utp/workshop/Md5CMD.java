package pe.edu.utp.workshop;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import pe.edu.utp.utils.UTPBinary;
import pe.edu.utp.workshop.support.Md5Option;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;

public class Md5CMD {

    public static String md5(String[] args) throws ParseException, DigestException, NoSuchAlgorithmException {
        String res = "";
        Options options = new Options();
        options.addOption("n","name",false,"Include filename");
        CommandLine cmd = new DefaultParser().parse(options,args);

        boolean includeFilename = cmd.hasOption("n");
        boolean hasTargetfile = cmd.getArgList().size() > 0;  // there are arguments

        Md5Option opt = new Md5Option.NoOption();
        if (!hasTargetfile) return "no target file";
        String filename = cmd.getArgList().getFirst();
        if (includeFilename){
            byte[] data = UTPBinary.readBinData(filename);
            File f = new File(filename);
            // Add here ChecksumWithFilename option
            opt = new Md5Option.ChecksumWithFilename(f.getName(),data);
        }else{
            byte[] data = UTPBinary.readBinData(filename);
            // Add here Checksum option
            opt = new Md5Option.Checksum(data);
        }

        // Proccess Md5 Option
        // Use the interface Md5Option and return
        // 1. "no options" in case of NoOption
        // 2. "md5value" in case of Checksum
        // 3. "filename - md5value" in case of ChecksumWithFilename

        return "to-do";
    }

    private static String md5ToString(byte[] data) throws DigestException, NoSuchAlgorithmException {
        byte[] md5 = UTPBinary.md5( data );
        StringBuilder sb = new StringBuilder();
        for (byte b : md5) {
            sb.append( String.format("%02x",b) );
        }
        return sb.toString();
    }

    private static String getMd5WithFilename(String filename,byte[] data) throws DigestException, NoSuchAlgorithmException {
        String out = "%s - %s";
        return out.formatted(filename, md5ToString(data));
    }

}
