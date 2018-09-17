package com.example.marble.domain;

import com.example.marble.domain.enums.DegreeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Table
@Entity
public class Student extends BaseEntity {

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

    @Column(name = "EMAIL")
    @Email
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private Course course;

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Teacher> teachers = new ArrayList<>();
}
