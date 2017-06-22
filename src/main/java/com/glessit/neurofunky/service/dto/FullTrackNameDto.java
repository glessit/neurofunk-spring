package com.glessit.neurofunky.service.dto;

import com.glessit.neurofunky.web.rest.dto.ArtistDto;
import com.glessit.neurofunky.web.rest.dto.SourceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class FullTrackNameDto {
    private Set<ArtistDto> artists = new HashSet<>();
    private String track;
    private Integer length;
    private SourceDto source;
}
