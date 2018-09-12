package com.example.marble.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "TEACHER")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEACHER_ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date startDate;


    // Many Teachers can belong to one university. The university is the FK.
    // FK is stated in the child reference
    // Bi-directional mapping to the parent reference.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIVERSITY_ID")
    private University university;


    // Join table to link the source and target entities of the many to many relationship.
    @ManyToMany
    @JoinTable(name = "TEACHER_STUDENT",
        joinColumns = @JoinColumn(name = "TEACHER_ID"),
        inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    private List<Student> students;


}
