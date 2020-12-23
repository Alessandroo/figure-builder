package com.issoft.builder.figure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@JsonIgnoreProperties(value = "position", allowGetters = true)
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int position;
}
