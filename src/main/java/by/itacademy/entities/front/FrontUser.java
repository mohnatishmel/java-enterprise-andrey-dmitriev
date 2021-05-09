package by.itacademy.entities.front;

import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@Builder
@Getter

public class FrontUser {

    private final int id;
    private final FrontCredential credential;
    private final FrontPersonalInformation personalInformation;
    private final Collection<FrontRole> roles;
    private final boolean accountNotLocked;
}
