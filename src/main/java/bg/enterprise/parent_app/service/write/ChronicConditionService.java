package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.ChronicConditionDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.ChronicCondition;
import bg.enterprise.parent_app.model.mapper.ChronicConditionMapper;
import bg.enterprise.parent_app.repository.write.ChildRepository;
import bg.enterprise.parent_app.repository.write.ChronicConditionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChronicConditionService {

    private final ChronicConditionRepository chronicConditionRepository;
    private final ChronicConditionMapper chronicConditionMapper;
    private final ChildRepository childRepository;

    public ChronicConditionDto getChronicConditionById(Long id) {
        log.info("Fetching chronic condition: [id={}]", id);
        return chronicConditionRepository.findById(id)
                .map(chronicConditionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Chronic condition not found with id " + id));
    }

    @Transactional
    public ChronicConditionDto createChronicCondition(Long childId, ChronicConditionDto chronicConditionDto) {
        log.info("Creating new chronic condition for child: [id={}]", childId);

        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + childId));

        ChronicCondition chronicCondition = chronicConditionMapper.toEntity(chronicConditionDto);
        chronicCondition.setChild(child);

        chronicCondition = chronicConditionRepository.save(chronicCondition);
        return chronicConditionMapper.toDTO(chronicCondition);
    }

    @Transactional
    public ChronicConditionDto updateChronicCondition(Long id, ChronicConditionDto chronicConditionDto) {
        log.info("Updating chronic condition: [id={}]", id);

        ChronicCondition chronicCondition = chronicConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chronic condition not found with id " + id));

        chronicConditionMapper.updateEntityFromDto(chronicConditionDto, chronicCondition);
        chronicCondition = chronicConditionRepository.save(chronicCondition);
        return chronicConditionMapper.toDTO(chronicCondition);
    }

    @Transactional
    public void deleteChronicCondition(Long id) {
        log.info("Deleting chronic condition: [id={}]", id);

        ChronicCondition chronicCondition = chronicConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chronic condition not found with id " + id));

        chronicConditionRepository.delete(chronicCondition);
        log.info("Successfully deleted chronic condition: [id={}]", id);
    }

    public List<ChronicConditionDto> getChronicConditionsByChildId(Long childId) {
        log.info("Fetching chronic conditions for: [id={}]", childId);

        if (!childRepository.existsById(childId)) {
            throw new EntityNotFoundException("Child not found with id " + childId);
        }

        return chronicConditionRepository.findByChildId(childId)
                .stream()
                .map(chronicConditionMapper::toDTO)
                .collect(Collectors.toList());
    }
}