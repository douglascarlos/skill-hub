package br.feevale.dao;

import br.feevale.http.validator.Unique;
import br.feevale.model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAO extends DAO implements Unique {

    public ArrayList<Project> list() {
        String sql = "SELECT id, name, description FROM projects WHERE deleted_at IS NULL";
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getLong("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));

                projects.add(project);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return projects;
    }

    public ArrayList<Project> filterByName(String name) {
        String sql = "" +
                "SELECT id, name, description " +
                "FROM projects " +
                "WHERE deleted_at IS NULL AND UPPER(name) like '%" + name.toUpperCase() + "%' " +
                "ORDER BY name";
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getLong("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));

                projects.add(project);
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return projects;
    }

    public void save(Project project) {
        if (project.getId() > 0) {
            this.update(project);
        } else {
            this.store(project);
        }
    }

    public Project find(long id) {
        String sql = "SELECT id, name, description FROM projects WHERE deleted_at IS NULL AND id = ?";

        Project project;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            project = new Project();

            while (rs.next()) {
                project.setId(rs.getLong("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }

        if (project.getId() == 0) {
            throw new RuntimeException("Projeto não encontrado.");
        }

        return project;
    }

    public void delete(Project project) {
        String sql = "" +
                "UPDATE projects SET " +
                "deleted_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, project.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    private void store(Project project) {
        String sql = "INSERT INTO projects (name, description, created_at, updated_at) values (?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    private void update(Project project) {
        String sql = "" +
                "UPDATE projects SET " +
                "name = ?, " +
                "description = ?, " +
                "updated_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setLong(3, project.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean unique(String value, String column) {
        return this.unique(value, column, 0);
    }

    @Override
    public boolean unique(String value, String column, long exceptId) {
        String sql = "SELECT id FROM projects WHERE deleted_at IS NULL AND UPPER(" + column + ") = UPPER(?)";

        Project project = new Project();
        project.setId(exceptId);

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                project.setId(rs.getLong("id"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException exception) {
            System.out.println("-------");
            System.out.println(exception.getMessage());
            System.out.println("-------");
            throw new RuntimeException(exception);
        }
        return project.getId() == exceptId;
    }
}
