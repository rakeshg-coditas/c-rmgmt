package com.coditas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @Field("email")
    private String email;

    @Field("name")
    private String name;

    @Field("picture")
    private String picture;

    @Field("role")
    private String role;

    @Field("skills")
    private String[] skills;

    @Field("isBillable")
    @JsonProperty(value = "billable")
    private String isBillable;

    @Field("joiningDate")
    @JsonProperty(value = "joining_date")
    private String joiningDate;

    @Field("careerStartDate")
    @JsonProperty(value = "career_start_date")
    private String careerStartDate;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(String isBillable) {
        this.isBillable = isBillable;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getCareerStartDate() {
        return careerStartDate;
    }

    public void setCareerStartDate(String careerStartDate) {
        this.careerStartDate = careerStartDate;
    }
}
