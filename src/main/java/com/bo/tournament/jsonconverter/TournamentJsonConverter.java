package com.bo.tournament.jsonconverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TournamentJsonConverter implements TournamentMessageConverter<Object> {

	private HttpMessageConverter<Object> delegate = new MappingJackson2HttpMessageConverter();

	private String fieldsParam;
	private FieldsFilter fieldsFilter;

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		// TODO Auto-generated method stub
		return delegate.canRead(clazz, mediaType);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		// TODO Auto-generated method stub
		return delegate.canWrite(clazz, mediaType);
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		// TODO Auto-generated method stub
		return delegate.getSupportedMediaTypes();
	}

	@Override
	public Object read(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return delegate.read(clazz, inputMessage);
	}

	@Override
	public void write(Object t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String[] fields = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getParameterValues(fieldsParam);
		if (fields != null && fields.length != 0) {
			// get required field names
			List<String> names = new ArrayList<String>();
			for (String field : fields) {
				String[] f_names = field.split("\\s*,\\s*");
				names.addAll(Arrays.asList(f_names));
			}
			t = fieldsFilter.doFilter(t, names);
		}
		delegate.write(t, contentType, outputMessage);
	}

	@Override
	public void setFieldParam(String fieldsParam) {
		this.fieldsParam = fieldsParam;

	}

	@Override
	public void setFieldFilter(FieldsFilter fieldsFilter) {
		this.fieldsFilter = fieldsFilter;
	}

}
