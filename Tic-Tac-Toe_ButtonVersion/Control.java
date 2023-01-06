import java.awt.*;
import java.awt.event.*;
import java.math.MathContext;
import java.util.List;
import javax.swing.*;

public class Control {
    public static Model model_control = new Model();
    public static View view_control = new View();
    public static void main(String[] args) {
        view_control.CreateWindow();
        view_control.playAgain_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Model.ResetTable();
            }
        });
        view_control.changeSize_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                view_control.CreateWindow();
                view_control.playButton_panel.removeAll();
                Model.ResetTable();
                view_control.frame.dispose();
            }
        });
        view_control.next_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(model_control.CheckInput(view_control.sizeInput_txtfield.getText())){
                    view_control.CreateGame(Integer.parseInt(view_control.sizeInput_txtfield.getText()));
                    view_control.frame2.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter number", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        view_control.save_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model_control.SaveGame(view_control.tablesize,view_control.buttons,view_control.currentPlayer,view_control.count);
            }
        });

        view_control.load_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               List<String> list =  model_control.LoadGame();
               int old_tablesize = Integer.parseInt(list.get(0));
               if(view_control.tablesize == old_tablesize){
                    view_control.currentPlayer = list.get(2);
                    view_control.count = Integer.parseInt(list.get(3));
                   System.out.println(list.get(1));
                   char[] XO_list = list.get(1).toCharArray();
                   System.out.println(XO_list);
                   int XO_listDefaulthIndex = 0;
                   for(int i = 0; i < view_control.tablesize ; i++){
                       for(int j = 0; j < view_control.tablesize; j++){
                            view_control.buttons[i][j].setForeground(new Color(0,0,255));
                           if(String.valueOf(XO_list[XO_listDefaulthIndex]).equals("n")){
                                view_control.buttons[i][j].setText("");
                           }else{
                               if(String.valueOf(XO_list[XO_listDefaulthIndex]).equals("x")){
                                    view_control.buttons[i][j].setForeground(new Color(255,0,0));
                               }else{
                                    view_control.buttons[i][j].setForeground(new Color(0,0,255));
                               }
                                view_control.buttons[i][j].setText(String.valueOf(XO_list[XO_listDefaulthIndex]));
                           }
                           if(view_control.currentPlayer == "x"){
                                view_control.textfield.setText("x turn");
                           }else{
                                view_control.textfield.setText("o turn");
                           }
                           XO_listDefaulthIndex += 1;
                       }
                   }
                   ShowWinCase(Integer.parseInt(list.get(3)));
                   JOptionPane.showMessageDialog(null, "Load Finished", null, JOptionPane.INFORMATION_MESSAGE);
               }else{
                   JOptionPane.showMessageDialog(null, "Table size is not the same (Table size must be " + old_tablesize +").", null, JOptionPane.INFORMATION_MESSAGE);
               }
            }
        });
        
        view_control.exit_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Model.ExitGame();
            }
        });

        
        
    } 
    public static void ShowWinCase(int count){
        boolean check = model_control.CheckWin(view_control.tablesize,view_control.currentPlayer,view_control.buttons);
        if(count == view_control.tablesize * view_control.tablesize){
            Model.ButtonEnable(view_control.tablesize);
            view_control.textfield.setText("draw");
        }else{
            if(check && view_control.currentPlayer.equals("o")){
                Model.ButtonEnable(view_control.tablesize);
                view_control.textfield.setForeground(Color.GREEN);
                view_control.textfield.setText("x wins");
            }else if(check && view_control.currentPlayer.equals("x")){
                Model.ButtonEnable(view_control.tablesize);
                view_control.textfield.setForeground(Color.GREEN);
                view_control.textfield.setText("o wins");
            }
        }
    }
}
