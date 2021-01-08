package by.itacademy.taskcore.dao;

import by.itacademy.taskcore.exception.DaoException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDao implements Dao{

    @Override
    public List<String> read(String name) throws DaoException {
        List<String> data = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/" + name))) {
            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                data.add(buffer);
            }
        } catch (IOException e) {
            throw new DaoException("File can't be read", e);
        }
        return data;
    }
}
