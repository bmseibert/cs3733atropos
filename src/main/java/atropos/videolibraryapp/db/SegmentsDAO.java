package atropos.videolibraryapp.db;

import java.sql.*;
import java.util.ArrayList;
import atropos.videolibraryapp.model.Segment;
/**
 * 
 * @author Tidd
 *
 */
public class SegmentsDAO {

	java.sql.Connection conn;

    public SegmentsDAO(String rdsMySqlDatabaseUrl, String dbUsername, String dbPassword) {
    	try  {
    		conn = DatabaseUtil.connect(rdsMySqlDatabaseUrl, dbUsername, dbPassword);
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
            ps.close();

            ps = conn.prepareStatement("INSERT INTO VideoSegment VALUES(?,?,?,?,?);"); 
            ps.setString(1,  segment.getName());
            ps.setString(2,  segment.getCharacter());
            ps.setString(3,  segment.getUrl());
            ps.setBoolean(4,  segment.getIsMarked());
            ps.setBoolean(5, segment.getIsRemote());
            ps.executeUpdate();    
            ps.close();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public ArrayList<Segment> getAllSegments() throws Exception {
        
        ArrayList<Segment> allSegments = new ArrayList<Segment>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment;");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	Segment s = generateSegment(resultSet);
                allSegments.add(s);
            }
            resultSet.close();
            ps.close();
            return allSegments;

        } catch (Exception e) {
            throw new Exception("Failed in getting segments: " + e.getMessage());
        }
    }
    
    public ArrayList<Segment> getAllSegmentsByMarkRemote(boolean isMarked, boolean isRemote) throws Exception {
        
        ArrayList<Segment> allSegments = new ArrayList<Segment>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment where isMarked=? and isRemote=?;");
            ps.setBoolean(1, isMarked);
            ps.setBoolean(2, isRemote);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	Segment s = generateSegment(resultSet);
                allSegments.add(s);
            }
            resultSet.close();
            ps.close();
            return allSegments;

        } catch (Exception e) {
            throw new Exception("Failed in getting segments: " + e.getMessage());
        }
    }
    


    public ArrayList<Segment> searchSegmentsCharacterQoute(String qoute, String character, boolean isRemote) throws Exception{
    	ArrayList<Segment> allSegments = new ArrayList<Segment>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment where name LIKE ? AND `character` LIKE ? AND isRemote=?;");
            ps.setString(1, "%"+qoute+"%");
            ps.setString(2, "%"+character+"%");
            ps.setBoolean(3, isRemote);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	Segment s = generateSegment(resultSet);
                allSegments.add(s);
            }
            resultSet.close();
            ps.close();
            return allSegments;

        } catch (Exception e) {
            throw new Exception("Failed in getting search segments by character: " + e.getMessage());
        }
    }

    
    
    
    public ArrayList<Segment> searchSegmentsCharacter(String search, boolean isRemote) throws Exception{
    	ArrayList<Segment> allSegments = new ArrayList<Segment>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment where VideoSegment.character LIKE ? and isRemote=?;");
            ps.setString(1, "%"+search+"%");
            ps.setBoolean(2, isRemote);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	Segment s = generateSegment(resultSet);
                allSegments.add(s);
            }
            resultSet.close();
            ps.close();
            return allSegments;

        } catch (Exception e) {
            throw new Exception("Failed in getting search segments by character: " + e.getMessage());
        }
    }
    	
    
    public ArrayList<Segment> searchSegmentsQuote(String search, boolean isRemote) throws Exception{
    	ArrayList<Segment> allSegments = new ArrayList<Segment>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VideoSegment where name LIKE ? and isRemote=?;");
            ps.setString(1, "%"+search+"%");
            ps.setBoolean(2, isRemote);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	Segment s = generateSegment(resultSet);
                allSegments.add(s);
            }
            resultSet.close();
            ps.close();
            return allSegments;

        } catch (Exception e) {
            throw new Exception("Failed in getting search segments by qoute: " + e.getMessage());
        }
    }
        
    
    
    private Segment generateSegment(ResultSet resultSet) throws Exception {
        String name  = resultSet.getString("name");
        String character = resultSet.getString("character");
        String url  = resultSet.getString("url");
        boolean isMarked = resultSet.getBoolean("isMarked");
        boolean remote = resultSet.getBoolean("isRemote");
        return new Segment (name, character, url, isMarked, remote);
    }

}