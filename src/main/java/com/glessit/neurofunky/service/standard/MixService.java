package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.Mix;
import com.glessit.neurofunky.entity.Source;
import com.glessit.neurofunky.repository.MixRepository;
import com.glessit.neurofunky.service.IMixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

@Service
public class MixService implements IMixService {

    @Autowired
    private MixRepository mixRepository;
/*

    @Autowired
    private EntityManagerFactory entityManagerFactory;
*/


    @Override
    @Transactional
    public void create() {

        Source source = new Source("https://www.youtube.com/watch?v=1RgvGke_OaY&index=2&list=RDk9GLhS4dvJA");
        Mix mix = new Mix("Mix1", source, 120);
        mix.setTags(new ArrayList<>());
        mix.getTags().add("heavy");
//        EntityManager en = entityManagerFactory.createEntityManager();
//        en.persist(mix);
//        en.flush();
        System.out.println();
        mixRepository.save(mix);

    }
}
