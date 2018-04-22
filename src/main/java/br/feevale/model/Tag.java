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
}
