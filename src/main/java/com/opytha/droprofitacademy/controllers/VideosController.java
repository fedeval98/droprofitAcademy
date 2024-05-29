package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.VideosDTO;
import com.opytha.droprofitacademy.dtos.requests.NewVideo;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VideosController {

    @Autowired
    private VideosService videosService;

    @GetMapping("/videos")
    public List<VideosDTO> getAllVideos(){return videosService.getAllVideosDTO();}

    @GetMapping("/videos/{id}")
    public ResponseEntity<VideosDTO> getClient(@PathVariable Long id){
        return videosService.getVideo(id);
    }

    @PostMapping("/videos/create")
    public ResponseEntity<String> createVideo(@RequestBody NewVideo newVideo, Roles roltype){
        return videosService.createVideo(newVideo, roltype);
    }

    @PatchMapping("/videos/update")
    public ResponseEntity<String> updateVideo (@RequestBody NewVideo newVideo, Roles roltype, Long id){
        return  videosService.updateVideo(newVideo, roltype, id);
    }

    @DeleteMapping("/videos/remove")
    public ResponseEntity<String> removeVideo(@RequestParam Long id, Roles roltype){
        return videosService.removeVideo(id, roltype);
    }
}
