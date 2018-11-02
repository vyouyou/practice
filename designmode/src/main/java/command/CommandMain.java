package command;

/**
 * @author qisy01
 * @create 18-11-1
 * @since 1.0.0
 */
public class CommandMain {
    public static void main(String[] args) {
        Light l1 = new Light();
        OnCommand onCommand1 = new OnCommand(l1);
        OffCommand offCommand1 = new OffCommand(l1);
        Light l2 = new Light();
        OnCommand onCommand2 = new OnCommand(l2);
        OffCommand offCommand2 = new OffCommand(l2);
        OnCommand[] onCommands = new OnCommand[]{onCommand1, onCommand2};
        OffCommand[] offCommands = new OffCommand[]{offCommand1, offCommand2};
        Invoker invoker = new Invoker(offCommands, onCommands);
        invoker.closeAll();
    }
}
