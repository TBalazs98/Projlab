package View;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A jatek adatainak betolteseert felelos osztaly
 */
public class GameData extends AbstractTableModel {

    private ArrayList<String> games;

    /**
     * A GameData publikus konstruktora
     */
    public GameData(){
        try {
            File dir_File=new File ("Files");
            String caononical_path= dir_File.getCanonicalPath();
            File dir_File_Input=new File(caononical_path,"Saved");
            String canonical_path_2=dir_File_Input.getCanonicalPath();

            System.out.println(canonical_path_2);

            File asd = new File(canonical_path_2);
            File[] savedgames =  asd.listFiles();

            games = new ArrayList<String>();

            for(int i = 0; i< Objects.requireNonNull(savedgames).length; i++)
            {
                String game = savedgames[i].getName();
                game = game.replace(".txt","");
                games.add(game);
                System.out.println(game);
            }

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

        String currgame = (games.get(rowIndex));

        switch(columnIndex){
            default: return currgame;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            default:
                return "Saved games";
        }
    }

    public List<String> getList(){
        return this.games;
    }


}
