package com.metuncc.PhotoHocam.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Image entity for PhotoHocam application.
 * this entity belongs to images.
 *
 * @author Nurberat Gökmen
 * @version 1.0.0
 */


@Entity
@Data
public class Image {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")

    /**
     * unique id for each image
     */
    private Long id;

    /**
     * user who sends the image
     * Every user can send an image to more than one receiver
     */

    @ManyToOne
    private User sender;

    /**
     * user who receives the image
     * each user can receive an image from more than one friend
     */

    @ManyToOne
    private User receiver;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")

    /**
     * to store the binary data of the image. for large objects
     */

    private byte[] data;



}
