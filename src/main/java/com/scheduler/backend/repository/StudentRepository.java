package com.scheduler.backend.repository;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.scheduler.backend.model.Student;
import com.scheduler.backend.service.HibernateService;


@SuppressWarnings("unchecked")
public class StudentRepository implements Repository<Student> {

	@Override
	public Student save(Student entity) {
		try{
			Session databaseSession = HibernateService.getSessionFactory().openSession();
			databaseSession.beginTransaction();
			databaseSession.saveOrUpdate(entity);
			databaseSession.save(entity);
			databaseSession.getTransaction().commit();
			databaseSession.close();
			}catch(HibernateException ex) {
				ex.printStackTrace();
			}
			return entity;
		}
	
	public DefaultTableModel studentToTableModel( List<Student> students ) {
		String[] columns= {"ID","FirstName","LastName","Sex","RoomNr","Faculty","YearOfStudy","Email","Media"};
		String[][] tuple = new String[students.size()][columns.length] ;
		int i=0;
		for(Student student:students){
			tuple[i][0] = student.getId().toString();
			tuple[i][1] = student.getFirstName();
			tuple[i][2] = student.getLastName(); 
			tuple[i][3] = student.getSex();
			tuple[i][4] = student.getRoomNr()+"";
			tuple[i][5] = student.getFaculty();
			tuple[i][6] = student.getYearStudy();
			tuple[i][7] = student.getEmail();
			tuple[i][8] = student.getMedia().toString();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(tuple,columns);		
		return model;
	}
	
	public List<Student> search(String searchComboBox, String searchTextField){
		
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		databaseSession.beginTransaction();
		
		Query query=databaseSession.createQuery("from com.scheduler.backend.model.Student S WHERE S."+searchComboBox+"='"+ searchTextField+"'");
//		query.setParameter(0, searchComboBox);
//		query.setParameter(1, searchTextField);
		System.out.println(searchComboBox);
		System.out.println(searchTextField);
		List<Student> result=query.list();
		databaseSession.close();
		if(result.size()!=0)
			return result;
		else
			return null;
	}

	@Override
	public Student findById(Long id) {
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		Student found=(Student) databaseSession.get(Student.class, id);
		databaseSession.close();
		return found;
	}

	@Override
	public List<Student> findAll() {
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		databaseSession.beginTransaction();
		Query query=databaseSession.createQuery("from com.scheduler.backend.model.Student");
		List<Student> result=query.list();
		databaseSession.close();
		return result;
	}
	
	public List<Student> findByRoomNr(String roomNr) {
		String qry ="from com.scheduler.backend.model.Student S where S.roomNr = " + roomNr;
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		databaseSession.beginTransaction();
		Query query=databaseSession.createQuery(qry);
		//query.setParameter("param", roomNrString);
		//System.out.println(roomNrString);
		List<Student> result = query.list();
		databaseSession.close();
		if(result.size()!=0) {
			return result;
		}
		else {
			return null;
		}
		
		
	}

	@Override
	public boolean delete(Student entity) {
		try{
			Session databaseSession = HibernateService.getSessionFactory().openSession();
			databaseSession.beginTransaction();
			databaseSession.delete(entity);
			databaseSession.getTransaction().commit();
			databaseSession.close();
			return true;
		}catch(HibernateException e) {
				System.out.println("Cannot delete(Student.repository)");
				e.printStackTrace();
				return false;}
	}

	@Override
	public boolean deleteById(Long id) {
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		databaseSession.beginTransaction();
		Student found = (Student) databaseSession.get(Student.class, id);
		if(found!=null)
		{
			databaseSession.delete(found);
			databaseSession.getTransaction().commit();
		}
		databaseSession.close();
			if (found != null)
				return true;
			else
				return false;
		}

	
}