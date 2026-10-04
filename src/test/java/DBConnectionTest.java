import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void connectionShouldNotBeNull()
            throws Exception {

        Connection conn =
                DBConnection.getConnection();

        assertNotNull(conn);

        conn.close();
    }
}