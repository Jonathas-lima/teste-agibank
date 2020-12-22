package br.al.mcz.agibank.filereader;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FilereaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilereaderApplication.class, args);

    }

}
