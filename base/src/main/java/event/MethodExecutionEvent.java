package event;

import lombok.Getter;
import lombok.Setter;

import java.util.EventObject;

/**
 * @author qisy01
 * @create 18-10-26
 * @since 1.0.0
 */
@Setter
@Getter
public class MethodExecutionEvent extends EventObject {

    private String methodName;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MethodExecutionEvent(Object source,String name) {
        super(source);
        this.methodName = name;
    }
}
