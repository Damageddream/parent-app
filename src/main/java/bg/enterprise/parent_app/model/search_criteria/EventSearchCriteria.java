package bg.enterprise.parent_app.model.search_criteria;

import bg.enterprise.parent_app.model.type.IllnessType;
import bg.enterprise.parent_app.model.type.InjuryType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EventSearchCriteria {
    private String name;
    private String childFirstName;
    private Long childId;
    private IllnessType illnessType;
    private InjuryType injuryType;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}