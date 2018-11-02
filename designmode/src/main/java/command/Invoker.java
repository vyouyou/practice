package command;

/**
 * invoker中会包含多个命令，并且可以将命令任意组合
 *
 * @author qisy01
 * @create 18-11-1
 * @since 1.0.0
 */
public class Invoker {
    private final OffCommand[] offCommands;
    private final OnCommand[] onCommands;

    public Invoker(OffCommand[] offCommands, OnCommand[] onCommands) {
        this.offCommands = offCommands;
        this.onCommands = onCommands;
    }

    public void closeAll() {
        for (OffCommand offCommand : this.offCommands) {
            offCommand.invoke();
        }
    }

    public void openAll() {
        for (OnCommand onCommand : this.onCommands) {
            onCommand.invoke();
        }
    }
}
