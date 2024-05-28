package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.VideosDTO;
import com.opytha.droprofitacademy.dtos.requests.NewVideo;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.models.enums.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VideosService {

    Videos findById (Long id);

    void deleteVideo (Long id);

    void saveVideo(Videos videos);

    List<Videos> getAllVideos();

    List<VideosDTO> getAllVideosDTO();

    VideosDTO getVideoById(Long id);

    ResponseEntity<VideosDTO> getVideo(Long id);

    ResponseEntity<String> createVideo (NewVideo newVideo, Roles roltype);

    ResponseEntity<String> updateVideo (NewVideo newVideo, Roles roltype, Long id);

    ResponseEntity<String> removeVideo(Long id, Roles roltype);
}
