package bg.enterprise.parent_app.service.write;

public interface EventService <T> {
    T createEvent(T event);

    void deleteEvent(Long id);

    void updateEvent(T event);
}
