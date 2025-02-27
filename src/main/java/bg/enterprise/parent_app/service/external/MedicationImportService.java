package bg.enterprise.parent_app.service.external;

import bg.enterprise.parent_app.model.entity.MedicationProduct;
import bg.enterprise.parent_app.repository.search.SearchableMedicationProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicationImportService {

    private final SearchableMedicationProductRepository medicationProductRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public void importMedicationDataFromXls() throws Exception {
        String fileUrl = "https://rejestry.ezdrowie.gov.pl/api/rpl/medicinal-products/public-pl-report/get-xlsx";
        byte[] xlsBytes = restTemplate.getForObject(fileUrl, byte[].class);
        log.info("File downloaded");

        try (InputStream is = new ByteArrayInputStream(xlsBytes);
             Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            ExecutorService executor = Executors.newFixedThreadPool(100);

            for (Row row : sheet) {
                if (row.getRowNum() == 0)
                    continue;

                final String medicationIdentifier = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null;
                final String productName = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null;
                final String commonName = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null;
                final String administrationMethod = row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null;
                final String pharmaceuticalForm = row.getCell(8) != null ? row.getCell(8).getStringCellValue() : null;
                final String activeSubstance = row.getCell(15) != null ? row.getCell(15).getStringCellValue() : null;
                final String manufacturerName = row.getCell(16) != null ? row.getCell(16).getStringCellValue() : null;
                final String leaflet = row.getCell(25) != null ? row.getCell(25).getStringCellValue() : null;
                final String summaryOfProductCharacteristics = row.getCell(26) != null ? row.getCell(26).getStringCellValue() : null;

                executor.submit(() -> {
                    MedicationProduct product = new MedicationProduct();
                    product.setMedicationIdentifier(medicationIdentifier);
                    product.setProductName(productName);
                    product.setCommonName(commonName);
                    product.setAdministrationMethod(administrationMethod);
                    product.setPharmaceuticalForm(pharmaceuticalForm);
                    product.setActiveSubstance(activeSubstance);
                    product.setManufacturerName(manufacturerName);
                    product.setLeaflet(leaflet);
                    product.setProductCharacteristics(summaryOfProductCharacteristics);
                    medicationProductRepository.save(product);
                });
            }
            log.info("Medication data imported");
            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.MINUTES)) {
                throw new IllegalStateException("Timeout waiting for medication import tasks to finish.");
            }
        }
    }
}