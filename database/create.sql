CREATE TABLE people (
  id SERIAL NOT NULL,
  name VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  enrollment_number INTEGER NOT NULL,
  created_at DATE NOT NULL,
  updated_at DATE NOT NULL,
  deleted_at DATE NULL,
  PRIMARY KEY(id)
);

CREATE TABLE projects (
  id SERIAL NOT NULL,
  name VARCHAR NOT NULL,
  description TEXT NOT NULL,
  created_at DATE NOT NULL,
  updated_at DATE NOT NULL,
  deleted_at DATE NULL,
  PRIMARY KEY(id)
);

CREATE TABLE tags (
  id SERIAL NOT NULL,
  name VARCHAR NOT NULL,
  created_at DATE NOT NULL,
  updated_at DATE NOT NULL,
  deleted_at DATE NULL,
  PRIMARY KEY(id)
);

CREATE TABLE levels (
  id SERIAL NOT NULL,
  name VARCHAR NOT NULL,
  number INTEGER NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE members (
  id SERIAL NOT NULL,
  person_id INTEGER NOT NULL,
  project_id INTEGER NOT NULL,
  role VARCHAR NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NULL,
  PRIMARY KEY(id),
  INDEX members_project_id(project_id),
  INDEX members_person_id(person_id),
  FOREIGN KEY(project_id)
    REFERENCES projects(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(person_id)
    REFERENCES people(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE skills (
  id SERIAL NOT NULL,
  level_id INTEGER NOT NULL,
  tag_id INTEGER NOT NULL,
  person_id INTEGER NOT NULL,
  created_at DATE NOT NULL,
  updated_at DATE NOT NULL,
  deleted_at DATE NULL,
  PRIMARY KEY(id),
  INDEX skills_person_id(person_id),
  INDEX skills_tag_id(tag_id),
  INDEX skills_level_id(level_id),
  FOREIGN KEY(person_id)
    REFERENCES people(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(tag_id)
    REFERENCES tags(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(level_id)
    REFERENCES levels(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE member_skill (
  id SERIAL NOT NULL,
  skill_id INTEGER NOT NULL,
  member_id INTEGER NOT NULL,
  PRIMARY KEY(id),
  INDEX member_skill_member_id(member_id),
  INDEX member_skill_skill_id(skill_id),
  FOREIGN KEY(member_id)
    REFERENCES members(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(skill_id)
    REFERENCES skills(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


