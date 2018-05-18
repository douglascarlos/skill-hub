package br.feevale.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member extends Model {

    private Person person;
    private Project project;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Skill> skills;

    public Member(){
        this.skills = new ArrayList<>();
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

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

    public void setStartDate(String startDate) {
        if(!startDate.isEmpty()){
            DateTimeFormatter mask = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.startDate = LocalDate.parse(startDate, mask);
        }
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

    public void setEndDate(String endDate) {
        if(!endDate.isEmpty()){
            DateTimeFormatter mask = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.endDate = LocalDate.parse(endDate, mask);
        }
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
