package com.ead.course.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
