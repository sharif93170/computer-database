package com.excilys.cdb.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.excilys.cdb.dao.DaoCompany;
import com.excilys.cdb.dao.DaoComputer;
import com.excilys.cdb.exception.DBException;
import com.excilys.cdb.exception.DernierePageException;
import com.excilys.cdb.exception.PremierePageException;
import com.excilys.cdb.model.Company;

@Service
public class CompanyService {

	static Logger logger = LoggerFactory.getLogger(CompanyService.class);

	private final DaoCompany daoCompany;
	private final DaoComputer daoComputer;
	private final PlatformTransactionManager transactionManager;

	public CompanyService(DaoComputer daoComputer, DaoCompany daoCompany,
			PlatformTransactionManager transactionManager) {
		this.daoCompany = daoCompany;
		this.daoComputer = daoComputer;
		this.transactionManager = transactionManager;
	}

	public <T> List<Company> findAll()
			throws SQLException, PremierePageException, DernierePageException, IOException, DBException {
		return daoCompany.findAll();
	}

	public void delete(int id) throws IOException, DBException, SQLException {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				try {
					daoComputer.deleteByCompanyId(id);
				} catch (IOException | SQLException | DBException e) {
					logger.error(e.getMessage());
				}
				try {
					daoCompany.delete(id);
				} catch (IOException | DBException e) {
					logger.error(e.getMessage());
				}
			}
		});

	}

}