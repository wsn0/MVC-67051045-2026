package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataManager {
    public ArrayList<Report> reports = new ArrayList<>();
    public ArrayList<Rumour> rumours = new ArrayList<>();
    public ArrayList<Users> users = new ArrayList<>();

    public DataManager() {
        initData();
    }

    private void initData() {
        for (int i = 1; i <= 8; i++) {
            addUser(new Users("U100000" + i, "User " + i, "General"));
        }

        addUser(new Users("C2000001", "Checker A", "Checker"));
        addUser(new Users("C2000002", "Checker B", "Checker"));

        createRumour(new Rumour("11112222", "Rumour 1", "FB", "2026-02-07", 1));
        createRumour(new Rumour("22223333", "Rumour 2", "X", "2025-05-12", 5, "panic"));
        createRumour(new Rumour("33334444", "Rumour 3", "YT", "2025-02-12", 3));
        createRumour(new Rumour("44445555", "Rumour 4", "FB", "2025-09-08", 3));
        createRumour(new Rumour("55556666", "Rumour 5", "X", "2025-10-19", 6, "panic"));
        createRumour(new Rumour("66667777", "Rumour 6", "FB", "2025-07-07", 4));
        createRumour(new Rumour("77778888", "Rumour 7", "X", "2025-11-11", 2));
        createRumour(new Rumour("88889999", "Rumour 8", "FB", "2026-01-07", 5, "panic"));

        for (int i = 1; i <= 5; i++) {
            addReport(new Report("U100000" + i, "11112222","2026-02-0" + i, "Fake News"));
        }

        addReport(new Report("U1000123", "66667777", "2025-07-12", "Fact Checked"));
    }

    private Rumour searchRumour(String id) {
        for (Rumour r : rumours) {
            if (r.getId().equals(id)) { return r; }
        }

        return null;
    }

    public int reportCount(String id) {
        int count = 0;

        for (Report rep : reports) {
            if (rep.getRumId().equals(id)) {
                count++;
            }
        }

        return count;
    }

    public void verifyRumour(String id, Users u) {
        if (u.isChecker() == false) {
            return;
        }

        for (Rumour r : rumours) {
            if (r.getId().equals(id)) {
                r.setVerify(true);
                break;
            }
        }
    }

    public void addUser(Users u) {
        for (Users us : users) {
            if (us.getId().equals(u.getId()) && us.getRole().equals(u.getRole())) {
                return;
            }
        }

        users.add(u);
    }

    public boolean addReport(Report r) {
        Rumour rm = searchRumour(r.getRumId());
        boolean flag = false;

        if (rm == null || rm.isVerify()) {
            return flag;
        }

        // เช็ค reporter id ว่า reporter คนนี้เคยรายงานไปหรือยัง
        for (Report rep : reports) {
            if (rep.getRepId().equals(r.getRepId()) && rep.getRumId().equals(r.getRumId())) {
                return flag;
            }
        }

        reports.add(r);
        rm.updateStatus(reportCount(rm.getId()));
        flag = true;

        return flag;
    }

    public void createRumour(Rumour r) {
        for (Rumour rm : rumours) {
            if (r.getId().equals(rm.getId())) {
                return;
            }
        }

        rumours.add(r);
    }

    // DESC Order
    public void filterByCredit() {
        Collections.sort(rumours, new Comparator<Rumour>() {
        @Override
        public int compare(Rumour r1, Rumour r2) {
            if (r2.getCredit() < r1.getCredit()) return -1;
            if (r2.getCredit() > r1.getCredit()) return 1;

            return 0;
        }
    });
    }

    // DESC Order
    public void filterByRepNum() {
        Collections.sort(rumours, new Comparator<Rumour>() {
        @Override
        public int compare(Rumour r1, Rumour r2) {
            int count1 = reportCount(r1.getId());
            int count2 = reportCount(r2.getId());

            return count2 - count1; 
        }
    });
    }
}
