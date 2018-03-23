package com.bo.tournament.jsonconverter;

import java.util.List;

public interface FieldsFilter {
    Object doFilter(Object obj, List<String> names)
            throws TournamentFieldException;
}