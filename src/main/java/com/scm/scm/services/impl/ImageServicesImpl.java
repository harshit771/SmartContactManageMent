package com.scm.scm.services.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.scm.helper.ContactConstant;
import com.scm.scm.services.ImageServices;

@Service
public class ImageServicesImpl implements ImageServices{

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file, String fileName) {

        try {
            byte[] data = new byte[file.getInputStream().available()];
            file.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id", fileName));

            return getURLFromPublicId(fileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

       
    }

    @Override
    public String getURLFromPublicId(String publicId) {
        
        return  cloudinary.url()
        .transformation(new Transformation<>()
        .width(ContactConstant.CONTACT_IMAGE_WIDTH)
        .height(ContactConstant.CONTACT_IMAGE_HEIGHT)
        .crop(ContactConstant.CONTACT_IMAGE_CROP))
        .generate(publicId);
    }

}
