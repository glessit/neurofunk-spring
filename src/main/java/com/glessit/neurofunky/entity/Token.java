package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"NFK_TOKEN\"")
public class Token extends AbstractPersistable<Long> implements java.io.Serializable {

    @Column(nullable = false)
    private Long token;

    @OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expireDateTime;

    public Token(Long token, User user, LocalDateTime localDateTime) {
        this.token = token;
        this.user = user;
        this.expireDateTime = localDateTime;
    }
}
