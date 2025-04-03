package bg.enterprise.parent_app.service.search;

import bg.enterprise.parent_app.model.dto.InjuryDto;
import bg.enterprise.parent_app.model.entity.Illness;
import bg.enterprise.parent_app.model.entity.Injury;
import bg.enterprise.parent_app.model.mapper.InjuryMapper;
import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;
import bg.enterprise.parent_app.repository.search.SearchableInjuryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class SearchableInjuryService implements SearchableEventService<InjuryDto>{

    private final SearchableInjuryRepository searchableInjuryRepository;
    private final InjuryMapper injuryMapper;

    @Override
    public List<InjuryDto> searchEvents(EventSearchCriteria criteria) {
        log.info("Searching injury events by criteria: {}", criteria);
        List<Injury> results = searchableInjuryRepository.searchBy(
                criteria.getName(),
                criteria.getChildFirstName(),
                criteria.getInjuryType(),
                criteria.getDescription(),
                criteria.getStartDate(),
                criteria.getEndDate()
        );
        log.info("Found {} injury events", results.size());
        log.debug("Found events: {}", results);
        return results.stream()
                .map(injuryMapper::toDTO)
                .toList();
    }
}
