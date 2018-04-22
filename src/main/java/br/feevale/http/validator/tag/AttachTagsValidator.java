package br.feevale.http.validator.tag;

import br.feevale.dao.TagDAO;
import br.feevale.http.validator.Unique;
import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class AttachTagsValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new AttachTagsValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {
        Unique rule = new TagDAO();

        this.numeric("ID", input.get("id"));
        this.required("ID", input.get("id"));

        return this.isNotValid();
    }
}
