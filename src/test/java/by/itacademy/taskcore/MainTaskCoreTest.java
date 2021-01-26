package by.itacademy.taskcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTaskCoreTest {

    PrintStream out;
    ByteArrayOutputStream os;
    String filename;
    String expected;

    @BeforeEach
    void init() throws IOException {
        filename = "speed.txt";
        os = new ByteArrayOutputStream();
        out = new PrintStream(os);
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
    void mainPositive() throws UnsupportedEncodingException {
        MainTaskCore.main(new String[] {filename});
        String actual = os.toString("UTF8");
        assertEquals(expected,actual);
    }

    @Test
    void mainNegative() throws UnsupportedEncodingException {
        MainTaskCore.main(new String[] {"wrongFIleName"});
        String actual = os.toString("UTF8");
        assertEquals("\n\n\n\nno\n\n",actual);
    }
}