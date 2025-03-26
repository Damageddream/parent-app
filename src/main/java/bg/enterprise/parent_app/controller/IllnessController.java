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
@RequestMapping("/illness")
public class IllnessController {

    private final IllnessService illnessService;
    private final SearchableIllnessService searchableIllnessService;

    @PostMapping("/create")
    public ResponseEntity<IllnessDto> createIllness(@RequestBody IllnessDto illnessDto) {
        log.info("Received request to create illness: {}", illnessDto);
        return new ResponseEntity<>(illnessService.createEvent(illnessDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateIllness(@RequestBody IllnessDto illnessDto) {
        log.info("Received request to update illness: {} [id={}]", illnessDto, illnessDto.getId());
        illnessService.updateEvent(illnessDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<IllnessDto>> searchIllness(@RequestBody EventSearchCriteria criteria) {
        log.info("Received request to search illness by criteria: {}", criteria);
        return new ResponseEntity<>(searchableIllnessService.searchEvents(criteria), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIllness(@PathVariable Long id) {
        log.info("Received request to delete illness: [id={}]", id);
        illnessService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
