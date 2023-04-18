package com.clarit.hs.service.graphql;

import com.clarit.hs.controller.IGraphqlservice;
import com.clarit.hs.service.items.Customer;
import com.clarit.hs.service.items.repo.ItemRepositoryCus;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;



@Service
public class GrpahqlService implements IGraphqlservice {

    public static final Logger logger = LoggerFactory.getLogger(GrpahqlService.class);

    @Autowired
    ItemRepositoryCus itemRepositoryCus;

        @Value("classpath:schema.graphqls")
        private Resource schemaResource;

        private GraphQL graphQL;


        @PostConstruct
        public void loadSchema() throws IOException {
            File schemaFile = schemaResource.getFile();                //getschema
            TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
            RuntimeWiring wiring = buildwiring();
            GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry,wiring);
            graphQL = GraphQL.newGraphQL(schema).build();

        }

        private RuntimeWiring buildwiring(){
            DataFetcher <List<Customer>> fetcher = data ->{
                return (List<Customer>) itemRepositoryCus.findAll();
            };
            DataFetcher<Customer> fetcher1 = data ->{
                return (Customer) itemRepositoryCus.findName(data.getArgument("name"));
            };

            return RuntimeWiring.newRuntimeWiring().type("Query", typeWriting ->
                        typeWriting.dataFetcher("allCustomers", fetcher)
                                .dataFetcher("getCustomer", fetcher1)).build();


            }


    @Override
        public ResponseEntity<Object> getAllCustomer(String query) {
            logger.info("Getting details - {}",query);
            ExecutionResult result = graphQL.execute(query);
            logger.info("Getting details Successfully");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }





}
