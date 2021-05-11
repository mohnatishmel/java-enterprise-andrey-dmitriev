package by.itacademy.controller.converter;

public interface Converter <T,E>{
    E convert( T t);
}
