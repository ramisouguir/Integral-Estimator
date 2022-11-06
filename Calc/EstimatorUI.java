 
 
import java.awt.*;
import java.awt.event.*;
 
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
 
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
 
import java.text.*;
 
 public class EstimatorUI extends JPanel {
    //Values for the fields
    private String equation = "x^2";
    private float thresh = 5.0f; 
    private int iterations = 50;
    private float start = 2.0f;
    private float end = 5.0f;
    
 
    //Labels to identify the fields
    private JLabel equationLabel;
    private JLabel threshLabel;
    private JLabel iterationsLabel;
    private JLabel startLabel;
    private JLabel endLabel;
    private JLabel estimateLabel;
    private JLabel calculateLabel;
 
    //Strings for the labels
    private static String equationString = "Equation: ";
    private static String threshString = "Threshold: ";
    private static String iterationsString = "Precision: ";
    private static String startString = "Lower Bound: ";
    private static String endString = "Upper Bound: ";
    private static String estimateString = "Estimate: ";
    private static String calculateString = "T: ";
 
    //Fields for data entry
    private JTextField equationField;
    private JTextField threshField;
    private JTextField iterationsField;
    private JTextField startField;
    private JTextField endField;
    private JTextField estimateField;
    private JButton calculateButton;
 
    //Formats to format and parse numbers
    private NumberFormat threshFormat;
    private NumberFormat iterationFormat;
    
 
     public EstimatorUI() {
        super(new BorderLayout());
        float estimate = Estimator.finalEstimate(equation, thresh, iterations, start, end); 
        
        
        //Create the labels.
        equationLabel = new JLabel(equationString);
        threshLabel = new JLabel(threshString);
        iterationsLabel = new JLabel(iterationsString);
        startLabel = new JLabel(startString);
        endLabel = new JLabel(endString);
        estimateLabel = new JLabel(estimateString);
 
        //Create the text fields and set them up.
        equationField = new JTextField(equation);
        equationField.setColumns(10);
 
        threshField = new JTextField(""+thresh);
        threshField.setColumns(10);
 
        iterationsField = new JTextField(""+iterations);
        iterationsField.setColumns(10);
        
        startField = new JTextField(""+start);
        startField.setColumns(10);
        
        endField = new JTextField(""+end);
        endField.setColumns(10);
        
        estimateField = new JTextField(""+estimate);
        estimateField.setColumns(10);
        estimateField.setEditable(false);
        estimateField.setForeground(Color.blue);
        
        calculateButton = new JButton("Calculate");
        
        //Tell accessibility tools about label/textfield pairs.
        equationLabel.setLabelFor(equationField);
        threshLabel.setLabelFor(threshField);
        iterationsLabel.setLabelFor(iterationsField);
        startLabel.setLabelFor(startField);
        endLabel.setLabelFor(endField);
        equationLabel.setLabelFor(equationField);
 
        //Lay out the labels in a panel.
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(equationLabel);
        labelPane.add(threshLabel);
        labelPane.add(iterationsLabel);
        labelPane.add(startLabel);
        labelPane.add(endLabel);
        labelPane.add(estimateLabel);
 
        //Layout the text fields in a panel.
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(equationField);
        fieldPane.add(threshField);
        fieldPane.add(iterationsField);
        fieldPane.add(startField);
        fieldPane.add(endField);
        fieldPane.add(estimateField);
        
        Action action = new AbstractAction()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               equation = equationField.getText();
               thresh = Float.parseFloat(threshField.getText());
               iterations = Integer.parseInt(iterationsField.getText());
               start = Float.parseFloat(startField.getText());
               end = Float.parseFloat(endField.getText());
               float estimate = Estimator.finalEstimate(equation, thresh, iterations, start, end);
               estimateField.setText(""+estimate);
            }
        };
        calculateButton.addActionListener(action);
        JPanel buttonPane = new JPanel(new GridLayout(0,1));
        buttonPane.add(calculateButton);
        
        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(buttonPane, BorderLayout.SOUTH);
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FormattedTextFieldDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new EstimatorUI());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}