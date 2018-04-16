package br.feevale.http.validator.tag;

import br.feevale.dao.TagDAO;
import br.feevale.http.validator.Unique;
import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SaveTagValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SaveTagValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {
        Unique rule = new TagDAO();

        this.numeric("ID", input.get("id"));
        this.required("Nome", input.get("name"));
        this.min("Nome", input.get("name"));
        this.max("Nome", input.get("name"));
        this.unique("Nome", input.get("name"), "name", input.get("id"), rule);

        return this.isNotValid();
    }
}
