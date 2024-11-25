package com.jts.movie.model.entities;

import com.jts.movie.enums.Gender;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    private Integer age;

    private String address;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    private String mobileNo;

    @Column(unique=true)
    private String emailID;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<Ticket>ticketList=new ArrayList<>();

}
