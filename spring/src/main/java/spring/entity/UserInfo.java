package spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
@Entity
@Setter
@Getter
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String salt;
    private byte state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",
            joinColumns = {@JoinColumn(name = "uid")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private List<SysRole> roleList;
}
