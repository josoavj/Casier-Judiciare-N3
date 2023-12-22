package Bulletin.print;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity @Table(name = "PRINTER")
public class PrinterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameConserned;
    private Date date;
    public PrinterEntity(){
        this("null");
    }
    public PrinterEntity(String nameConserned){
        this.nameConserned = nameConserned;
    }

    public int getId() {
        return id;
    }

    public String getNameConserned() {
        return nameConserned;
    }

    public void setNameConserned(String nameConserned) {
        this.nameConserned = nameConserned;
        this.date = Date.valueOf(LocalDate.now());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
