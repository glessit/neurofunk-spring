package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"NFK_MIX\"")
@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "MIX")
public class Mix extends AbstractPersistable<Long> implements java.io.Serializable {

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column
    private String image;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="source_id")
    private Source source;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer length;

    @Getter
    @Setter
    @Column
    private Long likes = 0l;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "\"NFK_MIX_TAGS\"",
            joinColumns=@JoinColumn(name = "mix_id")
    )
    @OrderColumn
    @Column(name="tags")
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private Mix() {}

    public Mix(String name, Source source, Integer length) {
        this.name = name;
        this.source = source;
        this.length = length;
    }
}
