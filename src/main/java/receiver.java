import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class receiver
{

    public static void main(String[] argv) throws Exception
    {
//      (RECEIVE DATA) AI APP --> RABBITMQ --> JAVA APP (SAVE OR UPDATE ATTENDANCE) -->CHANGE TO JSON --> MONGO
//      0. AI APP SEND MESSAGE TO RABBITMQ
//      1. JAVA APP RECEIVE THE MESSAGE FROM RABBITMQ
//      2. CHANGE TO JSON
//      3. SEND TO MONGO

        rabbitmq("receive");

//      (SEND DATA) CHANGE TO GSON --> JAVA APP --> RABBITMQ --> AI APP
//      0. CHANGE TO JSON
//      1. JAVA APP SEND MESSAGE TO RABBITMQ WHICH IS EMPLOYEE NAME & PERMISSION
//      2. AI APP RECEIVE MESSAGE WHICH IS EMPLOYEE NAME & PERMISSION

//        send_data(); // send employee name

    }

    static void rabbitmq(String condition) throws IOException, TimeoutException
    {
//      CONNECTION CONFIGURATION FOR RABBITMQ

        String ATTENDANCE = "attendance";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");


        if(condition.equals("receive"))
        {
            Connection connection = factory.newConnection();
            Channel attendance = connection.createChannel();
            attendance.queueDeclare(ATTENDANCE, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            DeliverCallback deliverCallback = (consumerTag, delivery) ->
            {
                String message = new String(delivery.getBody(), "UTF-8");
                mongo(gson(message, "jsontojava"));
            };


            attendance.basicConsume(ATTENDANCE, true, deliverCallback, consumerTag -> { });
        }

        else if(condition.equals("send"))
        {

//            javatojson();
//            SEND EMPLOYEE NAME
            System.out.println("send data to AI App");
        }
    }

    static Attendance gson(String message, String choice)
    {
        Gson gson = new Gson();
        Attendance attendance = null;

        if(choice.equals("jsontojava"))
        {
//        Convert JSON File to Java Object
            attendance = gson.fromJson(message, Attendance.class);
        }
        else if(choice.equals("javatojson"))
        {
            System.out.println("java to json");
        }
        return attendance;
    }

    static void mongo(Attendance attendance)
    {

        MongoCollection empColl;
        MongoCollection attColl;
        //Initialize the connection to the database
        DB db = new MongoClient().getDB("skymind");

        Jongo jongo = new Jongo(db);


//      Establish the connection to the employee collection
        empColl = jongo.getCollection("employee");
        attColl = jongo.getCollection("attendance");

//        if emp_num and date are not exist : add all except time_out

        if(attColl.findOne("{emp_num: "+attendance.getEmp_num()+", date: '"+attendance.getDate()+"'}").as(Attendance.class)==null)
        {
            attendance = new Attendance(attendance.getEmp_num(),attendance.getDate(),attendance.getTime_in(),"");
            attColl.save(attendance);
            System.out.println("save attendance");
        }

        //        else if exist just update time out

        else
        {
            attColl.update("{emp_num: "+attendance.getEmp_num()+", date: '"+attendance.getDate()+"'}").with("{$set:{time_out: '"+attendance.getTime_out()+"'}}");
        }
    }
}