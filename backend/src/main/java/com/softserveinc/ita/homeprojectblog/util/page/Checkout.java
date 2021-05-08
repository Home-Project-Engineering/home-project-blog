package com.softserveinc.ita.homeprojectblog.util.page;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Checkout {
    String sort = null;
    Integer pageNum = null;
    Integer pageSize = null;

    public Checkout checkoutAndSetDefaults(String sort, Integer pageNum, Integer pageSize) {

        String sortNotNull = Optional.ofNullable(sort).orElse("-id");
        Integer pageNumNotNull = Optional.ofNullable(pageNum).orElse(1) - 1;
        Integer pageSizeNotNull = Optional.ofNullable(pageSize).orElse(10);

        return new Checkout(sortNotNull, pageNumNotNull, pageSizeNotNull);
    }
}
