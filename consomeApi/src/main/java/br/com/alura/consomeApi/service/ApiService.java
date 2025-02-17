package br.com.alura.consomeApi.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService implements IApiService {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converterJson(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String chamaApi(String uri){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException io) {
            throw new RuntimeException(io);
        } catch (InterruptedException i) {
            throw new RuntimeException(i);
        }

        String json = response.body();
        return json;
    }
}
