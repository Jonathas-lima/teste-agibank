package br.al.mcz.agibank.filereader.cucumber;

import br.al.mcz.agibank.filereader.FilereaderApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = FilereaderApplication.class)
public class SpringIntegrationTest {
}
