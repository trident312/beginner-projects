import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JTextField inputTextField;
    private JButton countButton;
    private JLabel resultLabel;

    public Main() {
        frame = new JFrame("Word Counter");
        inputTextField = new JTextField(20);
        countButton = new JButton("Count Words");
        resultLabel = new JLabel();

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText();
                int wordCount = countWords(input);
                resultLabel.setText("Word count: " + wordCount);
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("Enter a sentence: "));
        frame.add(inputTextField);
        frame.add(countButton);
        frame.add(resultLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static int countWords(String input) {
        // Remove leading and trailing white spaces
        input = input.trim();

        // If the input is empty, return 0
        if (input.isEmpty()) {
            return 0;
        }

        // Split the input string by spaces
        String[] words = input.split("\\s+");

        // Return the number of words
        return words.length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
