package by.itacademy.taskcore.model.speed;

import by.itacademy.taskcore.model.MeasurementContainer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class SpeedContainer implements MeasurementContainer {

    private final double value;
    private final String unit;

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
