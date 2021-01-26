package by.itacademy.taskcore.dao;

import by.itacademy.taskcore.exception.DaoException;
import by.itacademy.taskcore.model.speed.Speed;
import by.itacademy.taskcore.model.speed.SpeedContainer;
import by.itacademy.taskcore.validator.Validator;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FileDao implements Dao<SpeedContainer>{

    private final Validator<String> validator;

    @Override
    public List<SpeedContainer> read(String name) throws DaoException {
        List<String> data = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/" + name))) {
            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                data.add(buffer);
            }
        } catch (IOException e) {
            throw new DaoException("File can't be read", e);
        }
        return convertToSpeedContainerList(data);
    }

    private List<SpeedContainer> convertToSpeedContainerList(List<String> data) {
        List<SpeedContainer> speedContainerList = new ArrayList<>();
        SpeedContainer buffer;
        for (String string : data) {
            if (validator.validate(string)) {
                buffer = getSpeedContainer(string);
                speedContainerList.add(buffer);
            }
        }
        return speedContainerList;
    }

    private SpeedContainer getSpeedContainer(String s) {
        String[] data = s.split(" ");
        double value = Double.parseDouble(data[0]);
        String unit = data[1];
        Speed speed = new Speed(value, unit);
        return new SpeedContainer(speed);
    }
}
