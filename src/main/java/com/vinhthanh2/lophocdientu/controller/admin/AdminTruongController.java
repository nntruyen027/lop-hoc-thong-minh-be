package com.vinhthanh2.lophocdientu.controller.admin;

import com.vinhthanh2.lophocdientu.dto.req.TruongReq;
import com.vinhthanh2.lophocdientu.entity.Xa;
import com.vinhthanh2.lophocdientu.repository.TinhRepo;
import com.vinhthanh2.lophocdientu.repository.XaRepo;
import com.vinhthanh2.lophocdientu.service.GiaoVienService;
import com.vinhthanh2.lophocdientu.service.LopService;
import com.vinhthanh2.lophocdientu.service.TruongService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
@RequestMapping("/quan-tri/truong")
@Getter
@Setter
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminTruongController {
    final private TruongService truongService;
    final private LopService lopService;
    final private GiaoVienService giaoVienService;
    final private TinhRepo tinhRepo;
    final private XaRepo xaRepo;

    @GetMapping
    public ResponseEntity<?> layDsTruong(@RequestParam(required = false, defaultValue = "") String search,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(truongService.layDsTruong(search, page, size));
    }

    @GetMapping("{id}/lop")
    public ResponseEntity<?> layDsLopCuaTruong(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(lopService.layDsLopTheoTruong(id, search, page, size));
    }

    @GetMapping("{id}/giao-vien")
    public ResponseEntity<?> layDsGiaoVienThuocTruong(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(giaoVienService.layGiaoVienTheoTruong(id, search, page, size));
    }

    @PostMapping
    public ResponseEntity<?> taoTruong(@RequestBody TruongReq truongReq) {
        return ResponseEntity.ok(truongService.taoTruong(truongReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaTruong(@PathVariable Long id, @RequestBody TruongReq truongReq) {
        return ResponseEntity.ok(truongService.suaTruong(id, truongReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaTruong(@PathVariable Long id) {
        truongService.xoaTruong(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/importer/template/tinh/{tinhId}")
    public ResponseEntity<byte[]> downloadTemplate(@PathVariable Long tinhId) throws IOException {
        Resource resource = new ClassPathResource("templates/mau_import_truong.xlsx");

        // Đọc file từ classpath
        InputStream inputStream = resource.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        List<Xa> xas = xaRepo.layTatCaXa("", tinhId, 1, 100000);

        XSSFSheet sheetXa = workbook.getSheet("dm_xa");
        if (sheetXa == null) sheetXa = workbook.createSheet("dm_xa");

        int lastRowXa = sheetXa.getLastRowNum();
        for (int i = lastRowXa; i > 0; i--) {
            Row row = sheetXa.getRow(i);
            if (row != null) sheetXa.removeRow(row);
        }

        Row headerXa = sheetXa.getRow(0);
        if (headerXa == null) headerXa = sheetXa.createRow(0);
        headerXa.createCell(0).setCellValue("ID");
        headerXa.createCell(1).setCellValue("Tên xã");

        int rowIndexXa = 1;
        for (Xa xa : xas) {
            Row row = sheetXa.createRow(rowIndexXa++);
            row.createCell(0).setCellValue(xa.getId());
            row.createCell(1).setCellValue(xa.getId() + " - " + xa.getTen());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mau_import_truong.xlsx")
                .body(out.toByteArray());
    }

    @PostMapping(value = "/importer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importTruong(@RequestParam("file") MultipartFile file) throws IOException {
        truongService.importTruong(file);
        return ResponseEntity.ok().build();
    }

}
