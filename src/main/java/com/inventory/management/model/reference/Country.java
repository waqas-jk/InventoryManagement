package com.inventory.management.model.reference;

import javax.persistence.Entity;

import com.inventory.management.model.BaseEntityNameAware;

/**
 * @author Waqas
 */

@Entity
public class Country extends BaseEntityNameAware {

	public static final Long USA_ID = 1L;
	public static final Long IRELAND_ID = 2L;
	public static final Long NETHERLAND_ID = 3L;
	public static final Long DUBAI_ID = 4L;
	public static final Long AUSTRALIA_ID = 5L;
	public static final Long ITALY_ID = 6L;
	public static final Long PAKISTAN_ID = 7L;
	public static final Long MEXICO_ID = 8L;

}
