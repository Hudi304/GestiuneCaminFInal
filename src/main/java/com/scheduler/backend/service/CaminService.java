package com.scheduler.backend.service;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.scheduler.backend.model.Camin;
import com.scheduler.backend.repository.CaminRepository;

public class CaminService implements Service<Camin> {

	private CaminRepository caminRepository = new CaminRepository();

	@Override
	public Camin save(Camin entity) {
		return caminRepository.save(entity);
	}

	@Override
	public Camin findById(Long id) {
		return caminRepository.findById(id);
	}

	@Override
	public List<Camin> findAll() { 
		return caminRepository.findAll();
	}

	@Override
	public boolean delete(Camin entity) {
		return caminRepository.delete(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return caminRepository.deleteById(id);
	}

	public List<Camin> findByAdmin(String admin) {
		return caminRepository.findByAdmin(admin);
	}

	public List<Camin> findByAdress(String adress) {
		return caminRepository.findByAdress(adress);
	}

	public Camin findByNumber(Integer number) {
		return caminRepository.findByNumber(number);
	}

	public DefaultTableModel caminToTableModel(List<Camin> camine) {
		String[] columns = { "number", "admin", "adress", "roomCount" };
		String[][] tuple = new String[camine.size()][columns.length];
		int i = 0;
		for (Camin camin : camine) {
			tuple[i][0] = camin.getNumber().toString();
			tuple[i][1] = camin.getAdmin();
			tuple[i][2] = camin.getAdress();
			tuple[i][3] = camin.getRoomCount().toString();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(tuple, columns);
		return model;
	}

	public boolean isInteger(String s) {// daca cineva vede asta vreodata, imi pare rau...... :( :( :(
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}

		return true;
	}

}
