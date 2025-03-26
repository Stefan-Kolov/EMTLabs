package com.emt.emtlabs.model.dto;

import lombok.Data;

@Data
public class HostDto {

    private String name;

    private String surname;

    private Long country;

    public HostDto() {

    }

    public HostDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
