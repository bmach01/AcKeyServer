package org.bmach01.AcKeyAPI.domain.user;

import org.bmach01.AcKeyAPI.domain.user.AccountStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
    @Id String id;
    String username;
    String password;
    String device;
    AccountStatus status;

    public String toString() {
        return username + " " + id;
    }
}
