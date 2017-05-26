package com.glessit.neurofunky.rest;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/artist")
public class ArtistResource {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping
    public void test() {
        artistRepository.save(new Artist("Noisia"));
        System.out.println();
    }
}
