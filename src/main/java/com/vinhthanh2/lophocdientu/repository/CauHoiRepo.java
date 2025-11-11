package com.vinhthanh2.lophocdientu.repository;


import com.vinhthanh2.lophocdientu.entity.CauHoi;
import com.vinhthanh2.lophocdientu.entity.NhomCauHoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CauHoiRepo extends JpaRepository<CauHoi, Long> {
}
