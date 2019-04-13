package br.com.neolog.welcomekit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Application
{

    public static void main(
        String[] args )
    {
        new SpringApplication( Application.class ).run( args );
    }
}
