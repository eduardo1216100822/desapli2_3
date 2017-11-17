package cerr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cerr.model.Animal;



public class AnimalDAOImpl implements AnimalDAO{
	private Connection connection;
	private Statement statemet;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	
	public AnimalDAOImpl() {
		getConnection();
	}
	
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres","1234");
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	@Override
	public void createAnimal(Animal animal) {
		if(connection!=null) {
			try {
				preparedStatement = connection.prepareStatement("INSERT INTO animals (name, home,age) values(?,?,?);");
				preparedStatement.setString(1, animal.getName());
				preparedStatement.setString(2, animal.getHome());
				preparedStatement.setInt(3, animal.getAge());
				preparedStatement.execute();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Animal readAnimal(Long id) {
			Animal animal = null;
		
		if(connection!=null) {
			try {
				preparedStatement = connection.prepareStatement("Select * from animals WHERE id = ?;");
				preparedStatement.setLong(1, id);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					animal = new Animal(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return animal;
	}

	@Override
	public List<Animal> readAllAnimals() {
		List<Animal> animals = new ArrayList<Animal>();
		
		if(connection!=null) {
			try {
				preparedStatement = connection.prepareStatement("Select * from animals;");
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					
					Animal animal = new Animal(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4));
					animals.add(animal);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return animals;
	}

	@Override
	public void updateAnimal(Animal animal) {
		try {
			preparedStatement = connection.prepareStatement("UPDATE animals set name = ?, home =?, age= ? WHERE id = ?;");
			preparedStatement.setString(1, animal.getName());
			preparedStatement.setString(2, animal.getHome());
			preparedStatement.setInt(3, animal.getAge());
			preparedStatement.setLong(4, animal.getId());
			preparedStatement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAnimal(Long id) {
		if(connection!=null) {
			try {
				preparedStatement = connection.prepareStatement("DELETE FROM animals WHERE id = ?;");
				preparedStatement.setLong(1, id);
				preparedStatement.execute();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
