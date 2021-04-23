package com.itacademy.blog.data.entity;


import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    public enum RoleEnum {

        BLOGGER(Set.of()),

        MODERATOR(Set.of(Permission.TAG_REMOVE, Permission.POST_UPDATE, Permission.POST_DELETE, Permission.COMMENTS_UPDATE, Permission.COMMENTS_DELETE)),

        ADMIN(Set.of(Permission.USER_MANAGEMENT, Permission.TAG_REMOVE, Permission.POST_UPDATE, Permission.POST_DELETE, Permission.COMMENTS_UPDATE, Permission.COMMENTS_DELETE)),

        EXPERT(Set.of());

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

    @Enumerated(EnumType.STRING)
    private RoleEnum role;


}
