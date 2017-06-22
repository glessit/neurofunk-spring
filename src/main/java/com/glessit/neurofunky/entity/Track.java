package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"NFK_TRACK\"")
public class Track extends AbstractPersistable<Long> implements java.io.Serializable {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="\"NFK_TRACK_ARTIST\"",
            joinColumns=@JoinColumn(name="track_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="artist_id", referencedColumnName="id"))
    private Set<Artist> artists = new HashSet<>();

    @Column(unique = true)
    private String track;

    @Column
    private Integer length;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="source_id")
    private Source source;

    private Track() {}

    public Track(Set<Artist> artists, String track, Integer length, Source source) {
        this.artists = artists;
        this.track = track;
        this.length = length;
        this.source = source;
    }
}
