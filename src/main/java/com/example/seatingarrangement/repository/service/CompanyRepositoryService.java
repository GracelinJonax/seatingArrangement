package com.example.seatingarrangement.repository.service;

import com.example.seatingarrangement.dto.GetLayoutDto;
import com.example.seatingarrangement.model.Company;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompanyRepositoryService {
    Optional<Company> findByCompanyName(String companyName);
    Optional<Company> findByCompanyId(String companyId);
    GetLayoutDto findByLayoutId(String layoutId);
}
