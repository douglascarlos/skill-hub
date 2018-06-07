package br.feevale.presenter.collectionItem;

import br.feevale.model.Model;

public class ProjectPresenter implements Presenter {
    @Override
    public String generate(Model model) {
        return "<li class=\"collection-item avatar\">\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col s12 m6\">\n" +
                "                <i class=\"material-icons circle black-text amber\">description</i>\n" +
                "                <strong class=\"title\">" + model.getName() + "</strong>\n" +
                "            </div>\n" +
//                "            <div class=\"col s12 m6\">\n" +
//                "                <div class=\"chip\">7 Pessoas</div>\n" +
//                "                <div class=\"chip\">19 Competências</div>\n" +
//                "            </div>\n" +
                "        </div>\n" +
                "        <a href=\"#!\" class=\"secondary-content\"><i class=\"material-icons dropdown-trigger pointer\" data-target='dropdown_action_pj_" + model.getId() + "'>more_vert</i></a>\n" +
                "        <ul id='dropdown_action_pj_" + model.getId() + "' class='dropdown-content-index-project'>\n" +
                "            <li><a href=\"/project?action=Edit&id=" + model.getId() + "#general-data\">Dados Gerais</a></li>\n" +
                "            <li><a href=\"/project?action=Edit&id=" + model.getId() + "#members\">Membros</a></li>\n" +
                "            <li><a href=\"/member?action=Create&project_id=" + model.getId() + "\">Adicionar Membro</a></li>\n" +
                "            <li><a href=\"/project?action=Edit&id=" + model.getId() + "#report\">Relatório Gráfico</a></li>\n" +
                "        </ul>\n" +
                "    </li>";
    }
}
