package com.glessit.neurofunky.rest;

import com.glessit.neurofunky.entity.Mix;
import com.glessit.neurofunky.entity.Source;
import com.glessit.neurofunky.repository.MixRepository;
import com.glessit.neurofunky.service.IMixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/mix")
public class MixResource {
//
//    @Autowired
//    private IMixService mixService;


    @Autowired
    private MixRepository mixRepository;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public void createMix() {
//        mixService.create();

        Source source = new Source("https://www.youtube.com/watch?v=1RgvGke_OaY&index=2&list=RDk9GLhS4dvJA");
        Mix mix = new Mix("SuperNFK123123", source, 2314);
        mix.setTags(new ArrayList<>());
        mix.getTags().add("simple");
//        EntityManager en = entityManagerFactory.createEntityManager();
//        en.persist(mix);
//        en.flush();
        System.out.println();
        mixRepository.save(mix);

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void getMix() {
        List<Mix> mixes = mixRepository.findAll();

        System.out.println();
    }


}
