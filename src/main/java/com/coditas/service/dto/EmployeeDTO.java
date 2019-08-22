package com.coditas.service.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.coditas.domain.Employee} entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Serializable {

    private String id;

    private String email;

    private String name;

    private String skills;

    private String projects;

    private String picture;

    private String skill_category;

    private String report_to;

    private String team;

    private LocalDate career_start_date;

    private LocalDate joining_date;

    private String role;

    private String employeeId;

    private String billable;

    private String officeLocation;

    private String employmentType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSkill_category() {
        return skill_category;
    }

    public void setSkill_category(String skill_category) {
        this.skill_category = skill_category;
    }

    public String getReport_to() {
        return report_to;
    }

    public void setReport_to(String report_to) {
        this.report_to = report_to;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public LocalDate getCareer_start_date() {
        return career_start_date;
    }

    public void setCareer_start_date(LocalDate career_start_date) {
        this.career_start_date = career_start_date;
    }

    public LocalDate getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(LocalDate joining_date) {
        this.joining_date = joining_date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", name='" + getName() + "'" +
            ", skills='" + getSkills() + "'" +
            ", projects='" + getProjects() + "'" +
            ", picture='" + getPicture() + "'" +
            ", skill_category='" + getSkill_category() + "'" +
            ", report_to='" + getReport_to() + "'" +
            ", team='" + getTeam() + "'" +
            ", career_start_date='" + getCareer_start_date() + "'" +
            ", joining_date='" + getJoining_date() + "'" +
            ", role='" + getRole() + "'" +
            ", employeeId='" + getEmployeeId() + "'" +
            ", billable='" + getBillable() + "'" +
            ", officeLocation='" + getOfficeLocation() + "'" +
            ", employmentType='" + getEmploymentType() + "'" +
            "}";
    }
}
