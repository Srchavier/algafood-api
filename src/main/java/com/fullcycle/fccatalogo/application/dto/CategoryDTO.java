package com.fullcycle.fccatalogo.application.dto;

import java.io.Serializable;

import com.fullcycle.fccatalogo.domain.entity.Category;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public Category toEntity() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return mapper.map(this, Category.class);
    }
    
}
