package com.glessit.neurofunky.web.rest;

import com.glessit.neurofunky.entity.DTO.LikeDTO;
import com.glessit.neurofunky.entity.LikeType;
import com.glessit.neurofunky.service.ILikeService;
import com.glessit.neurofunky.service.exception.NotFoundException;
import com.glessit.neurofunky.web.MAPPING;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.glessit.neurofunky.util.LogUtil.debug;
import static com.glessit.neurofunky.util.SecurityUtil.HAS_ROLE_USER;

@RestController
@RequestMapping(value = MAPPING.LIKE)
@Slf4j
public class LikeResource {

    private final ILikeService likeService;
    private final ConversionService conversionService;

    @Autowired
    public LikeResource(
            ILikeService likeService,
            @Qualifier(value = "primaryConverterService") ConversionService conversionService) {
        this.likeService = likeService;
        this.conversionService = conversionService;
    }

    @RequestMapping(value = "/it/{id}", method = RequestMethod.GET)
    @PreAuthorize(value = HAS_ROLE_USER)
    public final LikeDTO likeIt(@PathVariable("id") Long id) throws NotFoundException {
        debug(log, "Like it {}", id);
        return conversionService.convert(likeService.like(id, LikeType.Value.NEWS), LikeDTO.class);
    }
}
