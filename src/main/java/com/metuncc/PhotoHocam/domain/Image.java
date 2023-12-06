package com.metuncc.PhotoHocam.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @Lob
    @Column(length = 10000000)
    private String data;



}
