package spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 *
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
@Entity
@Setter
@Getter
public class SysRole {
    @Id
    @GeneratedValue
    private Integer id;
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;

    /**
     * 角色-权限关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission",
        joinColumns = {@JoinColumn(name = "roleId")},
        inverseJoinColumns = {@JoinColumn(name = "permissionId")}
    )
    private List<SysPermission> permissions;

    /**
     * 用户-角色关系定义
     */
    @ManyToMany
    @JoinTable(name = "SysUserRole",
        joinColumns = {@JoinColumn(name = "roleId")},
        inverseJoinColumns = {@JoinColumn(name = "uid")}
    )
    private List<UserInfo> userInfos;
}
