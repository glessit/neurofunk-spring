package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.Like;
import com.glessit.neurofunky.entity.LikeType;
import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.entity.User;
import com.glessit.neurofunky.repository.LikeTypeRepository;
import com.glessit.neurofunky.service.ILikeService;
import com.glessit.neurofunky.service.INewsService;
import com.glessit.neurofunky.service.ISecurityService;
import com.glessit.neurofunky.service.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class LikeService implements ILikeService {

    @Autowired
    private INewsService newsService;
    @Autowired
    private ISecurityService securityService;
    @Autowired
    private LikeTypeRepository likeTypeRepository;

    @Override
    @Transactional
    public Like like(Long id, LikeType.Value value) throws NotFoundException {
        Like result = null;
        User currentUser = securityService.getAuthenticatedUser();
        switch (value) {
            case NEWS:
                News item = newsService.getNewsById(id);
                if (item.isUserHasLike(currentUser.getId()) == null) {
                    // add like to item
                    item.addLike(new Like(currentUser, findLikeType(value)));
                    newsService.save(item);
                }
                break;
        }

//        telegramBot.sendMessage()

        return result;
    }


    @Override
    public LikeType findLikeType(LikeType.Value value) {
        return likeTypeRepository.findOneByType(value.toString());
    }
}
