package br.feevale.http.validator.search;

import br.feevale.dao.TagDAO;
import br.feevale.http.validator.Unique;
import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SearchValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SearchValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {

        this.required("Filtro", input.get("filter"));
        this.min("Filtro", input.get("filter"));
        this.max("Filtro", input.get("filter"));

        return this.isNotValid();
    }

    public boolean validateFilterByModel(String[] value) {
        if (value == null || value.length == 0) {
            this.addError("Você deve informar pelo o que está procurando.");
            return false;
        }
        return true;
    }
}
