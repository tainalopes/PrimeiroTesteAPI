package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.tests.booking.tests.GetBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.ContractTests.class)
@Suite.SuiteClasses({
        GetBookingTest.class

})

public class ContractTests {
}
