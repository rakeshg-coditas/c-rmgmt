package com.coditas.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.coditas.domain.TeamMembers} entity.
 */
public class TeamMembersDTO implements Serializable {

    private String id;

    private String name;

    private String lead;

    private String[] members;


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

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeamMembersDTO teamMembersDTO = (TeamMembersDTO) o;
        if (teamMembersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teamMembersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeamMembersDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lead='" + getLead() + "'" +
            ", members='" + getMembers() + "'" +
            "}";
    }
}
