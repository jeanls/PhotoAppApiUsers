package com.jean.leal.data;

import com.jean.leal.ui.model.response.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws", fallback = AlbumsFallback.class)
public interface AlbumsServiceClient {
    @GetMapping("/users/{userId}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String userId);
}

@Component
class AlbumsFallback implements AlbumsServiceClient{

    @Override
    public List<AlbumResponseModel> getAlbums(String userId) {
        return new ArrayList<>();
    }
}
