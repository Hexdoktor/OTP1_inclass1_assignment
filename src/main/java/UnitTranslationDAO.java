import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnitTranslationDAO{
    public List<UnitTranslation>getUnits(String language){
        List<UnitTranslation> units = new ArrayList<>();

        String sql= """
                SELECT
                unit_id,
                name,
                description
                FROM unit_translation
                WHERE lang = ?
                """;
        try(
                Connection conn = DBConnection.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, language);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                units.add(
                        new UnitTranslation(
                                rs.getInt("unit_id"),
                                rs.getString("name"),
                                rs.getString("description")
                        )
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if(units.isEmpty()){
            return getUnits("en");
        }

        return units;
    }
}