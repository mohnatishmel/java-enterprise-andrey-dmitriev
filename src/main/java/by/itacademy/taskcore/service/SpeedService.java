package by.itacademy.taskcore.service;

import by.itacademy.taskcore.dao.Dao;
import by.itacademy.taskcore.exception.DaoException;
import by.itacademy.taskcore.model.MeasurementContainer;
import by.itacademy.taskcore.model.speed.Speed;
import by.itacademy.taskcore.model.speed.SpeedContainer;
import by.itacademy.taskcore.util.converter.SpeedConverter;
import by.itacademy.taskcore.validator.Validator;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor

public class SpeedService {

    private final Dao dao;
    private final Validator<String> validator;
    private final SpeedConverter speedConverter;
    private List<MeasurementContainer<Speed>> spedContainerCache;
    private String fileName;

    public boolean loadData(String fileName) {
        this.fileName = fileName;
        return loadData();
    }

    public String readData() {
        loadData();
        StringBuilder data = new StringBuilder();
        for (MeasurementContainer<Speed> speed : spedContainerCache) {
            data.append(speed.getOriginal())
                    .append(" = ")
                    .append(speed.get("ms"))
                    .append("\n");
        }
        return data.toString().trim();
    }


    public String absolutSort() {
        loadData();
        Comparator<MeasurementContainer<Speed>> comparator =
                Comparator.comparingDouble(m -> m.get("ms").getValue());
        return spedContainerCache.stream()
                .sorted(comparator)
                .map( n -> n.getOriginal().toString())
                .collect( Collectors.joining( "\n" ) );
    }

    public String findSpeedEqualToTwoKilometersPerHour() {
        loadData();
        boolean result = spedContainerCache.stream().anyMatch(m -> m.get("ms").getValue() == (2.0 * 1000.0 / 3600.0));
        if (result) {
            return "yes";
        } else {
            return "no";
        }
    }

    public String relativeRevertedSort() {
        loadData();
        List<String> definedOrder = Arrays.asList("kmh", "mph", "kn", "ms");
        Comparator<MeasurementContainer<Speed>> unitComparator =
                Comparator.comparingInt(m -> definedOrder.indexOf(m.getOriginal().getUnit()));
        Comparator<MeasurementContainer<Speed>> valueComparator =
                Comparator.comparingDouble(m -> m.getOriginal().getValue());
        return spedContainerCache.stream()
                .sorted(unitComparator.thenComparing(valueComparator.reversed()))
                .map( n -> n.getOriginal().toString())
                .collect( Collectors.joining( "\n" ) );
    }

    private boolean loadData() {
        if (isCacheEmpty()) {
            List<String> data;
            try {
                data = dao.read(fileName);
            } catch (DaoException e) {
                return false;
            }
            spedContainerCache = convertDaoStringDataToSpeedContainerList(data);
            spedContainerCache = speedConverter.convertToMetersPerSecond(spedContainerCache);
        }
        return true;
    }

    private boolean isCacheEmpty() {
        return spedContainerCache == null || spedContainerCache.isEmpty();
    }

    private List<MeasurementContainer<Speed>> convertDaoStringDataToSpeedContainerList(List<String> data) {
        List<MeasurementContainer<Speed>> speedContainerList = new ArrayList<>();
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
