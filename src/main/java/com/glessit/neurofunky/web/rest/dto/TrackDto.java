package com.glessit.neurofunky.web.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class TrackDto {

    private Set<Long> artists = new HashSet<>();
    private String track;
    private Integer length;
}
