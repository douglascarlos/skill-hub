package br.feevale.presenter.collectionItem;

import br.feevale.model.Model;

public class TagPresenter implements Presenter {
    @Override
    public String generate(Model model) {
        return "<li class=\"collection-item avatar\">\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col s12 m6\">\n" +
                "                <i class=\"material-icons circle black-text amber\">label</i>\n" +
                "                <strong class=\"title\">" + model.getName() + "</strong>\n" +
                "            </div>\n" +
//                "            <div class=\"col s12 m6\">\n" +
//                "                <div class=\"chip\">5 Sub-Tags</div>\n" +
//                "                <div class=\"chip\">7 Pessoas</div>\n" +
//                "                <div class=\"chip\">19 Projetos</div>\n" +
//                "            </div>\n" +
                "        </div>\n" +
                "        <a href=\"#!\" class=\"secondary-content\"><i class=\"material-icons dropdown-trigger pointer\" data-target='dropdown_action_tg_" + model.getId() + "'>more_vert</i></a>\n" +
                "        <ul id='dropdown_action_tg_" + model.getId() + "' class='dropdown-content'>\n" +
                "            <li><a href=\"/tag?action=Edit&id=" + model.getId() + "#general-data\">Dados Gerais</a></li>\n" +
                "            <li><a href=\"/tag?action=Edit&id=" + model.getId() + "#attach-tags\">Sub-Tags</a></li>\n" +
                "        </ul>\n" +
                "    </li>";
    }
}
