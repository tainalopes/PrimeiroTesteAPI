package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.tests.booking.tests.DeleteBookingTest;
import br.com.restassuredapitest.tests.booking.tests.GetBookingTest;
import br.com.restassuredapitest.tests.booking.tests.PostBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.AcceptanceTests.class)
@Suite.SuiteClasses({

        DeleteBookingTest.class,
        PostBookingTest.class,
        GetBookingTest.class

})

public class AcceptanceTests {
}
