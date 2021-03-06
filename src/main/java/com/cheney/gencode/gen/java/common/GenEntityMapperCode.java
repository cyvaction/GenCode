package com.cheney.gencode.gen.java.common;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.cheney.gencode.module.Entity;
import com.cheney.gencode.util.db.SQLUtil;
import com.cheney.gencode.util.string.StringUtil;
import com.cheney.gencode.util.vm.ToolBox;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.CreateTable;

public class GenEntityMapperCode {
	public static String gen(Map<String, String> parmMap, Entity entity) throws JSQLParserException {
		String code = "";
		// 入参设置
		String createTableSql = parmMap.get("createTableSql");
		// 设置类名
		CreateTable createTableStmt = (CreateTable) CCJSqlParserUtil.parse(createTableSql.trim());
		String className = StringUtil.tableNameToClassName(createTableStmt.getTable().getName(),entity.getTablePrefix(), "_");
		entity.setClassName(className);
		// 获取所需的属性map
		Map<String, String> columnMap = SQLUtil.getColumnNameAndAttrMap(createTableSql, "_");

		// 根据模板生成代码
		Properties p = new  Properties();
        //这里加载类路径里的模板而不是文件系统路径里的模板
        p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" ); 
		VelocityEngine velocityEngine = new VelocityEngine(p);
		VelocityContext velocityContext = new VelocityContext();
		StringWriter stringWriter = new StringWriter();
		velocityContext.put("entity", entity);
		velocityContext.put("columnMap", columnMap);
		velocityContext.put("parmMap", parmMap);
		velocityContext.put("toolBox", new ToolBox());
		velocityEngine.mergeTemplate("templates/code/java/mapper_entity.vm", "UTF-8", velocityContext,stringWriter);
		code += stringWriter.toString();

		return code;

	}
}
