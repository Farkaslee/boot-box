package com.boot.box.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MybatisGeneratorTest {

	public static void generateMybaitsFiles() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException{
		 System.out.println("start generator ...");
        //读取文件
        File configFile = new File(MybatisGeneratorTest.class.getClassLoader().getResource("generatorConfig.xml").getFile());
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        //true:覆盖生成
        DefaultShellCallback callback = new DefaultShellCallback(false);
        try {
            Configuration config = cp.parseConfiguration(configFile);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            ProgressCallback progressCallback = new VerboseProgressCallback();
            myBatisGenerator.generate(progressCallback);
            System.out.println("代码成功生成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
         System.out.println("end generator!");

	}

    public static  void main(String[] a){
        try {
            generateMybaitsFiles();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable e){
            e.printStackTrace();
        }
    }

}
