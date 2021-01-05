package by.itacademy.taskcore.util.converter;

import by.itacademy.taskcore.exception.IllegalUnitTypeException;
import by.itacademy.taskcore.model.Convertible;

public class Converter {

    public static Convertible convertToMetersPerSecond(Convertible convertible) throws IllegalUnitTypeException {
        double value = convertible.getValue();
        String unit = convertible.getUnit();
        if (unit != null) {
            if (unit.equals("kmh")) {
                value = kilometersPerHourToMetersPerSecond(value);
            }  if (unit.equals("mph")) {
                value = milesPerHourToMetersPerSecond(value);
            }  if (unit.equals("kn")) {
                value = knotsToMetersPerSecond(value);
            } else {
                throw new IllegalUnitTypeException("Provided unit type is not supported");
            }
        } else {
            throw new IllegalUnitTypeException("Unit type can't be null");
        }
        convertible.setUnit("ms");
        convertible.setValue(value);
        return convertible;
    }

    private static double kilometersPerHourToMetersPerSecond(double value) {
        value *= 1000;
        value = metersPerHourToMetersPerSecond(value);
        return value;
    }

    private static double milesPerHourToMetersPerSecond(double value) {
        value *= 1609;
        value = metersPerHourToMetersPerSecond(value);
        return value;
    }

    private static double knotsToMetersPerSecond(double value) {
        value *= 1852;
        value = metersPerHourToMetersPerSecond(value);
        return value;
    }

    private static double metersPerHourToMetersPerSecond(double value) {
        return value / 3600;
    }
}
