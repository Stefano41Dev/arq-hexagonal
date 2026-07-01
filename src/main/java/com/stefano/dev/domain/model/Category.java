package com.stefano.dev.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Category {
    private Integer id;
    private String name;
    private Boolean isActive = true;
}
