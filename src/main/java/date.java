import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.joda.time.LocalDateTime;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class date
{
    public static void main(String[] args) {
//        Date date = new Date();
//
//        System.out.println(date);

//        Date dates = new Date(1601913600000L);
//
//        System.out.println(dates);
//        System.exit(1);



//        long epoch = System.currentTimeMillis()/1000;
//        System.out.println(epoch);

        DB db = new MongoClient().getDB("Confab");
        Jongo jongo = new Jongo(db);
        MongoCollection staffColl = jongo.getCollection("Staff");
//        Staff staff = new Staff(date, 1);
//        staffColl.insert(staff);

//        Staff  a = staffColl.findOne("{emp_num: 1}").as(Staff.class);
//        System.out.println(a.getDate());





//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

//        System.out.println(format.parse("2020-10-06 00:00:00"));
//
//        Date start = format.parse("2020-10-06 00:00:00");

//        Date start = new Date(1601913600000L);
//
//        System.out.println(start);
//        System.exit(1);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String text = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);


//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatting: " + formattedDate);



//        Staff  a = staffColl.findOne("{date: {$gte : "+start+"}").as(Staff.class);

//        System.out.println(a.getDate());

    }
}
