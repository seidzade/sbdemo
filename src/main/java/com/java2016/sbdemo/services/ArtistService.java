package com.java2016.sbdemo.services;

import com.java2016.sbdemo.domain.Artist;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ArtistService {

  private Map<Long,Artist> artistRepository = new HashMap<>();

  public Artist addArtist(Artist artist) {
    artist.setId(System.currentTimeMillis());
    artistRepository.put(artist.getId(),artist);
    return artist;
  }

  public Artist getArtistById(Long id) {
    return artistRepository.get(id);
  }
}
