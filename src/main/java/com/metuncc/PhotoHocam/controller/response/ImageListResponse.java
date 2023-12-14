package com.metuncc.PhotoHocam.controller.response;


import com.metuncc.PhotoHocam.dto.ImageListDTO;
import lombok.Data;

import java.util.List;

@Data
public class ImageListResponse {
    private List<ImageListDTO> imageListDTOList;
}
