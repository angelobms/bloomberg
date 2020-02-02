package com.bloomberg.calculatorapi.config;

import com.bloomberg.calculatorapi.domain.OperationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class CalculatorConfig {

    /**
     * Config objectMapper enabling wrapping for root elements.
     * @return Instance of objectMapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);
        builder.featuresToEnable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        builder.featuresToDisable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
        builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        ObjectMapper mapper = builder.build();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    /**
     * Bean responsible for map from dto to entity.
     * @return ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<String, OperationType> operationTypeConverter = new AbstractConverter<>() {
            @Override
            protected OperationType convert(String source) {
                OperationType operationType;
                try {
                    operationType = OperationType.valueOf(source.toUpperCase());
                } catch (Exception e) {
                    operationType = OperationType.findByOperator(source);
                }
                return operationType;
            }
        };
        modelMapper.addConverter(operationTypeConverter);
        return modelMapper;
    }
}
