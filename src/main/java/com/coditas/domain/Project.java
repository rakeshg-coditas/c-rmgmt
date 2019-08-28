package com.coditas.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * A Project.
 */
@Document(collection = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("client")
    private String client;

    @Field("billing_type")
    private String billing_type;

    @Field("team")
    private String team;

    @Field("lead")
    private String lead;

    @Field("technologies")
    private String[] technologies;

    @Field("start_date")
    private LocalDate start_date;

    @Field("end_date")
    private LocalDate end_date;

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

    public Project() {
    }

    public Project name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public Project client(String client) {
        this.client = client;
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBilling_type() {
        return billing_type;
    }

    public Project billing_type(String billing_type) {
        this.billing_type = billing_type;
        return this;
    }

    public void setBilling_type(String billing_type) {
        this.billing_type = billing_type;
    }

    public String getTeam() {
        return team;
    }

    public Project team(String team) {
        this.team = team;
        return this;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getLead() {
        return lead;
    }

    public Project lead(String lead) {
        this.lead = lead;
        return this;
    }

    public Project(String[] technologies) {
        this.technologies = technologies;
    }

    public String[] getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String[] technologies) {
        this.technologies = technologies;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public Project start_date(LocalDate start_date) {
        this.start_date = start_date;
        return this;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public Project end_date(LocalDate end_date) {
        this.end_date = end_date;
        return this;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "Project{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", client='" + client + '\'' +
            ", billing_type='" + billing_type + '\'' +
            ", team='" + team + '\'' +
            ", lead='" + lead + '\'' +
            ", technologies=" + Arrays.toString(technologies) +
            ", start_date=" + start_date +
            ", end_date=" + end_date +
            '}';
    }
}
