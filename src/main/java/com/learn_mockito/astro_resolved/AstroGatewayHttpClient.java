package com.learn_mockito.astro_resolved;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@AllArgsConstructor
@SuppressWarnings("HttpUrlsUsage")
public class AstroGatewayHttpClient implements Gateway<AstroResponse> {

  private static final String DEFAULT_URL = "http://api.open-notify.org/";
  private final String url;

  public AstroGatewayHttpClient() {
    this(DEFAULT_URL);
  }

  @Override
  public AstroResponse getResponse() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url + "astro.json"))
      .timeout(Duration.ofSeconds(2))
      .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return new Gson().fromJson(response.body(), AstroResponse.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
