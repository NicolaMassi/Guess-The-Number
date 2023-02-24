import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.AbstractBorder;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class GUI implements ActionListener {
    
    private String sequence = GenerateSequence();
    private int trysCount = 0;
    
    private final JFrame frame;
    
    private final JPanel titlePanel;
    private final JPanel sequencePanel;
    private final JPanel trysPanel;
    private final JPanel tipsPanel;
    private final JPanel inputPanel;
    
    private final JButton submit = new JButton();
    private final JTextField[] sequenceTF = new JTextField[4];
    private final JTextField trys = new JTextField();
    private final JTextField input = new JTextField();
    private final JTextPane tips = new JTextPane();
    private final JLabel title = new JLabel();
    private final JLabel trysText = new JLabel();
    private final JLabel inputText = new JLabel();
    private final JLabel tipsText = new JLabel();
    
    private final Font titleFont = new Font("Times New Roman", Font.ITALIC, 40);
    private final Font contentFont = new Font("Times New Roman", Font.PLAIN, 20);
    private final Font sFont = new Font("Times New Roman", Font.PLAIN, 11);

    private final Color cText = new Color(0,0,0);
    private final Color cBackground = new Color(255,255,255);
    private final Color cError = new Color(219, 50, 77);
    private final Color cGuess = new Color(56, 167, 0);
    private final Color cWrongLoc = new Color(244, 255, 82);
    
    private final Border empty = BorderFactory.createEmptyBorder();
    AbstractBorder rounded = new TextBubbleBorder(cText, 0,10,0);
    AbstractBorder errorRounded = new TextBubbleBorder(cError, 1,10,0);


    public GUI(){
        frame = new JFrame("GuessTheNumber");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 550));
        frame.setBackground(cBackground);
        frame.setLayout(null);
        frame.setResizable(false);
        
        titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(400, 100));
        titlePanel.setBounds(0,0,400,100);
        
        sequencePanel = new JPanel();
        sequencePanel.setPreferredSize(new Dimension(400, 100));
        sequencePanel.setBounds(44,120,300,50);
        
        trysPanel = new JPanel();
        trysPanel.setPreferredSize(new Dimension(400, 150));
        trysPanel.setBounds(80,185,200, 30);
        
        inputPanel = new JPanel();
        inputPanel.setBounds(100, 210, 200,100);

        tipsPanel = new JPanel();
        tipsPanel.setPreferredSize(new Dimension(400, 200));
        tipsPanel.setBounds(70, 340, 250,130);
        
        title.setText("Guess the Number");
        title.setFont(titleFont);
        title.setForeground(cText);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(400,100));
        title.setBorder(empty);
        titlePanel.add(title);
        
        for(int i = 0 ; i < 4; i++){
            sequenceTF[i] = new JTextField(1);
            sequenceTF[i].setPreferredSize(new Dimension(44, 34));
            sequenceTF[i].setEditable(false);
            sequenceTF[i].setFocusable(false);
            sequenceTF[i].setFont(contentFont);
            sequenceTF[i].setBackground(cBackground);
            sequenceTF[i].setForeground(cText);
            sequenceTF[i].setBorder(rounded);
            sequenceTF[i].setHorizontalAlignment(JTextField.CENTER);
        }
        
        sequencePanel.setLayout(new GridLayout(1, 6, 44, 0));
        
        for(int i = 0; i < 4; i++){
            sequencePanel.add(sequenceTF[i]);
        }
        
        trysPanel.setLayout(new GridLayout(1, 2, 0, 0));
        
        trysText.setText("Tries: ");
        trysText.setFont(contentFont);
        trysText.setForeground(cText);
        trysText.setHorizontalAlignment(JLabel.RIGHT);
        trysText.setPreferredSize(new Dimension(50, 50));
        
        trys.setBounds(0, 0, 200, 50);
        trys.setEditable(false);
        trys.setFocusable(false);
        trys.setFont(sFont);
        trys.setBackground(cBackground);
        trys.setForeground(cText);
        trys.setBorder(rounded);
        trys.setHorizontalAlignment(JTextField.CENTER);
        
        trysPanel.add(trysText);
        trysPanel.add(trys);

        inputPanel.setLayout(new GridLayout(2, 1, 0, 0));

        inputText.setText("Enter the sequence:");
        inputText.setBounds(0, 205, 200, 50);
        inputText.setFont(contentFont);
        inputText.setForeground(cText);
        inputText.setHorizontalAlignment(JLabel.CENTER);

        input.setBounds(0, 50, 200, 50);
        input.setFont(contentFont);
        input.setForeground(cText);
        input.setBorder(rounded);
        input.setHorizontalAlignment(JTextField.CENTER);
        
        submit.setText("Enter!");
        submit.setBounds(150, 320, 100, 30);
        submit.addActionListener(this);
        submit.setFont(contentFont);
        submit.setForeground(cText);
        submit.setBackground(cBackground);
        submit.setBorder(rounded);
        
        inputPanel.add(inputText);
        inputPanel.add(input);

        tipsPanel.setLayout(new GridLayout(2, 1, 0, 0));

        tipsText.setText("Tips:");
        tipsText.setHorizontalAlignment(JLabel.CENTER);
        tipsText.setFont(contentFont);
        tipsText.setForeground(cText);
        tipsText.setBackground(cBackground);
        
        tipsPanel.add(tipsText);

        tips.setEditable(false);
        tips.setFont(sFont);
        tips.setForeground(cText);
        tips.setBackground(cBackground);
        tips.setBorder(rounded);
        
        tipsPanel.add(tips);

        frame.add(titlePanel);
        frame.add(sequencePanel);
        frame.add(trysPanel);
        frame.add(inputPanel);
        frame.add(submit);
        frame.add(tipsPanel);
        frame.setVisible(true);
        frame.pack();
    }
    
    public static void main(String[] args){
        GUI guessTheNumber = new GUI();
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        input.setBorder(rounded);

        if(e.getSource() == submit && !haveErrors()){
            trysCount++;
            tips.setText("");
            sequenceComp();
            trys.setText(String.valueOf(trysCount));
        }
    }

    //Generate the sequence the user need to guess
    private String GenerateSequence(){
        String sequence = "";
        
        for(int i = 0; i < 4; i++){
            sequence += String.valueOf((int)(Math.random() * 9));
        }
        
        return sequence;
    }

    //Check for errors in the user input
    private boolean haveErrors(){
        if(input.getText().isBlank() || input.getText().length() != 4){
            input.setBorder(errorRounded);
            return true;
        }

        for(int i = 0; i < 4; i++){
            if(!Character.isDigit(input.getText().charAt(i))){
                input.setBorder(errorRounded);
                return true;
            }
        }

        return false;
    }

    //Compare the user sequence and the generated one
    private void sequenceComp(){

        //Reset the tips text to allow new ones to appear on screen
        tips.setText("");

        //Reset the background color
        for(int j = 0; j < 4; j++){
            sequenceTF[j].setBackground(cBackground);
            sequenceTF[j].setText("");
        }
        //Control step by step on input sequence
        for(int i = 0; i < 4; i++){

            //Comparison on the generated sequence
            for(int j = 0; j < 4; j++){
                if(input.getText().charAt(i) == sequence.charAt(j) && i == j){
                    sequenceTF[i].setBackground(cGuess);
                    sequenceTF[i].setText("" + sequence.charAt(i));
                }

                else if(input.getText().charAt(i) == sequence.charAt(j) && i != j && sequenceTF[i].getBackground() != cGuess){
                    sequenceTF[i].setBackground(cWrongLoc);
                }

                else if(input.getText().charAt(i) != sequence.charAt(j) && sequenceTF[i].getBackground() != cGuess && sequenceTF[i].getBackground() != cWrongLoc){
                    sequenceTF[i].setBackground(cError);
                }
            }
        }

        //Setting the tips for the user
        for(int i = 0; i < 4; i++){

            if(sequenceTF[i].getBackground() == cGuess){
                tips.setText(tips.getText() + "The " + (i + 1) + "° number is right." + "\n");
            }

            else if(sequenceTF[i].getBackground() == cWrongLoc){
                tips.setText(tips.getText() + "The " + (i + 1) + "° number is right but in wrong position." + "\n");
            }

            else{
                tips.setText(tips.getText() + "The " + (i + 1) + "° number is wrong." + "\n");
            }
        }
    }
}    

// Class used for rounded borders
// Author: Andrew Thompson (https://stackoverflow.com/users/418556/andrew-thompson)
class TextBubbleBorder extends AbstractBorder {

        private Color color;
        private int thickness = 4;
        private int radii = 8;
        private int pointerSize = 7;
        private Insets insets = null;
        private BasicStroke stroke = null;
        private int strokePad;
        private int pointerPad = 4;
        private boolean left = true;
        RenderingHints hints;
    
        TextBubbleBorder(
                Color color) {
            this(color, 4, 8, 7);
        }
    
        TextBubbleBorder(
                Color color, int thickness, int radii, int pointerSize) {
            this.thickness = thickness;
            this.radii = radii;
            this.pointerSize = pointerSize;
            this.color = color;
    
            stroke = new BasicStroke(thickness);
            strokePad = thickness / 2;
    
            hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
    
            int pad = radii + strokePad;
            int bottomPad = pad + pointerSize + strokePad;
            insets = new Insets(pad, pad, bottomPad, pad);
        }
    
        TextBubbleBorder(
                Color color, int thickness, int radii, int pointerSize, boolean left) {
            this(color, thickness, radii, pointerSize);
            this.left = left;
        }
    
        @Override
        public Insets getBorderInsets(Component c) {
            return insets;
        }
    
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            return getBorderInsets(c);
        }
    
        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x, int y,
                int width, int height) {
    
            Graphics2D g2 = (Graphics2D) g;
    
            int bottomLineY = height - thickness - pointerSize;
    
            RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                    0 + strokePad,
                    0 + strokePad,
                    width - thickness,
                    bottomLineY,
                    radii,
                    radii);
    
            Polygon pointer = new Polygon();
    
            if (left) {
                // left point
                pointer.addPoint(
                        strokePad + radii + pointerPad,
                        bottomLineY);
                // right point
                pointer.addPoint(
                        strokePad + radii + pointerPad + pointerSize,
                        bottomLineY);
                // bottom point
                pointer.addPoint(
                        strokePad + radii + pointerPad + (pointerSize / 2),
                        height - strokePad);
            } else {
                // left point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad),
                        bottomLineY);
                // right point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad + pointerSize),
                        bottomLineY);
                // bottom point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad + (pointerSize / 2)),
                        height - strokePad);
            }
    
            Area area = new Area(bubble);
            area.add(new Area(pointer));
    
            g2.setRenderingHints(hints);
    
            // Paint the BG color of the parent, everywhere outside the clip
            // of the text bubble.
            Component parent  = c.getParent();
            if (parent!=null) {
                Color bg = parent.getBackground();
                Rectangle rect = new Rectangle(0,0,width, height);
                Area borderRegion = new Area(rect);
                borderRegion.subtract(area);
                g2.setClip(borderRegion);
                g2.setColor(bg);
                g2.fillRect(0, 0, width, height);
                g2.setClip(null);
            }
    
            g2.setColor(color);
            g2.setStroke(stroke);
            g2.draw(area);
        }
    }
