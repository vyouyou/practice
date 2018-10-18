import java.awt.*;
import java.awt.event.*;

/**
 * @author qisy01
 * @create 18-10-16
 * @since 1.0.0
 */
public class GuiTest {
    public static void main(String[] args) {
        Frame f = new Frame("hello");
        f.setBounds(0,0,200,200);
        Button button = new Button("点我啊");
        f.add(button);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("你再点一下");
            }
        });
        f.setVisible(true);
    }
}
