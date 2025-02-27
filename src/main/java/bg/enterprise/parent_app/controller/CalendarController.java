package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;
import bg.enterprise.parent_app.service.search.SearchableIllnessService;
import bg.enterprise.parent_app.service.write.IllnessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final IllnessService illnessService;
    private final SearchableIllnessService searchableIllnessService;

    @PostMapping("/illness/create")
    public ResponseEntity<IllnessDto> createIllness(@RequestBody IllnessDto illnessDto) {
        log.info("Received request to create illness: {}", illnessDto);
        return new ResponseEntity<>(illnessService.createEvent(illnessDto), HttpStatus.CREATED);
    }

    @PostMapping("/illness/search")
    public ResponseEntity<List<IllnessDto>> searchIllness(@RequestBody EventSearchCriteria criteria) {
        log.info("Received request to search illness by criteria: {}", criteria);
        return new ResponseEntity<>(searchableIllnessService.searchEvents(criteria), HttpStatus.FOUND);
    }
}
