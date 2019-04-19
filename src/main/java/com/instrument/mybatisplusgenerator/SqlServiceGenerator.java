package com.instrument.mybatisplusgenerator;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SQL Service代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class SqlServiceGenerator {
    /**
     * 指定需要生成的表名称
     */
    private static String[] tableNames={"VIP","tt","tt_test"};
    /**
     * 指定数据库地址
     */
    private static String url = "jdbc:sqlserver://***.**.8.***;DatabaseName=***";
    /**
     * 指定数据库驱动名称
     */
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    /**
     * 数据库用户名
     */
    private static String userName = "***";
    /**
     * 数据库密码
     */
    private static String pwd = "***";
    /**
     * 指定代码生成人
     */
    private static String author ="ghf";
    /**
     * 指定模块名称
     */
    private static String moduleName ="generator";
    /**
     * 指定模块路径(java代码)
     */
    private static String modulePath ="com.instrument.mybatisplusgenerator";



    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        //指定代码生成人
        gc.setAuthor(author);
        //生成完毕后，是否默认打开生成目录
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(pwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //指定模块名称
        pc.setModuleName(moduleName);
        //指定模块路径
        pc.setParent(modulePath);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(true);
       // strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        //需要生成的表
        strategy.setInclude(tableNames);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
