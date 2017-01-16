package com.mmashyr.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Anton on 16.01.2017.
 */
@MappedSuperclass
public class BasicEntity implements Serializable {

    private Long id;

    public BasicEntity() {
    }

    public BasicEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}