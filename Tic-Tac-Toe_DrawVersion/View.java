import java.awt.*;
import javax.swing.*;

public class View extends Control{

    Control control = new Control();

    public int table_size;
    final int table_panel_size = 600;

    String[][] xo_2d_array;

    JLabel sizeInput_title_label = new JLabel("Enter size of table");
    JLabel header_playerturn = new JLabel();

    JButton playGame_btn = new JButton("Play");
    JButton resetGame_btn = new JButton("Reset");
    JButton changeSize_btn = new JButton("Size");
    JButton save_btn = new JButton("Save");
    JButton load_btn = new JButton("Load");
    JButton exit_btn = new JButton("Exit");

    JFrame size_input_frame = new JFrame();
    JFrame game_frame = new JFrame();

    JTextField size_input_txtfield = new JTextField(25);

    JPanel empty_panel = new JPanel();
    JPanel xo_table_panel = new JPanel();
    JPanel optionButton_panel = new JPanel();

    //Create UI window for get tabel size from user
    public void CreateSizeInputScreen(){
        size_input_frame.setSize(300,330);
        size_input_frame.setVisible(true);
        size_input_frame.setTitle("Tic-Tac-Toe");
        size_input_frame.setLayout(new FlowLayout());
        size_input_frame.getContentPane().add(sizeInput_title_label);
        size_input_frame.getContentPane().add(size_input_txtfield);
        size_input_frame.getContentPane().add(playGame_btn);
        size_input_frame.pack();
        size_input_frame.setLocationRelativeTo(null);
        size_input_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Create window for draw the table
    public void CreateGameScreen(int size){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(700,700);

        empty_panel.setSize(800,500);
        optionButton_panel.setSize(800,100);

        table_size = size;
        Create2DArray();

        header_playerturn.setBackground(Color.pink);
        header_playerturn.setForeground(Color.BLACK);
        header_playerturn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        header_playerturn.setHorizontalAlignment(JLabel.CENTER);
        header_playerturn.setText(current_player + " turn");
        header_playerturn.setOpaque(true);

        changeSize_btn.setPreferredSize(new Dimension(80,40));
        resetGame_btn.setPreferredSize(new Dimension(80,40));
        save_btn.setPreferredSize(new Dimension(80,40));
        load_btn.setPreferredSize(new Dimension(80,40));
        exit_btn.setPreferredSize(new Dimension(80,40));

        optionButton_panel.setBackground(Color.pink);
        optionButton_panel.add(changeSize_btn);
        optionButton_panel.add(resetGame_btn);
        optionButton_panel.add(save_btn);
        optionButton_panel.add(load_btn);
        optionButton_panel.add(exit_btn);

        this.setTitle("Tic-Tac-Toe Game");

        xo_table_panel.setPreferredSize(new Dimension(table_panel_size, table_panel_size));
        xo_table_panel.addMouseListener(this);
        
        empty_panel.add(xo_table_panel);
        xo_table_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(header_playerturn,BorderLayout.NORTH);
        this.add(empty_panel,BorderLayout.CENTER);
        this.add(optionButton_panel,BorderLayout.SOUTH);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //Draw table
    public void DrawTable(){
        view_control.header_playerturn.setText(current_player + " turn");
        int responsive = table_panel_size / table_size;
        xo_table_panel.removeAll();
        Graphics2D g2d = (Graphics2D) xo_table_panel.getGraphics();
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0 ; i < table_size ; i++){
            g2d.setColor(Color.black);
            g2d.drawLine(0, i * responsive, table_panel_size, i * responsive);
            for (int j = 0 ; j < table_size ; j++){
                g2d.setColor(Color.black);
                g2d.drawLine(j * responsive, 0, j * responsive, table_panel_size);
                g2d.setFont(new Font("Calibri", Font.PLAIN, responsive));
                //Check symbol for color
                if (xo_2d_array[i][j].equals("o")){
                    g2d.setColor(Color.blue);
                    if(table_size < 10){
                        g2d.drawString(xo_2d_array[i][j],(responsive * j) + 20,(responsive * (i + 1)) - 20); 
                    }else{
                        g2d.drawString(xo_2d_array[i][j],(responsive * j) + 5,(responsive * (i + 1)) - 5); 
                    }
                }
                else if(xo_2d_array[i][j].equals("x")){
                    g2d.setColor(Color.red);
                    if(table_size < 10){
                        g2d.drawString(xo_2d_array[i][j],(responsive * j) + 20,(responsive * (i + 1)) - 20); //ok
                    }else{
                        g2d.drawString(xo_2d_array[i][j],(responsive * j) + 5,(responsive * (i + 1)) - 5); //ok
                    }                    
                }
            }
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        DrawTable();
    }

    public void AddXO(Integer row,Integer col) {
        if(xo_2d_array[row][col] == "x" || xo_2d_array[row][col] == "o"){
        } else {
            if(current_player.equals("x")){
                xo_2d_array[row][col] = "x";
            }else{
                xo_2d_array[row][col] = "o";
            }
            ChangeCurrentPlayer();
        }
        repaint();
    }

    public void Create2DArray(){
        xo_2d_array = new String[table_size][table_size];
        for(int i = 0 ; i < table_size ; i++){
            for(int j = 0 ; j < table_size ; j++){
                xo_2d_array[i][j] = " ";
            }
        }
    }
}
