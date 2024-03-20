package com.example.seatingarrangement.repository.service.impl;

import com.example.seatingarrangement.dto.GetLayoutDto;
import com.example.seatingarrangement.model.Company;
import com.example.seatingarrangement.repository.CompanyRepository;
import com.example.seatingarrangement.repository.service.CompanyRepositoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyRepositoryServiceImpl implements CompanyRepositoryService {
    public final CompanyRepository companyRepository;

    public CompanyRepositoryServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    @Override
    public GetLayoutDto findByLayoutId(String layoutId) {
        return companyRepository.findByLayoutId(layoutId);
    }
}
