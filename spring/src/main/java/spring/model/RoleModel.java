package spring.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色model
 *
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@Setter
@Getter
public class RoleModel {
    private Integer id;

    private String name;

    private String description;

    private String permissionIds;
}
