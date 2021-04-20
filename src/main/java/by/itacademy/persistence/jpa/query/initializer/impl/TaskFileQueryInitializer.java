package by.itacademy.persistence.jpa.query.initializer.impl;

import by.itacademy.entities.file.File;
import by.itacademy.persistence.jpa.query.initializer.QueryInitializer;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Query;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

public class TaskFileQueryInitializer implements QueryInitializer<File> {

    @Override
    public void processUpdateQuery(Query query, File file) {
        byte[] bytes = file.getBytes();
        ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);
        query.setParameter("file", byteArrayIS);
        query.setParameter("name", file.getName());
        query.setParameter("id", file.getId());
        try {
            byteArrayIS.close();
        } catch (IOException e) {
            log.debug("Enable to close input stream", Arrays.toString(e.getStackTrace()));
        }
    }

}
