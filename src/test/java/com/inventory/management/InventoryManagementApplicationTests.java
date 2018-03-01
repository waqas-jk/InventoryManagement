package com.inventory.management;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.inventory.management.rest.InventoryBeanConverterTest;
import com.inventory.management.rest.InventoryControllerTest;
import com.inventory.management.rest.OfficeControllerTest;
import com.inventory.management.rest.ProductControllerTest;
import com.inventory.management.rest.SizeControllerTest;
import com.inventory.management.rest.WarehouseControllerTest;
import com.inventory.management.rest.IT.InventoryControllerIT;
import com.inventory.management.rest.IT.OfficeControllerIT;
import com.inventory.management.rest.IT.ProductControllerIT;
import com.inventory.management.rest.IT.SizeControllerIT;
import com.inventory.management.rest.IT.WarehouseControllerIT;
import com.inventory.management.services.InventoryServiceTest;
import com.inventory.management.services.OfficeServiceTest;
import com.inventory.management.services.ProductServiceTest;
import com.inventory.management.services.ReferenceServiceTest;
import com.inventory.management.services.WarehouseServiceTest;


@RunWith(Suite.class)

@SuiteClasses({
	InventoryServiceTest.class,
	OfficeServiceTest.class,
	ProductServiceTest.class,
	ReferenceServiceTest.class,
	WarehouseServiceTest.class,
	InventoryBeanConverterTest.class,
	InventoryControllerTest.class,
	OfficeControllerTest.class,
	ProductControllerTest.class,
	SizeControllerTest.class,
	WarehouseControllerTest.class,
	InventoryControllerIT.class,
	OfficeControllerIT.class,
	ProductControllerIT.class,
	SizeControllerIT.class,
	WarehouseControllerIT.class
})
public class InventoryManagementApplicationTests  {


}
