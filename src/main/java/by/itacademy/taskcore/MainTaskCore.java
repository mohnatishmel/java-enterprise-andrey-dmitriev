package by.itacademy.taskcore;

import by.itacademy.taskcore.controller.Controller;
import by.itacademy.taskcore.dao.Dao;
import by.itacademy.taskcore.dao.FileDao;
import by.itacademy.taskcore.model.speed.SpeedContainer;
import by.itacademy.taskcore.service.SpeedService;
import by.itacademy.taskcore.util.converter.SpeedConverter;
import by.itacademy.taskcore.validator.DaoDataValidator;
import by.itacademy.taskcore.validator.Validator;

public class MainTaskCore {



    public static void main(String[] args) {

        Dao<SpeedContainer> dao;
        SpeedService service;
        Validator<String> daoDataValidator;
        SpeedConverter speedConverter;
        Controller consoleController;

        {
            daoDataValidator = new DaoDataValidator();
            dao = new FileDao(daoDataValidator);
            speedConverter = new SpeedConverter();
            service = new SpeedService(dao,speedConverter);
            consoleController = new Controller(service, args[0]);
        }

        consoleController.start();
    }

}

