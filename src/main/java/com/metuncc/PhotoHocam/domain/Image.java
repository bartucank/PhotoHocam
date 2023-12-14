package com.metuncc.PhotoHocam.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Image entity for PhotoHocam application.
 * this entity belongs to images.
 *
 * @author Nurberat Gokmen
 * @version 1.0.0
 */


@Entity

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
