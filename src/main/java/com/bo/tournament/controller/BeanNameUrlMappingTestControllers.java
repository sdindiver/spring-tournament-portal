package com.bo.tournament.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class BeanNameUrlMappingTestControllers {
	@SuppressWarnings("unused")
	public static class ByControllerAdaptor extends AbstractController {
		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			ModelAndView data = new ModelAndView();
			data.addObject("value", "indiver");
			return data;
		}
	}

	// Experimental
	@SuppressWarnings("unused")
	public static class ByHttpRequestHandlerAdaptor implements HttpRequestHandler {
		public void handleRequest(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.getWriter().write("Bean name url mapping by http request is handled");

		}

	}
//	@Controller
//	public static class ByRequestMappingAdaptor {
//
//		public ByRequestMappingAdaptor(){
//			
//		}
//		@RequestMapping(value = "/hello")
//		public String handleRequest() {
//			return "beanMappingWithRequestAdaptor";
//		}
//
//	}

}
