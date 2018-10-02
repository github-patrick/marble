package com.example.marble.domain.dtos;

import lombok.Data;


@Data
public class AddressDto {

    private Long id;

    private String addressLineOne;

    private String addressLineTwo;

    private String postcode;
}
