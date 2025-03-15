package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.ChronicConditionDto;
import bg.enterprise.parent_app.service.write.ChronicConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chronic-conditions")
@RequiredArgsConstructor
@Slf4j
public class ChronicConditionController {

    private final ChronicConditionService chronicConditionService;


    @GetMapping("/{id}")
    public ResponseEntity<ChronicConditionDto> getChronicConditionById(@PathVariable Long id) {
        log.info("Received request to fetch chronic condition: [id={}]", id);
        ChronicConditionDto chronicConditionDto = chronicConditionService.getChronicConditionById(id);
        return ResponseEntity.ok(chronicConditionDto);
    }


    @PostMapping("/{childId}")
    public ResponseEntity<ChronicConditionDto> createChronicCondition(@PathVariable Long childId, @RequestBody ChronicConditionDto chronicConditionDto) {
        log.info("Received request to create a new chronic condition for child: [id={}]", childId);
        ChronicConditionDto createdCondition = chronicConditionService.createChronicCondition(childId, chronicConditionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCondition);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ChronicConditionDto> updateChronicCondition(@PathVariable Long id, @RequestBody ChronicConditionDto chronicConditionDto) {
        log.info("Received request to update chronic condition: [id={}]", id);
        ChronicConditionDto updatedCondition = chronicConditionService.updateChronicCondition(id, chronicConditionDto);
        return ResponseEntity.ok(updatedCondition);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChronicCondition(@PathVariable Long id) {
        log.info("Received request to delete chronic condition: [id={}]", id);
        chronicConditionService.deleteChronicCondition(id);
        return ResponseEntity.noContent().build();
    }
}
