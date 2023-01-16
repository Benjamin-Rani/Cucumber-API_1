package stepdefinition;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/Features/getAllProgram.feature",
		"src/test/resources/Features/getProgramById.feature",
		"src/test/resources/Features/putbatch.feature"},
                  glue={"stepdefinition"},
                  dryRun = false,
                  monochrome= true, plugin= {"pretty","json:target/JSON/JSONReports.json"}
                  //plugin= {"pretty","junit:target/JUnitReports/report.xml"}
)

public class BatchRunner {

}


//"src/test/resources/Features/deletebatch.feature"