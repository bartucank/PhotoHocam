package com.metuncc.PhotoHocam.dto;

import com.metuncc.PhotoHocam.domain.Image;
import lombok.Data;

import java.util.List;

@Data
public class ImageListDTO {

    private String username;
    private List<ImageDTO> imageList;
}
