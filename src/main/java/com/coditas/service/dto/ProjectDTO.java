package com.coditas.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * A DTO for the {@link com.coditas.domain.Project} entity.
 */
public class ProjectDTO implements Serializable {

    private String id;

    private String name;

    private String client;

    private String billing_type;

    private String team;

    private String lead;

    private String[] technologies;

    private LocalDate start_date;

    private LocalDate end_date;


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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBilling_type() {
        return billing_type;
    }

    public void setBilling_type(String billing_type) {
        this.billing_type = billing_type;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String[] getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String[] technologies) {
        this.technologies = technologies;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectDTO projectDTO = (ProjectDTO) o;
        if (projectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
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
