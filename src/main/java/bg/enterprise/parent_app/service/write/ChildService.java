package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.Parent;
import bg.enterprise.parent_app.model.mapper.ChildMapper;
import bg.enterprise.parent_app.repository.write.ChildRepository;
import bg.enterprise.parent_app.repository.write.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final ChildMapper childMapper;
    private final ParentRepository parentRepository;

    public ChildDto getChildById(Long id) {
        log.info("Fetching child: [id={}]", id);
        return childRepository.findById(id)
                .map(childMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + id));
    }

    @Transactional
    public ChildDto createChild(Long parentId, ChildDto childDto) {
        log.info("Creating new child under parent: [id={}]", parentId);

        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with id " + parentId));

        Child child = childMapper.toEntity(childDto);
        child.setParent(parent);

        child = childRepository.save(child);
        return childMapper.toDTO(child);
    }

    @Transactional
    public ChildDto updateChild(Long id, ChildDto childDto) {
        log.info("Updating child with: [id={}]", id);

        Child child = childRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + id));

        childMapper.updateEntityFromDto(childDto, child);
        child = childRepository.save(child);
        return childMapper.toDTO(child);
    }

    @Transactional
    public void deleteChild(Long id) {
        log.info("Deleting child: [id={}]", id);

        Child child = childRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + id));

        childRepository.delete(child);
        log.info("Successfully deleted child: [id={}]", id);
    }

    public List<ChildDto> getChildrenByParentId(Long parentId) {
        log.info("Fetching children for parent: [id={}]", parentId);

        if (!parentRepository.existsById(parentId)) {
            throw new EntityNotFoundException("Parent not found with id " + parentId);
        }

        return childRepository.findByParentId(parentId)
                .stream()
                .map(childMapper::toDTO)
                .collect(Collectors.toList());
    }
}