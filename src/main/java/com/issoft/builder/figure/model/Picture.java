package com.issoft.builder.figure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pictures")
@JsonIgnoreProperties(value = {"createdDate", "modificationDate"}, allowGetters = true)
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(updatable= false)
    private LocalDateTime createdDate;

    private LocalDateTime modificationDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Group group;

    @PrePersist
    void onCreate() {
        this.setCreatedDate(LocalDateTime.now());
        this.setModificationDate(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setModificationDate(LocalDateTime.now());
    }
}
