
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Model {

    //method 
    public boolean CheckInput(String tablesize){
        try {
            Integer.parseInt(tablesize);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public void SaveGame(int tablesize,JButton [][] buttons, String currentPlayer ,int count){
        try {
            FileWriter writer = new FileWriter(new File("save.txt"));
            writer.write(String.valueOf(tablesize));
            writer.write("\n");
            for(int i = 0; i < tablesize; i++){
                for(int j = 0; j < tablesize; j++){
                    if(!buttons[i][j].getText().equals("x") && !buttons[i][j].getText().equals("o")) 
                    {
                        writer.write("n");
                    }else{
                        writer.write(buttons[i][j].getText());
                    }
                }
            }
            writer.write("\n");
            writer.write(currentPlayer);
            writer.write("\n");
            writer.write(String.valueOf(count));
            writer.write("\n");
            writer.close();
            JOptionPane.showMessageDialog(null, "Saved", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<String> LoadGame(){
        List<String> list = new ArrayList<String>();  
        try {
            BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
            String data;
            while((data = reader.readLine()) != null){
               list.add(data);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Boolean CheckWin(int tablesize, String currentPlayer, JButton[][] buttons){
        String[] checker = new String[tablesize];
        for (int i=0;i<tablesize;i++){
            if(currentPlayer.equals("x")){
                checker[i] = "o";
            }
            else{
                checker[i] = "x";
            }
        }
        String[] horizontal = new String[tablesize];
        String[] temp_hor = new String[tablesize];
        String[] temp_ver = new String[tablesize];
        for (int i=0;i<tablesize;i++){
            for (int x=0;x<tablesize;x++){
                horizontal[x] = buttons[i][x].getText();
            }
            if(Arrays.deepEquals(checker, horizontal)){      // Check Row
                return true;
            }
            
            String[] temp = new String[tablesize];
            for (int j=0;j<tablesize;j++){
                temp[j] = buttons[j][i].getText();
            }
            if(Arrays.deepEquals(checker, temp)){            // Check Column
                return true;
            }
            temp_hor[i] = buttons[i][i].getText();
            temp_ver[i] = buttons[i][(tablesize-1)-i].getText();
        }
        if(Arrays.deepEquals(checker, temp_hor) || Arrays.deepEquals(checker, temp_ver)){     // Check Diagonal
            return true;
        }
        return false;
    }
        public static void ResetTable() {
        for(int i = 0; i < Control.view_control.tablesize; i++){
            for(int j = 0; j < Control.view_control.tablesize; j++){
                Control.view_control.buttons[i][j].setEnabled(true);
                Control.view_control.buttons[i][j].setBackground(new Color(237, 187, 153));
                Control.view_control.buttons[i][j].setText("");
            }
        }
        Control.view_control.count = 0;
        Control.view_control.currentPlayer = "x";
        Control.view_control.textfield.setForeground(Color.BLACK);
        Control.view_control.textfield.setText("Tic-Tac-Toe");
        
    }
    public static void ExitGame() {
        System.exit(0); 
    }

    public static void ButtonEnable(int tablesize){
        for(int x = 0; x < tablesize; x++){
            for(int y = 0; y < tablesize; y++){
                Control.view_control.buttons[x][y].setEnabled(false);
            }
        }
    }
}
