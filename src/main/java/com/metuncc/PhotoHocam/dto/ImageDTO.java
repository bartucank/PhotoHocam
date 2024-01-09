package com.metuncc.PhotoHocam.dto;

import lombok.Getter;
import lombok.Setter;


public class ImageDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private byte[] data;

    public ImageDTO(byte[] data, Long id) {
        this.data = data;
        this.id=id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
