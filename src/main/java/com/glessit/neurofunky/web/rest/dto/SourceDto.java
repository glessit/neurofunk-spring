package com.glessit.neurofunky.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SourceDto {
    private String youtube;
    private String description;
}
