package net.javaguides.springboot.demo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MysqlConDiffblueTest {
    /**
     * Method under test: {@link MysqlCon#getResultSet(Connection, String)}
     */
    @Test
    void testGetResultSet() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(Mockito.<String>any())).thenReturn(mock(ResultSet.class));
        Connection con = mock(Connection.class);
        when(con.createStatement()).thenReturn(statement);

        // Act
        MysqlCon.getResultSet(con, "Query");

        // Assert
        verify(con).createStatement();
        verify(statement).executeQuery(eq("Query"));
    }

    /**
     * Method under test: {@link MysqlCon#getResultSet(Connection, String)}
     */
    @Test
    void testGetResultSet2() throws SQLException {
        // Arrange
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(Mockito.<String>any())).thenThrow(new SQLException());
        Connection con = mock(Connection.class);
        when(con.createStatement()).thenReturn(statement);

        // Act and Assert
        assertThrows(SQLException.class, () -> MysqlCon.getResultSet(con, "Query"));
        verify(con).createStatement();
        verify(statement).executeQuery(eq("Query"));
    }

    /**
     * Method under test: {@link MysqlCon#getResultSet(Connection, String)}
     */
    @Test
    void testGetResultSet3() throws SQLException {
        // Arrange
        Connection con = mock(Connection.class);
        when(con.createStatement()).thenThrow(new SQLException());

        // Act and Assert
        assertThrows(SQLException.class, () -> MysqlCon.getResultSet(con, "Query"));
        verify(con).createStatement();
    }
}
