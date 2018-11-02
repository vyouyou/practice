package command;

/**
 * 封装light的on方法
 *
 * @author qisy01
 * @create 18-11-1
 * @since 1.0.0
 */
public class OnCommand implements ICommond {

    private final Light light;

    public OnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void invoke() {
        light.on();
    }
}
