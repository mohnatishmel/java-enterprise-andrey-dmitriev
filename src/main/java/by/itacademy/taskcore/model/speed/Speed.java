package by.itacademy.taskcore.model.speed;

import by.itacademy.taskcore.model.Measurement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.text.DecimalFormat;

@AllArgsConstructor
@EqualsAndHashCode

public class Speed implements Measurement {

    private double value;
    private String unit;

    public Speed(Speed speed) {
        value = speed.getValue();
        unit = speed.getUnit();
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
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
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(value) + " " + unit;
    }

}
