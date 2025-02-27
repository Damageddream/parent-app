package bg.enterprise.parent_app.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IllnessType {
    VIRAL("Viral"),
    BACTERIAL("Bacterial"),
    FUNGAL("Fungal"),
    PARASITIC("Parasitic"),
    OTHER("Other");

    @Getter
    private final String name;
}