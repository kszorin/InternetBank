package kszorin.internetbank.implementations;

import kszorin.internetbank.dao.TransactionDao;
import kszorin.internetbank.models.Transaction;
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
 * Created by kszorin on 13.02.2017.
 */

@Repository
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private PreparedStatementSetter getPreparedStatementSetter(final Transaction transaction) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setInt(++i, transaction.getId());
                ps.setInt(++i, transaction.getIdBillSender());
                ps.setInt(++i, transaction.getIdBillRecipient());
                ps.setFloat(++i, transaction.getAmount());
            }
        };
    }
    private RowMapper<Transaction> rowMapper = new RowMapper<Transaction>() {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setIdBillSender(rs.getInt("id_bill_sender"));
            transaction.setIdBillRecipient(rs.getInt("id_bill_recipient"));
            transaction.setAmount(rs.getFloat("amount"));
            return transaction;
        }
    };

    @Override
    public void insert(Transaction transaction) {
        String query = "INSERT INTO transactions (id_bill_sender, id_bill_recipient, amount) VALUES (?, ?, ?);";
        jdbcTemplate.update(query, getPreparedStatementSetter(transaction));
    }

    @Override
    public Transaction getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM transactions WHERE id=?;", rowMapper, id);
    }

    @Override
    public List<Transaction> getAll() {
        return jdbcTemplate.query("SELECT * FROM transactions", rowMapper);
    }

    @Override
    public List<Transaction> getAllByIdBill(Integer idBill) {
        return jdbcTemplate.query("SELECT * FROM transactions WHERE (id_bill_sender='1') OR (id_bill_recipient='1');", rowMapper, idBill);
    }

    @Override
    public List<Transaction> getAllByIdClient(Integer idClient) {
        return null;
    }
}
