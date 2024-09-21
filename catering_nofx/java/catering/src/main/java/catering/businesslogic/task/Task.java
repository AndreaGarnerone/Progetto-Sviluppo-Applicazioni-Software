package catering.businesslogic.task;

import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.shift.Shift;
import catering.businesslogic.user.User;
import catering.persistence.BatchUpdateHandler;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Task {
    private static HashMap<Integer, Task> all = new HashMap<>();
    private int id;

    private int sumSheet_id;

    private int portions;
    private String amount;
    private int estimatedTime; // expressed as minutes
    private boolean completed;
    private Recipe recipe;
    private User cook;
    private Shift shift;

    public Task(int sumSheet_id) {
        this.setCompleted(false);
        this.sumSheet_id = sumSheet_id;
    }

    public static ArrayList<Task> loadAllTasks() {
        String query = "SELECT * FROM Tasks";
        ArrayList<User> cooks = User.loadAllCooks();
        ArrayList<Shift> shifts = Shift.loadAllShift();
        ArrayList<Recipe> recipes = Recipe.getAllRecipes();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (!(all.containsKey(id))) {
                    int sumSheet_id = rs.getInt("summarysheet_id");
                    int cook_id = rs.getInt("cook_id");
                    int shift_id = rs.getInt("shift_id");
                    int recipe_id = rs.getInt("recipe_id");
                    Task task = new Task(sumSheet_id);
                    task.id = id;
                    task.portions = rs.getInt("portions");
                    task.amount = rs.getString("amount");
                    task.estimatedTime = rs.getInt("estimated_time");
                    task.completed = rs.getBoolean("completed");
                    if (cook_id != -1) {
                        for (User user : cooks) {
                            if (user.getId() == cook_id) {
                                task.cook = user;
                                break;
                            }
                        }
                    }
                    if (shift_id != -1) {
                        for (Shift value : shifts) {
                            if (value.getId() == shift_id) {
                                task.shift = value;
                                break;
                            }
                        }
                    }
                    if (recipe_id != -1) {
                        for (Recipe value : recipes) {
                            if (value.getId() == recipe_id) {
                                task.recipe = value;
                                break;
                            }
                        }
                    }
                    all.put(task.id, task);
                }
            }
        });
        return new ArrayList<Task>(all.values());
    }

    public static void saveAssignementEdit(Task task) {
        String query = "UPDATE Tasks SET cook_id=?, shift_id=? WHERE id=" + task.id;
        PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, task.cook.getId());
                ps.setInt(2, task.shift.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
            }
        });
    }

    public static void saveSetTaskCompleted(Task task) {
        String query = "UPDATE Tasks SET completed=true WHERE id=" + task.id;
        PersistenceManager.executeUpdate(query);
    }

    public String toString() {
        String r = "";
        if (recipe != null) r += "\nRicetta: " + recipe.toString();
        r += "\nQuantita': " + amount;
        r += "\nPorzioni: " + portions;
        r += "\nTempo stimato: " + estimatedTime + " minuti";
        r += "\nCompletato: " + completed;
        if (cook != null) r += "\nCuoco: " + cook.getUserName();
        if (shift != null) r += "\nTurno: " + shift.toString();
        return r;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getSumSheet_id() {
        return sumSheet_id;
    }

    public User getCook() {
        return cook;
    }

    public Shift getShift() {
        return shift;
    }

    public void assignTask(Recipe recipe, Shift shift, User cook) throws UseCaseLogicException {
        this.recipe = recipe;
        if (cook != null) {
            try {
                if (cook.isWorking(shift)) {
                    this.cook = cook;
                } else {
                    throw new TaskException();
                }
            } catch (TaskException te) {
                System.out.println("Cook already working in specified shift");
                return;
            }
        }
        this.shift = shift;
    }

    public void editAssignement(Shift shift, User cook) throws TaskException {
        try {
            this.shift = shift;
            if (cook.isWorking(shift)) {
                this.cook = cook;
            } else {
                throw new TaskException();
            }
        } catch (TaskException te) {
            System.out.println("Cook already working in specified shift");
        }
    }

    public void setTaskData(int time, int portions, String amount) {
        setEstimatedTime(time);
        setPortions(portions);
        setAmount(amount);
    }

    public static void saveNewTask(Task task) {
        String taskInsert = "INSERT INTO catering.Tasks (summarysheet_id,cook_id,shift_id,recipe_id,portions,amount,estimated_time,completed) VALUES (?,?,?,?,?,?,?,?);";
        int[] result = PersistenceManager.executeBatchUpdate(taskInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, task.sumSheet_id);
                if (task.cook != null) {
                    ps.setInt(2, task.cook.getId());
                } else {
                    ps.setInt(2, -1);
                }
                if (task.shift != null) {
                    ps.setInt(3, task.shift.getId());
                } else {
                    ps.setInt(3, -1);
                }
                if (task.recipe != null) {
                    ps.setInt(4, task.recipe.getId());
                } else {
                    ps.setInt(4, -1);
                }
                ps.setInt(5, task.portions);
                if (task.amount != null) {
                    ps.setString(6, PersistenceManager.escapeString(task.amount));
                } else {
                    ps.setString(6, null);
                }
                ps.setInt(7, task.estimatedTime);
                ps.setBoolean(8, task.completed);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    task.id = rs.getInt(1);
                }
            }
        });
    }

    public static void saveNewAssignement(Task task) {
        String assignementInsert = "UPDATE Tasks SET recipe_id = ?, cook_id = ?, shift_id = ? WHERE id=" + task.id;
        int[] result = PersistenceManager.executeBatchUpdate(assignementInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, task.recipe.getId());
                if (task.cook != null) {
                    ps.setInt(2, task.cook.getId());
                } else {
                    ps.setInt(2, -1);
                }
                ps.setInt(3, task.shift.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
//                // should be only one
//                if (count == 0) {
//                    task.id = rs.getInt(1);
//                }
            }
        });
    }

    public static void saveTaskData(Task task) {
        String assignementInsert = "UPDATE Tasks SET estimated_time = ?, portions = ?, amount = ? WHERE id=" + task.id;
        int[] result = PersistenceManager.executeBatchUpdate(assignementInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, task.estimatedTime);
                ps.setInt(2, task.portions);
                ps.setString(3, PersistenceManager.escapeString(task.amount));
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
            }
        });
    }

}
