import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit, theme;
    JMenuItem newFile, saveFile, openFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JMenuItem darkTheme, moonLight;
    JTextArea textArea;
    TextEditor() {
        //Initialize frame
        frame = new JFrame();

        //initialize textarea
        textArea = new JTextArea();
        //Initialize menuBar
        menuBar = new JMenuBar();
        // initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");
        theme = new JMenu("Themes");

        // initialize menuItems
        //file menuItems
        newFile = new JMenuItem("New File");
        saveFile = new JMenuItem("Save File");
        openFile = new JMenuItem("Open File");

        // edit MenuItem
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Exit");
        // add action Listener to the file item
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);

        // theme
        darkTheme = new JMenuItem("Dark Theme");
        moonLight  = new JMenuItem("Moonlight Theme");
        theme.add(darkTheme);
        theme.add(moonLight);

        // add actionListener to the edit item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding menuItem to the file
        file.add(newFile);
        file.add(saveFile);
        file.add(openFile);
        // adding menuItem to the edit
        edit.add(cut);
        edit.add(paste);
        edit.add(copy);
        edit.add(selectAll);
        edit.add(close);
        // adding menuItem to the theme
        darkTheme.addActionListener(this);
        moonLight.addActionListener(this);

        //Add menu to the menuBar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(theme);

        // SET menuBar to our frame
        frame.setJMenuBar(menuBar);
       // frame.add(textArea);
        //create Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        // add text area to the panel

        panel.add(textArea, BorderLayout.CENTER);

        //Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       // Add Scroll pane to the panel
        panel.add(scrollPane);

        frame.add(panel);

        //set dimensions to our frame
        frame.setBounds(100, 100, 800, 500);

        // set frame to visible
        frame.setVisible(true);

    }
     @Override
     public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource() == cut){
             textArea.cut();
        }
         if(actionEvent.getSource() == copy){
             textArea.copy();
         }
         if(actionEvent.getSource() == paste){
             textArea.paste();
         }
         if(actionEvent.getSource() == selectAll){
             textArea.selectAll();
         }
         if(actionEvent.getSource() == close){
              System.exit(0);
         }
         if(actionEvent.getSource() == newFile){
              TextEditor window = new TextEditor();
         }
         if(actionEvent.getSource() == saveFile){
             JFileChooser fileChooser = new JFileChooser("C:");
             int chooseOption = fileChooser.showSaveDialog(null);
             if(chooseOption ==JFileChooser.APPROVE_OPTION){
                 File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".text");
                 try{

                     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                     textArea.write(bufferedWriter);
                     bufferedWriter.close();
                    // JOptionPane.showMessageDialog(C:,"File saved Successfully !");
                 }
                 catch(Exception exception){
                     exception.printStackTrace();
                 }
             }

         }
         if(actionEvent.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";

                    while((intermediate = bufferedReader.readLine())!= null){
                        output += intermediate+"\n";
                    }
                    textArea.setText(output);
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
         }
         // THEME
         if(actionEvent.getSource() == darkTheme){
             textArea.setBackground(Color.DARK_GRAY);
             textArea.setForeground(Color.WHITE);
         }
         if(actionEvent.getSource() == moonLight){
             textArea.setBackground(new Color(107, 169, 255));
             textArea.setForeground(Color.black);
         }

     }
    public static void main(String[] args) {

        TextEditor obj = new TextEditor();
    }
}