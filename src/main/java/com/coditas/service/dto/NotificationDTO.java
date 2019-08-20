package com.coditas.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.coditas.domain.Notification} entity.
 */
public class NotificationDTO implements Serializable {

    private String id;

    private String to;

    private String from;

    private String message;

    private Boolean isRead;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationDTO notificationDTO = (NotificationDTO) o;
        if (notificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
            "id=" + getId() +
            ", to='" + getTo() + "'" +
            ", from='" + getFrom() + "'" +
            ", message='" + getMessage() + "'" +
            ", isRead='" + isIsRead() + "'" +
            "}";
    }
}
