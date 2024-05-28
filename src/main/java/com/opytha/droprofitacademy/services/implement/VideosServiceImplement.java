package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.VideosDTO;
import com.opytha.droprofitacademy.dtos.requests.NewVideo;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.repositories.VideosRepository;
import com.opytha.droprofitacademy.services.VideosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
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
    public void deleteVideo(Long id){videosRepository.deleteById(id);}
    @Override
    public List<Videos> getAllVideos(){
        return videosRepository.findAll();
    }

    @Override
    public List<VideosDTO> getAllVideosDTO(){
        return getAllVideos().stream().map(VideosDTO::new).collect(Collectors.toList());
    }

    @Override
    public VideosDTO getVideoById(Long id) {
        return videosRepository.findById(id).map(VideosDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<VideosDTO> getVideo(Long id){
        VideosDTO videosDTO = getVideoById(id);

        if(videosDTO != null){
            return new ResponseEntity<>(videosDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> createVideo (NewVideo newVideo, Roles roltype){

        if(roltype == Roles.USER){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        if(newVideo.getVideoName().isBlank()){
            return new ResponseEntity<>("Video name can't be blank", HttpStatus.FORBIDDEN);
        }
        if(newVideo.getUrl().isBlank()){
            return new ResponseEntity<>("URL can't be blank", HttpStatus.FORBIDDEN);
        }
        if(newVideo.getCourses().getId() == null){
            return new ResponseEntity<>("Course can't be blank", HttpStatus.FORBIDDEN);
        }


        Videos video = new Videos(newVideo.getVideoName(), newVideo.getUrl(), LocalDate.now());

        saveVideo(video);

        return new ResponseEntity<>("Video added successfully", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateVideo (NewVideo newVideo, Roles roltype, Long id){
        if(roltype == Roles.USER){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        Videos video = findById(id);
        if (video == null){
            return new ResponseEntity<>("Video not found",HttpStatus.NOT_FOUND);
        }

        if(newVideo.getVideoName().isBlank()){
            return new ResponseEntity<>("Video name can't be blank", HttpStatus.FORBIDDEN);
        }
        if(newVideo.getUrl().isBlank()){
            return new ResponseEntity<>("URL can't be blank", HttpStatus.FORBIDDEN);
        }
        if(newVideo.getCourses().getId() == null){
            return new ResponseEntity<>("Course can't be blank", HttpStatus.FORBIDDEN);
        }

        video.setVideoName(newVideo.getVideoName());
        video.setUrl(newVideo.getUrl());
        video.setCourses(newVideo.getCourses());

        saveVideo(video);

        return new ResponseEntity<>("Video updated successfully", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public  ResponseEntity<String> removeVideo(Long id, Roles roltype) {

        Videos video = findById(id);

        if (video == null){
            return new ResponseEntity<>("Video not found",HttpStatus.NOT_FOUND);
        }

        if(roltype == Roles.USER){
                return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
            }

        deleteVideo(id);

        return new ResponseEntity<>("Account remove successfully", HttpStatus.OK);

    }

}
