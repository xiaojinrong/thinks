package com.xiao.tools.db.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.xiao.tools.db.model.DBEntity;
import com.xiao.tools.db.model.RowEntity;
import com.xiao.tools.db.model.TableEntity;
import com.xiao.tools.db.structure.AnnotationBuilder;
import com.xiao.tools.db.structure.AttributeBuilder;
import com.xiao.tools.db.structure.ClassBuilder;
import com.xiao.tools.db.structure.MethodBuilder;
import com.xiao.tools.db.structure.ParamBuilder;
import com.xiao.tools.io.FileUtil;
import com.xiao.tools.string.StringUtil;

public class DBUtil {

	private static Connection connection;

	private static List<TableEntity> tables = new ArrayList<TableEntity>();

	private DBUtil() {
	};

	/**
	 * 获取表结构
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void init(DBEntity db) {
		try {
			tables.clear();
			connection = ConnectionUtil.connection(db);
			DatabaseMetaData dm = connection.getMetaData();
			String dbName = connection.getCatalog();
			ResultSet tRs = dm.getTables(dbName, "%", "%", new String[] { "TABLE" });
			while (tRs.next()) {
				TableEntity table = new TableEntity();
				table.setName(tRs.getString("TABLE_NAME"));
				table.setRemark(tRs.getString("REMARKS"));
				ResultSet colRs = dm.getColumns(dbName, "%", table.getName(), "%");
				ResultSet primaryKeyResultSet = dm.getPrimaryKeys(dbName, null, table.getName());
				List<RowEntity> rows = new ArrayList<RowEntity>();
				while (colRs.next()) {
					RowEntity row = new RowEntity();
					row.setName(colRs.getString("COLUMN_NAME"));
					row.setType(colRs.getString("TYPE_NAME"));
					row.setLength(colRs.getInt("COLUMN_SIZE"));
					row.setDigits(colRs.getInt("DECIMAL_DIGITS"));
					row.setIsNull(colRs.getInt("NULLABLE"));
					row.setRemark(colRs.getString("REMARKS"));
					row.setDefaultValue(colRs.getString("COLUMN_DEF"));
					while (primaryKeyResultSet.next()) {
						if (colRs.getString("COLUMN_NAME").equals(primaryKeyResultSet.getString("COLUMN_NAME"))) {
							row.setPrimary(true);
						}
					}
					rows.add(row);
				}
				table.setRows(rows);
				tables.add(table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成JavaBean文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void generateBean(String path, String packageName) {
		List<ClassBuilder> classBuilders = new ArrayList<ClassBuilder>();
		for (TableEntity table : tables) {
			// 构建类
			ClassBuilder classBuilder = new ClassBuilder();
			String className = DBPropertyUtil.firstWordUpper(DBPropertyUtil.createNewName(table.getName()));
			String cdocumentInfo = DBPropertyUtil.lastWordChange(table.getRemark());
			// 所属包名
			classBuilder.setPackageName(packageName + ".entity");
			// 设置类名
			classBuilder.setClassName(className);
			// 设置文档注释
			classBuilder.setDocumentInfo(cdocumentInfo);
			// 设置依赖
			Set<String> dependencys = classBuilder.getDependencyPackages();
			dependencys.add("java.io.Serializable");
			// 设置接口
			List<String> interfaces = classBuilder.getInterfaces();
			interfaces.add("Serializable");
			// 设置属性
			List<AttributeBuilder> attributeBuilders = classBuilder.getAttributeBuilders();
			attributeBuilders.add(new AttributeBuilder("private static final", "long", "serialVersionUID", "1L", ""));
			// 设置方法
			List<MethodBuilder> methodBuilders = classBuilder.getMethodBuilders();
			for (RowEntity row : table.getRows()) {
				String propertyName = DBPropertyUtil.createNewName(row.getName());
				String propertyType = DBPropertyUtil.mapping(row.getType());
				String mdocumentInfo = row.getRemark();
				// 判断属性类型是否需要导包
				if (ClassProperty.BASIC_TYPE_DATA.equals(propertyType)) {
					dependencys.add(ClassProperty.PACKAGE_NAME_DATE);
				}
				// 构建属性
				AttributeBuilder attributeBuilder = new AttributeBuilder(ClassProperty.ACCESS_PRIVATE, propertyType,
						propertyName, mdocumentInfo);
				String methodName = DBPropertyUtil.firstWordUpper(propertyName);
				// 构建Getter方法
				String mGetName = ClassProperty.METHOD_NAME_GET + methodName;
				String mGetContent = "return " + propertyName + ";";
				MethodBuilder methodBuilderGet = new MethodBuilder(ClassProperty.ACCESS_PUBLIC, propertyType, mGetName,
						mGetContent);
				// 构建Setter方法
				String mSetName = ClassProperty.METHOD_NAME_SET + methodName;
				String mSetContent = "this." + propertyName + "=" + propertyName + ";";
				MethodBuilder methodBuilderSet = new MethodBuilder(ClassProperty.ACCESS_PUBLIC,
						ClassProperty.BASIC_TYPE_VOID, mSetName, mSetContent);
				methodBuilderSet.getParamBuilders().add(new ParamBuilder(propertyType, propertyName, mdocumentInfo));
				// 添加属性&方法
				attributeBuilders.add(attributeBuilder);
				methodBuilders.add(methodBuilderGet);
				methodBuilders.add(methodBuilderSet);
			}
			classBuilders.add(classBuilder);
		}
		for (ClassBuilder classBuilder : classBuilders) {
			BeanUtil.createBeanFile(path, classBuilder);
		}
	}

	/**
	 * 生成Dao
	 * 
	 * @param path
	 */
	public static void generateDao(String path, String packageName) {
		List<ClassBuilder> classBuilders = new ArrayList<ClassBuilder>();
		for (TableEntity table : tables) {
			ClassBuilder classBuilder = new ClassBuilder();
			String className = DBPropertyUtil.firstWordUpper(DBPropertyUtil.createNewName(table.getName()));
			String cdocumentInfo = DBPropertyUtil.lastWordChange(table.getRemark(), "数据接口");
			classBuilder.setPackageName(packageName + ".dao");
			classBuilder.setClassName(className + "Dao");
			classBuilder.setType("interface");
			classBuilder.setParentClass("BaseDaoAPI<" + className + ">");
			classBuilder.setDocumentInfo(cdocumentInfo);
			classBuilder.getAnnotationBuilders().add(new AnnotationBuilder("Mapper"));
			classBuilder.getDependencyPackages().add("org.apache.ibatis.annotations.Mapper");
			classBuilders.add(classBuilder);
			// 设置依赖
			Set<String> dependencys = classBuilder.getDependencyPackages();
			dependencys.add(packageName + ".entity." + className);
			dependencys.add(packageName + ".dao.BaseDaoAPI");
		}
		for (ClassBuilder classBuilder : classBuilders) {
			BeanUtil.createBeanFile(path, classBuilder);
		}
	}

	/**
	 * 生成Service
	 * 
	 * @param path
	 */
	public static void generateService(String ipath, String packageName) {
		// 接口
		List<ClassBuilder> iclassBuilders = new ArrayList<ClassBuilder>();
		// 实现
		List<ClassBuilder> implclassBuilders = new ArrayList<ClassBuilder>();

		for (TableEntity table : tables) {
			String className = DBPropertyUtil.firstWordUpper(DBPropertyUtil.createNewName(table.getName()));
			// 接口
			ClassBuilder iclassBuilder = new ClassBuilder();
			String icdocumentInfo = DBPropertyUtil.lastWordChange(table.getRemark(), "业务逻辑接口");
			iclassBuilder.setPackageName(packageName + ".service");
			iclassBuilder.setClassName(className + "ServiceAPI");
			iclassBuilder.setType("interface");
			iclassBuilder.setParentClass("BaseServiceAPI");
			iclassBuilder.setDocumentInfo(icdocumentInfo);
			// 设置依赖
			Set<String> dependencys = iclassBuilder.getDependencyPackages();
			dependencys.add(packageName + ".service.BaseServiceAPI");
			dependencys.add("java.util.List");
			dependencys.add(packageName + ".entity." + className);
			// 添加方法
			// 根据Id查询对象
			MethodBuilder iMethodGet_0 = new MethodBuilder("", className, "get");
			iMethodGet_0.getParamBuilders().add(new ParamBuilder("String", "id", "主键ID"));
			iMethodGet_0.setDocumentInfo("查询对象");
			// 查询对象
			MethodBuilder iMethodGet_1 = new MethodBuilder("", "List<" + className + ">", "query");
			iMethodGet_1.getParamBuilders()
					.add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className), "对象"));
			iMethodGet_1.setDocumentInfo("查询对象");

			// 保存对象
			MethodBuilder iMethodGet_2 = new MethodBuilder("", "int", "save");
			iMethodGet_2.getParamBuilders()
					.add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className), "对象"));
			iMethodGet_2.setDocumentInfo("保存对象");

			// 更新对象
			MethodBuilder iMethodGet_4 = new MethodBuilder("", "int", "update");
			iMethodGet_4.getParamBuilders()
					.add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className), "对象"));
			iMethodGet_4.setDocumentInfo("更新对象");

			// 删除对象
			MethodBuilder iMethodGet_5 = new MethodBuilder("", "int", "remove");
			iMethodGet_5.getParamBuilders().add(new ParamBuilder("String", "id", "主键ID"));
			iMethodGet_5.setDocumentInfo("删除对象");

			// 批量删除对象
			MethodBuilder iMethodGet_6 = new MethodBuilder("", "int", "removeBatch");
			iMethodGet_6.getParamBuilders().add(new ParamBuilder("List<String>", "ids", "主键ID"));
			iMethodGet_6.setDocumentInfo("批量删除对象");

			List<MethodBuilder> iMethodBuilders = iclassBuilder.getMethodBuilders();
			iMethodBuilders.add(iMethodGet_0);
			iMethodBuilders.add(iMethodGet_1);
			iMethodBuilders.add(iMethodGet_2);
			iMethodBuilders.add(iMethodGet_4);
			iMethodBuilders.add(iMethodGet_5);
			iMethodBuilders.add(iMethodGet_6);

			iclassBuilders.add(iclassBuilder);
			// 实现
			ClassBuilder implclassBuilder = new ClassBuilder();
			String implcdocumentInfo = DBPropertyUtil.lastWordChange(table.getRemark(), "业务逻辑接口实现类");
			implclassBuilder.setPackageName(packageName + ".service.impl");
			implclassBuilder.setClassName(className + "Service");
			implclassBuilder.setType("class");
			implclassBuilder.setParentClass("BaseService");
			implclassBuilder.setDocumentInfo(implcdocumentInfo);
			implclassBuilder.getInterfaces().add(iclassBuilder.getClassName());
			implclassBuilder.getAnnotationBuilders()
					.add(new AnnotationBuilder("Service", DBPropertyUtil.firstWordLower(iclassBuilder.getClassName())));
			implclassBuilder.getAttributeBuilders().add(new AttributeBuilder("public", className + "Dao",
					DBPropertyUtil.firstWordLower(className + "Dao"),
					DBPropertyUtil.lastWordChange(table.getRemark(), "数据接口"), new AnnotationBuilder("Resource")));
			Set<String> implDependencys = implclassBuilder.getDependencyPackages();
			implDependencys.add("org.springframework.stereotype.Service");
			implDependencys.add("javax.annotation.Resource");
			implDependencys.add(packageName + ".service." + iclassBuilder.getClassName());
			implDependencys.add(packageName + ".dao." + className + "Dao");
			implDependencys.add(packageName + ".service.BaseService");
			implDependencys.add("java.util.List");
			implDependencys.add(packageName + ".entity." + className);
			// 添加方法
			// 根据Id查询对象
			MethodBuilder implMethodGet_0 = new MethodBuilder("public", className, "get");
			implMethodGet_0.getParamBuilders().add(new ParamBuilder("String", "id", "主键ID"));
			implMethodGet_0.setContent("return " + DBPropertyUtil.firstWordLower(className) + "Dao.get(id);");
			implMethodGet_0.setDocumentInfo("查询对象");
			// 查询对象
			MethodBuilder implMethodGet_1 = new MethodBuilder("public", "List<" + className + ">", "query");
			implMethodGet_1.getParamBuilders()
					.add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className), "对象"));
			implMethodGet_1.setContent("return " + DBPropertyUtil.firstWordLower(className) + "Dao.query("
					+ DBPropertyUtil.firstWordLower(className) + ");");
			implMethodGet_1.setDocumentInfo("查询对象");

			// 保存对象
			MethodBuilder implMethodGet_2 = new MethodBuilder("public", "int", "save");
			implMethodGet_2.getParamBuilders()
					.add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className), "对象"));
			implMethodGet_2.setContent("return " + DBPropertyUtil.firstWordLower(className) + "Dao.save("
					+ DBPropertyUtil.firstWordLower(className) + ");");
			implMethodGet_2.setDocumentInfo("保存对象");

			// 更新对象
			MethodBuilder implMethodGet_4 = new MethodBuilder("public", "int", "update");
			implMethodGet_4.getParamBuilders()
					.add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className), "对象"));
			implMethodGet_4.setContent("return " + DBPropertyUtil.firstWordLower(className) + "Dao.update("
					+ DBPropertyUtil.firstWordLower(className) + ");");
			implMethodGet_4.setDocumentInfo("更新对象");

			// 删除对象
			MethodBuilder implMethodGet_5 = new MethodBuilder("public", "int", "remove");
			implMethodGet_5.getParamBuilders().add(new ParamBuilder("String", "id", "主键ID"));
			implMethodGet_5.setContent("return " + DBPropertyUtil.firstWordLower(className) + "Dao.remove(id);");
			implMethodGet_5.setDocumentInfo("删除对象");

			// 批量删除对象
			MethodBuilder implMethodGet_6 = new MethodBuilder("public", "int", "removeBatch");
			implMethodGet_6.getParamBuilders().add(new ParamBuilder("List<String>", "ids", "主键ID"));
			implMethodGet_6.setContent("return " + DBPropertyUtil.firstWordLower(className) + "Dao.removeBatch(ids);");
			implMethodGet_6.setDocumentInfo("批量删除对象");

			List<MethodBuilder> implMethodBuilders = implclassBuilder.getMethodBuilders();
			implMethodBuilders.add(implMethodGet_0);
			implMethodBuilders.add(implMethodGet_1);
			implMethodBuilders.add(implMethodGet_2);
			implMethodBuilders.add(implMethodGet_4);
			implMethodBuilders.add(implMethodGet_5);
			implMethodBuilders.add(implMethodGet_6);

			implclassBuilders.add(implclassBuilder);
		}
		for (int i = 0; i < iclassBuilders.size(); i++) {
			BeanUtil.createBeanFile(ipath, iclassBuilders.get(i));
			BeanUtil.createBeanFile(ipath, implclassBuilders.get(i));
		}
	}

	/**
	 * 生成Controller
	 * 
	 * @param path
	 */
	public static void generateController(String path, String packageName) {
		List<ClassBuilder> classBuilders = new ArrayList<ClassBuilder>();
		for (TableEntity table : tables) {
			ClassBuilder classBuilder = new ClassBuilder();
			String className = DBPropertyUtil.firstWordUpper(DBPropertyUtil.createNewName(table.getName()));
			String cdocumentInfo = DBPropertyUtil.lastWordChange(table.getRemark(), "Controller");
			classBuilder.setPackageName(packageName + ".controller");
			classBuilder.setClassName(className + "Control");
			classBuilder.setType("class");
			classBuilder.setDocumentInfo(cdocumentInfo);
			classBuilder.getAnnotationBuilders().add(new AnnotationBuilder("RestController"));
			classBuilder.getAnnotationBuilders()
					.add(new AnnotationBuilder("RequestMapping", "/" + DBPropertyUtil.firstWordLower(className)));
			Set<String> dependencys = classBuilder.getDependencyPackages();
			dependencys.add("com.alibaba.fastjson.JSON");
			dependencys.add(packageName + ".entity." + className);
			dependencys.add(packageName + ".service." + className + "ServiceAPI");
			dependencys.add("javax.annotation.Resource");
			dependencys.add("java.util.List");
			dependencys.add("org.springframework.web.bind.annotation.GetMapping");
			dependencys.add("org.springframework.web.bind.annotation.PathVariable");
			dependencys.add("org.springframework.web.bind.annotation.PostMapping");
			dependencys.add("org.springframework.web.bind.annotation.RequestMapping");
			dependencys.add("org.springframework.web.bind.annotation.RestController");
			String serviceAPI = DBPropertyUtil.firstWordLower(className + "ServiceAPI");
			classBuilder.getAttributeBuilders().add(new AttributeBuilder("public", className + "ServiceAPI", serviceAPI,
					DBPropertyUtil.lastWordChange(table.getRemark(), "服务接口"), new AnnotationBuilder("Resource")));
			// 添加方法
			// 根据Id查询对象
			MethodBuilder methodGet_0 = new MethodBuilder("public", "String", "get");
			methodGet_0.getAnnotationBuilders().add(new AnnotationBuilder("GetMapping", "/get/{id}"));
			methodGet_0.getParamBuilders().add(new ParamBuilder("String", "id", "主键ID", new AnnotationBuilder("PathVariable", "id")));
			methodGet_0.setContent(className + " " + DBPropertyUtil.firstWordLower(className) + " = " + serviceAPI + ".get(id);");
			methodGet_0.setContent("return JSON.toJSONString(" + DBPropertyUtil.firstWordLower(className) + ");");
			methodGet_0.setDocumentInfo("根据主键ID获取对象");
			// 查询对象
			MethodBuilder methodGet_1 = new MethodBuilder("public", "String", "query");
			methodGet_1.getAnnotationBuilders().add(new AnnotationBuilder("GetMapping", "/query"));
			methodGet_1.getParamBuilders().add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className), "对象"));
			methodGet_1.setContent("List<"+className + "> " + DBPropertyUtil.firstWordLower(className) + "s = " + serviceAPI + ".query("+DBPropertyUtil.firstWordLower(className)+");");
			methodGet_1.setContent("return JSON.toJSONString(" + DBPropertyUtil.firstWordLower(className) + "s);");
			methodGet_1.setDocumentInfo("查询对象");
			
			// 保存对象
			MethodBuilder methodGet_2 = new MethodBuilder("public", "String", "save");
			methodGet_2.getAnnotationBuilders().add(new AnnotationBuilder("PostMapping", "/save"));
			methodGet_2.getParamBuilders().add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className),
					DBPropertyUtil.firstWordLower(className) + "对象"));
			methodGet_2.setContent(
					"int count = " + serviceAPI + ".save(" + DBPropertyUtil.firstWordLower(className) + ");");
			methodGet_2.setContent("return \"保存\"+count;");
			methodGet_2.setDocumentInfo("保存对象");
			// 更新对象
			MethodBuilder methodGet_3 = new MethodBuilder("public", "String", "update");
			methodGet_3.getAnnotationBuilders().add(new AnnotationBuilder("PostMapping", "/update"));
			methodGet_3.getParamBuilders().add(new ParamBuilder(className, DBPropertyUtil.firstWordLower(className),
					DBPropertyUtil.firstWordLower(className) + "对象"));
			methodGet_3.setContent(
					"int count = " + serviceAPI + ".update(" + DBPropertyUtil.firstWordLower(className) + ");");
			methodGet_3.setContent("return \"更新\"+count;");
			methodGet_3.setDocumentInfo("更新对象");
			// 删除对象
			MethodBuilder methodGet_4 = new MethodBuilder("public", "String", "delete");
			methodGet_4.getAnnotationBuilders().add(new AnnotationBuilder("PostMapping", "/delete/{id}"));
			methodGet_4.getParamBuilders()
					.add(new ParamBuilder("String", "id", "主键ID", new AnnotationBuilder("PathVariable", "id")));
			methodGet_4.setContent("int count = " + serviceAPI + ".remove(id);");
			methodGet_4.setContent("return \"删除\"+count;");
			methodGet_4.setDocumentInfo("删除对象");
			List<MethodBuilder> methods = classBuilder.getMethodBuilders();
			methods.add(methodGet_0);
			methods.add(methodGet_1);
			methods.add(methodGet_2);
			methods.add(methodGet_3);
			methods.add(methodGet_4);
			classBuilders.add(classBuilder);
		}
		for (ClassBuilder classBuilder : classBuilders) {
			BeanUtil.createBeanFile(path, classBuilder);
		}
	}

	/**
	 * 创建BaseDao
	 * 
	 * @param paths
	 * @param packageName
	 */
	public static void createBaseDao(String paths, String packageName) {
		String sourceDaoTxt = FileUtil.readSysText("/builder/BaseDaoAPI.txt");
		StringBuffer append = new StringBuffer();
		append.append("package " + packageName + ".dao;" + StringUtil.LINE);
		String targetDaoTxt = append.toString() + sourceDaoTxt;
		paths = paths + packageName.replace(".", "/") + "/dao/BaseDaoAPI.java";
		FileUtil.writeFile(paths, targetDaoTxt);
	}

	/**
	 * 创建Service
	 * 
	 * @param paths
	 * @param packageName
	 */
	public static void createBaseService(String paths, String packageName) {
		// 创建ServiceAPI
		String sourceSAPITxt = FileUtil.readSysText("/builder/BaseServiceAPI.txt");
		StringBuffer apiAppend = new StringBuffer();
		apiAppend.append("package " + packageName + ".service;" + StringUtil.LINE);
		String targetSAPITxt = apiAppend.toString() + sourceSAPITxt;
		String apiPaths = paths + packageName.replace(".", "/") + "/service/BaseServiceAPI.java";
		FileUtil.writeFile(apiPaths, targetSAPITxt);
		// 创建Service
		StringBuffer serviceAppend = new StringBuffer();
		serviceAppend.append("package " + packageName + ".service;" + StringUtil.LINE);
		String sourceServiceTxt = FileUtil.readSysText("/builder/BaseService.txt");
		String targetServiceTxt = serviceAppend.toString() + sourceServiceTxt;
		String servicePaths = paths + packageName.replace(".", "/") + "/service/BaseService.java";
		FileUtil.writeFile(servicePaths, targetServiceTxt);
	}

	/**
	 * 生成Mapper文件
	 * 
	 * @param paths
	 * @param packageName
	 */
	public static void generateMapper(String paths, String packageName) {
		for (TableEntity table : tables) {
			// 类名
			String className = DBPropertyUtil.firstWordUpper(DBPropertyUtil.createNewName(table.getName()));
			String cclassName = DBPropertyUtil.firstWordLower(className);
			StringBuffer resultMap = new StringBuffer();
			StringBuffer fieldSql = new StringBuffer();
			StringBuffer entityWhere = new StringBuffer();
			StringBuffer saveField = new StringBuffer();
			StringBuffer saveProperty = new StringBuffer();
			StringBuffer updateSql = new StringBuffer();
			String isPrimary = "";
			for (RowEntity row : table.getRows()) {
				// 属性名
				String propertyName = DBPropertyUtil.createNewName(row.getName());
				entityWhere.append(StringUtil.SPACE_TWELVE + "<if test=\""+ propertyName
						+ "!=null and " + propertyName + "!=''\">and " + row.getName() + " = #{"
						+ propertyName + "}</if>" + StringUtil.LINE);
				fieldSql.append(row.getName() + ",");
				saveField.append(StringUtil.SPACE_TWELVE + "<if test=\"" + propertyName
						+ "!=null and "+ propertyName + "!=''\">" + row.getName() + ",</if>"
						+ StringUtil.LINE);
				saveProperty.append(StringUtil.SPACE_TWELVE + "<if test=\""+ propertyName
						+ "!=null and "+ propertyName + "!=''\">#{" + propertyName + "},</if>"
						+ StringUtil.LINE);
				if (row.isPrimary()) {
					resultMap.insert(0, StringUtil.SPACE_EIGHT + "<id property=\"" + propertyName + "\" column=\""
							+ row.getName() + "\" />" + StringUtil.LINE);
					isPrimary = row.getName();
					continue;
				}
				resultMap.append(StringUtil.SPACE_EIGHT + "<result property=\"" + propertyName + "\" column=\""
						+ row.getName() + "\" />" + StringUtil.LINE);
				updateSql.append(StringUtil.SPACE_TWELVE + "<if test=\"" + propertyName
						+ "!=null and "+ propertyName + "!=''\">" + row.getName() + " = #{"
						+ propertyName + "},</if>" + StringUtil.LINE);
			}
			// 读取Mapper模板文件
			String mapperTxt = FileUtil.readSysText("/builder/mapper.txt");
			// 更新namespace占位符
			String mapper = mapperTxt.replace("{{0}}", packageName + ".dao." + className + "Dao")
					.replace("{{1}}", packageName + ".entity." + className)
					.replace("{{2}}", StringUtil.subLine(resultMap.toString()))
					.replace("{{3}}", StringUtil.sublength(fieldSql.toString())).replace("{{4}}", table.getName())
					.replace("{{5}}", isPrimary).replace("{{6}}", cclassName)
					.replace("{{7}}", StringUtil.subLine(entityWhere.toString()))
					.replace("{{8}}", StringUtil.subLine(saveField.toString()))
					.replace("{{9}}", StringUtil.subLine(saveProperty.toString()))
					.replace("{{10}}", StringUtil.subLine(updateSql.toString()))
					.replace("{{11}}", DBPropertyUtil.createNewName(isPrimary));
			String tempPath = paths + "/mapper/" + cclassName + "Mapper.xml";
			FileUtil.writeFile(tempPath, mapper);
		}
	}

	/**
	 * 创建Entity、Dao、Service、Controller
	 */
	public static void createAll(String path, String packageName) {
		createBaseDao(path, packageName);
		createBaseService(path, packageName);
		generateBean(path, packageName);
		generateDao(path, packageName);
		generateService(path, packageName);
		generateController(path, packageName);
		generateMapper(path, packageName);
	}
}
