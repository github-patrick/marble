package com.example.marble.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@Table
public class University extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNIVERSITY_ID")
    private Long id;

    @Column(name = "UNIVERSITY_NAME", unique = true)
    @NotNull
    @NotEmpty(message = "University name cannot be empty")
    private String name;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList<>();

    // @Transient denotes that this field will not be persisted to the data store.
    @Transient
    private boolean funded;

}
