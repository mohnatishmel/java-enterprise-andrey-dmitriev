package by.itacademy.taskcore.validator;

public class DaoDataValidator implements Validator<String> {

    @Override
    public boolean validate(String data) {
        boolean result = false;
        if (data != null) {
            String regularExpression = "^\\d+\\s+(ms|kmh|mph|kh)";
            result = data.matches(regularExpression);
        }
        return result;
    }
}
