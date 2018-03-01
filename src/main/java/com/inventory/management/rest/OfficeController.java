package com.inventory.management.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.management.Constants;
import com.inventory.management.exception.OfficeAlreadyExistsException;
import com.inventory.management.model.Office;
import com.inventory.management.model.Warehouse;
import com.inventory.management.services.OfficeService;
import com.inventory.management.services.ReferenceEntityService;
import com.inventory.management.services.WarehouseService;

@RestController
@RequestMapping("/office")
public class OfficeController {

	private OfficeService officeService;
	private ReferenceEntityService refSerivce;
	private WarehouseService warehouseService;

	@Autowired
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	@Autowired
	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}

	@Autowired
	public void setReferenceEntityService(ReferenceEntityService refSerivce) {
		this.refSerivce = refSerivce;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<Office> listAllOffices() {

		return officeService.getAllOffices();
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "view/{officeId}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public Office getOfficeById(@PathVariable("officeId") Long officeId) {

		Office office = officeService.getOffice(officeId);

		if (office == null) {
			throw new EntityNotFoundException("Requested office can not be found.");
		}

		return office;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "view/country/{countryIdOrName}", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public Office getOfficeByCountry(@PathVariable("countryIdOrName") String countryIdOrName) {

		Office office = null;
		if (StringUtils.isNumeric(countryIdOrName)) {
			office = officeService.getOfficeByCountryId(Long.valueOf(countryIdOrName));
		} else {
			office = officeService.getOffice(countryIdOrName);
		}

		if (office == null) {
			throw new EntityNotFoundException("Requested office can not be found.");
		}

		return office;
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "{officeId}/view/warehouse", method = RequestMethod.GET, produces = Constants.CONTENT_TYPE_JSON)
	public List<Warehouse> getWarehouseByOffice(@PathVariable("officeId") Long officeId) {

		Office office = officeService.getOffice(officeId);

		if (office == null) {
			throw new EntityNotFoundException("Requested office can not be found.");
		}

		return warehouseService.getAllWarehousesByOffice(officeId);
	}

	/**
	 * 
	 * @param filter
	 * @param level
	 * @return
	 * @throws OfficeAlreadyExistsException
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = Constants.CONTENT_TYPE_JSON, produces = Constants.CONTENT_TYPE_JSON)
	public Office addNewOffice(@Valid @RequestBody Office office) throws OfficeAlreadyExistsException {

		Office exisitngOffice = officeService.getOfficeByCountryId(office.getCountry().getId());

		if (exisitngOffice == null) {
			office.setCountry(refSerivce.getCountry(office.getCountry().getId()));
			return officeService.saveOffice(office);
		}

		throw new OfficeAlreadyExistsException(
				"One country can not have two office. Office already exists in this country.");
	}

}
