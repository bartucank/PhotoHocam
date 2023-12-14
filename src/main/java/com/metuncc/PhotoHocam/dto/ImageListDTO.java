package com.metuncc.PhotoHocam.dto;

import com.metuncc.PhotoHocam.domain.Image;
import lombok.Data;

import java.util.List;

/**
 * this class is used for transferring the data of the image within the application
 */

@Data
public class Imagedto {

    /**
     * username of the user sent the image
     */
    private String username;

    /**
     * list of the images sent from the specified user
     */
    private List<Image> imageList;
}
