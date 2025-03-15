package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.service.write.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/parent")

public class ParentController {

    private final ParentService parentService;


    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> getParentById(@PathVariable Long id) {
        log.info("Received request to fetch parent: [id={}]", id);
        ParentDto parentDto = parentService.getParentById(id);
        return ResponseEntity.ok(parentDto);
    }


    @PostMapping
    public ResponseEntity<ParentDto> createParent(@RequestBody ParentDto parentDto) {
        log.info("Received request to create a new parent: [id={}]", parentDto.getEmail());
        ParentDto createdParent = parentService.createParent(parentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParent);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ParentDto> updateParent(@PathVariable Long id, @RequestBody ParentDto parentDto) {
        log.info("Received request to update parent: [id={}]", id);
        ParentDto updatedParent = parentService.updateParent(id, parentDto);
        return ResponseEntity.ok(updatedParent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        log.info("Received request to delete parent: [id={}]", id);
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
