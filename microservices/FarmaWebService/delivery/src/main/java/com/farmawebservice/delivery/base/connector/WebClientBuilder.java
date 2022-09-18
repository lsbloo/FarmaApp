package com.farmawebservice.delivery.base.connector;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class WebClientBuilder {

    private WebClient client;
    private Integer TIME_OUT = 30000;
    private static WebClientBuilder instance = null;

    public static WebClientBuilder getInstance(String urlConnect) {
        if (instance == null) {
            return new WebClientBuilder(urlConnect);
        } else {
            return instance;
        }
    }

    public WebClientBuilder(String urlConnect) {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIME_OUT)
                .responseTimeout(Duration.ofMillis(TIME_OUT))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(TIME_OUT, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(TIME_OUT, TimeUnit.MILLISECONDS)));

        client = WebClient.builder().baseUrl(urlConnect).defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", urlConnect))
                .clientConnector(new ReactorClientHttpConnector((httpClient)))
                .build();
    }


    public String sendMethodPost(String resource, Object data, String accessToken) {
        if (data == null) {
            return this.client.post().uri(resource).header("Authorization", accessToken).retrieve().bodyToMono(String.class).block();
        } else {
            return this.client.post().uri(resource).header("Authorization", accessToken).bodyValue(data).retrieve().bodyToMono(String.class).block();
        }
    }


    public String sendMethodGet(String resource, Object data) {
        return this.client.get().uri(resource).retrieve().bodyToMono(String.class).block();
    }
}
