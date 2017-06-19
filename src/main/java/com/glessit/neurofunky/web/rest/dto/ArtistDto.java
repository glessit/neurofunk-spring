package com.glessit.neurofunky.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArtistDto {
    private Long id;
    private String name;
    private String image;
}
