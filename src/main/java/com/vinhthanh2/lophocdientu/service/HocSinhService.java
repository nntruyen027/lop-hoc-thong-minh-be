package com.vinhthanh2.lophocdientu.service;

import com.vinhthanh2.lophocdientu.entity.HocSinh;
import com.vinhthanh2.lophocdientu.repository.HocSinhRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class HocSinhService {

    private final HocSinhRepo hocSinhRepo;

    public HocSinhService(HocSinhRepo hocSinhRepo) {
        this.hocSinhRepo = hocSinhRepo;
    }

    // Lấy tất cả
    public List<HocSinh> getAll() {
        return hocSinhRepo.findAll();
    }

    // Lấy theo Id
    public Optional<HocSinh> getById(Long id) {
        return hocSinhRepo.findById(id);
    }

    // Thêm hoặc sửa
    public HocSinh save(HocSinh hs) {
        return hocSinhRepo.save(hs);
    }

    // Xóa
    public void delete(Long id) {
        hocSinhRepo.deleteById(id);
    }

    // Thêm nhiều từ file Excel
    public void importFromExcel(MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Bỏ dòng header
                Row row = sheet.getRow(i);
                if (row == null) continue;

                HocSinh hs = new HocSinh();
                hs.setHoTen(getCellString(row.getCell(0)));
                hs.setLop(getCellString(row.getCell(1)));
                hs.setNgaySinh(getCellString(row.getCell(2)));
                hs.setLaNam(getCellBoolean(row.getCell(3)));
                hs.setSoThich(getCellString(row.getCell(4)));
                hs.setMonHocYeuThich(getCellString(row.getCell(5)));
                hs.setDiemManh(getCellString(row.getCell(6)));
                hs.setDiemYeu(getCellString(row.getCell(7)));
                hs.setNgheNghiepMongMuon(getCellString(row.getCell(8)));
                hs.setNhanXetGiaoVien(getCellString(row.getCell(9)));
                hs.setGhiChu(getCellString(row.getCell(10)));
                hs.setRealisticScore(getCellInt(row.getCell(11)));
                hs.setInvestigativeScore(getCellInt(row.getCell(12)));
                hs.setArtisticScore(getCellInt(row.getCell(13)));
                hs.setSocialScore(getCellInt(row.getCell(14)));
                hs.setEnterprisingScore(getCellInt(row.getCell(15)));
                hs.setConventionalScore(getCellInt(row.getCell(16)));
                hs.setAssessmentResult(getCellString(row.getCell(17)));

                hocSinhRepo.save(hs);
            }
        }
    }

    private String getCellString(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    private Boolean getCellBoolean(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.BOOLEAN) return cell.getBooleanCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue() != 0;
        if (cell.getCellType() == CellType.STRING) return "true".equalsIgnoreCase(cell.getStringCellValue());
        return null;
    }

    private Integer getCellInt(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) return (int) cell.getNumericCellValue();
        if (cell.getCellType() == CellType.STRING) {
            try {
                return Integer.parseInt(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
