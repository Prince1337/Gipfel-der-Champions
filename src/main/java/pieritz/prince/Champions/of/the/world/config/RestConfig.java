package pieritz.prince.Champions.of.the.world.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        config.getExposureConfiguration()
                .forDomainType(F1WorldChampion.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods
                        .disable(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH)
                ).withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH));

    }
}

