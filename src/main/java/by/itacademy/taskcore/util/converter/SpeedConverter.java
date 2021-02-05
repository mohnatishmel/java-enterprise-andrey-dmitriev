package by.itacademy.taskcore.util.converter;

import by.itacademy.taskcore.model.Measurement;
import by.itacademy.taskcore.model.MeasurementContainer;
import by.itacademy.taskcore.model.speed.Speed;

import java.util.List;

public class SpeedConverter {

    public List<MeasurementContainer<Speed>> convertToMetersPerSecond(List<MeasurementContainer<Speed>> list) {
        for (MeasurementContainer<Speed> mc : list) {
            Measurement measurement = mc.getOriginal();
            convertToMetersPerSecond(measurement);
            mc.add(measurement);
        }
        return list;
    }

    public Measurement convertToMetersPerSecond(Measurement measurement) {
        double value = measurement.getValue();
        String unit = measurement.getUnit();
        if (unit != null) {
            if (unit.equals("ms")) {
                return measurement;
            } if (unit.equals("kmh")) {
                value = kilometersPerHourToMetersPerSecond(value);
            }  if (unit.equals("mph")) {
                value = milesPerHourToMetersPerSecond(value);
            }  if (unit.equals("kn")) {
                value = knotsToMetersPerSecond(value);
            }
        }
        measurement.setUnit("ms");
        measurement.setValue(value);
        return measurement;
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
