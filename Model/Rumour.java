package Model;

public class Rumour {
    private String id;
    private String titile;
    private String src;
    private String date; // format: yyyy-mm-dd
    private int credit = 0;
    private String status = "normal";
    private boolean isVerify = false;

    private int maxRumour = 10;

    private Users u;
    private DataManager db;

    public Rumour() {}

    public Rumour(String id, String titile, String src, String date, int credit) {
        setId(id);
        this.titile = titile;
        this.src = src;
        this.date = date;
        setCredit(credit);
    }

    public Rumour(String id, String titile, String src, String date, int credit, String status) {
        setId(id);
        this.titile = titile;
        this.src = src;
        this.date = date;
        setCredit(credit);
        this.status = status;
    }

    // Logics
    protected void verifyRumour(String id) {
        if (u.isChecker() == false) {
            return;
        }

        for (Rumour r : db.rumours) {
            if (r.getId().equals(id)) {
                r.setVerify(true);
                break;
            }
        }
    }

    protected void updateStatus(int num) {
        if ((!isVerify()) && num == maxRumour) {
            this.status = "panic";
        }
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.length() == 8 && (!id.startsWith("0"))) { this.id = id; }
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        if (credit >= 0) { this.credit = credit; }
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean v) {
        this.isVerify = v;
    }
    
}
