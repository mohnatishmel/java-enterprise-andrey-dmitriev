package by.itacademy.taskcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MainTaskCoreTest {

    PrintStream out;
    ByteArrayOutputStream os;
    ByteArrayInputStream in;
    String filename;
    String expected;

    @BeforeEach
    void init() throws IOException {
        filename = "speed.txt";
        in = new ByteArrayInputStream(filename.getBytes(StandardCharsets.UTF_8));
        os = new ByteArrayOutputStream();
        out = new PrintStream(os);
        System.setIn(in);
        System.setOut(out);

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/test/mainTest.txt"))) {
            String buffer;
            StringBuilder fileData = new StringBuilder();
            while ((buffer = bufferedReader.readLine()) != null) {
                fileData.append(buffer)
                        .append("\n");
            }
            expected = fileData.toString().trim();
        }
    }

    @Test
    void main() throws UnsupportedEncodingException {
        MainTaskCore.main(new String[0]);
        String actual = os.toString("UTF8");
        assertEquals(expected,actual);
    }
}