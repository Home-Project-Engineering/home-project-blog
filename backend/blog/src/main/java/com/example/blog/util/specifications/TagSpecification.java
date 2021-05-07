package com.example.blog.backend.util.specifications;

import com.example.blog.backend.repository.entities.TagEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TagSpecification {
    public Specification<TagEntity> id(Long id) {

        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public Specification<TagEntity> name(String name) {

        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("name"), name);
        };
    }
}
