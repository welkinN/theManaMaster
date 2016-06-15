package model.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOWorld {
	
	DBConnection dbConnection;
	ArrayList<String> worldMap;
	
	
	public DAOWorld(){
			dbConnection = DBConnection.getInstance();
			/*
			for(int y=0; y<12; y++){
				for(int x=0; x<20; x++){
					map[x][y] = worldMap.get(x + (y*20));
					System.out.print(" " + map[x][y] + " ");
				}
				System.out.println("");
			}
			*/		
	}
	
	public ArrayList<String> loadWorldById(final int id) {
		worldMap = new ArrayList<String>();
		try {
			CallableStatement call;
			call = dbConnection.getConnection().prepareCall("{call loadingWorld"+id + "(?)}");
			
			call.setInt(1, id);
			call.execute();
			final ResultSet resultat = call.getResultSet();
			while(resultat.next()){
				for(int x = 1 ; x <= 20 ; x++)
					worldMap.add(resultat.getString(x));
				}
			return worldMap;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	public ArrayList<String> loadWorldByName(final String name) {
		try {
			worldMap = new ArrayList<String>();
			CallableStatement call;
			call = dbConnection.getConnection().prepareCall("{call loading"+name+"(?)}");
			
			call.setString(1, name);
			call.execute();
			final ResultSet resultat = call.getResultSet();
			while(resultat.next()){
				for(int x = 1 ; x <= 20 ; x++)
					worldMap.add(resultat.getString(x));
				}
			return worldMap;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#find(java.lang.String)
	 *//*
	public World find(final String name) {
		World helloWorld = new World();

		try {
			final String sql = "{call helloworldByKey(?)}";
			final CallableStatement call = dbConnection.getConnection().prepareCall(sql);
			call.setString(1, key);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if (resultSet.first()) {
				helloWorld = new HelloWorld(resultSet.getInt("id"), key, resultSet.getString("message"));
			}
			return helloWorld;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public void createWorldFromProcedure(World world){
		
	}
	
	public void updateWorldFromProcedure(World world){
		
	}
	
	public void deleteWorldFromProcedure(World world){
		
	}	
	*/


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* A mettre dans MODEL */
	public void getWorldFromQuery(int id){
		this.executeQuery("SELECT * FROM world" + id);
	}
	/* ------------------- */
	
	protected ArrayList<String> executeQuery(String query){
		ArrayList<String>worldMap = new ArrayList<String>();
		dbConnection.open();
		try {
			ResultSet resultat = dbConnection.getStatement().executeQuery(query);
			try{
				while(resultat.next()){
					for(int x = 1 ; x <= 20 ; x++)
						worldMap.add(resultat.getString(x + 1));
				}
			} catch (SQLException e){
				e.printStackTrace();
			} finally{
				if(resultat != null)
					resultat.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.close();
		}
		System.out.println(worldMap);
		return worldMap;
		
	}
	
	
	
}
