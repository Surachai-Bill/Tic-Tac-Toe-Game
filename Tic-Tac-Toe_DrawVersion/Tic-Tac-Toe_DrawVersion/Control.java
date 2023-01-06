import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class Control extends JFrame implements MouseListener {
    public static Model model_control = new Model();
    public static View view_control = new View();

    static String current_player = "x";
    static int mode =0;
    static int count;
  
    public static void main(String[] args) {
        view_control.CreateSizeInputScreen();
        view_control.playGame_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(model_control.CheckSizeInput(view_control.size_input_txtfield.getText())){
                    if(mode == 0){
                        view_control.AddListener();
                    }
                    view_control.CreateGameScreen(Integer.parseInt(view_control.size_input_txtfield.getText()));  
                    view_control.size_input_frame.dispose();
                    view_control.DrawTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter number", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        view_control.resetGame_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                count = 0;
                current_player = "x";
                view_control.Create2DArray();
                view_control.repaint();
            }
        });
        
        view_control.changeSize_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                count = 0;
                current_player = "x";
                mode +=1;
                view_control.CreateSizeInputScreen();
                view_control.dispose();
            }
        });
        
        view_control.save_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model_control.SaveGame(view_control.table_size, view_control.xo_2d_array, current_player, count);
            }
        });
    
        view_control.load_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                List<String> list = model_control.LoadGame();
                int old_tablesize = Integer.parseInt(list.get(0));
                if(view_control.table_size == old_tablesize){
                    current_player = list.get(2);
                    count = Integer.parseInt(list.get(3));
                    System.out.println("Load " + current_player);
                    char[] XO_list = list.get(1).toCharArray();
                    System.out.println(XO_list);
                    int XO_listDefaulthIndex = 0;
                    for(int i = 0; i < view_control.table_size ; i++){
                        for(int j = 0; j < view_control.table_size; j++){
                            if(String.valueOf(XO_list[XO_listDefaulthIndex]).equals("n")){
                                    view_control.xo_2d_array[i][j] = "";
                            }else{
                                    view_control.xo_2d_array[i][j] = String.valueOf(XO_list[XO_listDefaulthIndex]);
                                }
                            XO_listDefaulthIndex += 1;
                        }
                    }
                    view_control.repaint();
                    JOptionPane.showMessageDialog(null, "Load Finished", null, JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Table size is not the same (Table size must be " + old_tablesize +").", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
            
        view_control.exit_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }      

    public void AddListener(){
        view_control.xo_table_panel.addMouseListener(this);
    }

    public void ChangeCurrentPlayer(){
        if (this.current_player.equals("x")){
            this.current_player = "o";
        }
        else{
            this.current_player = "x";
        }
        view_control.header_playerturn.setText(current_player + " turn");
        ++this.count;
    }

    private int[] convert_mousepos(int mouse_x,int mouse_y){
        int[] row_col = {(int)mouse_y/(view_control.table_panel_size / view_control.table_size), (int)mouse_x/(view_control.table_panel_size / view_control.table_size)};
        return row_col;
    }

    public void UpdateTable(){
        repaint();
    }



//-- Mouse Event Handler -------------------------------------------------------------------------------------------------------------------
    @Override
    public void mouseReleased(MouseEvent e) {
        int[] row_col = convert_mousepos(e.getX(),e.getY());
        view_control.AddXO(row_col[0],row_col[1]);
        if(count == view_control.table_size * view_control.table_size && !model_control.CheckWinner(current_player)){
            JOptionPane.showMessageDialog(null, "Draw", null, JOptionPane.INFORMATION_MESSAGE);
            this.count = 0;
            this.current_player = "x";
            view_control.Create2DArray();
            repaint();
        }else if(model_control.CheckWinner(current_player)){
            if(current_player.equals("x")){
                JOptionPane.showMessageDialog(null, "Player O Win", null, JOptionPane.INFORMATION_MESSAGE);

            }else{
                JOptionPane.showMessageDialog(null, "Player X Win", null, JOptionPane.INFORMATION_MESSAGE);
            }
            this.count = 0;
            this.current_player = "x";
            view_control.Create2DArray();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {} 
//---------------------------------------------------------------------------------------------------------------------------------
}

