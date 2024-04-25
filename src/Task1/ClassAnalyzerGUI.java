package Task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassAnalyzerGUI extends JFrame {

    private JTextField classNameField;
    private JTextArea resultArea;

    public ClassAnalyzerGUI() {
        setTitle("Class Analyzer");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание панели с меткой и полем ввода для имени класса
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel classNameLabel = new JLabel("Enter the full name of the class:");
        classNameLabel.setFont(new Font("Georgia", Font.BOLD, 17));
        classNameField = new JTextField(30);
        inputPanel.add(classNameLabel);
        inputPanel.add(classNameField);

        // Создание кнопки "Analyze" и добавление обработчика событий
        JButton analyzeButton = new JButton("Analyze");
        analyzeButton.setBackground(new Color(255, 182, 193));
        analyzeButton.setPreferredSize(new Dimension(120, 40)); // Увеличение размера кнопки
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = classNameField.getText().trim();
                if (!className.isEmpty()) {
                    String analysisResult = ClassAnalyzer.analyzeClass(className);
                    resultArea.setText(analysisResult);
                }
            }
        });

        // Создание кнопки "Clean up" и добавление обработчика событий
        JButton cleanUpButton = new JButton("Clean up");
        cleanUpButton.setBackground(new Color(255, 182, 193));
        cleanUpButton.setPreferredSize(new Dimension(120, 40)); // Увеличение размера кнопки
        cleanUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.setText("");
            }
        });


        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(255, 182, 193));
        exitButton.setPreferredSize(new Dimension(120, 40)); // Увеличение размера кнопки
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Установка шрифта и размера текста для кнопок
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        analyzeButton.setFont(buttonFont);
        cleanUpButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        // Создание панели с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(analyzeButton);
        buttonPanel.add(cleanUpButton);
        buttonPanel.add(exitButton);

        // Создание области для отображения результатов анализа
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Courier New", Font.PLAIN, 16)); // Изменяем шрифт
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Добавление всех компонентов на окно
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClassAnalyzerGUI gui = new ClassAnalyzerGUI();
                gui.setVisible(true);
            }
        });
    }
}
