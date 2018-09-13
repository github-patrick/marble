package com.example.marble.domain;

import com.example.marble.domain.enums.DegreeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @Column(name = "FIRST_NAME", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotEmpty
    private String lastName;

    @Column(name = "COURSE", nullable = false)
    @NotEmpty
    private String course;

    @Column(name = "DEGREE_TYPE")
    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();
}
