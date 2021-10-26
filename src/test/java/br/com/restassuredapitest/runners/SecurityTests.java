package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.tests.booking.tests.DeleteBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.SecurityTests.class)
@Suite.SuiteClasses({

        DeleteBookingTest.class

})

public class SecurityTests {
}
