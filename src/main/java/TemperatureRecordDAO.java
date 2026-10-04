import java.sql.Connection;
import java.sql.PreparedStatement;

public class TemperatureRecordDAO {

    public void save(
            TemperatureRecord record
    ) {

        String sql = """
            INSERT INTO temperature_record
            (input_value,result_value,unit_id)
            VALUES(?,?,?)
            """;

        try(
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setDouble(
                    1,
                    record.getInputValue()
            );

            ps.setDouble(
                    2,
                    record.getResultValue()
            );

            ps.setInt(
                    3,
                    record.getUnitId()
            );

            ps.executeUpdate();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
