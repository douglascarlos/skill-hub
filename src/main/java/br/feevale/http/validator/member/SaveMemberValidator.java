package br.feevale.http.validator.member;

import br.feevale.dao.TagDAO;
import br.feevale.http.validator.Unique;
import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SaveMemberValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SaveMemberValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {
        Unique rule = new TagDAO();

        this.required("Projeto", input.get("project_id"));
        this.required("Pessoa", input.get("person_id"));
        this.required("Função", input.get("role"));
        this.min("Função", input.get("role"));
        this.max("Função", input.get("role"));
        this.required("Início da participação", input.get("start_date"));
        this.required("Fim da participação", input.get("end_date"));
        this.dateGreaterThanOrEqualTo("Fim da participação", input.get("end_date"), "Início da participação", input.get("start_date"));
        return this.isNotValid();
    }

    public boolean validateSkills(String[] value) {
        if (value == null || value.length == 0) {
            this.addError("Deve ser selecionado pelo menos uma Competência.");
            return false;
        }
        return true;
    }


}
