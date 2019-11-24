package atropos.videolibraryapp.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import atropos.videolibraryapp.model.Playlist;
import atropos.videolibraryapp.model.Segment;

/*
 * @author Tidd
 */
public class PlaylistsDAO {
	java.sql.Connection conn;
	SegmentsDAO segDAO;

    public PlaylistsDAO(String rdsMySqlDatabaseUrl, String dbUsername, String dbPassword) {
    	try  {
    		conn = DatabaseUtil.connect(rdsMySqlDatabaseUrl, dbUsername, dbPassword);
    		segDAO = new SegmentsDAO(rdsMySqlDatabaseUrl, dbUsername, dbPassword);
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    
    
    public boolean createNewPlaylist(Playlist playlist) throws Exception{
    	try {
    			PreparedStatement ps = conn.prepareStatement("INSERT INTO Playlist VALUES(?);");
	            ps.setString(1,  playlist.getName());
	            ps.executeUpdate();
	            ps.close();
	            return true;
    	}
    	
    	 catch (Exception e) {
         	e.printStackTrace();
             throw new Exception("Failed in creating a Playlist in the DB: " + e.getMessage());
         } 
    	
    }
    
    public boolean deletePlaylist(Playlist playlist) throws Exception{
    	try {

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE name=?;");
            ps.setString(1,  playlist.getName());
            ps.executeUpdate();
            ps.close();
            return true;
    			
    	}
    	catch (Exception e) {
         	e.printStackTrace();
             throw new Exception("Failed in deleting Playlist from DB: " + e.getMessage());
         }
    }
    
    public Playlist getPlaylist(String name) throws Exception {
        
        try {
        	Playlist playlist = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
            		playlist = generatePlaylist(resultSet);			//If a new playlist name create a new playlist object
            	}  	
            resultSet.close();
            ps.close();
            
            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM PlaylistSegment WHERE playlistName=?;");
            ps2.setString(1,  name);
            ResultSet resultSet2 = ps2.executeQuery();
            while (resultSet2.next()) {
            		playlist.appendVideoSegment(segDAO.getSegment(resultSet2.getString("segmentName")));
            	}  	
            resultSet2.close();
            ps2.close();
            return playlist;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting Playlist from DB: " + e.getMessage());
        }
    }
    
    
    public boolean addSegmentToPlaylist(Playlist playlist) throws Exception {
        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PlaylistSegment WHERE playlistName = ? AND segmentName = ?;");
	            ps.setString(1, playlist.getName());
	            ps.setString(2, playlist.getLastSegmentName());
	            ResultSet resultSet = ps.executeQuery();
	            
	            // already present?
	            while (resultSet.next()) {
	            	Playlist s = generatePlaylist(resultSet);
	                resultSet.close();
	                return false;
	            }
	
	            ps = conn.prepareStatement("INSERT INTO PlaylistSegment values(?,?);");
	            ps.setString(1,  playlist.getName());
	            ps.setString(2,  playlist.getLastSegmentName());
	            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to add Segment to Playlist in DB: " + e.getMessage());
        }
    }
    
    
    public boolean deleteSegmentFromPlaylist(Playlist playlist) throws Exception {
        try {
	
        		PreparedStatement ps = conn.prepareStatement("DELETE FROM PlaylistSegment WHERE playlistName=? AND segmentName=?;");
	            ps.setString(1,  playlist.getName());
	            ps.setString(2,  playlist.getLastSegmentName());
	            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to delete Segment from Playlist in DB " + e.getMessage());
        }
    }

    
	public List<String> getAllPlaylistsNames() throws Exception {
	        
	        List<String> allPlaylists = new ArrayList<>();
	        try {
	        
	            Statement statement = conn.createStatement();
	            String query = "SELECT * FROM Playlist;";
	            ResultSet resultSet = statement.executeQuery(query);
	            while (resultSet.next()) {
	            	allPlaylists.add(resultSet.getString("name"));
	            }
				
	            resultSet.close();
	            statement.close();
	            return allPlaylists;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting list of Playlist names from DB: " + e.getMessage());
	        }
	    }

    
    

    public ArrayList<Playlist> getAllPlaylists() throws Exception {
        
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM PlaylistSegment ORDER BY (playlistName);";
            ResultSet resultSet = statement.executeQuery(query);
            String currentPlaylist = "";
            String pastPlaylist = "";
            Playlist s = new Playlist("");
            while (resultSet.next()) {
            	currentPlaylist = resultSet.getString("playlistName");
            	if (currentPlaylist != pastPlaylist){
            		if(pastPlaylist != "") {
            			allPlaylists.add(s);
            		}
            		pastPlaylist = currentPlaylist;
            		s = generatePlaylist(resultSet);
            	}
            	s.appendVideoSegment(segDAO.getSegment(resultSet.getString("segmentName")));
            }
			allPlaylists.add(s);
            resultSet.close();
            statement.close();
            return allPlaylists;

        } catch (Exception e) {
            throw new Exception("Failed in getting list of Playlists from DB: " + e.getMessage());
        }
    }
    
    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        return new Playlist (name);
    }
    
}
