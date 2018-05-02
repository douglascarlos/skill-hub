package br.feevale.model;

public abstract class Model {

    private long id;
    private String name;

    /**
     * TODO: extrair classe ModelWithName, pois há Model que não exige name. Ex: Skill
     */
    public Model(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean exists(){
        return this.id > 0;
    }
}
