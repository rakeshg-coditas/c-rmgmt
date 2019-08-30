package com.coditas.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.coditas.domain.Client} entity.
 */
public class ClientDTO implements Serializable {

    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String name;

    @NotNull
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    private String address;

    private Boolean isActive;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (clientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
