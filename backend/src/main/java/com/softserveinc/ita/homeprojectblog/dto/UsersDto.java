package com.softserveinc.ita.homeprojectblog.dto;

import com.softserveinc.ita.homeprojectblog.generated.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {

    private Collection<User> users;

    private long quantity;

}
