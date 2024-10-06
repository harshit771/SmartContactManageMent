package com.scm.scm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageServices {

    public String uploadImage(MultipartFile file, String fileName);

    public String getURLFromPublicId(String publicId);

}
