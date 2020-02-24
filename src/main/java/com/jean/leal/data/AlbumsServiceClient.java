package com.jean.leal.data;

import com.jean.leal.ui.model.response.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "albums-ws")
public interface AlbumsServiceClient {
    @GetMapping("/users/{userId}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String userId);
}
