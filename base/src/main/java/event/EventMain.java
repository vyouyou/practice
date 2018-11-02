package event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qisy01
 * @create 18-10-26
 * @since 1.0.0
 */
public class EventMain {
    enum MethodExecutionStatus {BEGIN, END}

    ;

    private List<MethodExecutionEventListener> listeners
            = new ArrayList<>();

    public void methodToMonitor() {
        MethodExecutionEvent event =
                new MethodExecutionEvent(this, "methodToMonitor");
        publishEvent(MethodExecutionStatus.BEGIN, event);
    }

    protected void publishEvent(MethodExecutionStatus status,
                                MethodExecutionEvent event) {
        listeners.forEach(listener -> {
            if (MethodExecutionStatus.BEGIN.equals(status)) {
                listener.onMethodBegin(event);
            } else {
                listener.onMethodEnd(event);
            }
        });
    }

    public void addListener(MethodExecutionEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MethodExecutionEventListener listener) {
        listeners.remove(listener);
    }

    public static void main(String[] args) {
        EventMain eventMain = new EventMain();
        eventMain.addListener(new SimpleMethodExecutionEventListener());
        eventMain.methodToMonitor();
    }
}
