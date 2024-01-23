package pe.edu.utp.workshop.support;

public sealed interface Md5Option {
    record NoOption() implements Md5Option{};
    record Checksum(byte[] data) implements Md5Option{};
    record ChecksumWithFilename(String filename, byte[] data) implements Md5Option{};
}
