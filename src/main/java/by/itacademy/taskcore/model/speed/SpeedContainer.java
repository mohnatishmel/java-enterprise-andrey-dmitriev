package by.itacademy.taskcore.model.speed;

import by.itacademy.taskcore.model.Convertible;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode

public class SpeedContainer implements Convertible {

    private final String id;
    private double value;
    private String unit;

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public Convertible clone() {
        return new SpeedContainer(id,value,unit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
