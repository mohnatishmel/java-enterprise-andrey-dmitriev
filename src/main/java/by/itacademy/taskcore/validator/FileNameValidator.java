package by.itacademy.taskcore.validator;

public class FileNameValidator implements Validator<String> {

    @Override
    public boolean validate(String fileName) {
        return filenameExtensionCheck(fileName);
    }

    private boolean filenameExtensionCheck(String fileName) {
        boolean result = false;
        if (fileName != null) {
            String regularExpression = "/*.txt";
            result = fileName.matches(regularExpression);
        }
        return result;
    }
}
