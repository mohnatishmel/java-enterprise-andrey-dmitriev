package by.itacademy.entities.front;

import lombok.Getter;

public class Message {

    public Message(String message) {
        this.message = message;
    }

    @Getter
    private String message;
}

