package br.com.fiap.teleajuda.infrastructure.persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {

    Connection getConnection() throws SQLException;
}
