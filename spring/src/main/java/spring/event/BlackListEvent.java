package spring.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
/**
 * 事件
 */
public class BlackListEvent extends ApplicationEvent {
    private String address;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public BlackListEvent(Object source,String address) {
        super(source);
        this.address = address;
    }
}
