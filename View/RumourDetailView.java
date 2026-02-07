package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controller.MainController;
import Model.DataManager;
import Model.Rumour;
import Model.Report;

public class RumourDetailView extends JPanel {
    private MainController frame;
    private DataManager db;
    private Rumour rm;

    public RumourDetailView(MainController control, DataManager d, Rumour rm) {
        this.frame = control;
        this.db = d;
        this.rm = rm;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        add(new JLabel("======== Rumour Detail ========"));
        add(new JLabel("Rumour ID: " + rm.getId()));
        add(new JLabel("Title: " + rm.getTitle()));
        add(new JLabel("Source: " + rm.getSrc()));
        add(new JLabel("Date: " + rm.getDate()));
        add(new JLabel("Rumour Credit: " + rm.getCredit()));
        add(new JSeparator());

        int repCount = db.reportCount(rm.getId());
        add(new JLabel("Total Reports of this rumour: " + repCount));

        JLabel statusLbl = new JLabel("Status: " + rm.getStatus());
        if ("panic".equals(rm.getStatus())) statusLbl.setForeground(Color.RED);
        add(statusLbl);

        add(Box.createVerticalStrut(20));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.listView();
            }
        });
        add(btnBack);
    }

}
