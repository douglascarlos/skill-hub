package br.feevale.presenter.collectionItem;

import br.feevale.model.Model;
import br.feevale.model.Person;

public class PersonPresenter implements Presenter {
    @Override
    public String generate(Model model) {
        Person person = (Person) model;
        return "<li class=\"collection-item avatar\">\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col s12 m6\">\n" +
                "                <i class=\"material-icons circle black-text amber\">person</i>\n" +
                "                <strong class=\"title\">" + model.getName() + "</strong>\n" +
                "                <p>\n" +
                "                    " + person.getEnrollmentNumberFormatted() + " <br>\n" +
                "                    " + person.getEmail() + "\n" +
                "                </p>\n" +
                "            </div>\n" +
                "            <div class=\"col s12 m6\">\n" +
                "                <div class=\"chip\">7 Cometências</div>\n" +
                "                <div class=\"chip\">3 Projetos</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <a href=\"#!\" class=\"secondary-content\"><i class=\"material-icons dropdown-trigger pointer\" data-target='dropdown_action_pp_" + model.getId() + "'>more_vert</i></a>\n" +
                "        <ul id='dropdown_action_pp_" + model.getId() + "' class='dropdown-content'>\n" +
                "            <li><a href=\"/person?action=Edit&id=" + model.getId() + "#general-data\">Dados Gerais</a></li>\n" +
                "            <li><a href=\"/person?action=Edit&id=" + model.getId() + "#skills\">Competências</a></li>\n" +
                "            <li><a href=\"/person?action=Edit&id=" + model.getId() + "#report\">Relatório Gráfico</a></li>\n" +
                "        </ul>\n" +
                "    </li>";
    }
}
