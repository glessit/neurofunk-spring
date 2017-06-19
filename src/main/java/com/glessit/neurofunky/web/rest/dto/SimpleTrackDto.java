package com.glessit.neurofunky.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SimpleTrackDto {
    private String fullTrackName;
    private Long length;
    private String youtubeURI;
}
