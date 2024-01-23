package pe.edu.utp.workshop;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import java.security.DigestException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

public class RecordTest {

    @Test
    public void md5CmdTest() throws ParseException, DigestException, NoSuchAlgorithmException {
        String[] args = {"src\\main\\resources\\rainbug.jpg"};
        String res = Md5CMD.md5(args);
        assertTrue("MD5 incorrect", res.equals("06c790d239d383bcf3aedf4c6c8babe4"));
    }

    @Test
    public void md5CmdWithFilenameTest() throws ParseException, DigestException, NoSuchAlgorithmException {
        String[] args = {"-n","src\\main\\resources\\rainbug.jpg"};
        String res = Md5CMD.md5(args);
        assertTrue("MD5 incorrect", res.equals("rainbug.jpg - 06c790d239d383bcf3aedf4c6c8babe4"));
    }

    @Test
    public void md5CmdWithNoOptionTest() throws ParseException, DigestException, NoSuchAlgorithmException {
        String[] args = {};
        String res = Md5CMD.md5(args);
        assertTrue("MD5 incorrect", res.equals("no target file"));
    }

}
