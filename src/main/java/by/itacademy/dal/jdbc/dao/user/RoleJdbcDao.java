package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.dal.jdbc.BaseAbstractJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.mapper.user.RoleResultSetMapper;
import by.itacademy.dal.jdbc.query.user.RoleJdbcSqlQueryHolder;
import by.itacademy.exception.DaoException;
import by.itacademy.model.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleJdbcDao extends BaseAbstractJdbcDao {

    public RoleJdbcDao(Connector connector) {
        super(connector);
    }

    public Role getById(int id) throws DaoException {
        try(Connection connection = getConnector().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getByIdSql());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getResultSetMapper().processResultSetMapping(rs);
                }
                throw new DaoException("Invalid entity id: " + id);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }
    public Role getByRoleName(String role) throws DaoException {
        try(Connection connection = getConnector().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getGetByNameSql());
            ps.setString(1, role);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getResultSetMapper().processResultSetMapping(rs);
                }
                throw new DaoException("Invalid entity name: " + role);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }


    private RoleJdbcSqlQueryHolder getSqlHolder() {
        return new RoleJdbcSqlQueryHolder();
    }
    
   private RoleResultSetMapper getResultSetMapper() {
        return new RoleResultSetMapper();
   }
}
