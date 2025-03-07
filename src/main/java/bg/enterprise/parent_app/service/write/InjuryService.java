package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.InjuryDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.Injury;
import bg.enterprise.parent_app.model.mapper.InjuryMapper;
import bg.enterprise.parent_app.repository.write.ChildRepository;
import bg.enterprise.parent_app.repository.write.InjuryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InjuryService {

    private final InjuryRepository injuryRepository;
    private final InjuryMapper injuryMapper;
    private final ChildRepository childRepository;

    public InjuryDto getInjuryById(Long id) {
        log.info("Fetching injury with ID: {}", id);
        return injuryRepository.findById(id)
                .map(injuryMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Injury not found with id " + id));
    }

    @Transactional
    public InjuryDto createInjury(Long childId, InjuryDto injuryDto) {
        log.info("Creating new injury record for child ID: {}", childId);

        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + childId));

        Injury injury = injuryMapper.toEntity(injuryDto);
        injury.setChild(child);

        injury = injuryRepository.save(injury);
        return injuryMapper.toDTO(injury);
    }

    @Transactional
    public InjuryDto updateInjury(Long id, InjuryDto injuryDto) {
        log.info("Updating injury with ID: {}", id);

        Injury injury = injuryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Injury not found with id " + id));

        injuryMapper.updateEntityFromDto(injuryDto, injury);
        injury = injuryRepository.save(injury);
        return injuryMapper.toDTO(injury);
    }

    @Transactional
    public void deleteInjury(Long id) {
        log.info("Deleting injury with ID: {}", id);

        Injury injury = injuryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Injury not found with id " + id));

        injuryRepository.delete(injury);
        log.info("Successfully deleted injury with ID: {}", id);
    }

    public List<InjuryDto> getInjuriesByChildId(Long childId) {
        log.info("Fetching injuries for child ID: {}", childId);

        if (!childRepository.existsById(childId)) {
            throw new EntityNotFoundException("Child not found with id " + childId);
        }

        return injuryRepository.findByChildId(childId)
                .stream()
                .map(injuryMapper::toDTO)
                .collect(Collectors.toList());
    }
}