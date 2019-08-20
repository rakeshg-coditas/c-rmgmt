package com.coditas.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Notification.
 */
@Document(collection = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("to")
    private String to;

    @Field("from")
    private String from;

    @Field("message")
    private String message;

    @Field("is_read")
    private Boolean isRead;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public Notification to(String to) {
        this.to = to;
        return this;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public Notification from(String from) {
        this.from = from;
        return this;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public Notification message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isIsRead() {
        return isRead;
    }

    public Notification isRead(Boolean isRead) {
        this.isRead = isRead;
        return this;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }
        return id != null && id.equals(((Notification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", to='" + getTo() + "'" +
            ", from='" + getFrom() + "'" +
            ", message='" + getMessage() + "'" +
            ", isRead='" + isIsRead() + "'" +
            "}";
    }
}
