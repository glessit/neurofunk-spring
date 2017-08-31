package com.glessit.neurofunky.service;

import com.glessit.neurofunky.entity.Like;
import com.glessit.neurofunky.entity.LikeType;
import com.glessit.neurofunky.service.exception.NotFoundException;

public interface ILikeService {

    /**
     * Do like something
     * @param id LikeType (see. LikeType entity) for ex. it can be News, Artists ..
     * @param value LikeType.Type
     * @return
     */
    Like like(Long id, LikeType.Value value) throws NotFoundException;
    LikeType findLikeType(LikeType.Value value);
}
