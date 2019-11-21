package atropos.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Playlist;
import model.Segment;


public class PlaylistsDAO {
	java.sql.Connection conn;
	SegmentsDAO segDAO;

    public PlaylistsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Playlist getPlaylist(String name) throws Exception {
        
        try {
        	Playlist playlist = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            String playlistName = null;
            while (resultSet.next()) {
            	if (playlistName != resultSet.getString("name")) {
            		playlist = generatePlaylist(resultSet);			//If a new playlist name create a new playlist object
            	}
            	playlist.appendVideoSegment(segDAO.getSegment(resultSet.getString("segmentName"))); //Get and append the segment object with the specific name
            }
            resultSet.close();
            ps.close();
            
            return playlist;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    
    
    //TODO replace this as add segment to playlist and remove segment from playlist
    public boolean updatePlaylistMark(Playlist playlist) throws Exception {
        try {
        	String query = "UPDATE Playlist SET isMarked=? WHERE name=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, Playlist.isMarked);
            ps.setString(2, Playlist.name);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update report: " + e.getMessage());
        }
    }
    
    public boolean deletePlaylist(Playlist playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Playlist WHERE name = ?;");
            ps.setString(1, Playlist.name);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public boolean addPlaylist(Playlist playlist) throws Exception {
        try {
        	for(int i=0; i < playlist.segments.size(); i++) {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Playlist WHERE name = ? and segmentName = ?;");
	            ps.setString(1, playlist.name);
	            ps.setString(2, playlist.segmemts[i]);
	            ResultSet resultSet = ps.executeQuery();
	            
	            // already present?
	            while (resultSet.next()) {
	            	Playlist s = generatePlaylist(resultSet);
	                resultSet.close();
	                return false;
	            }
	
	            ps = conn.prepareStatement("INSERT INTO Playlist (name,segmentName) values(?,?);");
	            ps.setString(1,  playlist.name);
	            ps.setString(2,  playlist.segments[i]);
	            ps.execute();
	        	}
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    
    //TODO finish this 
    public List<Playlist> getAllPlaylists() throws Exception {
        
        List<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM VideoPlaylist";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	Playlist s = generatePlaylist(resultSet);
                allPlaylists.add(s);
            }
            resultSet.close();
            statement.close();
            return allPlaylists;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }
    
    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        return new Playlist (name);
    }
    
}
