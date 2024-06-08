package com.ethan.service;

import com.ethan.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeService {

    private EmployeeService(){}
    public static EmployeeService getInstance(){return Singleton.INSTANCE;}

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    public void addEmployee(Employee employee){
        // EntityManagerFactory 생성
        createEntityManager();

        // Transaction 시작
        entityManager.getTransaction().begin();

        entityManager.persist(employee);
        // Transaction 커밋
        entityManager.getTransaction().commit();
        closeEntityManager();
    }

    public List<Employee> getList(){
        createEntityManager();
        List<Employee> list = entityManager.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        closeEntityManager();
        return  list;
    }

    /** EntityManagerFactory 생성 */
    private void createEntityManager(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /** EntityManager 종료 */
    private void closeEntityManager(){
        entityManager.close();
        entityManagerFactory.close();
    }

    private static class Singleton{
        private static final EmployeeService INSTANCE = new EmployeeService();
    }
}
