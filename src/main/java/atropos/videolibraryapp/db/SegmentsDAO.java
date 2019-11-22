package atropos.videolibraryapp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import atropos.videolibraryapp.model.Segment;
/**
 * 
 * @author Tidd
 *
 */
public class SegmentsDAO {

	java.sql.Connection conn;

    public SegmentsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Segment getSegment(String name) throws Exception {
        
        try {
        	Segment segment = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                segment = generateSegment(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return segment;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    public boolean updateSegmentMark(Segment segment) throws Exception {
        try {
        	String query = "UPDATE VideoSegment SET isMarked=? WHERE name=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, segment.getIsMarked());
            ps.setString(2, segment.getName());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update report: " + e.getMessage());
        }
    }
    
    public boolean deleteSegment(Segment segment) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM VideoSegment WHERE name = ?;");
            ps.setString(1, segment.getName());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public boolean addSegment(Segment segment) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment WHERE name = ?;");
            ps.setString(1, segment.getName());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
            	Segment s = generateSegment(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO Segment (name,character,url,isMarked) values(?,?,?,?,?);");
            ps.setString(1,  segment.getName());
            ps.setString(2,  segment.getCharacter());
            ps.setString(3,  segment.getUrl());
            ps.setBoolean(4,  segment.getIsMarked());
            ps.setString(5, segment.getSite());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public List<Segment> getAllSegments() throws Exception {
        
        List<Segment> allSegments = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM VideoSegment";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	Segment s = generateSegment(resultSet);
                allSegments.add(s);
            }
            resultSet.close();
            statement.close();
            return allSegments;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }
    
    private Segment generateSegment(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        String character = resultSet.getString("character");
        String url  = resultSet.getString("url");
        Boolean isMarked = resultSet.getBoolean("isMarked");
        String site = resultSet.getString("site");
        return new Segment (name, character, url, isMarked, site);
    }

}