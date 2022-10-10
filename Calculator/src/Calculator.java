import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Calculator {

    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 70;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;

    private JFrame window; // Main window
    private JComboBox<String> comboCalcType, comboTheme;
    private JTextField inText; // Input
    // TODO
    private JButton btnC, btnBack, btnMod, btnDiv, btnMul, btnSub, btnAdd, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnEqual;

    private char opt = ' '; // Save the operator
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addWrite = true; // Connect numbers in display
    private double val = 0; // Save the value typed for calculation

    /*
        Mx Calculator:
        X = Row
        Y = Column

        +-------------------+
        |   +-----------+   |   y[0]
        |   |           |   |
        |   +-----------+   |
        |                   |
        |   C  <-   %   /   |   y[1]
        |   7   8   9   *   |   y[2]
        |   4   5   6   -   |   y[3]
        |   1   2   3   +   |   y[4]
        |   .   0     =     |   y[5]
        +-------------------+
         x[0] x[1] x[2] x[3]

    */

    /*
        +-------------------+
        |   +-----------+   |   y[0]
        |   |           |   |
        |   +-----------+   |
        |                   |
        |   0   1   1   3   |   y[1]
        |   4   5   6   7   |   y[2]
        |   8   9   10  11  |   y[3]
        |   12  13  14  15  |   y[4]
        |   16  17    18    |   y[5]
        +-------------------+
         x[0] x[1] x[2] x[3]

    */

    public Calculator() {
        window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null); // Move window to center

        int[] x = {MARGIN_X, MARGIN_X + 90, 200, 290, 380};
        int[] y = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 180, MARGIN_Y + 260, MARGIN_Y + 340, MARGIN_Y + 420};

        inText = new JTextField("0");
        inText.setBounds(x[0], y[0], 350, 70);
        inText.setEditable(false);
        inText.setBackground(Color.WHITE);
        inText.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
        window.add(inText);

        btnC = initBtn("C", x[0], y[1], event -> {
            repaintFont();
            inText.setText("0");
            opt = ' ';
            val = 0;
        });

        btnBack = initBtn("<-", x[1], y[1], event -> {
            repaintFont();
            String str = inText.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inText.setText("0");
            } else {
                inText.setText(str2.toString());
            }
        });

        btnMod = initBtn("%", x[2], y[1], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText())) if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '%';
                go = false;
                addWrite = false;
            }
        });

        btnDiv = initBtn("/", x[3], y[1], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText())) if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '/';
                go = false;
                addWrite = false;
            } else {
                opt = '/';
            }
        });

        btn7 = initBtn("7", x[0], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("7");
                } else {
                    inText.setText(inText.getText() + "7");
                }
            } else {
                inText.setText("7");
                addWrite = true;
            }
            go = true;
        });

        btn8 = initBtn("8", x[1], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("8");
                } else {
                    inText.setText(inText.getText() + "8");
                }
            } else {
                inText.setText("8");
                addWrite = true;
            }
            go = true;
        });

        btn9 = initBtn("9", x[2], y[2], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("9");
                } else {
                    inText.setText(inText.getText() + "9");
                }
            } else {
                inText.setText("9");
                addWrite = true;
            }
            go = true;
        });

        btnMul = initBtn("*", x[3], y[2], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText())) if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '*';
                go = false;
                addWrite = false;
            } else {
                opt = '*';
            }
        });

        btn4 = initBtn("4", x[0], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("4");
                } else {
                    inText.setText(inText.getText() + "4");
                }
            } else {
                inText.setText("4");
                addWrite = true;
            }
            go = true;
        });

        btn5 = initBtn("5", x[1], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("5");
                } else {
                    inText.setText(inText.getText() + "5");
                }
            } else {
                inText.setText("5");
                addWrite = true;
            }
            go = true;
        });

        btn6 = initBtn("6", x[2], y[3], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("6");
                } else {
                    inText.setText(inText.getText() + "6");
                }
            } else {
                inText.setText("6");
                addWrite = true;
            }
            go = true;
        });

        btnSub = initBtn("-", x[3], y[3], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText())) if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }

                opt = '-';
                go = false;
                addWrite = false;
            } else {
                opt = '-';
            }
        });

        btn1 = initBtn("1", x[0], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("1");
                } else {
                    inText.setText(inText.getText() + "1");
                }
            } else {
                inText.setText("1");
                addWrite = true;
            }
            go = true;
        });

        btn2 = initBtn("2", x[1], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("2");
                } else {
                    inText.setText(inText.getText() + "2");
                }
            } else {
                inText.setText("2");
                addWrite = true;
            }
            go = true;
        });

        btn3 = initBtn("3", x[2], y[4], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("3");
                } else {
                    inText.setText(inText.getText() + "3");
                }
            } else {
                inText.setText("3");
                addWrite = true;
            }
            go = true;
        });

        btnAdd = initBtn("+", x[3], y[4], event -> {
            repaintFont();
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText())) if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '+';
                go = false;
                addWrite = false;
            } else {
                opt = '+';
            }
        });

        btnPoint = initBtn(".", x[0], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (!inText.getText().contains(".")) {
                    inText.setText(inText.getText() + ".");
                }
            } else {
                inText.setText("0.");
                addWrite = true;
            }
            go = true;
        });

        btn0 = initBtn("0", x[1], y[5], event -> {
            repaintFont();
            if (addWrite) {
                if (Pattern.matches("[0]*", inText.getText())) {
                    inText.setText("0");
                } else {
                    inText.setText(inText.getText() + "0");
                }
            } else {
                inText.setText("0");
                addWrite = true;
            }
            go = true;
        });

        btnEqual = initBtn("=", x[2], y[5], event -> {
            if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText())) if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '=';
                addWrite = false;
            }
        });
        btnEqual.setSize(2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);

        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close button clicked? = End The process
        window.setVisible(true);
    }

    private JComboBox<String> initCombo(String[] items, int x, int y, String toolTip, Consumer consumerEvent) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        combo.addItemListener(consumerEvent::accept);
        window.add(combo);

        return combo;
    }

    private JButton initBtn(String label, int x, int y, ActionListener event) {
        JButton btn = new JButton(label);
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(event);
        btn.setFocusable(false);
        window.add(btn);

        return btn;
    }

    public double calc(double x, String input, char opt) {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
        double y = Double.parseDouble(input);
        switch (opt) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            case '%':
                return x % y;
            case '^':
                return Math.pow(x, y);
            default:
                inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
                return y;
        }
    }

    private void repaintFont() {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
    }
}
