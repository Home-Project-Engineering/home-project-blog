package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.UsersDto;
import com.softserveinc.ita.homeprojectblog.generated.model.User;

import javax.validation.Valid;
import java.math.BigDecimal;

public interface UserService {
    UsersDto getAllUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize);

    User getUserById(BigDecimal id);

    User signUp(User body);

    User updateUser(User body, BigDecimal id);

    void deleteUser(BigDecimal id);
}
