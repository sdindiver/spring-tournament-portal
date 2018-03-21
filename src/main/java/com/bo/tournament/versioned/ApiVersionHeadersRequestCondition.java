package com.bo.tournament.versioned;



import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static com.bo.tournament.versioned.ApiVersionHeader.*;

public class ApiVersionHeadersRequestCondition extends AbstractRequestCondition<ApiVersionHeadersRequestCondition> {

    private final HeadersRequestCondition headersRequestCondition;

    private final Set<NameValueExpression<String>> expressions = new HashSet<>();

    public ApiVersionHeadersRequestCondition(String... headers) {
        this(new HeadersRequestCondition(headers));
    }

    private ApiVersionHeadersRequestCondition(HeadersRequestCondition headersRequestCondition) {
        this.headersRequestCondition = headersRequestCondition;
        expressions.addAll(headersRequestCondition.getExpressions());
    }

    @Override
    protected Collection<NameValueExpression<String>> getContent() {
        return this.expressions;
    }

    @Override
    protected String getToStringInfix() {
        return " && ";
    }

    @Override
    public ApiVersionHeadersRequestCondition combine(ApiVersionHeadersRequestCondition other) {
        HeadersRequestCondition newHeadersRequestCondition = headersRequestCondition.combine(other.headersRequestCondition);
        return new ApiVersionHeadersRequestCondition(newHeadersRequestCondition);
    }

    @Null
    @Override
    public ApiVersionHeadersRequestCondition getMatchingCondition(HttpServletRequest request) {
        if (nonNull(request.getHeader(HEADER_NAME)) && getContent().isEmpty()) {
            return null;
        }
        HeadersRequestCondition headersRequestCondition = this.headersRequestCondition.getMatchingCondition(request);
        if (isNull(headersRequestCondition)) {
            return null;
        }
        return this;
    }

    @Override
    public int compareTo(ApiVersionHeadersRequestCondition other, HttpServletRequest request) {
        return this.headersRequestCondition.compareTo(other.headersRequestCondition, request);
    }
}
