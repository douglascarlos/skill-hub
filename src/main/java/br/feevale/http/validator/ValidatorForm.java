package br.feevale.http.validator;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Map;

public abstract class ValidatorForm {

    private ArrayList<String> errors;

    public abstract boolean validate(Map<String, String> input);

    public boolean isNotValid() {
        return this.errors instanceof ArrayList && !this.errors.isEmpty();
    }

    protected void addError(String error) {
        if (!(this.errors instanceof ArrayList)) {
            this.errors = new ArrayList<String>();
        }
        this.errors.add(error);
    }

    public ArrayList<String> getErrors() {
        ArrayList<String> errors = this.errors;
        this.errors = null;
        return errors;
    }

    public boolean numeric(String attribute, String value) {
        try {
            if (!value.isEmpty()) {
                Long convertedInputId = Long.parseLong(value);
            }
        } catch (Exception exception) {
            this.addError("O campo " + attribute + " deve conter número válido.");
            return false;
        }
        return true;
    }

    public boolean required(String attribute, String value) {
        if (value == null || value.isEmpty()) {
            this.addError("O campo " + attribute + " é obrigatório.");
            return false;
        }
        return true;
    }

    public boolean exact(String attribute, String value, int amount) {
        if (value instanceof String && value.length() != amount) {
            this.addError("O campo " + attribute + " deve conter " + amount + " caracteres.");
            return false;
        }
        return true;
    }

    public boolean min(String attribute, String value, int amount) {
        if (value instanceof String && value.length() < amount) {
            this.addError("O campo " + attribute + " deve conter pelo menos " + amount + " caracteres.");
            return false;
        }
        return true;
    }

    public boolean min(String attribute, String value) {
        return this.min(attribute, value, 3);
    }

    public boolean max(String attribute, String value, int amount) {
        if (value instanceof String && value.length() > amount) {
            this.addError("O campo " + attribute + " deve conter pelo menos " + amount + " caracteres.");
            return false;
        }
        return true;
    }

    public boolean max(String attribute, String value) {
        return this.max(attribute, value, 255);
    }

    public boolean email(String attribute, String value) {
        if (!EmailValidator.getInstance().isValid(value)) {
            this.addError("O campo " + attribute + " deve conter um e-mail válido.");
            return false;
        }
        return true;
    }

    public boolean unique(String attribute, String value, String column, String exceptId, Unique rule) {
        long convertedInputId = 0;
        try {
            if (!exceptId.isEmpty()) {
                convertedInputId = Long.parseLong(exceptId);
            }
        } catch (Exception exception) {
            return true;
        }

        if (!rule.unique(value, column, convertedInputId)) {
            this.addError("O campo " + attribute + " possui um valor já registrado.");
            return false;
        }
        return true;
    }


}
