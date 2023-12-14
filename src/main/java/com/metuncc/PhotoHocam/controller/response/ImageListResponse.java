package com.metuncc.PhotoHocam.controller.response;


import com.metuncc.PhotoHocam.dto.ImageListDTO;
import lombok.Data;

import java.util.List;


public class ImageListResponse {
    private List<ImageListDTO> imageListDTOList;

    public List<ImageListDTO> getImageListDTOList() {
        return imageListDTOList;
    }

    public void setImageListDTOList(List<ImageListDTO> imageListDTOList) {
        this.imageListDTOList = imageListDTOList;
    }
}
