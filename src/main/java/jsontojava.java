import com.google.gson.Gson;

public class jsontojava
{
    public static void main(String[] args)
    {
        Gson gson = new Gson();
        String json = "{\"emp_num\": 3, \"date\": \"2/5/2020\", \"time_in\": \"4\", \"time_out\": \"4\"}";

        // Convert JSON File to Java Object
        Attendance attendance = gson.fromJson(json, Attendance.class);

        // print staff object
        System.out.println(attendance.getTime_in());
    }
}
