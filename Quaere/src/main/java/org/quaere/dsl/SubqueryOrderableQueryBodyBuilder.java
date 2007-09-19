package org.quaere.dsl;

import java.util.Comparator;


public interface SubqueryOrderableQueryBodyBuilder extends SubqueryQueryBodyBuilder {
    SubqueryOrderByBuilderContinuation orderBy(String identifier);
    SubqueryOrderByBuilderContinuation orderBy(String expression, Comparator comparator);
    SubqueryOrderByBuilderContinuation orderByDescending(String expression);
    SubqueryOrderByBuilderContinuation orderByDescending(String expression, Comparator comparator);
}
