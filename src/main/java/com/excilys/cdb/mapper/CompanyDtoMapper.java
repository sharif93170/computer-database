package com.excilys.cdb.mapper;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.model.Company;

@Component
public class CompanyDtoMapper {

	public Company fromCompanyDTO(CompanyDTO companyDto) {
		Long id = Long.parseLong(companyDto.getId());
		String name = companyDto.getName();
		return new Company.CompanyBuilder(id).name(name).build();
	}

	public CompanyDTO toCompanyDTO(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setId(company.getId().toString());
		companyDto.setName(company.getName());
		return companyDto;
	}

}
