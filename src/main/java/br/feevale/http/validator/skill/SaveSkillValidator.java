package br.feevale.http.validator.skill;

import br.feevale.http.validator.ValidatorForm;

import java.util.Map;

public class SaveSkillValidator extends ValidatorForm {

    public static ValidatorForm getInstance() {
        return new SaveSkillValidator();
    }

    @Override
    public boolean validate(Map<String, String> input) {

        this.numeric("ID", input.get("skill_id"));

        this.required("Pessoa", input.get("person_id"));
        this.numeric("Pessoa", input.get("person_id"));

        this.required("Tag", input.get("tag_id"));
        this.numeric("Tag", input.get("tag_id"));

        this.required("Nível", input.get("level_id"));
        this.numeric("Nível", input.get("level_id"));

        return this.isNotValid();
    }

}
