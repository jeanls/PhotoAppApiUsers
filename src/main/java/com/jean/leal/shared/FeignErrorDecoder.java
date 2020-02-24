package com.jean.leal.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    Environment environment;

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404:
                if (s.contains("getAlbums")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), environment.getProperty("albums.exceptions.albumsNotFound"));
                }
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
