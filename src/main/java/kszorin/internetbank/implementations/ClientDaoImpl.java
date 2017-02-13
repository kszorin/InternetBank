package kszorin.internetbank.implementations;

import kszorin.internetbank.dao.ClientDao;
import kszorin.internetbank.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kszorin on 12.02.2017.
 */
@Repository
public class ClientDaoImpl implements ClientDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private PreparedStatementSetter getPreparedStatementSetter(final Client client) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setString(++i, client.getSurname());
                ps.setString(++i, client.getName());
                ps.setString(++i, client.getPatronymic());
                ps.setString(++i, client.getAddress());
            }
        };
    }
    private RowMapper<Client> rowMapper = new RowMapper<Client>() {
        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setId(rs.getInt("id"));
            client.setSurname(rs.getString("surname"));
            client.setName(rs.getString("name"));
            client.setPatronymic(rs.getString("patronymic"));
            client.setAddress(rs.getString("address"));
            return client;
        }
    };

    @Override
    public void insert(Client client) {
        String query = "INSERT INTO clients (surname, name, patronymic, address) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(query, getPreparedStatementSetter(client));
    }

    @Override
    public Client getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM clients WHERE id=?;", rowMapper, id);
    }

    @Override
    public List<Client> getAll() {
        return jdbcTemplate.query("SELECT * FROM clients;", rowMapper);
    }
}
