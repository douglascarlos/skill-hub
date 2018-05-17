package br.feevale.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Member extends Model {

    private Person person;
    private Project project;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = LocalDate.of(startDate.getYear()+1900, startDate.getMonth()+1, startDate.getDate());
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = LocalDate.of(endDate.getYear()+1900, endDate.getMonth()+1, endDate.getDate());
    }

    public String getStartDateFormatted(){
        if(this.startDate == null){
            return "";
        }
        DateTimeFormatter mask = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.startDate.format(mask);
    }

    public String getEndDateFormatted(){
        if(this.endDate == null){
            return "";
        }
        DateTimeFormatter mask = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.endDate.format(mask);
    }

    public String getStartDateFormattedUrl(){
        if(this.startDate == null){
            return "";
        }
        DateTimeFormatter dayMask = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter monthMask = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter yearMask = DateTimeFormatter.ofPattern("yyyy");
        return this.startDate.format(dayMask) + "%2F" +
                this.startDate.format(monthMask) + "%2F" +
                this.startDate.format(yearMask);
    }

    public String getEndDateFormattedUrl(){
        if(this.endDate == null){
            return "";
        }
        DateTimeFormatter dayMask = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter monthMask = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter yearMask = DateTimeFormatter.ofPattern("yyyy");
        return this.endDate.format(dayMask) + "%2F" +
                this.endDate.format(monthMask) + "%2F" +
                this.endDate.format(yearMask);
    }
}
