package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Controller.MainController;
import Model.DataManager;
import Model.Rumour;

public class RumourListView extends JPanel {
    private MainController frame;
    private DataManager db;

    public RumourListView(MainController control, DataManager db) {
        this.frame = control;
        this.db = db;
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Rumour List", SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

 
        for (Rumour r : db.rumours) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // ขีดเส้นใต้

            int count = db.reportCount(r.getId());
            
            String labelText = String.format("ID: %s | %s (Reports: %d) [%s]", 
                                             r.getId(), r.getTitle(), count, r.getStatus());
            JLabel lbl = new JLabel(labelText);
            
            if ("panic".equals(r.getStatus())) {
                lbl.setForeground(Color.RED);
                lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
            }


            JButton btnView = new JButton("View Detail");
            btnView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.detailView(r);
                }
            });

            row.add(lbl);
            row.add(btnView);
            listPanel.add(row);
        }

        add(new JScrollPane(listPanel), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnSortbyCd = new JButton("Sort by Report Credits (Hot)");
        btnSortbyCd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.filterByCredit();
                frame.listView();
            }
        });
        add(btnSortbyCd, BorderLayout.SOUTH);

        JButton btnSortByRep = new JButton("Sort by Reports (Hot)");
        btnSortByRep.setBackground(new Color(255, 200, 200));
        btnSortByRep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.filterByRepNum();
                frame.listView();
            }
        });
        bottomPanel.add(btnSortbyCd);
        bottomPanel.add(btnSortByRep);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}