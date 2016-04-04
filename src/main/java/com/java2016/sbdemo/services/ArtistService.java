package com.java2016.sbdemo.services;

import com.java2016.sbdemo.dao.ArtistRepository;
import com.java2016.sbdemo.domain.Artist;
import com.java2016.sbdemo.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
  @Autowired
  private ArtistRepository artistRepository;

  public Artist addArtist(Artist artist) {
    artist.setId(System.currentTimeMillis());
    artistRepository.save(artist);
    return artist;
  }

  public Artist getArtistById(Long id) {
    Artist  ret = artistRepository.findOne(id);
    if (ret==null) throw new ObjectNotFoundException("artist",id);
    return ret;
  }
}
