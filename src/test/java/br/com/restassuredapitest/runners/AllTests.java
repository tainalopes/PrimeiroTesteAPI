package br.com.restassuredapitest.runners;


import br.com.restassuredapitest.tests.auth.tests.PostAuthTest;
import br.com.restassuredapitest.tests.booking.tests.GetBookingTest;
import br.com.restassuredapitest.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.AllTests.class)
@Suite.SuiteClasses({
        GetPingTest.class,
        GetBookingTest.class,
        PostAuthTest.class
})
public class AllTests {
}
