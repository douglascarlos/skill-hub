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
        item += "text: '" + this.getName() + "',";
        if(!this.getChildren().isEmpty()){
            item += "nodes: [";
            for(Tag tag : this.getChildren()){
                item += tag.toItemTreeView();
            }
            item += "],";
            item += "tags: ['<span class=\"new badge amber\" data-badge-caption=\"\">" + this.getChildren().size() + "</span>'],";
        }
        item += "},";
        return item;
    }
}
