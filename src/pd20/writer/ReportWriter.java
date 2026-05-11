package pd20.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.UserRaport;

import java.io.File;
import java.io.IOException;

public class ReportWriter {
    private ObjectMapper mapper = new ObjectMapper();

    public void write(UserRaport userRaport) {
        try {
            mapper.writeValue(
                    new File("Z:\\Projects\\RepJava2\\src\\pd20\\userReport.json"),
                    userRaport
            );        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
