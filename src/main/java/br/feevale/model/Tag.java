package br.feevale.model;

import java.util.ArrayList;

public class Tag extends Model {

    private ArrayList<Tag> children;

    public Tag() {
        this.children = new ArrayList<Tag>();
    }

    public ArrayList<Tag> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tag> children) {
        this.children = children;
    }

    public String toItemTreeView(){
        String item = "{";
        item += "id: '" + this.getId() + "',";
        item += "text: '" + this.getName() + "',";
        if(!this.getChildren().isEmpty()){
            item += "nodes: [";
            for(Tag tag : this.getChildren()){
                item += tag.toItemTreeView();
            }
            item += "],";
//            item += "tags: ['<span class=\"new badge amber\" data-badge-caption=\"\">" + this.getChildren().size() + "</span>'],";
        }
        item += "},";
        return item;
    }

    public String toModalOptions(){
        String modal = "<div id=\"modal_options_" + this.getId() + "\" class=\"modal\">\n" +
                "        <div class=\"modal-content\">\n" +
                "            <p>O que você deseja fazer?</p>\n" +
                "        </div>\n" +
                "        <div class=\"modal-footer\">\n" +
                "            <button class=\"btn modal-action modal-close waves-effect waves-red btn-mr\">Cancelar<i class=\"material-icons right\">cancel</i></button>\n" +
                "            <button class=\"btn modal-action modal-close waves-effect waves-red btn-mr btn_delete\" data-modal=\"modal_delete_" + this.getId() + "\">Excluir<i class=\"material-icons right\">delete</i></button>\n" +
                "            <a href=\"/tag?action=Edit&id=" + this.getId() + "#attach-tags\" class=\"btn modal-action modal-close waves-effect waves-green\">Sub-Tags<i class=\"material-icons right\">label</i></a>\n" +
                "            <a href=\"/tag?action=Edit&id=" + this.getId() + "#general-data\" class=\"btn modal-action modal-close waves-effect waves-green\">Dados Gerais<i class=\"material-icons right\">subject</i></a>\n" +
                "        </div>\n" +
                "    </div>";
        if(!this.getChildren().isEmpty()){
            for(Tag tag : this.getChildren()){
                modal += tag.toModalOptions();
            }
        }
        return modal;
    }

    public String toModalDelete(){
        String modal = "<div id=\"modal_delete_" + this.getId() + "\" class=\"modal\">\n" +
                "        <div class=\"modal-content\">\n" +
                "            <p>Você tem certeza que deseja <strong>excluir</strong> esta tag do sistema?</p>\n" +
                "        </div>\n" +
                "        <div class=\"modal-footer\">\n" +
                "            <button class=\"btn modal-action modal-close waves-effect waves-red btn-mr\">Cancelar<i class=\"material-icons right\">cancel</i></button>\n" +
                "            <a href=\"/tag?action=Delete&id=" + this.getId() + "\" class=\"btn modal-action modal-close waves-effect waves-green\">Confirmar<i class=\"material-icons right\">check</i></a>\n" +
                "        </div>\n" +
                "    </div>";
        if(!this.getChildren().isEmpty()){
            for(Tag tag : this.getChildren()){
                modal += tag.toModalDelete();
            }
        }
        return modal;
    }
}
