
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class View implements ActionListener {

    //init var
    public int tablesize, count;
    String currentPlayer = "x";

    Random random = new Random();
    JFrame frame = new JFrame();
    JFrame frame2 = new JFrame();

    JPanel title_panel = new JPanel();
    JPanel option_panel = new JPanel();
    JPanel playButton_panel = new JPanel();
    JPanel optionButton_panel = new JPanel();

    JLabel textfield = new JLabel();

    JButton[][] buttons;
    JButton playAgain_btn = new JButton("Reset");
    JButton changeSize_btn = new JButton("Size");
    JButton save_btn = new JButton("Save");
    JButton load_btn = new JButton("Load");
    JButton exit_btn = new JButton("Exit");

    JLabel title_label = new JLabel("Enter size of table");
    JTextField sizeInput_txtfield = new JTextField(25);
    JButton next_btn = new JButton("Play");
    int sizeInput;

    Control control = new Control();

    public void CreateWindow(){
        frame2.setSize(300,330);
        frame2.setVisible(true);
        frame2.setTitle("Tic-Tac-Toe");
        frame2.setLayout(new FlowLayout());
        frame2.getContentPane().add(title_label);
        frame2.getContentPane().add(sizeInput_txtfield);
        frame2.getContentPane().add(next_btn);
        frame2.pack();

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void CreateGame(int tableSize){
        tablesize = tableSize;
        buttons = new JButton[tableSize][tableSize];
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(211, 84, 0));
        textfield.setForeground(Color.BLACK);
        textfield.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,70);

        option_panel.setLayout(new BorderLayout());
        option_panel.setBounds(0,0,300,300);
        optionButton_panel.setBackground(new Color(211, 84, 0));


        playButton_panel.setLayout(new GridLayout(tableSize,tableSize));

        for(int i = 0; i < tableSize; i++){
            for(int j = 0; j < tableSize; j++){
                buttons[i][j] = new JButton();
                playButton_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,80));
                buttons[i][j].setBackground(new Color(237, 187, 153));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }
        
        


        changeSize_btn.setPreferredSize(new Dimension(80,50));
        playAgain_btn.setPreferredSize(new Dimension(80,50));
        save_btn.setPreferredSize(new Dimension(80,50));
        load_btn.setPreferredSize(new Dimension(80,50));
        exit_btn.setPreferredSize(new Dimension(80,50));

        optionButton_panel.add(changeSize_btn);
        optionButton_panel.add(playAgain_btn);
        optionButton_panel.add(save_btn);
        optionButton_panel.add(load_btn);
        optionButton_panel.add(exit_btn);

        title_panel.add(textfield);
        option_panel.add(optionButton_panel);

        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(playButton_panel,BorderLayout.CENTER);
        frame.add(option_panel,BorderLayout.SOUTH);
    }


    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < tablesize; i++){
            for(int j = 0; j < tablesize; j++){
                if(e.getSource() == buttons[i][j]){
                    if(currentPlayer == "x"){
                        if(buttons[i][j].getText() != ""){
                            JOptionPane.showMessageDialog(null, "Already used.", null, JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            buttons[i][j].setForeground(new Color(255,0,0));
                            buttons[i][j].setText("x");
                            change_player();
                            textfield.setText("o turn");
                            if(Control.model_control.CheckWin(tablesize,currentPlayer,buttons)){
                                Control.model_control.ButtonEnable(tablesize);
                                textfield.setForeground(Color.GREEN);
                                textfield.setText("x wins");
                            }
                        }
                    }else{
                        if(buttons[i][j].getText() != ""){
                            JOptionPane.showMessageDialog(null, "Already used.", null, JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            buttons[i][j].setForeground(new Color(0,0,255));
                            buttons[i][j].setText("o");
                            change_player();
                            textfield.setText("x turn");
                            if(Control.model_control.CheckWin(tablesize,currentPlayer,buttons)){
                                Control.model_control.ButtonEnable(tablesize);
                                textfield.setForeground(Color.GREEN);
                                textfield.setText("o wins");
                            }
                        }
                    }
                }
            }
        }
        if(!Control.model_control.CheckWin(tablesize,currentPlayer,buttons) && count == tablesize * tablesize){
            for(int i = 0; i < tablesize; i++){
                for(int j = 0; j < tablesize; j++){
                    buttons[i][j].setEnabled(false);
                }
            }
            textfield.setText("draw");
        }
    }
    void change_player(){
        if (this.currentPlayer.equals("x")){
            this.currentPlayer = "o";
        }
        else{
            this.currentPlayer = "x";
        }
        this.count++;
    }
    
}
