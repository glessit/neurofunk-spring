package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"NFK_SOURCE\"")
public class Source extends AbstractPersistable<Long> implements java.io.Serializable {

    @Getter
    @Setter
    @Column
    private String youtube;

    @Getter
    @Setter
    @Column
    private String description;

    public Source() {}

    public Source(String youtube) {
        this.youtube = youtube;
    }
}
