package br.feevale.dao;

import br.feevale.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDAO extends DAO {

    private SkillDAO skillDAO;

    public MemberDAO(){
        super();
        this.skillDAO = new SkillDAO();
    }

    public List<Member> listByProject(Project projectFilter){
        String sql = "SELECT " +
                "m.id, m.role, m.start_date, m.end_date, " +
                "m.person_id, p.name as person_name, p.enrollment_number, p.email, " +
                "m.project_id, pj.name as project_name, pj.description " +
                "FROM members m " +
                "LEFT JOIN people p ON p.id = m.person_id " +
                "LEFT JOIN projects pj ON pj.id = m.project_id " +
                "WHERE m.project_id = ? " +
                "ORDER BY p.name";

        ArrayList<Member> members = new ArrayList<Member>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, projectFilter.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setRole(rs.getString("role"));
                member.setStartDate(rs.getDate("start_date"));
                member.setEndDate(rs.getDate("end_date"));

                Person person = new Person();
                person.setId(rs.getLong("person_id"));
                person.setName(rs.getString("person_name"));
                person.setEmail(rs.getString("email"));
                person.setEnrollmentNumber(rs.getInt("enrollment_number"));

                Project project = new Project();
                project.setId(rs.getLong("id"));
                project.setName(rs.getString("project_name"));
                project.setDescription(rs.getString("description"));

                member.setPerson(person);
                member.setProject(project);

                members.add(member);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return members;
    }

    public void save(Member member){
        if(member.getId() > 0){
//            this.update(member);
        }else{
            this.store(member);
        }
    }

    private void store(Member member){
        String sql = "INSERT INTO members (person_id, project_id, role, start_date, end_date) values (?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, member.getPerson().getId());
            stmt.setLong(2, member.getProject().getId());
            stmt.setString(3, member.getRole());
            stmt.setDate(4, java.sql.Date.valueOf(member.getStartDate()));
            stmt.setDate(5, java.sql.Date.valueOf(member.getEndDate()));

            stmt.execute();
            stmt.close();

            member.setId(this.lastId());
            this.savaSkills(member);

        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    public long lastId(){
        String sql = "SELECT lastval() as id";

        long lastId = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                lastId = rs.getLong("id");
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }

        if(lastId == 0){
            throw new RuntimeException("Erro ao salvar novo membro do projeto.");
        }

        return lastId;
    }

    public void savaSkills(Member member){
        for (Skill skill : member.getSkills()){
            try {
                String sql = "INSERT INTO member_skill (skill_id, member_id) VALUES (?,?)";

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setLong(1, skill.getId());
                stmt.setLong(2, member.getId());

                stmt.execute();
                stmt.close();

            } catch (SQLException exception) {
                System.out.println("-------");
                System.out.println(exception.getMessage());
                System.out.println("-------");
                throw new RuntimeException(exception);
            }
        }
    }

    public Member find(long id){
        return this.find(id, true);
    }

    private Member find(long id, boolean withSkills){
        String sql =
                "SELECT " +
                "m.id, m.role, m.start_date, m.end_date, " +
                "m.project_id, pj.name as project_name, pj.description, " +
                "m.person_id, ps.name as person_name, ps.email, ps.enrollment_number " +
                "FROM members m " +
                "JOIN projects pj ON pj.id = m.project_id " +
                "JOIN people ps ON ps.id = m.person_id " +
                "WHERE m.id = ?";

        Member member;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            member = new Member();

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getLong("project_id"));
                project.setName(rs.getString("project_name"));
                project.setDescription(rs.getString("description"));

                Person person = new Person();
                person.setId(rs.getLong("person_id"));
                person.setName(rs.getString("person_name"));
                person.setEmail(rs.getString("email"));
                person.setEnrollmentNumber(rs.getInt("enrollment_number"));

                member.setId(rs.getLong("id"));
                member.setRole(rs.getString("role"));
                member.setStartDate(rs.getDate("start_date"));
                member.setEndDate(rs.getDate("end_date"));
                member.setProject(project);
                member.setPerson(person);

                if(withSkills){
                    member.setSkills(this.skillDAO.listByMember(member));
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }

        if(member.getId() == 0){
            throw new RuntimeException("Membro não encontrado.");
        }

        return member;
    }

}
