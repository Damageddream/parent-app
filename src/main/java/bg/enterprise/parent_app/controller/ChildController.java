package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.service.write.ChildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/children")
@RequiredArgsConstructor
@Slf4j
public class ChildController {

    private final ChildService childService;


    @GetMapping("/{id}")
    public ResponseEntity<ChildDto> getChildById(@PathVariable Long id) {
        log.info("Received request to fetch child: [id={}]", id);
        ChildDto childDto = childService.getChildById(id);
        return ResponseEntity.ok(childDto);
    }


    @PostMapping
    public ResponseEntity<ChildDto> createChild(@RequestBody ChildDto childDto) {
        log.info("Received request to create a new child under parent: [id={}]", childDto.getParentId());
        ChildDto createdChild = childService.createChild(childDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChild);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ChildDto> updateChild(@PathVariable Long id, @RequestBody ChildDto childDto) {
        log.info("Received request to update child: [id={}]", id);
        ChildDto updatedChild = childService.updateChild(id, childDto);
        return ResponseEntity.ok(updatedChild);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long id) {
        log.info("Received request to delete child: [id={}]", id);
        childService.deleteChild(id);
        return ResponseEntity.noContent().build();
    }
}