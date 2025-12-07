package com.vinhthanh2.lophocdientu.controller.admin;

import com.vinhthanh2.lophocdientu.dto.req.XaReq;
import com.vinhthanh2.lophocdientu.entity.Tinh;
import com.vinhthanh2.lophocdientu.repository.TinhRepo;
import com.vinhthanh2.lophocdientu.service.XaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/quan-tri/xa")
@Getter
@Setter
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminXaController {
    final private XaService xaService;
    final private TinhRepo tinhRepo;

    @GetMapping
    public ResponseEntity<?> layDsXa(@RequestParam(required = false, defaultValue = "") String search,
                                     @RequestParam(required = false) Long tinhId,
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(xaService.layDsXa(search, tinhId, page, size));
    }

    @PostMapping
    public ResponseEntity<?> taoXa(@RequestBody XaReq xaReq) {
        return ResponseEntity.ok(xaService.taoXa(xaReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaXa(@PathVariable Long id, @RequestBody XaReq xaReq) {
        return ResponseEntity.ok(xaService.suaXa(id, xaReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaXa(@PathVariable Long id) {
        xaService.xoaXa(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/importer/template")
    public ResponseEntity<byte[]> downloadTemplate() throws IOException {

        // Load file từ resources (OK cho JAR)
        ClassPathResource resource = new ClassPathResource("templates/mau_import_xa.xlsx");

        // Lấy InputStream thay vì getFile()
        InputStream inputStream = resource.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        List<Tinh> tinhs = tinhRepo.layTatCaTinh("", 1, 10000);

        XSSFSheet sheet = workbook.getSheet("DanhMucTinh");
        if (sheet == null) {
            sheet = workbook.createSheet("DanhMucTinh");
        }

        // Xóa dữ liệu cũ (ngoại trừ header)
        int lastRow = sheet.getLastRowNum();
        for (int i = lastRow; i > 0; i--) {
            Row row = sheet.getRow(i);
            if (row != null) sheet.removeRow(row);
        }

        // Header
        Row header = sheet.getRow(0);
        if (header == null) header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Tên tỉnh");

        // Ghi danh sách tỉnh
        int rowIndex = 1;
        for (Tinh tinh : tinhs) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(tinh.getId());
            row.createCell(1).setCellValue(tinh.getId() + " - " + tinh.getTen());
        }

        // Xuất file
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mau_import_xa.xlsx")
                .body(out.toByteArray());
    }


    @PostMapping(value = "/importer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importXa(@RequestParam("file") MultipartFile file) throws IOException {
        xaService.importXa(file);
        return ResponseEntity.ok().build();
    }

}
