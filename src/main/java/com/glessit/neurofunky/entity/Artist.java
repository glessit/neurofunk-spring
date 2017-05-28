package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "\"NFK_ARTIST\"")
@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "ARTIST") - second level
// cache!!!
public class Artist extends AbstractPersistable<Long> implements java.io.Serializable {

    @Column(unique = true)
    private String name;
    @Column
    private String image;

    public Artist() {}
    public Artist(String name) {
        this.name = name;
    }
}
