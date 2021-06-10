package apps.delta;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delta extends JFrame {
    Object defaultUI = UIManager.getLookAndFeel();

    public Delta() {
        super("SCR_Apps_Delta_1.0");

        this.add(new MyPanel());
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
        this.pack();
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

