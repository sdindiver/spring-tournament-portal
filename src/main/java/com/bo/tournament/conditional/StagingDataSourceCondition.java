package com.bo.tournament.conditional;
import java.util.Arrays;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class StagingDataSourceCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return Arrays.asList(context.getEnvironment().getActiveProfiles()).contains("staging");

	}

}