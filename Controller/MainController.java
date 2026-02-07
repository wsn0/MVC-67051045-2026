package Controller;

import Model.*;
import View.RumourDetailView;
import View.RumourListView;
import View.SummaryView;

import javax.swing.*;

public class MainController {
    private JFrame frame;
    private DataManager db;

    public MainController() {
        this.db = new DataManager();
        initView();
    }

    private void initView() {
        frame = new JFrame("Rumour Tracking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        listView();
    }

    public void listView() {
        RumourListView lv = new RumourListView(this, db);

        frame.setContentPane(lv);
        frame.revalidate();
        frame.repaint();
    }

    public void detailView(Rumour rm) {
        RumourDetailView de = new RumourDetailView(this, db, db.rumours.get(0));

        frame.setContentPane(de);
        frame.revalidate();
        frame.repaint();
    }

    public void summaryView() {
        SummaryView s = new SummaryView(this, db);

        frame.setContentPane(s);
        frame.revalidate();
        frame.repaint();
    }
}
