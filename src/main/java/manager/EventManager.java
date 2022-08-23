package manager;

import db.DBConnectionProvider;
import model.Event;
import model.Events;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class EventManager {
    private static Scanner scanner = new Scanner(System.in);
    private Connection connection;

    public EventManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addEvent(Event event) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "Insert into event(name,place,isOnline,price) Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, event.getName());
        preparedStatement.setString(2, event.getPlace());
        preparedStatement.setBoolean(3, event.isOnline());
        preparedStatement.setDouble(4, event.getPrice());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            event.setId(1);
        }
    }

    public List<Event> getAllEvent() throws SQLException {
        String query = "SELECT * FROM event";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM event");
        List<Event> events = new LinkedList<>();
        while (resultSet.next()) {
            events.add(getEventFromResultSet(resultSet));

        }
        return events;
    }

    private Event getEventFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getInt("id"));
        event.setName(resultSet.getString("name"));
        event.setPlace(resultSet.getString("place"));
        event.setOnline(Boolean.parseBoolean(resultSet.getString("isOnline")));
        event.setPrice(resultSet.getDouble("price"));
        event.setEvents(Events.valueOf(resultSet.getString("events")));
        return event;
    }

    public static void showAllEvents() throws SQLException {
        EventManager eventManager = new EventManager();
        List<Event> allEvents = eventManager.getAllEvent();
        for (Event event : allEvents) {
            System.out.println(event);
        }
    }
}
