package bg.enterprise.parent_app.service.search;

import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;

import java.util.List;

public interface SearchableEventService <T> {
    List<T> searchEvents(EventSearchCriteria event);
}