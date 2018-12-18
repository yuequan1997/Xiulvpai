package com.yuequan.xiulvpai.common.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限实体
 * @author yuequan
 * @since 1.0
 **/
@Entity
@Table(name = "permissions")
@Data
public class Permission {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String url;
    private String ancestors;
    @OneToOne
    private Permission parent;
    @Transient
    private transient Set<Permission> children;
}
