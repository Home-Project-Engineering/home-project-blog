package com.softserveinc.ita.homeprojectblog.entity;

import com.softserveinc.ita.homeprojectblog.security.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role", schema = "public")
public class RoleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private NameEnum name;

    public enum NameEnum {
        BLOGGER(Set.of()),

        MODERATOR(Set.of(Permission.TAG_REMOVE
                ,Permission.POST_UPDATE
                ,Permission.POST_DELETE
                ,Permission.COMMENTS_UPDATE
                ,Permission.COMMENTS_DELETE)),

        ADMIN(Set.of(Permission.USER_MANAGEMENT
                ,Permission.TAG_REMOVE
                ,Permission.POST_UPDATE
                ,Permission.POST_DELETE
                ,Permission.COMMENTS_UPDATE
                ,Permission.COMMENTS_DELETE
                ,Permission.ROLE_MANAGEMENT));

        private final Set<Permission> permission;

        NameEnum(Set<Permission> permission) {
            this.permission = permission;
        }

        public Set<Permission> getPermission() {
            return permission;
        }

        public Set<SimpleGrantedAuthority> getAuthorities() {
            return getPermission().stream()
                    .map(perm -> new SimpleGrantedAuthority(perm.getName()))
                    .collect(Collectors.toSet());
        }
    }

}
