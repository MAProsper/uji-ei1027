package dao.area;

import model.AreaComment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaCommentRowMapper implements RowMapper<AreaComment> {

    public AreaComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        AreaComment areaComment = new AreaComment();
        areaComment.setId(rs.getInt("id"));
        areaComment.setArea(rs.getInt("area"));
        areaComment.setComment(rs.getString("comment"));
        return areaComment;
    }
}