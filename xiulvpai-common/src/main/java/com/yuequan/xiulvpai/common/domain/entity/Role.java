package com.yuequan.xiulvpai.common.domain.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Set;

/**
 * 角色实体类
 * @author yuequan
 * @since 1.0
 **/
@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToMany
    private Set<Permission> permissions;
}