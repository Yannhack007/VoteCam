package group.sig.projet.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import group.sig.projet.Models.Voters;
import group.sig.projet.Repositories.VoterRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VoterServices {
    private final VoterRepository voterRepository;

    public VoterServices(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    public String uploadVotersFile(MultipartFile file) {
        String contentType = file.getContentType();
        try {
            if (contentType != null) {
                if (contentType.equals("application/vnd.ms-excel") || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                    return handleExcelFile(file);
                } else if (contentType.equals("application/json")) {
                    return handleJsonFile(file);
                } else if (contentType.equals("text/csv") || contentType.equals("application/csv")) {
                    return handleCsvFile(file);
                } else if (contentType.equals("application/pdf")) {
                    return handlePdfFile(file);
                } else {
                    return "Format de fichier non pris en charge : " + contentType;
                }
            }
            return "Impossible de détecter le type de fichier.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'importation du fichier : " + e.getMessage();
        }
    }

    private String handleCsvFile(MultipartFile file) throws Exception {
        List<Voters> voters = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    Voters voter = new Voters();
                    voter.setName(data[0].trim());
                    voter.setPhoneNumber(data[1].trim());
                    voter.setBureauVoteId(UUID.fromString(data[2].trim()));
                    voters.add(voter);
                }
            }
        }
        voterRepository.saveAll(voters);
        return "Fichier CSV importé avec succès. Nombre d'électeurs : " + voters.size();
    }

    private String handleExcelFile(MultipartFile file) throws Exception {
        List<Voters> voters = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Ignorer l'en-tête
            Voters voter = new Voters();
            voter.setName(row.getCell(0).getStringCellValue());
            voter.setPhoneNumber(row.getCell(1).getStringCellValue());
            voter.setBureauVoteId(UUID.fromString(row.getCell(2).getStringCellValue()));
            voters.add(voter);
        }

        workbook.close();
        voterRepository.saveAll(voters);
        return "Fichier Excel importé avec succès. Nombre d'électeurs : " + voters.size();
    }

    private String handleJsonFile(MultipartFile file) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Voters> voters = objectMapper.readValue(file.getInputStream(), new TypeReference<>() {});
        voterRepository.saveAll(voters);
        return "Fichier JSON importé avec succès. Nombre d'électeurs : " + voters.size();
    }

    private String handlePdfFile(MultipartFile file) throws Exception {
        List<Voters> voters = new ArrayList<>();
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);

        // Supposons que chaque ligne contient : nom, téléphone, ID du centre (séparés par des virgules)
        String[] lines = text.split("\n");
        for (String line : lines) {
            String[] data = line.split(",");
            if (data.length >= 3) {
                Voters voter = new Voters();
                voter.setName(data[0].trim());
                voter.setPhoneNumber(data[1].trim());
                voter.setBureauVoteId(UUID.fromString(data[2].trim()));
                voters.add(voter);
            }
        }

        document.close();
        voterRepository.saveAll(voters);
        return "Fichier PDF importé avec succès. Nombre d'électeurs : " + voters.size();
    }
}
