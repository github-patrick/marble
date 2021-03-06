package com.example.marble.domain;

import com.example.marble.domain.enums.DegreeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames =
            {"COURSE", "DEGREE_TYPE"}))
@Entity
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;

    @Column(name = "COURSE", nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "DEGREE_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> students;

}
