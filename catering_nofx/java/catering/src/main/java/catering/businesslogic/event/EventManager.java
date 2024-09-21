package catering.businesslogic.event;

import catering.businesslogic.shift.Shift;
import catering.businesslogic.shift.ShiftBoard;
import org.w3c.dom.events.Event;

import java.util.ArrayList;

public class EventManager {

    private ArrayList<EventInfo> events;
    public ArrayList<EventInfo> getEventInfo() {
        return EventInfo.loadAllEventInfo();
    }

    public ArrayList<ShiftBoard> getAllShiftTables() {
        ArrayList<ShiftBoard> allTables = new ArrayList<>();
        for (EventInfo e : events) {
            for (ServiceInfo s : e.getServices()) {
                allTables.add(s.getReferredShiftTable());
            }
        }
        return allTables;
    }
}
