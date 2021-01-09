package by.itacademy.taskcore.model.speed;

import by.itacademy.taskcore.model.Measurement;
import by.itacademy.taskcore.model.MeasurementContainer;

import java.util.HashMap;
import java.util.Map;

public class SpeedContainer implements MeasurementContainer<Speed> {

    private final Map<String, Speed> measurements;
    private final Speed original;

    {
        measurements = new HashMap<>(4);
    }

    public SpeedContainer(Speed speed) {
        original = speed;
        String unit = speed.getUnit();
        measurements.put(unit, speed);
    }

    @Override
    public void add(Measurement speed) {
        String unit = speed.getUnit();
        measurements.put(unit, (Speed) speed);
    }

    @Override
    public Speed getOriginal() {
        return new Speed(original);
    }

    @Override
    public Speed get(String unit) {
        Speed speed = measurements.get(unit);
        return new Speed(speed);
    }
}


