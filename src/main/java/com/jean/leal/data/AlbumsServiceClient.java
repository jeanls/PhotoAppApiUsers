package com.jean.leal.data;

import com.jean.leal.ui.model.response.AlbumResponseModel;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws", fallbackFactory = AlbumsFallbackFactory.class)
public interface AlbumsServiceClient {
    @GetMapping("/users/{userId}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String userId);
}

@Component
class AlbumsFallbackFactory implements FallbackFactory<AlbumsServiceClient> {
    @Override
    public AlbumsServiceClient create(Throwable throwable) {
        return new AlbumsServiceClientFallback(throwable);
    }
}

@AllArgsConstructor
@Slf4j
class AlbumsServiceClientFallback implements AlbumsServiceClient{

    private final Throwable throwable;

    @Override
    public List<AlbumResponseModel> getAlbums(String userId) {
        log.error(throwable.getLocalizedMessage());
        return new ArrayList<>();
    }
}
