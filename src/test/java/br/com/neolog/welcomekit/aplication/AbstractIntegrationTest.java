package br.com.neolog.welcomekit.aplication;



import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.neolog.welcomekit.Application;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;

/**
 * Classe Base para testes de integração. Abstrata pois não possui teste algum.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ActiveProfiles("test")
@SpringBootTest( classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT )
@TestPropertySource( locations = "classpath:spring/test-application.properties")
public abstract class AbstractIntegrationTest
{
    @Value( "${local.server.port}" )
    private Integer realPort;

    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * Pode-se definir outros {@link Before} nas classes-filha sem problemas.
     */
    @Before
    public final void parentSetUp()
    {
        RestAssured.port = realPort;

        RestAssured.config = RestAssuredConfig.config()
            .objectMapperConfig( ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory( new Jackson2ObjectMapperFactory() {

            	@Override
                public ObjectMapper create(
                    final Class arg0,
                    final String arg1 )
                {
                    return objectMapper;
                }
            } ) );
    }
}
