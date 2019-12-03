package atropos.videolibraryapp.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import atropos.videolibraryapp.model.Playlist;
import atropos.videolibraryapp.model.Site;

public class SiteDAO {
	java.sql.Connection conn;
	
	 public SiteDAO(String rdsMySqlDatabaseUrl, String dbUsername, String dbPassword) {
	    	try  {
	    		conn = DatabaseUtil.connect(rdsMySqlDatabaseUrl, dbUsername, dbPassword);
	    	} catch (Exception e) {
	    		conn = null;
	    	}
	    }

	    
	    
	    public boolean registerSite(Site site) throws Exception{
	    	try {
	    			PreparedStatement ps = conn.prepareStatement("INSERT INTO Site VALUES(?);");
		            ps.setString(1, site.getWebsite());
		            ps.executeUpdate();
		            ps.close();
		            return true;
	    	}
	    	
	    	 catch (Exception e) {
	         	e.printStackTrace();
	             throw new Exception("Failed in registering site to the DB: " + e.getMessage());
	         } 
	    	
	    }
	    
	    public boolean unregisterSite(Site site) throws Exception{
	    	try {

				PreparedStatement ps = conn.prepareStatement("DELETE FROM Site WHERE website=?;");
	            ps.setString(1, site.getWebsite());
	            ps.executeUpdate();
	            ps.close();
	            return true;
	    			
	    	}
	    	catch (Exception e) {
	         	e.printStackTrace();
	             throw new Exception("Failed in deleting Site from DB: " + e.getMessage());
	         }
	    }
	    
	    public Site getSite(String url) throws Exception {
	        
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Site WHERE website=?;");
	            ps.setString(1,  url);
	            ResultSet resultSet = ps.executeQuery();
	            Site site = new Site("");
	            while (resultSet.next()) {
	            		site = generateSite(resultSet);			//If a new playlist name create a new playlist object"
	            	}  	
	            resultSet.close();
	            ps.close();
	            return site;

	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new Exception("Failed in getting Site from DB: " + e.getMessage());
	        }
	    }
	    
	    public ArrayList<Site> getAllSites() throws Exception {
	        
	        ArrayList<Site> allSites = new ArrayList<Site>();
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Site;");
	            ResultSet resultSet = ps.executeQuery();
	            while (resultSet.next()) {
	            	allSites.add(generateSite(resultSet));
	            }
	            resultSet.close();
	            ps.close();
	            return allSites;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting list of Sites from DB: " + e.getMessage());
	        }
	    }
	    
	    private Site generateSite(ResultSet resultSet) throws Exception {
	        String url  = resultSet.getString("website");
	        return new Site(url);
	    }
	    

}
