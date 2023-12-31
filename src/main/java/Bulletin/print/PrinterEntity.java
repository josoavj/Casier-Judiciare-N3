package Bulletin.print;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity @Table(name = "PRINTER")
public class PrinterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameConcerned;
    private Date date;
    public PrinterEntity(){
        this("null");
    }
    public PrinterEntity(String nameConcerned){
        this.nameConcerned = nameConcerned;
    }

    public int getId() {
        return id;
    }

    public String getNameConcerned() {
        return nameConcerned;
    }

    public void setNameConcerned(String nameConcerned) {
        this.nameConcerned = nameConcerned;
        this.date = Date.valueOf(LocalDate.now());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
