import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class dummy
{
    public static void main(String[] args) throws IOException
    {
        DateTime dt = new DateTime();
        System.out.println(dt);



    }
}
