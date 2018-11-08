package spring.repository;

import spring.model.PermissionModel;

import java.util.List;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
public interface PermissionMapper {
    List<PermissionModel> queryPermissionsByIds(String ids);
}
