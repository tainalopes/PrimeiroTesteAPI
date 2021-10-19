package br.com.restassuredapitest.runners;


import br.com.restassuredapitest.tests.ClasseDeTeste;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.AllTests.class)
@Suite.SuiteClasses({
        ClasseDeTeste.class
})
public class AllTests {
}
