package br.al.mcz.agibank.filereader.cucumber;


import br.al.mcz.agibank.filereader.FilereaderApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features")
@SpringBootTest(classes = FilereaderApplication.class)
public class CucumberTest {

}
