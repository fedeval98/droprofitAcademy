package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.VideosDTO;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Videos;

import java.util.List;

public interface VideosService {

    Videos findById (Long id);

    void saveVideo(Videos videos);
    List<Videos> getAllVideos();

    List<VideosDTO> getAllVideosDTO();
}
