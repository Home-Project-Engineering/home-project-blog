package com.example.blog.util.specifications;


import com.example.blog.repository.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {

    public Specification<UserEntity> id(Long id) {

        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public Specification<UserEntity> name(String name) {

        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("name"), name);
        };
    }
}
