package com.glessit.neurofunky.entity;

import com.glessit.neurofunky.entity.type.RoleType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "\"NFK_ROLE\"")
public class Role extends AbstractPersistable<Long> implements java.io.Serializable {
    @Enumerated(EnumType.STRING)
    private RoleType type;
}
