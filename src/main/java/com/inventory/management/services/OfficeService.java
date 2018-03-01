package com.inventory.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.management.dao.OfficeRepository;
import com.inventory.management.model.Office;

/**
 * @author Waqas
 */

@Service
public class OfficeService {

	@Autowired
	private OfficeRepository officeRepository;

	public void setOfficeRepository(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;
	}

	/**
	 * Get all offices in all counties
	 * 
	 * @return
	 */
	public List<Office> getAllOffices() {
		return officeRepository.findAll();
	}

	/**
	 * Get office by office id.
	 * 
	 * @return
	 */
	public Office getOffice(Long officeId) {
		return officeRepository.findOne(officeId);
	}

	/**
	 * Get office by country id.
	 * 
	 * @return
	 */
	public Office getOfficeByCountryId(Long countryId) {
		return officeRepository.findOne(countryId);
	}

	/**
	 * Get office by country name.
	 * 
	 * @return
	 */
	public Office getOffice(String countryName) {
		return officeRepository.findOfficeByCountryName(countryName);
	}

	/**
	 * Add new office or update existing office.
	 * 
	 * @return
	 */
	public Office saveOffice(Office office) {
		return officeRepository.save(office);
	}

}
