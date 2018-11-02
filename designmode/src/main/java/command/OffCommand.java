package command;

/**
 * 封装light的off方法
 *
 * @author qisy01
 * @create 18-11-1
 * @since 1.0.0
 */
public class OffCommand implements ICommond {
    private final Light light;

    public OffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void invoke() {
        light.off();
    }
}
