package View;

import Model.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MaterialList extends JPanel {

    private final Map<String, ImageIcon> imageMap;

    public MaterialList(){
        String[] nameList = {"IRON", "COAL", "URAN0", "URAN1", "URAN2", "WATER"};
        imageMap = createImageMap(nameList);
        JList list = new JList(nameList);

        this.setPreferredSize(new Dimension(50,50));
        this.setVisible(true);

    }

    private Map<String, ImageIcon> createImageMap(String[] list) {
        Map<String, ImageIcon> map = new HashMap<>();
        int scalingx = 50, scalingy = 50;
        try {
            map.put("IRON", new ImageIcon(new ImageIcon("Files/Pictures/iron.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH)));
            map.put("COAL", new ImageIcon(new ImageIcon("Files/Pictures/coal.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH)));
            map.put("URAN0", new ImageIcon(new ImageIcon("Files/Pictures/uran_exp0.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH)));
            map.put("URAN1", new ImageIcon(new ImageIcon("Files/Pictures/uran_exp1.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH)));
            map.put("URAN2", new ImageIcon(new ImageIcon("Files/Pictures/uran_exp2.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH)));
            map.put("WATER", new ImageIcon(new ImageIcon("Files/Pictures/ice.png").getImage().getScaledInstance(scalingx, scalingy, Image.SCALE_SMOOTH)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public class MaterialListRenderer extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }

    private class nameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("sdhkjla");
        }

    }
}
