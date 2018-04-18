package br.feevale.model;

import br.feevale.helper.Formatter;

public class Person extends Model {

    private String email;
    private int enrollmentNumber;

    public Person() {
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

    public String getEnrollmentNumberFormatted(){
        return Formatter.paddingLeft(this.enrollmentNumber, 7, "0");
    }
}
