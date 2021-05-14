package by.itacademy.controller;

import by.itacademy.constant.Constant;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppErrorController implements ErrorController {

    @RequestMapping(Constant.ERROR_PATH)
    public ModelAndView handleError() {
        return new ModelAndView("error");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}

