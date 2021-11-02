package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.ChangePasswordDTO;
import com.homeproject.blog.backend.presentationlayer.model.ChangePassword;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordModelToChangePasswordDTOConverter implements BlogConverter<ChangePassword, ChangePasswordDTO> {
    @Override
    public ChangePasswordDTO convert(ChangePassword source) {
        return new ChangePasswordDTO(source.getOldPassword(), source.getNewPassword());
    }
}
