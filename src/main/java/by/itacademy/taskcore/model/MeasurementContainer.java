package by.itacademy.taskcore.model;

public interface MeasurementContainer <T extends Measurement> {

    void add(Measurement t);

    T getOriginal();

    T get(String unit);

}
