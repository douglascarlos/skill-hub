package br.feevale.http.validator.member;

import br.feevale.dao.TagDAO;
import br.feevale.http.validator.Unique;
import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SelectPersonValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SelectPersonValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {
        Unique rule = new TagDAO();

        this.numeric("ID", input.get("id"));
        this.required("Projeto", input.get("project_id"));
        this.required("Função", input.get("role"));
        this.min("Função", input.get("role"));
        this.max("Função", input.get("role"));
        this.required("Início da participação", input.get("start_date"));
        this.required("Fim da participação", input.get("end_date"));
        this.dateGreaterThanOrEqualTo("Fim da participação", input.get("end_date"), "Início da participação", input.get("start_date"));
        return this.isNotValid();
    }

}
