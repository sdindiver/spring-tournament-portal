package com.bo.tournament.jsonconverter;

import org.springframework.http.converter.HttpMessageConverter;

public interface TournamentMessageConverter<T> extends HttpMessageConverter<T>{

	public void setFieldParam(String fieldParam);
	public void setFieldFilter(FieldsFilter fieldFilter);
	
}
