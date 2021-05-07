package com.example.blog.backend.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public enum RoleEnum {

        BLOGGER(Set.of(Permission.CREATE_USER,
                Permission.SEE_POST_COMMENT_TAG,
                Permission.CUR_USER_ACTION,
                Permission.CREATE_POST_COMMENT)),

        MODERATOR(Set.of(Permission.CREATE_USER,
                Permission.SEE_POST_COMMENT_TAG,
                Permission.CUR_USER_ACTION,
                Permission.CREATE_POST_COMMENT,
                Permission.DELETE_UPDATE_POST_COMMENT_TAG)),

        ADMIN(Set.of(Permission.CREATE_USER,
                Permission.SEE_POST_COMMENT_TAG,
                Permission.CUR_USER_ACTION,
                Permission.CREATE_POST_COMMENT,
                Permission.DELETE_UPDATE_POST_COMMENT_TAG,
                Permission.MANAGE_USER));

        private final Set<Permission> permissions;

        RoleEnum(Set<Permission> permissions) {
            this.permissions = permissions;
        }

        public Set<Permission> getPermissions() {
            return permissions;
        }

        public Set<SimpleGrantedAuthority> getAuthorities() {
            return getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toSet());
        }
    }
}
