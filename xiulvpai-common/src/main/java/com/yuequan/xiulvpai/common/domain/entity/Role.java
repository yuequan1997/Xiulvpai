package com.yuequan.xiulvpai.common.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 角色实体类
 * @author yuequan
 * @since 1.0
 **/
@Entity
@Table(name = "roles")
@Getter
@Setter
@NamedEntityGraphs(
        @NamedEntityGraph(name = "roles.all",
                attributeNodes = {
                    @NamedAttributeNode("permissions")
                }
        )
)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToMany
    @JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns =  @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;
}