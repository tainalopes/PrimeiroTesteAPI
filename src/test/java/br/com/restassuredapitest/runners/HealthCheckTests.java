package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.tests.auth.tests.PostAuthTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.HealthCheckTests.class)
@Suite.SuiteClasses({
        PostAuthTest.class

})

public class HealthCheckTests {
}
