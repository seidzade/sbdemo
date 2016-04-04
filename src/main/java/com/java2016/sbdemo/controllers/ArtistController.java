package com.java2016.sbdemo.controllers;

import com.java2016.sbdemo.domain.Artist;
import com.java2016.sbdemo.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

  @RequestMapping(value="{id}", method = RequestMethod.GET)
  @ResponseBody
  public Artist getArtistByID(@PathVariable Long id) {
    return artistService.getArtistById(id);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<Artist> getArtistsByName(
      @RequestParam("q") String q,
      @RequestParam(value = "page",required = false, defaultValue = "0") Integer page) {
    return artistService.getArtistsByName(q,page,5);
  }

}