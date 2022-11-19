package ru.grow.sovcombank.solution.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class TimeObjectEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date changedTime;
}
