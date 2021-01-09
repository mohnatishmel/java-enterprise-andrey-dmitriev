package by.itacademy.taskcore;

import by.itacademy.taskcore.controller.Controller;
import by.itacademy.taskcore.controller.console.ConsoleController;
import by.itacademy.taskcore.dao.Dao;
import by.itacademy.taskcore.dao.FileDao;
import by.itacademy.taskcore.service.SpeedService;
import by.itacademy.taskcore.util.converter.SpeedConverter;
import by.itacademy.taskcore.validator.DaoDataValidator;
import by.itacademy.taskcore.validator.FileNameValidator;
import by.itacademy.taskcore.validator.Validator;

public class MainTaskCore {



    public static void main(String[] args) {

        Dao dao;
        SpeedService service;
        Validator<String> daoDataValidator;
        Validator<String> fileNameValidator;
        SpeedConverter speedConverter;
        Controller consoleController;

        {
            dao = new FileDao();
            daoDataValidator = new DaoDataValidator();
            fileNameValidator = new FileNameValidator();
            speedConverter = new SpeedConverter();
            service = new SpeedService(dao, daoDataValidator,speedConverter);
            consoleController = new ConsoleController(service, fileNameValidator);
        }

        consoleController.start();
    }

}

