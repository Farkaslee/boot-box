package com.boot.box.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class DataTypeResolver extends JavaTypeResolverDefaultImpl {

	protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
		FullyQualifiedJavaType answer;

		if (column.getScale() > 0 || forceBigDecimals) {
			answer = defaultType;
		} else {
			answer = new FullyQualifiedJavaType(Long.class.getName());
			/*
			 * } else { answer = new
			 * FullyQualifiedJavaType(Integer.class.getName()); }else if
			 * (column.getLength() > 4) { answer = new
			 * FullyQualifiedJavaType(Integer.class.getName()); } else { answer
			 * = new FullyQualifiedJavaType(Short.class.getName()); }
			 */

		}
		return answer;
	}
}
