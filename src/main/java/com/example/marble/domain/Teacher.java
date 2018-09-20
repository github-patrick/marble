package com.example.marble.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
@ToString(exclude = "university")
@Table
public class Teacher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEACHER_ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotEmpty
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date startDate;


    // Many Teachers can belong to one university. The university is the FK.
    // FK is stated in the child reference
    // Bi-directional mapping to the parent reference.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIVERSITY_ID")
    @JsonIgnore
    private University university;


    // Join table to link the source and target entities of the many to many relationship.
    // Cascade PERSIST used to persist a referenced entity
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "TEACHER_STUDENT",
        joinColumns = @JoinColumn(name = "TEACHER_ID"),
        inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    private List<Student> students = new ArrayList<>();

}
