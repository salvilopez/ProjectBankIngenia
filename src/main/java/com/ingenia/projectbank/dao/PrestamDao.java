package com.ingenia.projectbank.dao;

import com.ingenia.projectbank.model.Prestam;

import java.util.List;

public interface PrestamDao {
    List<Prestam> findPrestamsByUserId(Long id);
}
