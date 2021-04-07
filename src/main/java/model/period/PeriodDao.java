package model.period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeriodDao {
        private JdbcTemplate jdbcTemplate;

        @Autowired
        public void setDataSource(DataSource dataSource) {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }

        public void add(Period period) {
            jdbcTemplate.update("INSERT INTO Period VALUES(?, ?, ?)",
                    period.getId(), period.getStart(), period.getFinish());
        }

        public void update(Period period) {
            jdbcTemplate.update("UPDATE Period SET id =?, start =?, finish =?",
                    period.getId(), period.getStart(), period.getFinish());
        }

        public void delete(Period period) {
            jdbcTemplate.update("DELETE FROM Period WHERE id =?",
                    period.getId());
        }

        public List<Period> get() {
            try {
                return jdbcTemplate.query("SELECT * FROM Period", new PeriodRowMapper());
            }
            catch(EmptyResultDataAccessException e) {
                return new ArrayList<>();
            }
        }
}

