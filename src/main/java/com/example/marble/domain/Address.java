package com.example.marble.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table
@EqualsAndHashCode
@Builder
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLineOne;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLineTwo;

    @Column(name = "POSTCODE")
    private String postcode;

}
