package com.mmashyr.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Anton on 16.01.2017.
 */
@MappedSuperclass
public class BasicEntity implements Serializable {

    public BasicEntity() {
    }

}