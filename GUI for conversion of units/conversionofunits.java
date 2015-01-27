/**
 * conversion of units
 *
 * @version   $Id: conversionofunits.java,v 1.1 2014/11/14 05:19:46
 *
 * @author      Shraddha Atrawalkar
 *
 * Revisions:
 * Initial revision
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * This class creates a frame with elements like textarea,button combobox and
 * labels to display the conversion of units
 *
 */

public class conversionofunits implements ActionListener {

    // define elements that are part of the frame
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JLabel fromlabel;
    private JLabel tolabel;
    private JPanel controlPanel;
    private JPanel displayPanel;
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JPanel convPanel;
    private JLabel infoLabel;
    private JTextArea from;
    private JTextArea to;
    private JComboBox conop;
    private JButton convert;
    private JButton buttons[] = new JButton[12];

    // define array of string to hold the text of conversion units
    private String[] options = new String[] { "inches to meter",
            "meter to inches", "km/hr to mph", "mph to km/h",
            "Celsius to Fahrenheit", "Fahrenheit to Celsius",
            "km to light year", "light year to km" };
    // define an array to hold all the buttons to be displayed on the frame
    private String[] all_buttons = { "7", "8", "9",

    "4", "5", "6",

    "1", "2", "3",

    ".", "0", "C" };

    // flag to check if convert is hit
    public static boolean flag = false;
    // flag to check if this is the first time execution
    public static boolean first = false;

    /**
     * Constructor to initialize the Frame and its elements
     */
    public conversionofunits() {
        prepareGUI();
    }

    /**
     * Main function
     * 
     * @param args
     */
    public static void main(String[] args) {
        conversionofunits conversion1 = new conversionofunits();
        conversion1.unitConversion();
    }

    /**
     * Method to define frame and its elements
     */
    private void prepareGUI() {

        mainFrame = new JFrame("Conversion Program");
        // set the size and layout of the frame
        mainFrame.setSize(1000, 1000);
        mainFrame.setLayout(new GridLayout(6, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        // set the variable since this is the initialization of the frame
        first = true;
        // Add labels for header and information of the frame
        headerLabel = new JLabel("", JLabel.CENTER);
        headerLabel.setSize(400, 100);

        infoLabel = new JLabel("", JLabel.CENTER);
        infoLabel.setSize(500, 100);

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        // create a new Panel to add heading
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 1));
        mainFrame.add(controlPanel);
        controlPanel.add(new Panel());
        controlPanel.add(headerLabel);
        mainFrame.add(controlPanel);

        // create a new Panel to add display area
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(3, 3));
        displayPanel.setSize(10, 10);
        mainFrame.add(displayPanel);
        from = new JTextArea(2, 2);
        to = new JTextArea(2, 2);

        from.setEditable(false);
        to.setEditable(false);

        String op1 = options[0];
        String f1[] = op1.split("to");

        fromlabel = new JLabel(f1[0].concat(":   "), SwingConstants.RIGHT);
        tolabel = new JLabel(f1[1].concat(":   "), SwingConstants.RIGHT);
        displayPanel.add(fromlabel);
        displayPanel.add(from);
        displayPanel.add(new JPanel());
        displayPanel.add(new JPanel());
        displayPanel.add(new JPanel());
        displayPanel.add(new JPanel());
        displayPanel.add(tolabel);
        displayPanel.add(to);

        // create a new Panel to add combobox list
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(2, 2));
        mainFrame.add(new Panel().add(infoLabel));
        mainFrame.add(listPanel);
        conop = new JComboBox(options);
        conop.setSelectedIndex(0);
        listPanel.add(new JPanel());
        listPanel.add(conop);
        listPanel.add(new JPanel());
        listPanel.add(new JPanel());
        listPanel.add(new JPanel());
        listPanel.add(new JPanel());

        // create a new Panel to add buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5));
        // create buttons
        buttonPanel.add(new JPanel());
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton();
            // add the text to each button
            buttons[i].setText(all_buttons[i]);
            // add action listener to each button
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
            if (i == 2 || i == 5 || i == 8) {
                buttonPanel.add(new JPanel());
                buttonPanel.add(new JPanel());

            }

        }
        buttonPanel.add(new JPanel());
        buttonPanel.add(new JPanel());
        buttonPanel.add(new JPanel());
        buttonPanel.add(new JPanel());
        mainFrame.add(buttonPanel);

        // create a new Panel to add the convert button
        convPanel = new JPanel();
        convPanel.setLayout(new GridLayout(3, 3));
        mainFrame.add(convPanel);
        mainFrame.setVisible(true);
        convert = new JButton("Convert");
        convert.setSize(10, 10);
        JPanel tempanel = new JPanel();
        tempanel.setLayout(new BoxLayout(tempanel, BoxLayout.Y_AXIS));
        convPanel.add(new JPanel());
        convPanel.add(new JPanel());
        convPanel.add(new JPanel());
        convPanel.add(new JPanel());
        convPanel.add(convert);
        convPanel.add(new JPanel());
        convPanel.add(new JPanel());
        convPanel.add(new JPanel());
        convPanel.add(new JPanel());

    }

    /**
     * 
     */
    private void unitConversion() {
        // set the text for the labels
        headerLabel.setText("Conversion Of Units");
        infoLabel.setText("Choose the conversion you want to perform");

        // set the action listener for the JComboBox
        conop.addActionListener(new ActionListener() {

            // On selection of each option on the list change the labels for the
            // text area
            @Override
            public void actionPerformed(ActionEvent ce) {
                if (conop.getSelectedIndex() != -1) {
                    if (conop.getSelectedIndex() == 0) {
                        fromlabel.setText("Inch".concat(":   "));
                        tolabel.setText("Meter".concat(":   "));

                    } else if (conop.getSelectedIndex() == 1) {
                        fromlabel.setText("Meter".concat(":   "));
                        tolabel.setText("Inch".concat(":   "));

                    } else if (conop.getSelectedIndex() == 2) {
                        fromlabel.setText("Km/hr".concat(":   "));
                        tolabel.setText("Mph".concat(":   "));

                    } else if (conop.getSelectedIndex() == 3) {
                        fromlabel.setText("Mph".concat(":   "));
                        tolabel.setText("Km/hr".concat(":   "));

                    } else if (conop.getSelectedIndex() == 4) {
                        fromlabel.setText("Celsius".concat(":   "));
                        tolabel.setText("Farenheit".concat(":   "));

                    } else if (conop.getSelectedIndex() == 5) {
                        fromlabel.setText("Farenheit".concat(":   "));
                        tolabel.setText("Celsius   ");

                    } else if (conop.getSelectedIndex() == 6) {
                        fromlabel.setText("Km".concat(":   "));
                        tolabel.setText("Light Year".concat(":   "));

                    } else if (conop.getSelectedIndex() == 7) {
                        fromlabel.setText("Light Year".concat(":   "));
                        tolabel.setText("Km".concat(":   "));

                    }

                }

            }
        });

        // actions to be performed when Convert button is clicked
        convert.addActionListener(new ActionListener() {

            // get the value of input from textarea
            @Override
            public void actionPerformed(ActionEvent ae) {
                flag = true;
                first = false;
                double fromvalue;
                try {
                    fromvalue = Double.parseDouble(from.getText());
                } catch (Exception e) {
                    from.setText("0.0");
                    fromvalue = 0;
                }

                double tovalue = 0;

                // perform conversion when convert button is clicked
                if (conop.getSelectedIndex() != -1) {
                    if (conop.getSelectedIndex() == 0) {

                        tovalue = inchtometer(fromvalue);
                    } else if (conop.getSelectedIndex() == 1) {

                        tovalue = metertoinch(fromvalue);
                    } else if (conop.getSelectedIndex() == 2) {

                        tovalue = kmhtomph(fromvalue);
                    } else if (conop.getSelectedIndex() == 3) {

                        tovalue = mphtokmh(fromvalue);
                    } else if (conop.getSelectedIndex() == 4) {

                        tovalue = CelsiustoFahrenheit(fromvalue);
                    } else if (conop.getSelectedIndex() == 5) {

                        tovalue = FahrenheittoCelsius(fromvalue);
                    } else if (conop.getSelectedIndex() == 6) {

                        tovalue = kmtolightyear(fromvalue);
                    } else if (conop.getSelectedIndex() == 7) {

                        tovalue = lightyeartokm(fromvalue);
                    }

                }
                // set the converted value to the other textarea
                to.setText(Double.toString(tovalue));
                flag = false;
            }

        });
        mainFrame.setVisible(true);
    }

    /**
     * Convert from inch to meter
     * 
     * @param inch
     *            inch value
     * @return meter value
     */
    double inchtometer(double inch) {
        try {

            double newmeter = (inch / 39.370);
            return newmeter;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * convert meter to inch
     * 
     * @param meter
     *            meter value
     * @return inch value
     */
    double metertoinch(double meter) {
        try {

            double newinch = (meter * 39.370);
            return newinch;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * convert Fahrenheit to Celsius
     * 
     * @param fahrenneit
     *            value
     * @return Celsius value
     */
    double FahrenheittoCelsius(double faren) {
        try {

            double newcel = ((faren - 32) / 1.8);
            return newcel;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Convert Celsius to Fahrenheit
     * 
     * @param cel
     *            Celsius value
     * @return Fahrenheit value
     */
    double CelsiustoFahrenheit(double cel) {
        try {

            double newfar = ((cel * 1.8) + 32.00);
            return newfar;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Convert light year to kilometer
     * 
     * @param lightyr
     *            light year value
     * @return kilometer value
     */
    double lightyeartokm(double lightyr) {
        try {

            double newkm = (double) (lightyr * 9460730472580.80);
            return newkm;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Convert kilometer to lightyear
     * 
     * @param km
     *            kilometer value
     * @return light year value
     */
    double kmtolightyear(double km) {
        try {

            double newlight = (double) (km / 9460730472580.80);
            return newlight;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Convert miles/hr to kilometer/hr
     * 
     * @param mph
     *            miles/hr value
     * @return kilometer/hr value
     */
    double mphtokmh(double mph) {
        try {

            double newinch = (mph * 1.6093);
            return newinch;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Convert kilometer/hr to miles/hr
     * 
     * @param km
     *            kilometer/hr value
     * @return miles/hr value
     */
    double kmhtomph(double km) {
        try {

            double newmph = (km * 0.62137);
            return newmph;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Action performed when each number button is clicked
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (first || (flag)) {
            if (ae.getSource() == buttons[0]) {
                from.append("7");
            }
            if (ae.getSource() == buttons[1]) {
                from.append("8");
            }
            if (ae.getSource() == buttons[2]) {
                from.append("9");
            }
            if (ae.getSource() == buttons[3]) {
                from.append("4");
            }
            if (ae.getSource() == buttons[4]) {
                from.append("5");
            }
            if (ae.getSource() == buttons[5]) {
                from.append("6");
            }
            if (ae.getSource() == buttons[6]) {
                from.append("1");
            }
            if (ae.getSource() == buttons[7]) {
                from.append("2");
            }
            if (ae.getSource() == buttons[8]) {
                from.append("3");
            }
            if (ae.getSource() == buttons[9]) {
                from.append(".");
            }
            if (ae.getSource() == buttons[10]) {
                from.append("0");
            }

        }
        if (ae.getSource() == buttons[11]) {
            clear();
        }
    }

    /**
     * method performed when C(clear) button is clicked
     */
    private void clear() {
        from.setText("");
        to.setText("");
        first = true;
    }
}