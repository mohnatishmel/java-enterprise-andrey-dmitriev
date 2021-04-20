package by.itacademy.front.command;

import by.itacademy.service.FacadeService;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {

        forward("login");
    }
}
