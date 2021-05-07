package com.example.blog.backend.util.specifications;


import com.example.blog.backend.repository.entities.PostEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PostSpecification {
    public Specification<PostEntity> id(Long id) {

        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public Specification<PostEntity> userId(String userId) {

        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.join("author").get("id"), userId);
        };
    }


    public Specification<PostEntity> tagId(String tagId) {

        return (root, query, criteriaBuilder) -> {
            if (tagId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.join("tags").get("id"), tagId);
        };
    }

    public Specification<PostEntity> tagName(String tagName) {

        return (root, query, criteriaBuilder) -> {
            if (tagName == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.join("tags").get("name"), tagName);
        };
    }
}
