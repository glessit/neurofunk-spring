package com.glessit.neurofunky.service.dto;

import com.glessit.neurofunky.web.rest.dto.ArtistDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArtistTracksCountDto {
    private ArtistDto artist;
    private Integer tracks;
}
