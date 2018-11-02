package event;

import lombok.extern.java.Log;

/**
 * @author qisy01
 * @create 18-10-26
 * @since 1.0.0
 */
@Log
public class SimpleMethodExecutionEventListener implements MethodExecutionEventListener {
    @Override
    public void onMethodBegin(MethodExecutionEvent event) {
        String methodName = event.getMethodName();
        log.info("begin method is " + methodName);
    }

    @Override
    public void onMethodEnd(MethodExecutionEvent event) {
        String methodName = event.getMethodName();
        log.info("end method is " + methodName);
    }
}
