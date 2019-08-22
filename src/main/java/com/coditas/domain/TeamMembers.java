package com.coditas.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A TeamMembers.
 */
@Document(collection = "team_members")
public class TeamMembers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("lead")
    private String lead;

    @Field("members")
    private String[] members;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public TeamMembers name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLead() {
        return lead;
    }

    public TeamMembers lead(String lead) {
        this.lead = lead;
        return this;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String[] getMembers() {
        return members;
    }

    public TeamMembers members(String[] members) {
        this.members = members;
        return this;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeamMembers)) {
            return false;
        }
        return id != null && id.equals(((TeamMembers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TeamMembers{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lead='" + getLead() + "'" +
            ", members='" + getMembers() + "'" +
            "}";
    }
}
