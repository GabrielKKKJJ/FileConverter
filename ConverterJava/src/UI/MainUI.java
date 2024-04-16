package UI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Converter.Converter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    // Components
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel insertFileLabel;
    private JButton convertButton;
    private JTextField fileFieldTextField;
    private JButton chooseFileButton;
    private JButton chooseOutputpath;
    private JTextField outputpathFieldTextField;
    private JFileChooser fileChooser;
    private JCheckBox pngToJpgCheckBox;
    private JCheckBox jpgToPngCheckBox;
    // var
    private String imgPath;
    private String outputPath;
    private String convertTo;
    

    public MainUI() {
        createComponents();
        configureLayout();
        configureFrame();
        attachListeners();
    }

    private void createComponents() {
    // Components
        frame = new JFrame("File Converter");

        // Labels
        titleLabel = new JLabel("File Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        insertFileLabel = new JLabel("Insert your file", SwingConstants.CENTER);

        // Buttons
        convertButton = new JButton("Convert");
        convertButton.setPreferredSize(new Dimension(100, 30));
        convertButton.setEnabled(false);

        chooseFileButton = new JButton("Choose File");
        chooseFileButton.setPreferredSize(new Dimension(100, 30));

        chooseOutputpath = new JButton("Output Path");
        chooseOutputpath.setPreferredSize(new Dimension(100, 30));

        // Textfields
        fileFieldTextField = new JTextField(20);
        fileFieldTextField.setPreferredSize(new Dimension(100, 30));
        fileFieldTextField.setEnabled(false);

        outputpathFieldTextField = new JTextField(20);
        outputpathFieldTextField.setPreferredSize(new Dimension(100, 30));
        outputpathFieldTextField.setEnabled(false);

        // Checkbox
        pngToJpgCheckBox = new JCheckBox("PNG to JPG");
        jpgToPngCheckBox = new JCheckBox("JPG to PNG");
    }

    private void configureLayout() {
        // Layout
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);

        // Layout configs
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal layout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titleLabel)
                .addComponent(insertFileLabel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(chooseFileButton)
                    .addGap(5)
                    .addComponent(fileFieldTextField))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(chooseOutputpath)
                    .addGap(5)
                    .addComponent(outputpathFieldTextField))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(pngToJpgCheckBox)
                    .addGap(5)
                    .addComponent(jpgToPngCheckBox))
                .addComponent(convertButton)
            )

        );

        // Vertical layout
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(titleLabel)
            .addGap(20) // Space between titleLabel and insertFileLabel
            .addComponent(insertFileLabel)
            .addGap(20) // Space between insertFileLabel and buttons/textField
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(chooseFileButton)
                .addComponent(fileFieldTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(chooseOutputpath)
                .addComponent(outputpathFieldTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(pngToJpgCheckBox)
                .addComponent(jpgToPngCheckBox))
            .addComponent(convertButton)
        );
    }

    private void configureFrame() {
        // Frame configs
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
    }

    private void attachListeners() {
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);

                // Show open dialog; this method blocks until the dialog is closed
                int result = fileChooser.showOpenDialog(frame);

                // Check if a file was selected
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileFieldTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    convertButton.setEnabled(true);
                    
                    MainUI.this.imgPath = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }  
        });
        chooseOutputpath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    outputpathFieldTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    MainUI.this.outputPath = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pngToJpgCheckBox.isSelected() && !jpgToPngCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Please select a conversion type.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Converter converter = new Converter();
                if (MainUI.this.convertTo == "jpg") {
                    converter.convertPNGtoJPG(imgPath, outputPath);
                } else if (MainUI.this.convertTo == "png") {
                    converter.convertJPGtoPNG(imgPath, outputPath);
                }
                converter.convertPNGtoJPG(imgPath, outputPath);
            }
        });

        pngToJpgCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpgToPngCheckBox.setSelected(false);
                MainUI.this.convertTo = "jpg";
            }
        });
        jpgToPngCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pngToJpgCheckBox.setSelected(false);
                MainUI.this.convertTo = "png";
            }
        });
    }

}
