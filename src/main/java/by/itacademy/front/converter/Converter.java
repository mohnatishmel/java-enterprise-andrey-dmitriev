package by.itacademy.front.converter;

public interface Converter <T,E>{
    E convert( T t);
}
