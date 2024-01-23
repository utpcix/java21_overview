package pe.edu.utp.features.vthreads;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

public class Virtual {

    public static void main(String[] args) throws IOException {

        run10kThreads();
        //getWebsites();

    }

    private static void run10kThreads() {
        BlockingDeque<String> lista = new LinkedBlockingDeque<>();

        // Exec 10K virtual threads
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()){
            IntStream.range(0,10_000).forEach(i -> {
                var future = executor.submit(() -> {
                        return "task " + String.valueOf(i);
                    });
                try {
                    lista.add(future.get());
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            );
        }
        System.out.println(lista.size());
    }

    public static void getWebsites(){
        List<String> urls = new ArrayList<>(
                List.of("https://openjdk.org/jeps/433",
                        "https://openjdk.org/jeps/444",
                        "https://openjdk.org/jeps/431",
                        "https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html",
                        "https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/ThreadLocal.html",
                        "https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html",
                        "https://docs.oracle.com/javase/tutorial/networking/urls/definition.html"));

        // TODO - Download HTML content from list of URL using Virtual Threads
    }


    // Thanks to: https://stackoverflow.com/questions/3155488/how-to-read-the-https-page-content-using-java
    public static String getHttpsUrl(URL url) throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestProperty ( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0" );
        InputStream ins = con.getInputStream();
        String res =  new String(ins.readAllBytes(), StandardCharsets.UTF_8);
        ins.close();
        return res;
    }
}
