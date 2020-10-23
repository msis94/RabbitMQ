import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

//import java.time.LocalDate;
import java.util.Date;

public class Staff
{
    Date date;
    int emp_num;

    public Staff() {}

    public Staff(Date date, int emp_num) {
        this.date = date;
        this.emp_num = emp_num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEmp_num() {
        return emp_num;
    }

    public void setEmp_num(int emp_num) {
        this.emp_num = emp_num;
    }
}
