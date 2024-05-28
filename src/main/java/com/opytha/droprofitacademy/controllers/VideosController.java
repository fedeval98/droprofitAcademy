package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
public class VideosController {

    @Autowired
    private VideosService videosService;


}
