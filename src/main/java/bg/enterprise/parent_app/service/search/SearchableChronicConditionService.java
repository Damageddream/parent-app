package bg.enterprise.parent_app.service.search;

import bg.enterprise.parent_app.model.dto.ChronicConditionDto;
import bg.enterprise.parent_app.model.entity.ChronicCondition;
import bg.enterprise.parent_app.model.mapper.ChronicConditionMapper;
import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;
import bg.enterprise.parent_app.repository.search.SearchableChronicConditionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchableChronicConditionService implements SearchableEventService<ChronicConditionDto> {

    private final SearchableChronicConditionRepository searchableChronicConditionRepository;
    private final ChronicConditionMapper chronicConditionMapper;


    @Override
    public List<ChronicConditionDto> searchEvents(EventSearchCriteria criteria) {
        log.info("Searching chronic condition events by criteria: {}", criteria);
        List<ChronicCondition> results = searchableChronicConditionRepository.searchBy(
                criteria.getName(),
                criteria.getChildFirstName(),
                criteria.getDescription(),
                criteria.getStartDate(),
                criteria.getEndDate()
        );
        log.info("Found {} chronic condition events", results.size());
        log.debug("Found events: {}", results);
        return results.stream()
                .map(chronicConditionMapper::toDTO)
                .toList();
    }
}
