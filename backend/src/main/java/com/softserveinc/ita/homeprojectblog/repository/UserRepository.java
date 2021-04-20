package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.generated.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, BigDecimal> {

    Page<User> findByName(String name, Pageable pageable);

}
