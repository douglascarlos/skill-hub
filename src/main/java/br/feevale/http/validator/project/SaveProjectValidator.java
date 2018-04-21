package br.feevale.http.validator.project;

import br.feevale.dao.ProjectDAO;
import br.feevale.http.validator.Unique;
import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SaveProjectValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SaveProjectValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {
        Unique rule = new ProjectDAO();

        this.numeric("ID", input.get("id"));

        this.required("Nome", input.get("name"));
        this.min("Nome", input.get("name"));
        this.max("Nome", input.get("name"));
        this.unique("Nome", input.get("name"), "name", input.get("id"), rule);

        this.max("Decrição", input.get("decription"), 500);
        return this.isNotValid();
    }
}
