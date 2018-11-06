package spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
@Entity
@Setter
@Getter
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String resourceType;

    private String url;

    private String permission;

    private String parentId;

    private String parentIds;

    private Boolean available = Boolean.FALSE;
    @ManyToMany
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = {@JoinColumn(name = "permissionId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private List<SysRole> roles;

}
