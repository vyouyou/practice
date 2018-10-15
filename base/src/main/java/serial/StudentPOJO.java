package serial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author qisy01
 * @create 18-10-14
 * @since 1.0.0
 */
@Setter
@Getter
public class StudentPOJO implements Serializable {
    private String name;

    private Integer age;

    private String sex;

    final static long serialVersionUID = 3413042;
}
