package by.itacademy.taskcore.model;

public interface Convertible extends MeasurementContainer{

    void setValue(double value);

    void setUnit(String unit);
}
