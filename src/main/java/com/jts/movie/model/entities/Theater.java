package com.jts.movie.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theater")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique=true)
    private String address;

    @OneToMany(mappedBy="theater",cascade=CascadeType.ALL)
    private List<MovieShow>showList=new ArrayList<>();



}
