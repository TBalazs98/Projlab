package View;

import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GameData extends AbstractTableModel {

    private List<Integer> games;

    public GameData(){



        try {
            games = new ArrayList<Integer>();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Files/Saved/savedgames.txt"));
            games = (List<Integer>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public int getRowCount() {
        return this.games.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        int szam = games.get(rowIndex);

        switch(columnIndex){
            case 0: return szam;
            default: return szam;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Saved games";
            default:
                return "Saved games";
        }
    }

    public List<Integer> getList(){
        return this.games;
    }


}
