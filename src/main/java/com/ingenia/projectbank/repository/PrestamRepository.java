package com.ingenia.projectbank.repository;

import com.ingenia.projectbank.model.Prestam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamRepository extends JpaRepository<Prestam,Long> {
}
