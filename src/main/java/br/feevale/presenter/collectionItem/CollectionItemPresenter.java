package br.feevale.presenter.collectionItem;

import br.feevale.model.Model;
import br.feevale.model.Person;
import br.feevale.model.Project;
import br.feevale.model.Tag;

public class CollectionItemPresenter {

    private PersonPresenter personPresenter;
    private ProjectPresenter projectPresenter;
    private TagPresenter tagPresenter;

    public CollectionItemPresenter() {
        this.personPresenter = new PersonPresenter();
        this.projectPresenter = new ProjectPresenter();
        this.tagPresenter = new TagPresenter();
    }

    public String generate(Model model){
        if(model instanceof Person){
            return (new PersonPresenter()).generate(model);
        }else if(model instanceof Tag){
            return (new TagPresenter()).generate(model);
        }else if(model instanceof Project){
            return (new ProjectPresenter()).generate(model);
        }else {
            throw new RuntimeException("Objeto inválido para transformar em collection-item.");
        }
    }
}
