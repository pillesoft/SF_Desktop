/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author ihorvath
 */
public class DbContext {

  private static SessionFactory sessionFactory;

  public static void CreateDatabase(String DbName, char[] pwd, char[] encrpwd) {
    if (DbName.isEmpty()) {
      throw new IllegalArgumentException("DbName is empty");
    }
    if (Files.exists(getDbFileName(DbName))) {
      throw new IBHDatabaseException(IBHDatabaseException.AVAILABLE);
    }
    HashMap<String, String> sett = new HashMap<String, String>();
    sett.put("hibernate.connection.url", String.format("jdbc:h2:%s;CIPHER=AES", getDbPath(DbName).toString()));
    sett.put("hibernate.connection.username", DbName);
    sett.put("hibernate.connection.password", String.format("%s %s", String.valueOf(encrpwd), String.valueOf(pwd)));
    sett.put("hibernate.hbm2ddl.auto", "create");
    sett.put("hibernate.show_sql", "false");

    Init(sett);
    
  }
  
  public static void Connect(String DbName, char[] pwd, char[] encrpwd) {
    if (DbName.isEmpty()) {
      throw new IllegalArgumentException("DbName is empty");
    }
    if (!Files.exists(getDbFileName(DbName))) {
      throw new IBHDatabaseException(IBHDatabaseException.NOTAVAILABLE);
    }
    Map sett = new HashMap<String, String>();
    sett.put("hibernate.show_sql", "false");
    sett.put("hibernate.session.events.log", "false");
    sett.put("hibernate.generate_statistics", "false");
    sett.put("hibernate.connection.url", String.format("jdbc:h2:%s;IFEXISTS=TRUE;CIPHER=AES", getDbPath(DbName).toString()));
    sett.put("hibernate.connection.username", DbName);
    sett.put("hibernate.connection.password", String.format("%s %s", String.valueOf(encrpwd), String.valueOf(pwd)));
    
    Init(sett);
  }
  
  private static Path getDbPath(String DbName) {
    String appdatapath = System.getenv("APPDATA");
    Path dbpath = Paths.get(appdatapath, "IBHSP", DbName);

    return dbpath;
  }
  
  private static Path getDbFileName(String DbName) {
    return getDbPath(DbName.concat(".mv.db"));
  }
  
  private static void Init(Map sett) {
    final StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .applySettings(sett)
            .build();

    sessionFactory = new MetadataSources(standardRegistry)
            .buildMetadata()
            .buildSessionFactory();

  }
  
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
  
  
}
