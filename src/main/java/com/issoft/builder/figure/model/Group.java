package com.issoft.builder.figure.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
public class Group extends Element {
    @ManyToOne
    @JsonIgnore
    private Group parent;

    @OrderBy("position")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Group> childGroups;

    @OrderBy("position")
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Figure> childFigures;

    @Enumerated(EnumType.ORDINAL)
    private Direction direction;
}
