package JFrameModule;


import util.JTableUtils;
import javax.swing.*;
import LogicModule.Logic;

public class Frame extends JFrame{
    private JPanel panelMain;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonGetResult;

    public Frame() {
        this.setTitle("Task3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.pack();


        JTableUtils.initJTableForArray(tableInput, 40, true, true, true, true);
        JTableUtils.resizeJTable(tableInput, 3,4, 30, 70);


        JTableUtils.initJTableForArray(tableOutput, 40, true, true, false, false);
        JTableUtils.resizeJTable(tableOutput, 1,1, 30, 70);
        tableOutput.setEnabled(false);

        this.setVisible(true);
        this.setSize(860, 500);

        buttonGetResult.addActionListener(e -> {
            try {
                int[][] coefficients = JTableUtils.readIntMatrixFromJTable(tableInput);
                Logic.checkIfArrayIsNull(coefficients);

                int[][] matrixA = new int[coefficients.length][coefficients[0].length - 1];
                Logic.checkIfArrayIsSquare(matrixA);
                int[][] matrixB = new int[coefficients.length][1];

                for (int i = 0; i < coefficients.length; i++) {
                    for (int j = 0; j < coefficients[0].length - 1; j++) {
                        matrixA[i][j] = coefficients[i][j];
                    }
                }

                for (int i = 0; i < coefficients.length; i++) {
                    matrixB[i][0] = coefficients[i][coefficients[0].length-1];
                }

                double[][] result = Logic.getResultFrame(matrixA, matrixB);

                JTableUtils.writeArrayToJTable(tableOutput, result);
            } catch (Exception ex) {
                displayError("Ошибка в исходных данных");
            }
        });
    }

    private void displayError(String errorText) {
        JOptionPane.showMessageDialog(this, errorText,
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
    private void displayMessage(String messageText) {
        JOptionPane.showMessageDialog(this, messageText,
                "Сообщение", JOptionPane.INFORMATION_MESSAGE);
    }
}
