package catering.businesslogic.shift;

import java.util.ArrayList;

public class ShiftManager {
    private ArrayList<ShiftEventReceiver> eventReceivers;
    private ShiftBoard currentShiftBoard;

    public ShiftManager() {
        eventReceivers = new ArrayList<>();
    }
    public ArrayList<Shift> loadAllShift() {
        return Shift.loadAllShift();
    }
}
