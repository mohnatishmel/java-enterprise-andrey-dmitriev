package by.itacademy.taskcore.util.converter;

import by.itacademy.taskcore.model.speed.Speed;
import by.itacademy.taskcore.model.speed.SpeedContainer;

import java.util.List;

public class SpeedConverter {

    public List<SpeedContainer> convertToMetersPerSecond(List<SpeedContainer> list) {
        for (SpeedContainer sc : list) {
            Speed speed = sc.getOriginal();
            convertToMetersPerSecond(speed);
            sc.add(speed);
        }
        return list;
    }

    public void convertToMetersPerSecond(Speed speed) {
        double value = speed.getValue();
        String unit = speed.getUnit();
        if (unit != null) {
            if (unit.equals("kmh")) {
                value = kilometersPerHourToMetersPerSecond(value);
            }  if (unit.equals("mph")) {
                value = milesPerHourToMetersPerSecond(value);
            }  if (unit.equals("kn")) {
                value = knotsToMetersPerSecond(value);
            }
        }
        speed.setUnit("ms");
        speed.setValue(value);
    }

    private double kilometersPerHourToMetersPerSecond(double value) {
        value *= 1000;
        value = metersPerHourToMetersPerSecond(value);
        return value;
    }

    private double milesPerHourToMetersPerSecond(double value) {
        value *= 1609;
        value = metersPerHourToMetersPerSecond(value);
        return value;
    }

    private double knotsToMetersPerSecond(double value) {
        value *= 1852;
        value = metersPerHourToMetersPerSecond(value);
        return value;
    }

    private double metersPerHourToMetersPerSecond(double value) {
        return value / 3600;
    }
}
