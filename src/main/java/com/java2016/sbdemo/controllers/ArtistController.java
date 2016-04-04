package com.java2016.sbdemo.controllers;

import com.java2016.sbdemo.domain.Artist;
import com.java2016.sbdemo.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/artists")
public class ArtistController {
  @Autowired
  ArtistService artistService;

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public Artist addArtist(@RequestBody Artist artist) {
    return artistService.addArtist(artist);
  }



}
