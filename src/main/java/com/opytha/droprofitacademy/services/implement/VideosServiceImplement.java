package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.repositories.VideosRepository;
import com.opytha.droprofitacademy.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;

public class VideosServiceImplement implements VideosService {

    @Autowired
    private VideosRepository videosRepository;

    @Override
    public Videos findById(Long id) {
        return videosRepository.findById(id).orElse(null);
    }
}
