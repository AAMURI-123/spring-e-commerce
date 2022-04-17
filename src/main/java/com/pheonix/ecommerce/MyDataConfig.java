package com.pheonix.ecommerce;

import com.pheonix.ecommerce.entity.Country;
import com.pheonix.ecommerce.entity.Order;
import com.pheonix.ecommerce.entity.Product;
import com.pheonix.ecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataConfig implements RepositoryRestConfigurer {

    public EntityManager entityManager;

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @Autowired
    public MyDataConfig(EntityManager entityManager){this.entityManager = entityManager;}

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] disabledHttpMethods = {HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PUT,HttpMethod.PATCH};

        // disable httpmethod for product
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(disabledHttpMethods))
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable((disabledHttpMethods)));

        // disable httpmethod for productCategory
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(disabledHttpMethods))
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable((disabledHttpMethods)));
        
        config.getExposureConfiguration()
                .forDomainType(Country.class)
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(disabledHttpMethods))
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable((disabledHttpMethods)));

        config.getExposureConfiguration()
                .forDomainType(Order.class)
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(disabledHttpMethods))
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable((disabledHttpMethods)));

        // expose id
        exposeId(config);

        // add mapping to cors
        cors.addMapping("/api/**").allowedOrigins(allowedOrigins);

    }

    private void exposeId(RepositoryRestConfiguration config) {

        // get all entities from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create  an array of entites
        List<Class> entityClass = new ArrayList<>();

        // get entites for entity type
        for (EntityType entityType : entities){
            entityClass.add(entityType.getJavaType());
        }

        Class[] domainClass = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainClass);
    }
}
