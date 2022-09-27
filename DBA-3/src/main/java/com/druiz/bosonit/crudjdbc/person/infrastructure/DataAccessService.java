package com.druiz.bosonit.crudjdbc.person.infrastructure;

import com.druiz.bosonit.crudjdbc.person.domain.Person;
import com.druiz.bosonit.crudjdbc.person.domain.PersonDAO;
import com.druiz.bosonit.crudjdbc.person.domain.PersonMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataAccessService implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public DataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> allPerson() {
        var sql = """
                SELECT id, name, surname, mail
                FROM person
                LIMIT 100;
                 """;
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    @Override
    public int insertPerson(Person person) {
        var sql = """
                INSERT INTO person(name, surname, mail)
                VALUES (?, ?, ? );
                 """;
        return jdbcTemplate.update(
                sql,
                person.name(), person.surname(), person.mail()
        );
    }

    @Override
    public int deletePerson(int id) {
        var sql = """
                DELETE FROM person WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Person> selectPersonById(int id) {
        var sql = """
                SELECT id, name, surname, mail
                FROM person
                WHERE id = ?
                 """;
        return jdbcTemplate.query(sql, new PersonMapper(), id)
                .stream()
                .findFirst();
    }

}