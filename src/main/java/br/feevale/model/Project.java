package br.feevale.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends Model {

    private String description;
    private List<Member> members;
    private List<Skill> skills;

    public Project(){
        this.members = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Member getMemberById(long id){
        Member member = null;
        for(Member memberFromProject : this.getMembers()){
            if(memberFromProject.getId() == id){
                member = memberFromProject;
            }
        }
        return member;
    }
}
