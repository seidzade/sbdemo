package com.java2016.sbdemo.services;

import com.java2016.sbdemo.domain.Artist;
import com.java2016.sbdemo.exceptions.ObjectNotFoundException;
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
    Artist ret =  artistRepository.get(id);
    if (ret==null) throw new ObjectNotFoundException("artist",id);
    return ret;
  }
}
