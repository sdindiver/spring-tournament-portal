package com.bo.tournament.contraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Class-level constraint denoting that a field value should be confirmed by matching the value of another field.
 * @author Keith Donald
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=ConfirmValidator.class)
@Documented
public @interface Confirm {
	
	String message() default "{wrong field accessed!!!}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * The name of the field to be confirmed. e.g. password
	 */
	String field();

	/**
	 * The name of the field whose value must match the value of {@link #field()} to satisfy this constraint.
	 * Optional. If not specified, defaults to "confirm${field}" e.g. confirmPassword.
	 */
	String matches() default "";

	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		/**
		 * Used to specify multiple confirm fields per class.
		 */
		Confirm[] value();
		
	}
}
