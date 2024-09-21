package catering.businesslogic.shift;

import catering.businesslogic.event.Event;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.Service;
import catering.businesslogic.event.ServiceInfo;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftBoard {
    private int id;
    private ServiceInfo referredService;
    private EventInfo referredEvent;
    private ArrayList<Shift> shiftList;
}
