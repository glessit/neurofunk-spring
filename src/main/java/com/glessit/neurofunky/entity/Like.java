package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "\"NFK_LIKE\"")
public class Like extends AbstractPersistable<Long> implements java.io.Serializable {

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="type_id")
    private LikeType type;

    public Like() {}
}
