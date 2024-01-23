package pe.edu.utp.workshop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

public class IPv4 {

    private SequencedCollection<Integer> octets;

    public IPv4(Integer octect1, Integer octect2, Integer octect3, Integer octect4) {
        // Create an ArrayList, then add and addLast octects
    }

    public Integer getHostClassC(){
        // Return the Class C Host (the last octect)
        return null;
    }

    @Override
    public String toString() {
        // Return IPv4 in format: o1.o2.o3.o4
        return null;
    }

    public String reverseLookupZone(){
        // Return IPV4 in reverse lookup: o4.o3.o2.o1.in-addr.arpa
        return null;
    }
}
