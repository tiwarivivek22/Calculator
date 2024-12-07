import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    public static void main(String[] args) {
        MyFrame calculator = new MyFrame(); // object of class

        calculator.setTitle("Scientific Calculator");
        Image image = new ImageIcon("./images/icon.png").getImage();
        calculator.setIconImage(image);
        calculator.setSize(645, 500);
        calculator.setResizable(false);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setLayout(null);
        calculator.setVisible(true);
    }
}

class MyFrame extends JFrame implements ActionListener {
    Container jar;
    JLabel numbersystem, datatype, fibonacci;
    JTextField textField, fibonacciTerm;
    JButton[] numberButtons = new JButton[10];
    JButton[] letters = new JButton[6];
    JButton[] arithmetic = new JButton[6];
    JButton[] trigonometric = new JButton[9];
    JButton sin, cos, tan, insin, incos, intan, sinh, cosh, tanh;
    JButton add, diff, div, prod, mod, equ;
    JButton clr, point, percent, deg, rad, exp, log, fib;
    JComboBox<String> ns, dt;
    String[] modes = { "Decimal", "Binary", "Hexadecimal", "Octal" };
    String[] types = { "int", "byte", "short", "long", "float", "double" };
    JPanel panel2, panel1, panel3, panel4, panel5;
    double n1 = 0, n2 = 0, result = 0;
    int op = 0; // for operators
    boolean pointCheck = false; // for checking decimal point on the text field

    public MyFrame() { // CONSTRUCTOR
        jar = this.getContentPane();
        jar.setLayout(null);
        jar.setBackground(Color.decode("#bc6c25"));

        textField = new JTextField(); // Takes inputs and displays output
        textField.setBounds(25, 80, 225, 30);
        textField.setEditable(false);
        textField.addActionListener(this);
        textField.setBackground(Color.WHITE);

        clr = new JButton("CLR"); // clears everything on the text fields
        clr.setBounds(251, 80, 73, 30);
        clr.setFocusable(false);
        clr.addActionListener(this);
        clr.setBackground(Color.decode("#8b4007"));
        clr.setForeground(Color.WHITE);

        // PANEL 1: combo boxes and corresponding labels for changing modes and types
        panel1 = new JPanel();
        panel1.setBounds(25, 5, 300, 60);
        panel1.setLayout(new GridLayout(2, 2, 10, 0));
        panel1.setBackground(Color.decode("#bc6c25"));

        numbersystem = new JLabel("Select number system:");
        numbersystem.setForeground(Color.WHITE);
        ns = new JComboBox<String>(modes); // combobox for MODES
        ns.addActionListener(this);
        ns.setBackground(Color.decode("#8b4007"));
        ns.setForeground(Color.WHITE);
        ns.setFocusable(false);

        datatype = new JLabel("Select data type:");
        datatype.setForeground(Color.WHITE);
        dt = new JComboBox<String>(types); // combobox for TYPES
        dt.addActionListener(this);
        dt.setBackground(Color.decode("#8b4007"));
        dt.setForeground(Color.WHITE);
        dt.setFocusable(false);

        panel1.add(numbersystem); // adding all elements to panel
        panel1.add(datatype);
        panel1.add(ns);
        panel1.add(dt);

        // PANEL 2: numbers, letters, arithmetic operators and equal button
        panel2 = new JPanel();
        panel2.setBounds(50, 125, 250, 300);
        panel2.setLayout(new GridLayout(6, 4, 5, 5));
        panel2.setBackground(Color.decode("#bc6c25"));

        percent = new JButton("%"); // calculates percentage
        buttonFormat(percent);
        point = new JButton("."); // displays decimal point
        buttonFormat(point);

        add = new JButton("+"); // summation
        diff = new JButton("-"); // difference
        prod = new JButton("x"); // multiplication
        div = new JButton("/"); // division
        mod = new JButton("Re"); // remainder
        equ = new JButton("="); // equal

        arithmetic[0] = add;
        arithmetic[1] = diff;
        arithmetic[2] = prod;
        arithmetic[3] = div;
        arithmetic[4] = mod;
        arithmetic[5] = equ;

        for (int i = 0; i < arithmetic.length; i++) {
            buttonFormat(arithmetic[i]);
        }
        // format equal button differently for prominence
        arithmetic[5].setBackground(Color.decode("#8b4007"));
        arithmetic[5].setForeground(Color.WHITE);

        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            buttonFormat(numberButtons[i]);
        }
        for (int i = 0; i < letters.length; i++) {
            char j = (char) (65 + i);
            letters[i] = new JButton(" " + j);
            buttonFormat(letters[i]);
        }

        panel2.add(numberButtons[0]); // adding and arranging buttons in order
        panel2.add(numberButtons[1]);
        panel2.add(numberButtons[2]);
        panel2.add(numberButtons[3]);

        panel2.add(numberButtons[4]);
        panel2.add(numberButtons[5]);
        panel2.add(numberButtons[6]);
        panel2.add(numberButtons[7]);

        panel2.add(numberButtons[8]);
        panel2.add(numberButtons[9]);
        panel2.add(letters[0]);
        panel2.add(letters[1]);

        panel2.add(letters[2]);
        panel2.add(letters[3]);
        panel2.add(letters[4]);
        panel2.add(letters[5]);

        panel2.add(arithmetic[0]);
        panel2.add(arithmetic[1]);
        panel2.add(arithmetic[2]);
        panel2.add(arithmetic[3]);

        panel2.add(arithmetic[4]);
        panel2.add(percent);
        panel2.add(point);
        panel2.add(arithmetic[5]);

        // PANEL 3: radian, degree, exponential and log buttons which function without
        // equal button
        panel3 = new JPanel();
        panel3.setBounds(350, 30, 225, 80);
        panel3.setLayout(new GridLayout(2, 2, 5, 5));
        panel3.setBackground(Color.decode("#bc6c25"));

        deg = new JButton("DEG");
        buttonFormat(deg);

        rad = new JButton("RAD");
        buttonFormat(rad);

        exp = new JButton("EXP");
        buttonFormat(exp);

        log = new JButton("LOG");
        buttonFormat(log);

        panel3.add(deg); // adding elements to panel
        panel3.add(rad);
        panel3.add(log);
        panel3.add(exp);

        // PANEL 4: trigonometric functions
        panel4 = new JPanel();
        panel4.setBounds(350, 125, 225, 200);
        panel4.setLayout(new GridLayout(3, 3, 5, 5));
        panel4.setBackground(Color.decode("#bc6c25"));

        sin = new JButton("sin"); // sine
        cos = new JButton("cos"); // cosine
        tan = new JButton("tan"); // tangent
        insin = new JButton("sin-1"); // sine inverse
        incos = new JButton("cos-1"); // cosine inverse
        intan = new JButton("tan-1"); // tangent inverse
        sinh = new JButton("sinh"); // hyperbolic sine
        cosh = new JButton("cosh"); // hyperbolic cosine
        tanh = new JButton("tanh"); // hyperbolic tangent

        trigonometric[0] = sin;
        trigonometric[1] = cos;
        trigonometric[2] = tan;
        trigonometric[3] = insin;
        trigonometric[4] = incos;
        trigonometric[5] = intan;
        trigonometric[6] = sinh;
        trigonometric[7] = cosh;
        trigonometric[8] = tanh;

        for (int i = 0; i < trigonometric.length; i++) { // loop for formatting and adding
            buttonFormat(trigonometric[i]);
            panel4.add(trigonometric[i]);
        }

        // PANEL 5: display for fibonacci term
        panel5 = new JPanel();
        panel5.setBounds(350, 330, 225, 90);
        panel5.setLayout(new GridLayout(3, 1, 5, 5));
        panel5.setBackground(Color.decode("#bc6c25"));

        fibonacci = new JLabel("Fibonacci Term:"); // Label
        fibonacci.setForeground(Color.WHITE);

        fibonacciTerm = new JTextField(); // Text field
        fibonacciTerm.setEditable(false);
        fibonacciTerm.addActionListener(this);
        fibonacciTerm.setBackground(Color.WHITE);

        fib = new JButton("Display"); // Button
        fib.addActionListener(this);
        fib.setFocusable(false);
        fib.setBackground(Color.decode("#8b4007"));
        fib.setForeground(Color.WHITE);

        panel5.add(fibonacci); // adding all elements
        panel5.add(fibonacciTerm);
        panel5.add(fib);

        // Add every panel and element to the container
        jar.add(clr);
        jar.add(panel1);
        jar.add(panel2);
        jar.add(panel3);
        jar.add(panel4);
        jar.add(panel5);
        jar.add(textField);
        jar.setVisible(true);
    }

    public void buttonFormat(JButton x) {
        x.addActionListener(this);
        x.setFocusable(false);
        x.setBackground(Color.WHITE);
    }

    public void Letters(boolean enable) { // for enabling or disabling letters according to modes
        for (int i = 0; i < letters.length; i++) {
            letters[i].setEnabled(enable);
        }
    }

    public void Numbers(int n, boolean enable) { // enabling and disabling numbers for each mode
        for (int i = n; i < numberButtons.length; i++) {
            numberButtons[i].setEnabled(enable);
        }
    }

    public void Arithmetic(boolean enable) { // enabling or disabling arithmetic operators
        for (int i = 0; i < arithmetic.length; i++) {
            arithmetic[i].setEnabled(enable);
        }
    }

    public void realTypeButtons(boolean enable) { // buttons compatible with only real types
        for (int i = 0; i < trigonometric.length; i++) {
            trigonometric[i].setEnabled(enable);
        }
        point.setEnabled(enable);
        percent.setEnabled(enable);
        deg.setEnabled(enable);
        rad.setEnabled(enable);
        exp.setEnabled(enable);
        log.setEnabled(enable);
        fib.setEnabled(!enable);
        percent.setEnabled(enable);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < numberButtons.length; i++) { // Display numbers on text field
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }
        for (int i = 0; i < letters.length; i++) { // Display letters on text field
            if (e.getSource() == letters[i]) {
                textField.setText(textField.getText() + (char) (97 + i));
            }
        }
        if (e.getSource() == point) { // Display decimal point on text field
            if (!pointCheck) {
                textField.setText(textField.getText() + ".");
                pointCheck = true; // Each value can have a single decimal point at a time
            } else
                textField.setText(textField.getText() + "");
        }
        if (e.getSource() == clr) { // Clear text fields
            textField.setText("");
            fibonacciTerm.setText("");
            pointCheck = false;
        }
        if (e.getSource() == percent) { // Percent button divides the number by 100
            n1 = Float.parseFloat(textField.getText());
            result = n1 / 100;
            textField.setText(Float.toString((float) result));
        }
        try {
            if (e.getSource() == fib) { // Fibonacci term is displayed
                int n = Integer.parseInt(textField.getText());
                int[] series = new int[n];
                series[0] = 0;
                series[1] = 1;
                for (int i = 2, j = 1, k = 0; i < series.length; i++, j++, k++) {
                    series[i] = series[j] + series[k];
                }
                fibonacciTerm.setText(Integer.toString((int) series[n - 1]));
            }
        } catch (ArrayIndexOutOfBoundsException x) {
            fibonacciTerm.setText("Term not available");
        }

        // MODE: DECIMAL
        if (ns.getSelectedItem().equals("Decimal")) {
            Numbers(0, true); // Enable all numbers
            Letters(false); // Disable all letters
            Arithmetic(true);
            try {
                if (dt.getSelectedItem().equals("int")) { // TYPE: INT
                    realTypeButtons(false); // Disable buttons unsuitable with integer type
                    // Arithmetic operations
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Integer.parseInt(textField.getText());
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Integer.parseInt(textField.getText());
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Integer.toString((int) result));
                    }
                }
                if (dt.getSelectedItem().equals("byte")) { // TYPE: BYTE
                    realTypeButtons(false); // Disable buttons unsuitable with integer type
                    // Arithmetic operations
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Byte.parseByte(textField.getText());
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Byte.parseByte(textField.getText());
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Byte.toString((byte) result));
                    }
                }
                if (dt.getSelectedItem().equals("short")) { // TYPE: SHORT
                    realTypeButtons(false); // Disable buttons unsuitable with integer types
                    // Arithmetic operations
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Short.parseShort(textField.getText());
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Short.parseShort(textField.getText());
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Short.toString((short) result));
                    }
                }
                if (dt.getSelectedItem().equals("long")) { // TYPE: LONG
                    realTypeButtons(false); // Disable buttons unsuitable with integer type
                    // Arithmetic operations
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Long.parseLong(textField.getText());
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Long.parseLong(textField.getText());
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Long.toString((long) result));
                    }
                }
                if (dt.getSelectedItem().equals("float")) { // TYPE: FLOAT
                    realTypeButtons(true); // Enable buttons suitable with real type
                    // Arithmetic operations
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Float.parseFloat(textField.getText());
                            op = i;
                            textField.setText("");
                            pointCheck = false;
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Float.parseFloat(textField.getText());
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Float.toString((float) result));
                    }

                    if (e.getSource() == rad) { // Converts entered value from degrees to radians
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.toRadians(n1);
                        textField.setText(Float.toString((float) result));
                    }
                    if (e.getSource() == deg) { // Converts entered value from radians to degrees
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.toDegrees(n1);
                        textField.setText(Float.toString((float) result));
                    }
                    if (e.getSource() == exp) { // Exponential of entered value
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.exp(n1);
                        textField.setText(Float.toString((float) result));
                    }
                    if (e.getSource() == log) { // Logarithm of entered value
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.log(n1);
                        textField.setText(Float.toString((float) result));
                    }

                    // Trigonometric operations
                    if (e.getSource() == trigonometric[0]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.sin(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[1]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.cos(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[2]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.tan(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[3]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.asin(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[4]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.acos(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[5]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.atan(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[6]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.sinh(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[7]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.cosh(n1);
                        textField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == trigonometric[8]) {
                        n1 = Float.parseFloat(textField.getText());
                        result = Math.tanh(n1);
                        textField.setText(String.valueOf(result));
                    }
                }
                if (dt.getSelectedItem().equals("double")) { // TYPE: DOUBLE
                    realTypeButtons(true); // Enable buttons suitable with real type
                    // Arithmetic operations
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Double.parseDouble(textField.getText());
                            op = i;
                            textField.setText("");
                            pointCheck = false;
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Double.parseDouble(textField.getText());
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Double.toString(result));
                    }

                    // Radians to Degrees
                    if (e.getSource() == rad) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.toRadians(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == deg) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.toDegrees(n1);
                        textField.setText(Double.toString(result));
                    }
                    // Exponential of entered value
                    if (e.getSource() == exp) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.exp(n1);
                        textField.setText(Double.toString(result));
                    }
                    // Logarithm of entered value
                    if (e.getSource() == log) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.log(n1);
                        textField.setText(Double.toString(result));
                    }

                    // Trigonometric operations
                    if (e.getSource() == trigonometric[0]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.sin(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[1]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.cos(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[2]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.tan(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[3]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.asin(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[4]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.acos(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[5]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.atan(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[6]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.sinh(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[7]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.cosh(n1);
                        textField.setText(Double.toString(result));
                    }
                    if (e.getSource() == trigonometric[8]) {
                        n1 = Double.parseDouble(textField.getText());
                        result = Math.tanh(n1);
                        textField.setText(Double.toString(result));
                    }
                }
            } catch (NumberFormatException x) {
                textField.setText("Invalid Input");
            }
        }
        // MODE: BINARY
        if (ns.getSelectedItem().equals("Binary")) {
            // Disable all numbers and letters except 0 & 1
            Numbers(0, true);
            Numbers(2, false);
            Letters(false);
            realTypeButtons(false);
            fib.setEnabled(false);
            try {
                if (dt.getSelectedItem().equals("int")) { // TYPE: INT
                    Numbers(0, true);
                    Numbers(2, false);
                    Arithmetic(true);
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Integer.parseInt(textField.getText(), 2);
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Integer.parseInt(textField.getText(), 2);
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Integer.toBinaryString((int) result));
                    }
                }
                if (dt.getSelectedItem().equals("long")) { // TYPE: LONG
                    Numbers(0, true);
                    Numbers(2, false);
                    Arithmetic(true);
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Long.parseLong(textField.getText(), 2);
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Long.parseLong(textField.getText(), 2);
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Long.toBinaryString((long) result));
                    }
                }
                if (dt.getSelectedItem().equals("float")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("double")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("short")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("byte")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
            } catch (NumberFormatException x) {
                textField.setText("Invalid Input");
            }
        }
        // MODE: OCTAL
        if (ns.getSelectedItem().equals("Octal")) {
            // Disable letters and numbers 8,9
            Numbers(0, true);
            Numbers(8, false);
            Letters(false);
            realTypeButtons(false);
            fib.setEnabled(false);
            try {
                if (dt.getSelectedItem().equals("int")) {
                    Numbers(0, true);
                    Numbers(8, false);
                    Arithmetic(true);
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Integer.parseInt(textField.getText(), 8);
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Integer.parseInt(textField.getText(), 8);
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Integer.toOctalString((int) result));
                    }
                }
                if (dt.getSelectedItem().equals("long")) {
                    Numbers(0, true);
                    Numbers(8, false);
                    Arithmetic(true);
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Long.parseLong(textField.getText(), 8);
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Long.parseLong(textField.getText(), 8);
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Long.toOctalString((long) result));
                    }
                }
                if (dt.getSelectedItem().equals("float")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("double")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("short")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("byte")) {
                    Numbers(0, false);
                    Arithmetic(false);
                }
            } catch (NumberFormatException x) {
                textField.setText("Invalid Input");
            }
        }
        // MODE: HEXADECIMAL
        if (ns.getSelectedItem().equals("Hexadecimal")) {
            // Enable all numbers and letters
            Numbers(0, true);
            Letters(true);
            realTypeButtons(false);
            fib.setEnabled(false);
            try {
                if (dt.getSelectedItem().equals("int")) {
                    Numbers(0, true);
                    Letters(true);
                    Arithmetic(true);
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Integer.parseInt(textField.getText(), 16);
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Integer.parseInt(textField.getText(), 16);
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Integer.toHexString((int) result));
                    }
                }
                if (dt.getSelectedItem().equals("long")) {
                    Numbers(0, true);
                    Letters(true);
                    Arithmetic(true);
                    for (int i = 0; i < 5; i++) {
                        if (e.getSource() == arithmetic[i]) {
                            n1 = Long.parseLong(textField.getText(), 16);
                            op = i;
                            textField.setText("");
                        }
                    }
                    if (e.getSource() == arithmetic[5]) {
                        n2 = Long.parseLong(textField.getText(), 16);
                        switch (op) {
                            case 0:
                                result = n1 + n2;
                                break;
                            case 1:
                                result = n1 - n2;
                                break;
                            case 2:
                                result = n1 * n2;
                                break;
                            case 3:
                                result = n1 / n2;
                                break;
                            case 4:
                                result = n1 % n2;
                                break;
                        }
                        textField.setText(Long.toHexString((long) result));
                    }
                }
                if (dt.getSelectedItem().equals("float")) {
                    Numbers(0, false);
                    Letters(false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("double")) {
                    Numbers(0, false);
                    Letters(false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("short")) {
                    Numbers(0, false);
                    Letters(false);
                    Arithmetic(false);
                }
                if (dt.getSelectedItem().equals("byte")) {
                    Numbers(0, false);
                    Letters(false);
                    Arithmetic(false);
                }
            } catch (NumberFormatException x) {
                textField.setText("Invalid Input");
            }
        }
    }
}
