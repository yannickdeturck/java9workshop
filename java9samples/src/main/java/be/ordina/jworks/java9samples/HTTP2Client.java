package be.ordina.jworks.java9samples;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.net.URI;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP 2.0 client.
 *
 * @author Yannick De Turck
 */
public class HTTP2Client {
    public static void main(String[] args) throws Exception {
        // GET
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(
                HttpRequest
                        .newBuilder(new URI("http://www.google.com/"))
                        .headers("FooHeader", "fooValue", "BarHeader", "barValue")
                        .GET()
                        .build(),
                HttpResponse.BodyHandler.asString()
        );
        int statusCode = response.statusCode();
        String responseBody = response.body();
        System.out.println(statusCode);
        System.out.println(responseBody);

        // POST
        client = HttpClient.newHttpClient();
        response = client.send(
                HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/upload/"))
                        .POST(HttpRequest.BodyProcessor.fromFile(Paths.get("/tmp/file-to-upload.txt")))
                        .build(),
                HttpResponse.BodyHandler.discard(null));
        statusCode = response.statusCode();
        responseBody = response.body();
        System.out.println(statusCode);
        System.out.println(responseBody);

        // Async GET
        client = HttpClient.newHttpClient();
        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(new URI("https://labs.consol.de/"))
                        .GET()
                        .build(),
                HttpResponse.BodyHandler.asString());
        futureResponse.whenComplete((resp, throwable) -> {
            System.out.println(resp.statusCode());
            System.out.println(resp.body());
        });

        // Alternative Async GET
        client = HttpClient.newHttpClient();
        futureResponse = client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(new URI("https://labs.consol.de/"))
                        .GET()
                        .build(),
                HttpResponse.BodyHandler.asString());
        Thread.sleep(3000);
        if (futureResponse.isDone()) {
            System.out.println(futureResponse.get().statusCode());
            System.out.println(futureResponse.get().body());
        } else {
            futureResponse.cancel(true);
            System.out.println("Timeout, cancelling the request...");
        }

        // System proxy settings
        client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();
        response = client.send(
                HttpRequest.newBuilder()
                        .uri(new URI("https://localhost:8080"))
                        .GET()
                        .build(),
                HttpResponse.BodyHandler.asString());
        statusCode = response.statusCode();
        responseBody = response.body();
        System.out.println(statusCode);
        System.out.println(responseBody);

        // Basic authentication
        client = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("username", "password".toCharArray());
                    }
                })
                .build();
        response = client.send(
                HttpRequest.newBuilder()
                        .uri(new URI("https://localhost:8080"))
                        .GET()
                        .build(),
                HttpResponse.BodyHandler.asString());
        statusCode = response.statusCode();
        responseBody = response.body();
        System.out.println(statusCode);
        System.out.println(responseBody);
    }
}
