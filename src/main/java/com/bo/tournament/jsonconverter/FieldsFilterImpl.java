package com.bo.tournament.jsonconverter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FieldsFilterImpl implements FieldsFilter{

	@Override
	public Object doFilter(Object obj, List<String> names)
			throws TournamentFieldException {
		Object values;
		if(obj instanceof Collection){
			List<Object> list = new ArrayList<>();
			for(Object item : (Collection)obj){
				list.add(getFilteredData(item, names));

			}
			values=list;
		}else{
			values = getFilteredData(obj, names);
		}
		        
        return values;
	}

	private Map<String, Object> getFilteredData(Object obj, List<String> names){
		Map<String, Object> values = new HashMap<String, Object>();
		try {
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			    for (Field field : clazz.getDeclaredFields()) {
			        String name = field.getName();
			        if (names.contains(name)) {
			            field.setAccessible(true);
			            values.put(name, field.get(obj));
			        }
			        if(names.contains(field.getAnnotation(JsonProperty.class).value())){
			        	field.setAccessible(true);
			        	values.put(name, field.get(obj));
			        }
			    }
			}
		} catch (Exception e) {
			throw new TournamentFieldException("Fields Exception Occured");
		}
		return values;
	}

}
