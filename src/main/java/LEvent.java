
import java.io.Serializable;
import java.time.LocalDate;

public class LEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private String desc;
    private LocalDate date;
    private int id;

    public LEvent(LocalDate date, String desc, int id) {
        this.setDate(date);
        this.setDesc(desc);
        this.setId(id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDateTxt() {
        return "" + this.getDate();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String toString() {
        return "Data: " + this.getDate() + "            |  " + this.getDesc();
    }
}
