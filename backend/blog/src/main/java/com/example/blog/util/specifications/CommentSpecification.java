package com.example.blog.backend.util.specifications;

import com.example.blog.backend.repository.entities.CommentEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CommentSpecification {
    public Specification<CommentEntity> id(Long id) {

        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("id"), id);
        };
    }
    public Specification<CommentEntity> postId(String postId) {

        return (root, query, criteriaBuilder) -> {
            if (postId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.join("post").get("id"), postId);
        };
    }

    public Specification<CommentEntity> userId(String userId) {

        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.join("author").get("id"), userId);
        };
    }

    public Specification<CommentEntity> userName(String userName) {

        return (root, query, criteriaBuilder) -> {
            if (userName == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.join("author").get("name"), userName);
        };
    }

}
