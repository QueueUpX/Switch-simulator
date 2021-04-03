package apps.delta;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Label {
    public static final int PIN_RIGHT = 1;
    public static final int PIN_LEFT = 0;
    public boolean isInFocus = false;
    public boolean isSelected = false;
    private int width = 60;
    private int height = 24;
    private final int pinWidth = 18;
    private final int pinHeightOffset = height/2;
    private int x;
    private int y;
    private int position;
    private JTextField textField;
    private String value;

    Label(int x, int y, int position) {
        this.x = x;
        this.y = y;
        this.position = position;
        textField = new JTextField() {
            @Override
            public void setBorder(Border border) {

            }
        };
        ((AbstractDocument)textField.getDocument()).setDocumentFilter(new DocumentFilter(){
            final Pattern regEx = Pattern.compile("[01]");

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if(length > 1) {
                    return;
                }
                super.remove(fb, offset, length);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches() || textField.getText().length() > 2){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    e.consume();
                }

                super.keyPressed(e);

                textField.getParent().repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                value = textField.getText();
                textField.getParent().repaint();
            }
        });
        textField.setHighlighter(null);
        textField.setBounds(x+1, y+1, width-1, height-1);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.BOLD, 15));
        textField.setBackground(Color.white);
        textField.setCaretColor(Color.white);
        Set forwardKeys = textField.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        Set newForwardKeys = new HashSet(forwardKeys);
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
        Set backwardKeys = textField.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
        Set newBackwardKeys = new HashSet(backwardKeys);
        newBackwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
        newBackwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0));
        textField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
        textField.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newBackwardKeys);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                isInFocus = true;
                textField.getParent().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                isInFocus = false;
                textField.getParent().repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                textField.setCaretPosition(textField.getText().length());
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                isSelected = true;
                textField.setBounds(x-10+1, y-5+2, width+10-2, height+10-3);
                textField.getParent().repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                isSelected = false;
                textField.setBounds(x+1, y+1, width-1, height-1);
                textField.getParent().repaint();
            }
        });
    }

    void draw(Graphics2D g) {
        int x = this.x,y = this.y ,width = this.width, height = this.height;
        if(isInFocus) {
            g.setColor(Color.green);
        }
        if(isSelected) {
            x = this.x - 10;
            y = this.y - 5 ;
            width = this.width + 10;
            height = this.height + 10;
            g.setStroke(new BasicStroke(2));
        }
        g.drawRect(x, y, width, height);
        g.setColor(Color.black);
        drawPin(g);
    }

    private void drawPin(Graphics2D g) {
        g.setStroke(new BasicStroke(2));
        if (position == Label.PIN_RIGHT) {
            g.drawLine(x + width, y + pinHeightOffset, x + width + pinWidth, y + pinHeightOffset);
        }
        else {
            g.drawLine(x, y+pinHeightOffset, x-pinWidth, y+pinHeightOffset);
        }
        g.setStroke(new BasicStroke(1));
    }

    public void drawValue(Graphics2D g, String value) {
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString(value, x+(int)(g.getFontMetrics().stringWidth(value)/1.5), y+g.getFontMetrics().getHeight());
    }

    public Pin getPin() {
        if(position == Label.PIN_RIGHT) {
            return new Pin(x + width, y + pinHeightOffset, x + width + pinWidth, y + pinHeightOffset);
        }
        else {
            return new Pin(x, y+pinHeightOffset, x-pinWidth, y+pinHeightOffset);
        }
    }

    public JTextField getComponent() {
        return textField;
    }

    public String getValue() {
        return value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
