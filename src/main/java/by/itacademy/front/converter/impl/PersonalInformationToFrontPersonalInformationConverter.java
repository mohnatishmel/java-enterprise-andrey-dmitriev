package by.itacademy.front.converter.impl;

import by.itacademy.entities.front.FrontPersonalInformation;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.front.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonalInformationToFrontPersonalInformationConverter implements Converter<PersonalInformation, FrontPersonalInformation> {

    @Override
    public FrontPersonalInformation convert(PersonalInformation personalInformation) {
        String firstName = personalInformation.getFirstName();
        String lastName = personalInformation.getLastName();

        return new FrontPersonalInformation(firstName, lastName);
    }
}
