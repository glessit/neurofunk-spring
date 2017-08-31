package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "\"NFK_LIKE_TYPE\"")
public class LikeType extends AbstractPersistable<Long> {

    public enum Value {
        NEWS
    }

    @Column(nullable = false)
    private String type;

    public LikeType() {}

    public LikeType(String type) {
        this.type = type;
    }
}
