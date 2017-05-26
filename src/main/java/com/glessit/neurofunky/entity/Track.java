package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"NFK_TRACK\"")
public class Track extends AbstractPersistable<Long> implements java.io.Serializable {

    @ManyToMany
    @JoinTable(
            name="\"NFK_TRACK_ARTIST\"",
            joinColumns=@JoinColumn(name="track_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="artist_id", referencedColumnName="id"))
    private Set<Artist> artists = new HashSet<>();

    @Column(unique = true)
    private String track;

    @Column
    private Integer length;

    private Track() {}

    public Track(Set<Artist> artists, String track, Integer length) {
        this.artists = artists;
        this.track = track;
        this.length = length;
    }
}
