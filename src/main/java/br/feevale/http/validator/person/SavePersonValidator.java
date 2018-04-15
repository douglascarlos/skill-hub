package br.feevale.http.validator.person;

import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SavePersonValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SavePersonValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {
        this.numeric("ID", input.get("id"));
        this.required("Nome", input.get("name"));
        this.min("Nome", input.get("name"));
        this.max("Nome", input.get("name"));
        this.required("Matrícula", input.get("enrollment_number"));
        this.numeric("Matrícula", input.get("enrollment_number"));
        this.exact("Matrícula", input.get("enrollment_number"), 7);
        this.required("E-mail", input.get("email"));
        this.max("E-mail", input.get("email"));
        this.email("E-mail", input.get("email"));
        return this.isNotValid();
    }
}
