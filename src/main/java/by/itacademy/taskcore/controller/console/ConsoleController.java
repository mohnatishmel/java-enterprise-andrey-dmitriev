package by.itacademy.taskcore.controller.console;

import by.itacademy.taskcore.controller.Controller;
import by.itacademy.taskcore.service.SpeedService;
import by.itacademy.taskcore.validator.Validator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@RequiredArgsConstructor

public class ConsoleController implements Controller {

    private final SpeedService service;
    private final Validator<String> validator;
    private  Scanner systemInScanner;


    @Override
    public void start() {
        String filename = readDataFromConsoleInput();
        service.loadData(filename);
        //service.loadData("speed.txt");
        String buffer;
        buffer = service.readData();
        System.out.println(buffer + "\n");

        buffer = service.absolutSort();
        System.out.println(buffer + "\n");

        buffer = service.findSpeedEqualToTwoKilometersPerHour();
        System.out.println(buffer + "\n");

        buffer = service.relativeRevertedSort();
        System.out.print(buffer);

        systemInScanner.close();
    }

    private String readDataFromConsoleInput() {
        systemInScanner = new Scanner(System.in);
        String filename = systemInScanner.nextLine();
        while (validator.validate(filename)) {
            filename = systemInScanner.nextLine();
        }
        return filename;
    }

}
