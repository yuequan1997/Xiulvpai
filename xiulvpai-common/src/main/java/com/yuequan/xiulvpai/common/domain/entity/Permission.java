package com.yuequan.xiulvpai.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 权限实体
 * @author yuequan
 * @since 1.0
 **/
@Entity
@Table(name = "permissions")
@Data
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String path;
    private String ancestors;
    @Column(name = "permission_key")
    private String permissionKey;
    @OneToOne
    @JsonIgnore
    private Permission parent;
    @Transient
    private transient Set<Permission> children;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", ancestors='" + ancestors + '\'' +
                ", permissionKey='" + permissionKey + '\'' +
                '}';
    }
}
