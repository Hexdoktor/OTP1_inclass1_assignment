import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TemperatureRecordDAO {

    public void save(
            TemperatureRecord record
    ) {

        String sql = """
            INSERT INTO temperature_record
            (input_value,result_value,from_unit_id,to_unit_id,language)
            VALUES(?,?,?,?,?)
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
                    record.getFromUnitId()
            );

            ps.setInt(
                    4,
                    record.getToUnitId()
            );

            ps.setString(
                    5,
                    record.getLanguage()
            );

            ps.executeUpdate();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public List<String> getLastFiveConversions(){
        List<String> history = new ArrayList<>();

        String sql = """
                SELECT
                    input_value,
                    result_value,
                    language,
                    created_at
                FROM
                    temperature_record
                ORDER BY
                    created_at DESC
                LIMIT 5
                """;

        try(
                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()
        ){
            while (rs.next()){
                String entry =
                        String.format(
                                "%.2f -> %.2f (%s) [%s]",
                                rs.getDouble(
                                        "input_value"
                                ),
                                rs.getDouble(
                                        "result_value"
                                ),
                                rs.getString(
                                        "language"
                                ),
                                rs.getTimestamp(
                                        "created_at"
                                )
                        );
                history.add(entry);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return history;
    }
}
