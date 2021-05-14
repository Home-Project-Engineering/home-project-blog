package com.softserveinc.ita.homeprojectblog.entity;

import com.softserveinc.ita.homeprojectblog.security.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    NameEnum name;

    public enum NameEnum {
        BLOGGER(Set.of(Permission.BLOGGER_ROLE)),

        MODERATOR(Set.of(Permission.BLOGGER_ROLE,
                Permission.MODERATOR_ROLE)),

        ADMIN(Set.of(Permission.BLOGGER_ROLE,
                Permission.MODERATOR_ROLE,
                Permission.ADMIN_ROLE));

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
