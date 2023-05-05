package cz.educanet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class StudentBean {
    @Inject
    private EntityManagerFactoryService emfs;

    public void add(String fullname, String dateOfBirth, float averageGrade) {
        EntityManager em = emfs.getEmf().createEntityManager();

        StudentEntity se = new StudentEntity();
        se.setFullname(fullname);
        se.setDateOfBirth(dateOfBirth);
        se.setAverageGrade(averageGrade);

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(se);
        et.commit();
        em.close();
    }

    public List<StudentEntity> getAll() {
        EntityManager em = emfs.getEmf().createEntityManager();

        TypedQuery<StudentEntity> query = em.createQuery("select student from StudentEntity as student", StudentEntity.class);

        List<StudentEntity> result = query.getResultList();

        return result;
    }

    public Double getAverage() {
        EntityManager em = emfs.getEmf().createEntityManager();

        TypedQuery<Double> query = em.createQuery("select avg(student.averageGrade) from StudentEntity as student", Double.class);

        return query.getSingleResult();
    }


    public StudentEntity getStudent(int id) {
        EntityManager em = emfs.getEmf().createEntityManager();
        TypedQuery<StudentEntity> query = em.createQuery("select student from StudentEntity as student where student.id = :id", StudentEntity.class);
        query.setParameter("id", id);
        List<StudentEntity> result = query.getResultList();

        return result.get(0);
    }

    public void updateStudent(String fullname, String dateOfBirth, float averageGrade, int id) {
        EntityManager em = emfs.getEmf().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();


        Query query = em.createQuery("UPDATE StudentEntity SET fullname = :fullname, dateOfBirth = :dateOfBirth, averageGrade = :averageGrade WHERE id = :id");
        query.setParameter("fullname", fullname);
        query.setParameter("dateOfBirth", dateOfBirth);
        query.setParameter("averageGrade", averageGrade);
        query.setParameter("id", id);
        query.executeUpdate();

        et.commit();

        em.close();
    }

    public void delete(int id) {
        EntityManager em = emfs.getEmf().createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();

        Query query = em.createQuery("DELETE FROM StudentEntity WHERE id = :id");
        query.setParameter("id", id);
        int updatedRows = query.executeUpdate();
        System.out.println(updatedRows);

        et.commit();
    }
}
