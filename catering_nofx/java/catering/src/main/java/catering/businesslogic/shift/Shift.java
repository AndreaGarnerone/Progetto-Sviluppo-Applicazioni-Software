package catering.businesslogic.shift;

import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Shift {
    private static Map<Integer, Shift> all = new HashMap<>();
    private int id;
    private Date startTime;
    private Date endTime;
    private Date deadline;
    private String place;

    public static ArrayList<Shift> loadAllShift() {
        String query = "SELECT * FROM Shifts";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");

                if (all.containsKey(id)) {
                    Shift s = all.get(id);
                    s.place = rs.getString("place");
                    s.startTime = rs.getTimestamp("startTime");
                    s.endTime = rs.getTimestamp("endTime");
                    s.deadline = rs.getTimestamp("deadline");
                } else {
                    Shift s = new Shift();
                    s.id = id;
                    s.startTime = rs.getTimestamp("startTime");
                    s.endTime = rs.getTimestamp("endTime");
                    s.deadline = rs.getTimestamp("deadline");
                    s.place = rs.getString("place");
                    all.put(s.id, s);
                }
            }
        });
        return new ArrayList<>(all.values());
    }

    public String toString() {
        String r = "";
        r += "\nInizio turno: " + startTime;
        r += "\nFine turno: " + endTime;
        r += "\nDeadline: " + deadline;
        r += "\nLuogo: " + place;
        return r;
    }

    public int getId() {
        return id;
    }
}
