package com.softserveinc.ita.home.home_project_blog.specification;

import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SpecificationService<T> {
    public static final String RSQL_EQUAL = "==";

    public static final String RSQL_AND = ";";

    private static String toRSQLString(Map<String, String> filter) {
        return filter.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .map(entry -> entry.getKey() + RSQL_EQUAL + entry.getValue())
                .collect(Collectors.joining(RSQL_AND));
    }

    public Specification<T> getSpecification(Map<String, String> filter) {
        Specification<T> filterSpecification = RSQLJPASupport.toSpecification(toRSQLString(filter));
        return Optional.of(filterSpecification).orElseThrow();
    }
}
