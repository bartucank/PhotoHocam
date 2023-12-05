package com.metuncc.PhotoHocam.dto;

import com.metuncc.PhotoHocam.domain.Image;
import lombok.Data;

import java.util.List;

@Data
public class Imagedto {

    private String username;
    private List<Image> imageList;
}
