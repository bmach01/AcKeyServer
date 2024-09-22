package org.bmach01.AcKeyAPI.domain.activationCode;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "ActivationCodes")
public class ActivationCode {
    @Id String id;
    @Field("user_id") ObjectId userId;
    @Indexed(unique = true) String code;
    boolean used;
    @Field("valid_until")
    Date validUntil;
    @Field("used_on") Date usedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId.toString();
    }

    public void setUserId(String userId) {
        this.userId = new ObjectId(userId);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Date getUsedOn() {
        return usedOn;
    }

    public void setUsedOn(Date usedOn) {
        this.usedOn = usedOn;
    }
}
