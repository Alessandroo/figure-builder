package com.issoft.builder.figure.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    private Group parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Group> childGroups;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Figure> childFigures;

    @Enumerated(EnumType.ORDINAL)
    private Direction direction;
}
