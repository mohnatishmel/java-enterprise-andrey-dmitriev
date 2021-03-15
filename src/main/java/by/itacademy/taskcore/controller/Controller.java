package by.itacademy.taskcore.controller;

import by.itacademy.taskcore.service.SpeedService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Controller {

    private final SpeedService service;
    private String filename;

    public void start() {
        service.loadData(filename);
        String buffer;
        buffer = service.readData();
        System.out.println(buffer + "\n");

        buffer = service.absolutSort();
        System.out.println(buffer + "\n");

        buffer = service.findSpeedEqualToTwoKilometersPerHour();
        System.out.println(buffer + "\n");

        buffer = service.relativeRevertedSort();
        System.out.print(buffer);
    }
}
