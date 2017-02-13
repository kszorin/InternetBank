package kszorin.internetbank.implementations;

import kszorin.internetbank.dao.BillDao;
import kszorin.internetbank.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.InterruptibleBatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kszorin on 12.02.2017.
 */
@Repository
public class BillDaoImpl implements BillDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    private RowMapper<Bill> rowMapper = new RowMapper<Bill>() {
        @Override
        public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bill bill = new Bill();
            bill.setId(rs.getInt("id"));
            bill.setIdClient(rs.getInt("id_client"));
            bill.setSum(rs.getFloat("sum"));
            return bill;
        }
    };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private PreparedStatementSetter getPreparedStatementSetter(final Bill bill) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setInt(++i, bill.getIdClient());
                ps.setFloat(++i, bill.getSum());
            }
        };
    }

    @Override
    public void insert(Bill bill) {
        String query = "INSERT INTO bills (id_client, sum) VALUES (?, ?);";
        jdbcTemplate.update(query, getPreparedStatementSetter(bill));
    }

    @Override
    public Bill getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM bills WHERE id=?;", rowMapper, id);
    }

    @Override
    public List<Bill> getAll() {
        return jdbcTemplate.query("SELECT * FROM bills;", rowMapper);
    }

    @Override
    public List<Bill> getAllByIdClient(Integer idClient) {
        return jdbcTemplate.query("SELECT * FROM bills WHERE id_client=?;", rowMapper, idClient);
    }

    @Override
    public List<Integer> getBillIdsByIdClient(Integer idClient) {
        List<Bill> listBills=jdbcTemplate.query("SELECT * FROM bills WHERE id_client=?;", rowMapper, idClient);
        List<Integer> listIds = new ArrayList<Integer>();
        //копируем в отдельный список только id
        for (Bill bill: listBills)
            listIds.add(bill.getId());
        return listIds;
    }
}
