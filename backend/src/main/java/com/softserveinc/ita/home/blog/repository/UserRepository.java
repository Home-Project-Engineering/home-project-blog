package com.softserveinc.ita.home.blog.repository;
import com.softserveinc.ita.home.blog.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface UserRepository extends CrudRepository<User, Long> {




}
