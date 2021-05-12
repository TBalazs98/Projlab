package View;

import Controller.Main;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A jatekos altal kiadhato muveletek felulete, mine, move, drill, place, build
 */
public class CommandPanel extends JPanel implements ActionListener {
    private JPanel buttons;
    private JButton build, drill, move, mine, place, nextsettler;
    private int scaling = 60;
    private boolean next = false;
    JPanel inventory = new JPanel(new FlowLayout(FlowLayout.CENTER));;

    /**
     * Konstruktor
     */
    CommandPanel() {
        this.setLayout(new GridLayout(2,1));
        this.setPreferredSize(new Dimension(Game.getInstance().c.g.width / 2, 200));
        this.setBackground(Color.GRAY);
        Buttons();
        InventoryPanel();

        this.setVisible(true);
    }

    /**
     * Gombob egyseges kinezetenek megjelenitesere szolgal
     * @param button kezelni kivant gomb
     */
    public void SetButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
    }

    /**
     * Mine gomb kezelese, akkor valik aktivva, ha az aszteroidaban talalhato nyersanyak es mar ki van furva
     * @param button kezelni kivant gomb
     */
    public void SetButtonMine(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
        if(Main.settlers.get(Game.getInstance().c.SelectedSettler()) != null) {
            if ((Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().getLayers() > 0 && !Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().GetisEmpty()))
                button.setEnabled(false);
            else {
                button.setEnabled(true);
            }
        }
    }

    /**
     * Drill gomb kezelese, akkor valik aktivva, ha az aszteroidat meg nem furtuk ki
     * @param button kezelni kivant gomb
     */
    public void SetButtonDrill(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
        if(Main.settlers.get(Game.getInstance().c.SelectedSettler()) != null) {
            if (Main.settlers.get(Game.getInstance().c.SelectedSettler()).getAsteroid().getLayers() <= 0)
                button.setEnabled(false);
            else {
                button.setEnabled(true);
            }
        }
    }

    /**
     * A parancsgombok megjelenitesere szolgal, move, drill, mine, place, build es next
     */
    public void Buttons(){
        scaling = Game.getInstance().c.g.GetWidth()/20;
        int height = 60;


        build = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_build.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        build.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_build (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(build);

        drill = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_drill.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        drill.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_drill (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButtonDrill(drill);

        move = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_move.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        move.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_move (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(move);

        place = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_place.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        place.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_place (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(place);

        mine = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_mine.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        mine.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_mine (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButtonMine(mine);

        nextsettler = new JButton(new ImageIcon(new ImageIcon("Files/Pictures/button_next.png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        nextsettler.setRolloverIcon(new ImageIcon(new ImageIcon("Files/Pictures/button_next (1).png").getImage().getScaledInstance(scaling, height, Image.SCALE_SMOOTH)));
        SetButton(nextsettler);

        buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.setPreferredSize(new Dimension(Game.getInstance().c.g.getWidth()/2,60));
        buttons.add(move);
        buttons.add(drill);
        buttons.add(mine);
        buttons.add(place);
        buttons.add(build);
        buttons.add(nextsettler);

        buttons.setOpaque(false);

        this.add(buttons);

    }

    /**
     * A nyersanyagok paneljenek megjelenitesere szolgal
     */
    public void InventoryPanel() {
        inventory.removeAll();
        inventory.repaint();

        inventory.setPreferredSize(new Dimension(Game.getInstance().c.g.getWidth()/2,70));
        inventory.setBackground(Color.GRAY);
        ImageIcon  p =null;
        scaling = 60;
        int db = 0;
        if(Main.settlers.get(Game.getInstance().c.SelectedSettler())!=null) {
            for (Material m : Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetInventory().GetMaterials()) {
                db++;
                if (m.name == NormalMaterialName.IRON) {
                    p = new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                if (m.name == NormalMaterialName.COAL) {
                    p = new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                if (m.name == SublimableMaterialName.ICEWATER) {
                    p = new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                if (m.name == RadioactiveMaterialName.URAN) {
                    RadioactiveMaterial rm = (RadioactiveMaterial) m;
                    if (rm.GetExposure() == 0)
                        p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                    else if (rm.GetExposure() == 1)
                        p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                    else if (rm.GetExposure() == 2)
                        p = new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                }
                JButton btn = new JButton(p);
                setbtn(btn);
                inventory.add(btn);
            }

            for (TeleportGate t : Main.settlers.get(Game.getInstance().c.SelectedSettler()).GetGates()) {
                db++;
                p = new ImageIcon(new ImageIcon("Files/Pictures/teleportgate.jpg").getImage().getScaledInstance(scaling, scaling, Image.SCALE_SMOOTH));
                JButton btn = new JButton(p);
                setbtn(btn);
                inventory.add(btn);
            }
        }
        for(int i = 0; i < (13-db);i++){
            JButton btn = new JButton();
            setbtn(btn);
            inventory.add(btn);
        }
        this.add(inventory);
        this.repaint();
        this.validate();

    }

    /**
     * Gombok kinezetenek egysegesitese
     * @param btn valtoztatando gomb
     */
    public void setbtn(JButton btn){
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(60,60));
        btn.setBackground(Color.DARK_GRAY);
    }

    /**
     * Gombok kattintasanak figyelesere szolgal, adott muveletet hajtja vegre
     * @param e az aktualisan lenyomott gomb
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == move){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.g.dp.moveDetails(Game.getInstance().c.g);
            next = true;
        }
        if(e.getSource() == drill){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.HandleDrill();
            next = true;
        }
        if(e.getSource() == mine){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.HandleMine();
            next = true;
        }
        if(e.getSource() == place){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.HandlePlaceMaterial();
            next = true;
        }
        if(e.getSource() == build){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.g.dp.buildDetails(Game.getInstance().c.g);
            next = true;
        }
        if(e.getSource() == nextsettler){
            Game.getInstance().c.g.dp.removeAll();
            Game.getInstance().c.NextSettler();
        }
        this.removeAll();
        inventory.removeAll();
        buttons.removeAll();
        Buttons();
        if(next) {
            setButtonsOff();
            next = false;
        }
        InventoryPanel();
        this.repaint();
        this.validate();
    }

    /**
     * Egy settler egyszerre csak egy muveletet kepes vegrehajtani, emiatt deaktivaljuk a gombokat, a nextsettler kivetelevel
     */
    public void setButtonsOff(){
        this.move.setEnabled(false);
        this.drill.setEnabled(false);
        this.mine.setEnabled(false);
        this.build.setEnabled(false);
        this.place.setEnabled(false);
    }

}