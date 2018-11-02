package command;

import lombok.extern.java.Log;

/**
 * @author qisy01
 * @create 18-11-1
 * @since 1.0.0
 */
@Log
public class Light {
    public void on() {
        log.info("light on");
    }

    public void off() {
        log.info("light off");
    }
}
