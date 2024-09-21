package catering.businesslogic.task;

import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
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

public class SummarySheet {
    private static HashMap<Integer,SummarySheet> all = new HashMap<>();
    private int id;
    private ArrayList<Task> taskList;
    private ServiceInfo referredService;
    private EventInfo referredEvent;

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public SummarySheet(ServiceInfo service, EventInfo event) {
        this.referredService = service;
        this.referredEvent = event;
        this.taskList = new ArrayList<>();
    }

    public String toString() {
        StringBuilder r = new StringBuilder("\nFoglio riepilogativo - Evento: " + referredEvent.getName() + " - Servizio: " +
                referredService.getName() + "\nEvento assegnato a Chef: " + referredEvent.getChef() +
                "\nElenco dei compiti (" + taskList.size() + "):\n");
                for (Task t : taskList) {
                    r.append(t.toString()).append("\n");
                }
        return r.toString();
    }

    public ServiceInfo getReferredService() {
        return referredService;
    }

    public EventInfo getReferredEvent() {
        return referredEvent;
    }

    public void setReferredService(ServiceInfo referredService) {
        this.referredService = referredService;
    }

    public void setReferredEvent(EventInfo referredEvent) {
        this.referredEvent = referredEvent;
    }

    public Task addTask(int sumSheet_id) {
        Task task = new Task(sumSheet_id);
        if (!taskList.contains(task)) {
            this.taskList.add(task);
        }
        return task;
    }

    public void assignTask(Task task, Recipe recipe, Shift shift, User cook) throws UseCaseLogicException {
        taskList.get(taskList.indexOf(task)).assignTask(recipe,shift,cook);
    }

    public void editAssignement(Task task, Shift shift, User cook) throws TaskException {
        taskList.get(taskList.indexOf(task)).editAssignement(shift,cook);
    }

    public void setTaskCompleted(Task task) {
        taskList.get(taskList.indexOf(task)).setCompleted(true);
    }

    public void setTaskData(Task task, int time, int portions, String amount) {
        taskList.get(taskList.indexOf(task)).setTaskData(time,portions,amount);
    }

    public int getId() {
        return id;
    }

    public static void saveNewSummarySheet(SummarySheet sumSheet) {
        String sumSheetInsert = "INSERT INTO catering.SummarySheets (service_id, event_id) VALUES (?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(sumSheetInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, sumSheet.referredService.getId());
                ps.setInt(2, sumSheet.referredEvent.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    sumSheet.id = rs.getInt(1);
                }
            }
        });
    }

    public static void deleteSumSheetFromDB(SummarySheet sumSheet) {
        String query = "DELETE FROM SummarySheets WHERE id=" + sumSheet.getId();
        PersistenceManager.executeUpdate(query);
    }

    public static ArrayList<SummarySheet> loadAllSumSheets() {
        String query = "SELECT * FROM SummarySheets";
        ArrayList<EventInfo> events = EventInfo.loadAllEventInfo();
        ArrayList<Task> allTasks = Task.loadAllTasks();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (all.containsKey(id)) {
//                    SummarySheet sumSheet = all.get(id);
//                    sumSheet.service_id = rs.getInt("service_id");
//                    sumSheet.event_id = rs.getInt("event_id");
                } else {
                    int e_id = rs.getInt("event_id");
                    int s_id = rs.getInt("service_id");
                    EventInfo event = events.get(0);
                    for (int i = 1; event.getId() != e_id; i++) {
                        event = events.get(i);
                    }
                    ServiceInfo service = event.getServices().get(0);
                    for (int i = 1; service.getId()!= s_id; i++) {
                        service = event.getServices().get(i);
                    }
                    SummarySheet sumSheet = new SummarySheet(service,event);
                    sumSheet.id = id;
                    all.put(sumSheet.id, sumSheet);
                }
            }
        });
        for (SummarySheet sumSheet : all.values()) {
            for (int i = 0; i < allTasks.size(); i++) {
                if (allTasks.get(i).getSumSheet_id() == sumSheet.id &&
                    !sumSheet.taskList.contains(allTasks.get(i))) {
                    sumSheet.taskList.add(allTasks.get(i));
                }
            }
        }
        return new ArrayList<SummarySheet>(all.values());
    }
}
