package liftoff.atlas.getcultured.util;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ReadJSON {
    public static String readLocalJSONFile(String url) throws FileNotFoundException, ParseException, ParseException {
        JSONObject json =  (JSONObject) new JSONParser(JSONParser.MODE_JSON_SIMPLE)
                .parse(new FileReader(url));

        return json.toJSONString();
    }

    public static String fetchJSON(String url) throws IOException, ParseException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json =  (JSONObject) new JSONParser(JSONParser.MODE_JSON_SIMPLE).parse(res.body());
        return json.toJSONString();
    }
    
}