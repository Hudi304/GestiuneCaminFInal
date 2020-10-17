package com.scheduler.backend.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.scheduler.backend.model.Room;
import com.scheduler.backend.service.HibernateService;

public class RoomRepository implements Repository<Room>{

	private static final int FIRST_ROOM_NUMBER = 1;
	private static final int LAST_ROOM_NUMBER = 532;
	
	@Override
	public Room save(Room entity) {
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

	@Override
	public Room findById(Long id) {
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		Room found = (Room) databaseSession.get(Room.class, id);
		databaseSession.close();
		return found;
	}
	
	public List<Room> findEmpltyRooms(){
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		databaseSession.beginTransaction();
		Query query=databaseSession.createQuery("from com.scheduler.backend.model.Room R where R.studentsNr = 0");
		@SuppressWarnings("unchecked")
		List<Room> result =query.list();
		databaseSession.close();
		return result;
	}
	
	public List<Room> findNotFullRooms(){
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		databaseSession.beginTransaction();
		@SuppressWarnings("unchecked")
		List <Room> result=(List<Room>) databaseSession.createCriteria(Room.class).add(Restrictions.ne("studentsNr", new Integer(4))).list();
		return result;
	}
	
	public Room findByRoomNumber(String roomNr) {
		Session databaseSession = HibernateService.getSessionFactory().openSession();
		databaseSession.beginTransaction();
		Query query=databaseSession.createQuery("from com.scheduler.backend.model.Room R where R.roomNr = " + roomNr);
		@SuppressWarnings("unchecked")
		List<Room> result = query.list();
		Room room = new Room();
		room = result.get(0);
		databaseSession.close();
		//System.out.println(room.getRoomNr());
		return room;
	}
	
	public DefaultTableModel roomToTableModel( List<Room> rooms ) {
		String[] columns= {"RoomNumber","NrStudents","Orientation"};
		String[][] tuple = new String[rooms.size()][columns.length] ;
		int i=0;
		for(Room room:rooms){
			tuple[i][0] = room.getRoomNr().toString();
			tuple[i][1] = room.getStudentsNr().toString();
			tuple[i][2] = room.getOrientation();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(tuple,columns);		
		return model; 
	}
	
	public static boolean isValidRoom(int roomNumber) {
		if (roomNumber >= FIRST_ROOM_NUMBER && roomNumber <= LAST_ROOM_NUMBER) {
			if (roomNumber % 100 < 33 && roomNumber % 100 > 0) {
				return true;
			}
		}
		return false;
	}

	
	public static void generateEmptyRooms() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/scheduler", "root", "test");
			@SuppressWarnings("unused")
			Statement stmt = con.createStatement();

			String orientare;

			for (int i = 1; i < 1000; i++) {
				if (i % 100 >= 5 && i % 100 <= 23)
					orientare = "Strada";
				else
					orientare = "Campus";
				String insert = "insert into rooms (roomNr, studentsNr, orientation) values (" + i + ",0,'" + orientare
						+ "') ;";
				PreparedStatement pstmt = con.prepareStatement(insert);
				if (isValidRoom(i)) {
					pstmt.execute(insert);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Room> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Room entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
