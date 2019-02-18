/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2014 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.el;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import com.sun.el.lang.ELSupport;
import com.sun.el.lang.ExpressionBuilder;
import com.sun.el.stream.StreamELResolver;
import com.sun.el.util.MessageFactory;

/**
 * @see javax.el.ExpressionFactory
 * 
 * @author Jacob Hookom [jacob@hookom.net]
 * @author Kin-man Chung
 * @version $Change: 181177 $$DateTime: 2001/06/26 08:45:09 $$Author: kchung $
 */
public class ExpressionFactoryImpl extends ExpressionFactory {

	/**
	 * 
	 */
	public ExpressionFactoryImpl() {
		super();
	}

	public ExpressionFactoryImpl(Properties properties) {
		super();
		this.properties = properties;
		this.isBackwardCompatible22 = "true".equals(getProperty("javax.el.bc2.2"));
	}

	@Override
	public Object coerceToType(Object obj, Class<?> type) {
		try {
			return ELSupport.coerceToType(obj, type, isBackwardCompatible22);
		}
		catch (IllegalArgumentException ex) {
			throw new ELException(ex);
		}
	}

	@Override
	public MethodExpression createMethodExpression(ELContext context, String expression, Class<?> expectedReturnType, //
			Class<?>[] expectedParamTypes)//
	{
		ExpressionBuilder builder = new ExpressionBuilder(expression, context);
		MethodExpression me = builder.createMethodExpression(expectedReturnType,
				expectedParamTypes);
		if (expectedParamTypes == null && !me.isParametersProvided()) {
			throw new NullPointerException(MessageFactory.get("error.method.nullParms"));
		}
		return me;
	}

	@Override
	public ValueExpression createValueExpression(ELContext context,
			String expression, Class<?> expectedType) //
	{

		if (expectedType == null) {
			throw new NullPointerException(MessageFactory.get("error.value.expectedType"));
		}
		ExpressionBuilder builder = new ExpressionBuilder(expression, context);
		return builder.createValueExpression(expectedType);
	}

	@Override
	public ValueExpression createValueExpression(Object instance, Class<?> expectedType) {
		
		if (expectedType == null) {
			throw new NullPointerException(MessageFactory.get("error.value.expectedType"));
		}
		
		return new ValueExpressionLiteral(instance, expectedType);
	}

	public String getProperty(String key) {
		if (properties == null)
			return null;
		return properties.getProperty(key);
	}

	@Override
	public ELResolver getStreamELResolver() {
		return new StreamELResolver();
	}

	@Override
	public Map<String, Method> getInitFunctionMap() {
		Map<String, Method> funcs = new HashMap<String, Method>();
		return funcs;
	}

	private Properties properties;
	private boolean isBackwardCompatible22;
}
