package com.vinhthanh2.lophocdientu.repository;

import com.vinhthanh2.lophocdientu.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<File, Long> {
}
