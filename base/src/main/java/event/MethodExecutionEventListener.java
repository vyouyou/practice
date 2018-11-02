package event;

import java.util.EventListener;

/**
 * @author qisy01
 * @create 18-10-26
 * @since 1.0.0
 */
public interface MethodExecutionEventListener extends EventListener {
    void onMethodBegin(MethodExecutionEvent event);

    void onMethodEnd(MethodExecutionEvent event);

}
