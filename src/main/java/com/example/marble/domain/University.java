package com.example.marble.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "UNIVERSITY")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNIVERSITY_ID")
    private Long id;

    @Column(name = "UNIVERSITY_NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList<>();

    // @Transient denotes that this field will not be persisted to the data store.
    @Transient
    private boolean funded;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}
