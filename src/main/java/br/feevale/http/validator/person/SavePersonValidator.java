package br.feevale.http.validator.person;

import br.feevale.dao.PersonDAO;
import br.feevale.http.validator.Unique;
import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SavePersonValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SavePersonValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {
        Unique rule = new PersonDAO();

        this.numeric("ID", input.get("id"));
        this.required("Nome", input.get("name"));
        this.min("Nome", input.get("name"));
        this.max("Nome", input.get("name"));
        this.required("Matrícula", input.get("enrollment_number"));
        this.numeric("Matrícula", input.get("enrollment_number"));
        this.exact("Matrícula", input.get("enrollment_number"), 7);

        this.unique("Matrícula", input.get("enrollment_number"), "enrollment_number", input.get("id"), rule);
        this.required("E-mail", input.get("email"));
        this.max("E-mail", input.get("email"));
        this.email("E-mail", input.get("email"));
        this.unique("E-mail", input.get("email"), "email", input.get("id"), rule);
        return this.isNotValid();
    }
}
