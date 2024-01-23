package pe.edu.utp.features.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HandlerUtpServer implements HttpHandler {

    private Map<String, Resource> content;
    private Map<String, Resource> status;

    public HandlerUtpServer() {
        content = new HashMap<>();
        status = new HashMap<>();

        // Resources
        Resource index = Resource.ofHTML("<html><body><p>Welcome to UtpServer</p></body></html>");
        Resource notfound = Resource.ofHTML("<html><body><p>404 - NOT FOUND</p></body></html>");
        Resource image = Resource.of("src\\main\\resources\\rainbug.jpg");
        Resource icon = Resource.ofBase64(NANO_DUKE_BASE_64_ENCODED,"image/x-icon");

        // TODO - Add about resource (HTML: <html><body><p>UTP Server</p></body></html> )
        Resource about = Resource.ofHTML("<html><body><p>UTP Server</p></body></html>");

        // Mapping Paths to Resources
        content.put("/", index );
        content.put("/index", index );
        content.put("/index.html", index );
        content.put("/bug.jpg", image );
        content.put("/favicon.ico", icon);
        content.put("/about", about );

        // Status as Resources
        status.put("404",notfound);

    }

    private void makeResponse(HttpExchange exchange, int status, Resource resource) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", resource.type());
        exchange.getResponseHeaders().set("Content-Length", String.valueOf(resource.data().length));
        if ("HEAD".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(status, -1);
            return;
        }
        exchange.sendResponseHeaders(status, resource.data().length);
        try (var stream = exchange.getResponseBody()) {
            stream.write(resource.data());
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        Resource resource = content.get(path);

        if (resource != null){
            makeResponse(exchange, 200, resource);
        }else {
            // Not found
            resource = status.get("404");
            makeResponse(exchange, 404, resource);

        }

    }

    private static final String NANO_DUKE_BASE_64_ENCODED = // https://jdk.java.net
            """
            AAABAAEAEBAAAAAAAABoBQAAFgAAACgAAAAQAAAAIAAAAAEACAAAAAAAAAAAAAAAAAAAAAAAAAAA\
            AAAAAAAAAAAAAACAAACAAAAAgIAAgAAAAIAAgACAgAAAwMDAAICAgAAAAP8AAP8AAAD//wD/AAAA\
            /wD/AP//AAD///8AwwAAAM8AAADbAAAA5wAAAPMAAAD/AAAA/xcXAP8vLwD/U1MA/2tnAP9/fwD/\
            i4sA/5eXAP+jowD/r68A/7u7AP/HxwD/z8cA/9vbAP/n5wD/8/MA//v3ACsrUwA3N18AQ0NrAE9P\
            dwBXV38AY2OLAG9vlwB/f6cAi4uzAJeXvwCnp88As7PbAL+/5wDHx+8Az8/3AFMrKwBfNzcAa0ND\
            AHdPTwCDW1sAj2dnAJtzcwCnf38As4uLAL+XlwDLo6MA16+vAOO7uwDrw8MA+9PTAC9TLwA7XzsA\
            R2tHAFN3UwBfg18Aa49rAHebdwCDp4MAj7OPAJu/mwCny6cAs9ezAL/jvwDL78sA1/vXAIdvlwCX\
            f6cAp4+3ALObwwDDq9MAz7ffANvD6wCLl28Ak6N7AJ+vhwCru5MAt8efAMvbswDX578A4/PLAAtv\
            mwAPe6MAE4evABePtwAbm8MAF6fPABuz2wAjv+cAK8vzADfX/wD/8/8A/+v/AP/f/wD/0/8A/8f/\
            AP+3/wD/o/8A/5f/AP+D/wD/a/8A/0v/AOcA5wDXANcAwwDHALcAtwCjAKcAlwCXAIsAiwB3AHcA\
            ZwBnAE8AUwAvADMA6///AOf//wDf//8A0///ALv//wCb//8AP///AADz9wAA5+sAAN/fAADT0wAA\
            x8cAALu7AACzrwAAp6cAAJuXAACXjwAAf38AAHd3AABfXwAAR0cAADMzAP//9wD//+cA///bAP//\
            xwD//7sA//+XAP//fwD//1MA7+8AAOPjAADX1wAAy8sAAL+/AACzswAAo6MAAJeTAACLgwAAe3sA\
            AGdrAABbWwAAR0sAACMjAADz//MA3//nANf/1wDD/88Au/+7AKP/owCH/4cAZ/9nADf/NwAL/wAA\
            APMAAADrAAAA4wAAANcAAADLAAAAvwAAALMAAACnAAAAnwAAAJMAAACHAAAAfwAAAHcAAABvAAAA\
            ZwAAAF8AAABTAAAARwAAADcAAAAjAAD38/8A6+v/AN/f/wDT0/8Aw8P/AK+v/wCbm/8Ai4v/AHd3\
            /wBnZ/8AU1P/AEND/wAvL/8AFxf/AAAARwAAAFcAAABnAAAAcwAAAH8AAACLAAAAlwAAAKMAAACv\
            AAAAuwAAAMMAAADPAAAA2wAAAOcAAADzAHwAVACbAGkAugB+ANkAkwDwAKoA/yS2AP9IwgD/bM4A\
            /5DaAP+05gDw8PAA3NzcAMjIyAC0tLQAoKCgAICAgAAAAP8AAP8AAAD//wD/AAAA/wD/AP//AAD/\
            //8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAICAgAAAAAAAgAAA8I\
            AAACAA8AAAAAAAgAAAgPDw8PDw8PAAAAAAAAAAAAAA8PDw8PDwAAAAAAAAAADwAPCAgIDw8AAAAA\
            AAAAAAAAAAkICQgIAAgAAAAAAAAAAAAJDwkAAAAIAAAAAAAAAAAACAkBAAgACAAAAAAAAAAAAAAA\
            AAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\
            AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP//AAD//wAA548AAKBPAAAADwAACA8AAKAP\
            AADABwAA8BEAAPgRAAD4OQAA/DsAAPx/AAD+fwAA//8AAP//AAA=""";
}
