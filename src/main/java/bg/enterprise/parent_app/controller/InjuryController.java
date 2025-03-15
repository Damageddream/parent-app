package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.InjuryDto;
import bg.enterprise.parent_app.service.write.InjuryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/injuries")
@RequiredArgsConstructor
@Slf4j
public class InjuryController {

    private final InjuryService injuryService;


    @GetMapping("/{id}")
    public ResponseEntity<InjuryDto> getInjuryById(@PathVariable Long id) {
        log.info("Received request to fetch injury: [id={}]", id);
        InjuryDto injuryDto = injuryService.getInjuryById(id);
        return ResponseEntity.ok(injuryDto);
    }


    @PostMapping("/{childId}")
    public ResponseEntity<InjuryDto> createInjury(@PathVariable Long childId, @RequestBody InjuryDto injuryDto) {
        log.info("Received request to create a new injury record: [id={}]", childId);
        InjuryDto createdInjury = injuryService.createInjury(childId, injuryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInjury);
    }


    @PutMapping("/{id}")
    public ResponseEntity<InjuryDto> updateInjury(@PathVariable Long id, @RequestBody InjuryDto injuryDto) {
        log.info("Received request to update injury: [id={}]", id);
        InjuryDto updatedInjury = injuryService.updateInjury(id, injuryDto);
        return ResponseEntity.ok(updatedInjury);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInjury(@PathVariable Long id) {
        log.info("Received request to delete injury: [id={}]", id);
        injuryService.deleteInjury(id);
        return ResponseEntity.noContent().build();
    }
}
