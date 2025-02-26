package bg.enterprise.parent_app.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InjuryType {
    FRACTURE("Fracture"),
    SPRAIN("Sprain"),
    DISLOCATION("Dislocation"),
    LACERATION("Laceration"),
    CONTUSION("Contusion");

    @Getter
    private final String name;
}