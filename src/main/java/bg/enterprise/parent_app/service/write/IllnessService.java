package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.Illness;
import bg.enterprise.parent_app.model.mapper.IllnessMapper;
import bg.enterprise.parent_app.repository.write.ChildRepository;
import bg.enterprise.parent_app.repository.write.IllnessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IllnessService implements EventService<IllnessDto> {

    private final IllnessRepository illnessRepository;
    private final ChildRepository childRepository;
    private final IllnessMapper illnessMapper;

    @Override
    public IllnessDto createEvent(IllnessDto event) {
        Illness illness = illnessMapper.toEntity(event);
        verifyChild(event.getChildId(), illness);
        log.info("Saving illness: {}", illness);
        illness = illnessRepository.save(illness);
        log.info("Saved illness: {}", illness);
        return illnessMapper.toDTO(illness);
    }

    @Override
    public void deleteEvent(Long id) throws EntityNotFoundException {
        illnessRepository.deleteById(id);
    }


    @Override
    public IllnessDto updateEvent(IllnessDto update) {
        Illness illness = illnessRepository.findById(update.getId()).orElseThrow(() -> new EntityNotFoundException("Illness not found"));
        illnessMapper.updateIllnessFromDto(update, illness);
        return illnessMapper.toDTO(illnessRepository.save(illness));
    }

    private void verifyChild(Long childId, Illness illness) {
        log.info("Verifying existence of child entry with: [id={}]", childId);
        Child child = childRepository.findById(childId).orElseThrow(
                () -> new EntityNotFoundException("Child not found"));
        log.info("Child with: [id={}] found: {}", childId, child);
        illness.setChild(child);
    }
}
