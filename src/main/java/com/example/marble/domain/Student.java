package com.example.marble.domain;

import com.example.marble.domain.enums.DegreeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Table(name = "STUDENT")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "COURSE")
    private String course;

    @Column(name = "DEGREE_TYPE")
    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers = new ArrayList<>();
}
