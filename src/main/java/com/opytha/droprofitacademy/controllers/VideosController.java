package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.VideosDTO;
import com.opytha.droprofitacademy.dtos.requests.NewVideo;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.services.ClientService;
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
    public List<VideosDTO> getAllVideos(Authentication authentication){return videosService.getAllVideosDTO();}

    @GetMapping("/videos/{id}")
    public ResponseEntity<VideosDTO> getClient(@PathVariable Long id, Authentication authentication){
        return videosService.getVideo(id);
    }

    @PostMapping("/videos/create")
    public ResponseEntity<String> createVideo(@RequestBody NewVideo newVideo, Authentication authentication){
        return videosService.createVideo(newVideo, authentication.getName());
    }

    @PatchMapping("/videos/update")
    public ResponseEntity<String> updateVideo (@RequestBody NewVideo newVideo, Authentication authentication, @RequestParam Long id){
        return  videosService.updateVideo(newVideo, authentication.getName(), id);
    }

    @DeleteMapping("/videos/remove")
    public ResponseEntity<String> removeVideo(@RequestParam Long id, Authentication authentication){
        return videosService.removeVideo(id, authentication.getName());
    }
}
