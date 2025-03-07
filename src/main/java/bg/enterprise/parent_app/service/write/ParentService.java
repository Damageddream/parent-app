package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityAlreadyExists;
import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.entity.Parent;
import bg.enterprise.parent_app.model.mapper.ParentMapper;
import bg.enterprise.parent_app.repository.write.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;


    public ParentDto getParentById(Long id) {
        log.info("Fetching parent with ID: {}", id);
        return parentRepository.findById(id).map(parentMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with id " + id));
    }

    @Transactional
    public ParentDto createParent(ParentDto parentDto) {
        log.info("Creating new parent: {}", parentDto.getEmail());
        if (parentRepository.existsByEmail(parentDto.getEmail())) {
            throw new EntityNotFoundException("Parent with email " + parentDto.getEmail());
        }

        Parent parent = parentMapper.toEntity(parentDto);
        parent = parentRepository.save(parent);
        return parentMapper.toDTO(parent);
    }

    @Transactional
    public ParentDto updateParent(Long id, ParentDto parentDto) {
        log.info("Updating parent with ID: {}", id);

        Parent parent = parentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parent not found with id " + id));

        if (parentDto.getEmail() != null && parentRepository.existsByEmail(parent.getEmail())
                && !parent.getEmail().equals(parentDto.getEmail())) {
            throw new EntityAlreadyExists("Parent with email " + parentDto.getEmail() + " already exists");
        }

        parentMapper.updateEntityFromDto(parentDto, parent);
        parent = parentRepository.save(parent);
        return parentMapper.toDTO(parent);
    }

    @Transactional
    public void deleteParent(Long id) {
        log.info("Deleting parent with ID: {}", id);

        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with id " + id));

        parentRepository.delete(parent);
        log.info("Successfully deleted parent with ID: {}", id);

    }

}
