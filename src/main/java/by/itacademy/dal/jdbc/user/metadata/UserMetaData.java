package by.itacademy.dal.jdbc.user.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Builder

public class UserMetaData {

        private int id;
        private int credentialsId;
        private int personalInformationId;
        private boolean accountNotLocked;

        public static UserMetaData.UserMetaDataBuilder builder() {
                return new UserMetaData.UserMetaDataBuilder();
        }
}
