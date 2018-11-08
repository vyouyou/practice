package spring.repository;

import spring.model.RoleModel;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
public interface RoleMapper {
    RoleModel getRoleById(Integer id);
}
