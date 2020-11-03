package com.boot.box.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;


public class AddWhereCasePlugin extends PluginAdapter {

	private IntrospectedTable introspectedTableTwo;
	private XmlElement whereCaseElement;

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		introspectedTableTwo = introspectedTable;
		whereCaseElement = new XmlElement("sql");
		whereCaseElement.addAttribute(new Attribute("id", "wherecase"));
		whereCaseElement.addElement(new TextElement(" 1=1 "));
		for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			sb.append(introspectedColumn.getJavaProperty()).append(" != null and ").append(introspectedColumn.getJavaProperty())
					.append(" !=''");
			sb2.append(" and ").append(introspectedColumn.getActualColumnName()).append(" = #{")
					.append(introspectedColumn.getJavaProperty()).append("}");
			XmlElement newXmlElement = new XmlElement("if");
			newXmlElement.addAttribute(new Attribute("test", sb.toString()));
			newXmlElement.addElement(new TextElement(sb2.toString()));

			whereCaseElement.addElement(newXmlElement);
		}
		context.getCommentGenerator().addComment(whereCaseElement);

		XmlElement selectByCriteria = new XmlElement("select");
		selectByCriteria.addAttribute(new Attribute("id", "selectByCriteria"));
		if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
			selectByCriteria.addAttribute(new Attribute("resultMap", introspectedTable.getResultMapWithBLOBsId()));
		} else {
			selectByCriteria.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
		}

		FullyQualifiedJavaType parameterType = introspectedTable.getRules().calculateAllFieldsClass();

		selectByCriteria.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));

		context.getCommentGenerator().addComment(selectByCriteria);

		StringBuilder sb = new StringBuilder();
		sb.append("select ");

		if (stringHasValue(introspectedTable.getSelectByPrimaryKeyQueryId())) {
			sb.append('\'');
			sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
			sb.append("' as QUERYID,");
		}
		selectByCriteria.addElement(new TextElement(sb.toString()));
		selectByCriteria.addElement(getBaseColumnListElement());
		if (introspectedTable.hasBLOBColumns()) {
			selectByCriteria.addElement(new TextElement(","));
			selectByCriteria.addElement(getBlobColumnListElement());
		}

		sb.setLength(0);
		sb.append("from "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		selectByCriteria.addElement(new TextElement(sb.toString()));

		boolean and = false;
		for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
			sb.setLength(0);
			if (and) {
				sb.append("  and ");
			} else {
				sb.append("where ");
				and = true;
			}

			selectByCriteria.addElement(new TextElement(sb.toString()));
			selectByCriteria.addElement(getwhereCaseElement());
		}

		context.getCommentGenerator().addComment(selectByCriteria);

		// 生成countByCriteria语句
		XmlElement countByCriteria = new XmlElement("select");
		countByCriteria.addAttribute(new Attribute("id", "countByCriteria"));
		countByCriteria.addAttribute(new Attribute("resultType", "int"));
		countByCriteria.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
		sb = new StringBuilder();
		sb.append("select count(*) from ");
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		sb.append(" where ");
		countByCriteria.addElement(new TextElement(sb.toString()));
		countByCriteria.addElement(getwhereCaseElement());
		context.getCommentGenerator().addComment(countByCriteria);

		// 放到第2个元素
		document.getRootElement().addElement(2, whereCaseElement);
		document.getRootElement().addElement(3, selectByCriteria);
		document.getRootElement().addElement(4, countByCriteria);
		return true;
	}

	protected XmlElement getBaseColumnListElement() {
		XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
				introspectedTableTwo.getBaseColumnListId()));
		return answer;
	}

	protected XmlElement getBlobColumnListElement() {
		XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
				introspectedTableTwo.getBlobColumnListId()));
		return answer;
	}

	protected XmlElement getwhereCaseElement() {
		XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("refid", "wherecase"));
		return answer;
	}
}
