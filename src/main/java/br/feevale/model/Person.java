package br.feevale.model;

import br.feevale.helper.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Person extends Model {

    private String email;
    private int enrollmentNumber;
    private List<Skill> skills;

    public Person() {
        this.skills = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(int enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getEnrollmentNumberFormatted(){
        return Formatter.paddingLeft(this.enrollmentNumber, 7, "0");
    }
}
