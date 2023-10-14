/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.EmployeeGeringer;
import datamodel.Recipe;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBGeringer {
	   static SessionFactory sessionFactory = null;

	   public static SessionFactory getSessionFactory() {
	      if (sessionFactory != null) {
	         return sessionFactory;
	      }
	      Configuration configuration = new Configuration().configure();
	      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	      sessionFactory = configuration.buildSessionFactory(builder.build());
	      return sessionFactory;
	   }

	   public static List<Recipe> listRecipe() {
	      List<Recipe> resultList = new ArrayList<Recipe>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

	      try {
	         tx = session.beginTransaction();
	         List<?> recs = session.createQuery("FROM Recipe").list();
	         for (Iterator<?> iterator = recs.iterator(); iterator.hasNext();) {
	        	 Recipe rec = (Recipe) iterator.next();
	            resultList.add(rec);
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }

	   public static List<Recipe> listRecipe(String keyword) {
	      List<Recipe> resultList = new ArrayList<Recipe>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         System.out.println((Recipe)session.get(EmployeeGeringer.class, 1)); // use "get" to fetch data
	        // Query q = session.createQuery("FROM Employee");
	         List<?> recs = session.createQuery("FROM RecipeTable").list();
	         for (Iterator<?> iterator = recs.iterator(); iterator.hasNext();) {
	        	 Recipe rec = (Recipe) iterator.next();
	            if (rec.getType().startsWith(keyword)) {
	               resultList.add(rec);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }

	   public static void Recipe(String Type, String Ingrediants, String Duration, String Directions ) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         session.save(new Recipe(Type, Ingrediants, Duration, Directions  ));
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	   }





	}
