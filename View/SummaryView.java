package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controller.MainController;
import Model.DataManager;
import Model.Rumour;

public class SummaryView extends JPanel {
    private MainController frame;
    private DataManager db;

    public SummaryView(MainController control, DataManager d) {
        this.frame = control;
        this.db = d;

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Report Summary", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        String[] columns = {"Report ID", "Title", "Status", "Verify"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        for (Rumour rm : db.rumours) {
            if (rm.getStatus().equals("panic") || rm.isVerify()) {
                Object[] row = {
                    rm.getId(), 
                    rm.getTitle(), 
                    rm.getStatus(), 
                    rm.isVerify() ? "Verified" : "Unverified"
                };
                model.addRow(row);
            }
        }
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnBack = new JButton("Back");

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.listView();
            }
        });

        add(btnBack, BorderLayout.SOUTH);
    }
}
