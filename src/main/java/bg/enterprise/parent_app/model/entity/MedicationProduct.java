package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medication_product")
@Getter
@Setter
public class MedicationProduct extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. Identyfikator Produktu Leczniczego
    private String medicationIdentifier;

    // 2. Nazwa Produktu Leczniczego
    private String productName;

    // 3. Nazwa powszechnie stosowana
    private String commonName;

    // 7. Droga podania
    @Column(length = 3000)
    private String administrationMethod;

    // 9. Postać farmaceutyczna
    @Column(length = 3000)
    private String pharmaceuticalForm;

    // 16. Substancja czynna
    @Column(length = 3000)
    private String activeSubstance;

    // 17. Nazwa wytwórcy
    @Column(length = 3000)
    private String manufacturerName;

    // 26. Ulotka
    @Column(length = 3000)
    private String leaflet;

    // 27. Charakterystyka
    @Column(length = 3000)
    private String productCharacteristics;
}