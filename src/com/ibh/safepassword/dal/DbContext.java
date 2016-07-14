/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author ihorvath
 */
public class DbContext {

  private static SessionFactory sessionFactory;

  public static void Init() {

    final StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    sessionFactory = new MetadataSources(standardRegistry)
            .buildMetadata()
            .buildSessionFactory();

  }
  
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
