package by.itacademy.taskcore.service;

import by.itacademy.taskcore.dao.Dao;
import by.itacademy.taskcore.exception.DaoException;
import by.itacademy.taskcore.model.MeasurementContainer;
import by.itacademy.taskcore.model.speed.Speed;
import by.itacademy.taskcore.model.speed.SpeedContainer;
import by.itacademy.taskcore.util.converter.SpeedConverter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor

public class SpeedService {

    private final Dao<SpeedContainer> dao;
    private final SpeedConverter speedConverter;
    private List<SpeedContainer> spedContainerCache;
    private String fileName;

    {
        spedContainerCache = new ArrayList<>();
    }

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
        Comparator<SpeedContainer> comparator =
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
        Comparator<SpeedContainer> unitComparator =
                Comparator.comparingInt(m -> definedOrder.indexOf(m.getOriginal().getUnit()));
        Comparator<SpeedContainer> valueComparator =
                Comparator.comparingDouble(m -> m.getOriginal().getValue());
        return spedContainerCache.stream()
                .sorted(unitComparator.thenComparing(valueComparator.reversed()))
                .map( n -> n.getOriginal().toString())
                .collect( Collectors.joining( "\n" ) );
    }

    private boolean loadData() {
        if (isCacheEmpty()) {
            try {
                spedContainerCache = dao.read(fileName);
            } catch (DaoException e) {
                return false;
            }
            spedContainerCache = speedConverter.convertToMetersPerSecond(spedContainerCache);
        }
        return true;
    }

    private boolean isCacheEmpty() {
        return spedContainerCache == null || spedContainerCache.isEmpty();
    }
}
