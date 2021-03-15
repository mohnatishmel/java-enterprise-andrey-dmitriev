package by.itacademy.taskcore.model.speed;

import by.itacademy.taskcore.model.Measurement;
import lombok.*;

import java.text.DecimalFormat;

@AllArgsConstructor
@Data

public class Speed implements Measurement {

    private double value;
    private String unit;

    public Speed(Speed speed) {
        value = speed.getValue();
        unit = speed.getUnit();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(value) + " " + unit;
    }

}
