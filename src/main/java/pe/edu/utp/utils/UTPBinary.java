package pe.edu.utp.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UTPBinary {

    public static void catbin(String filename)
            throws IOException {
        try(BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))
        ){
            byte[] data = in.readAllBytes();
            int idx = 0;
            for (byte item : data) {
                System.out.print("["+item+"]");
                idx++;
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static byte[] readBinData(String filename) {
        try(BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))
        ){
            return in.readAllBytes();
        } catch (IOException e) {
            return new byte[] {};
        }
    }

    public static byte[] readBinData(String filename, int nbytes){
        try(BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))
        ){
            byte[] res = new byte[nbytes];
            in.read(res, 0, nbytes);
            return res;
        } catch (IOException e) {
            return new byte[] {};
        }
    }

    public static byte[] readBinData(String filename, int offset, int nbytes){
        try(BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(filename))
        ){
            byte[] res = new byte[nbytes];
            in.skip(offset);
            in.read(res, 0, nbytes);
            return res;
        } catch (IOException e) {
            return new byte[] {};
        }
    }

    public static void echobin(byte[] data, String filename)
            throws IOException{
        try(BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(filename,true))
        ){
            out.write(data);
        } catch (IOException e) {
            throw e;
        }
    }

    public static byte[] int2Bytes(int val){
        // Crear un byte buffer de 4 bytes
        // (el tipo int tiene un tamaño de 4 bytes)
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(val);
        return bb.array();
    }

    public static byte[] int2BigEndian(int val){
        return int2Bytes(val);
    }

    public static byte[] int2LittleEndian(int val){
        byte[] le = int2Bytes(val);
        byte[] be = new byte[le.length];
        int idx = 0;
        for (int i = le.length-1; i >= 0; i--) {
            be[idx] = le[i];
            idx++;
        }
        return be;
    }

    public static void swapEndian(byte[] data){
        byte[] atad = new byte[data.length];
        int idx = 0;
        for (int i = data.length-1; i >= 0; i--) {
            atad[idx] = data[i];
            idx++;
        }
        // copiar en data
        for (int i = 0; i < atad.length; i++) {
            data[i] = atad[i];
        }
    }

    public static int byteArray2int(byte[] data){
        ByteBuffer bb = ByteBuffer.wrap(data);
        return bb.getInt();
    }

    public static short byteArray2short(byte[] data){
        ByteBuffer bb = ByteBuffer.wrap(data);
        return bb.getShort();
    }

    public static byte[] md5(byte[] data) throws DigestException, NoSuchAlgorithmException {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            MessageDigest msg = (MessageDigest) md.clone();
            msg.update(data);
            return msg.digest();
        } catch (CloneNotSupportedException e) {
            throw new DigestException("No se pudo obtener el message digest:" + e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Algoritmo MD5 no fue encontrado");
        }
    }

}
