package com.carta.helper;

import com.carta.model.EmpTransaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";
    public static String WTYPE = "application/vnd.ms-excel"; //for windows user

    public static boolean hasCSVFormat(MultipartFile file) {

        if (TYPE.equalsIgnoreCase(file.getContentType()) || WTYPE.equalsIgnoreCase(file.getContentType())) {
            return true;
        }

        return false;
    }

    public static List<EmpTransaction> csvToTransactions (InputStream is) {

        List<EmpTransaction> transactions = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));) {

            CSVParser csvParser = CSVFormat.DEFAULT.parse(new InputStreamReader(is));
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                EmpTransaction empTransaction = new EmpTransaction(
                        csvRecord.get(0),
                        csvRecord.get(1),
                        csvRecord.get(2),
                        csvRecord.get(3),
                        LocalDate.parse(csvRecord.get(4)),
                        BigDecimal.valueOf(Double.parseDouble(csvRecord.get(5)))
                );

                transactions.add(empTransaction);
            }

        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

        return transactions;
    }
}
