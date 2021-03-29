package by.itacademy.front.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class MainPageCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        forward("main");
    }
}
