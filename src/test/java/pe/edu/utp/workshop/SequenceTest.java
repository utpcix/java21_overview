package pe.edu.utp.workshop;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SequenceTest {

    @Test
    public void IPv4Test(){

        IPv4 ip = new IPv4(192,168,0,100);
        Integer host = ip.getHostClassC();

        assertTrue("Host is not equals",host == 100);
    }

    public void Ipv4StringTest(){
        IPv4 ip = new IPv4(192,168,0,100);
        String ipv4 = ip.toString();

        assertTrue("IPv4 format is not correct",ipv4.equals("192.168.0.100"));

    }

    public void Ipv4ReverseLookup(){
        IPv4 ip = new IPv4(192,168,0,101);
        String ipv4 = ip.reverseLookupZone();

        assertTrue("Reverse Lookup Zone is not correct",ipv4.equals("100.0.168.192.in-addr.arpa"));

    }
}
