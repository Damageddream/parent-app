package bg.enterprise.parent_app.service.search;

import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.entity.Illness;
import bg.enterprise.parent_app.model.mapper.IllnessMapper;
import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;
import bg.enterprise.parent_app.repository.search.SearchableIllnessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchableIllnessService implements SearchableEventService<IllnessDto> {

    private final SearchableIllnessRepository searchableIllnessRepository;
    private final IllnessMapper illnessMapper;

    @Override
    public List<IllnessDto> searchEvents(EventSearchCriteria criteria) {
        log.info("Searching illness events by criteria: {}", criteria);
        List<Illness> results = searchableIllnessRepository.searchBy(
                criteria.getName(),
                criteria.getChildFirstName(),
                criteria.getIllnessType(),
                criteria.getDescription(),
                criteria.getStartDate(),
                criteria.getEndDate()
        );
        log.info("Found {} illness events", results.size());
        log.debug("Found events: {}", results);
        return results.stream()
                .map(illnessMapper::toDTO)
                .toList();
    }
}
