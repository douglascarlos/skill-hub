package br.feevale.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Member extends Model {

    private Person person;
    private Project project;
    private String role;
    private Date startDate;
    private Date endDate;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartDateFormatted(){
        SimpleDateFormat mask = new SimpleDateFormat("dd/MM/yyyy");
        return mask.format(this.startDate);
    }

    public String getEndDateFormatted(){
        SimpleDateFormat mask = new SimpleDateFormat("dd/MM/yyyy");
        return mask.format(this.endDate);
    }
}
