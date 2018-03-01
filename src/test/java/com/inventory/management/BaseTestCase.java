package com.inventory.management;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTestCase{

	protected static final Long DEFAULT_ID = 1L;
	protected static final Long INVALID_ID = 99L;

	protected static final Long DEFAULT_QUANTITY = 999L;

}
