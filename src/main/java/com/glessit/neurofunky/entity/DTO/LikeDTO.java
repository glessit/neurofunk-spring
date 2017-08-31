package com.glessit.neurofunky.entity.DTO;

import com.glessit.neurofunky.entity.Like;

public class LikeDTO extends AbstractEntityDTO {
    public LikeDTO(Like entity) {
        this.id = entity.getId();
    }
}
