package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.VideosDTO;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.repositories.VideosRepository;
import com.opytha.droprofitacademy.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideosServiceImplement implements VideosService {

    @Autowired
    private VideosRepository videosRepository;

    @Override
    public Videos findById(Long id) {
        return videosRepository.findById(id).orElse(null);
    }

    @Override
    public void saveVideo(Videos videos) {
        videosRepository.save(videos);
    }
    @Override
    public List<Videos> getAllVideos(){
        return videosRepository.findAll();
    }

    @Override
    public List<VideosDTO> getAllVideosDTO(){
        return getAllVideos().stream().map(VideosDTO::new).collect(Collectors.toList());
    }




}
