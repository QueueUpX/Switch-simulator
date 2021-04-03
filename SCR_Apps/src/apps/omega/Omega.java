package apps.omega;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class Omega extends JFrame {
    Object defaultUI = UIManager.getLookAndFeel();

    public Omega() {
        super("SCR_Apps_Omega_1.0");

        this.add(new MyPanel1());
        /*try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel() {

                @Override
                public void provideErrorFeedback(Component component) {

                    // Your beep decision goes here

                    // You want error feedback
                    //super.provideErrorFeedback(component);

                }
            });
        } catch (UnsupportedLookAndFeelException ignore) {
        }*/
        this.setSize(1020, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void dispose() {
        /*try {
            UIManager.setLookAndFeel((LookAndFeel) defaultUI);
        } catch (UnsupportedLookAndFeelException ignore) {
        }*/

        super.dispose();
    }
}