package Model;

public class Report {
    private String repId; // รหัสผู้รายงานไม่ซ้ำกัน
    private String rumId;
    private String date;
    private String type;

    public Report() {}

    public Report(String repId, String rumId, String date, String type) {
        this.repId = repId;
        this.rumId = rumId;
        this.date = date;
        this.type = type;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getRumId() {
        return rumId;
    }

    public void setRumId(String rumId) {
        this.rumId = rumId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
