package org.yuequan.xiulvpai.security.authorization.permission.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author yuequan
 * @since
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {
    private String path;
    private Set<String> roles;
}
