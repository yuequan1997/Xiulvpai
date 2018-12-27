package com.yuequan.xiulvpai.common.domain.entity;

import com.yuequan.xiulvpai.common.domain.entity.support.Gender;
import com.yuequan.xiulvpai.common.domain.entity.support.RecordTime;
import com.yuequan.xiulvpai.common.domain.entity.support.RecordTimeListener;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 用户实体类
 * @author yuequan
 * @since 1.0
 **/
@Entity
@Table(name = "users")
@Data
@EntityListeners({RecordTimeListener.class})
public class User implements RecordTime {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String username;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "avatar_url")
    private String avatarUrl;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "born_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bornAt;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @OneToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
