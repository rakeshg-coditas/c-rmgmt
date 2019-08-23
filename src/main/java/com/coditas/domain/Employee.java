package com.coditas.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Employee.
 */
@Document(collection = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("email")
    private String email;

    @Field("name")
    private String name;

    @Field("skills")
    private String[] skills;

    @Field("projects")
    private String[] projects;

    @Field("picture")
    private String picture;

    @Field("skill_category")
    private String skill_category;

    @Field("report_to")
    private String report_to;

    @Field("team")
    private String team;

    @Field("career_start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDate career_start_date;

    @Field("joining_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDate joining_date;

    @Field("role")
    private String role;

    @Field("employee_id")
    private String employeeId;

    @Field("billable")
    private String billable;

    @Field("office_location")
    private String officeLocation;

    @Field("employment_type")
    private String employmentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSkills() {
        return skills;
    }

    public Employee skills(String[] skills) {
        this.skills = skills;
        return this;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getProjects() {
        return projects;
    }

    public Employee projects(String[] projects) {
        this.projects = projects;
        return this;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }

    public String getPicture() {
        return picture;
    }

    public Employee picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSkill_category() {
        return skill_category;
    }

    public Employee skill_category(String skill_category) {
        this.skill_category = skill_category;
        return this;
    }

    public void setSkill_category(String skill_category) {
        this.skill_category = skill_category;
    }

    public String getReport_to() {
        return report_to;
    }

    public Employee report_to(String report_to) {
        this.report_to = report_to;
        return this;
    }

    public void setReport_to(String report_to) {
        this.report_to = report_to;
    }

    public String getTeam() {
        return team;
    }

    public Employee team(String team) {
        this.team = team;
        return this;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public LocalDate getCareer_start_date() {
        return career_start_date;
    }

    public Employee career_start_date(LocalDate career_start_date) {
        this.career_start_date = career_start_date;
        return this;
    }

    public void setCareer_start_date(LocalDate career_start_date) {
        this.career_start_date = career_start_date;
    }

    public LocalDate getJoining_date() {
        return joining_date;
    }

    public Employee joining_date(LocalDate joining_date) {
        this.joining_date = joining_date;
        return this;
    }

    public void setJoining_date(LocalDate joining_date) {
        this.joining_date = joining_date;
    }

    public String getRole() {
        return role;
    }

    public Employee role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Employee employeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBillable() {
        return billable;
    }

    public Employee billable(String billable) {
        this.billable = billable;
        return this;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public Employee officeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
        return this;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public Employee employmentType(String employmentType) {
        this.employmentType = employmentType;
        return this;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employee{" +
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
