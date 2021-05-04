/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bartek
 */
public class Main {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String count = client.target("http://localhost:8080/Complaints/" +
                                    "resources/complaints/count")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        System.out.println("Count: " + count);
        
        String complaints =  client.target("http://localhost:8080/Complaints/" +
                                    "resources/complaints/")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        
        JSONArray complaintsJson = new JSONArray(complaints);
        
        System.out.println("Complaints:");
        for (int i=0; i<complaintsJson.length(); i++) {
            JSONObject complaint = complaintsJson.getJSONObject(i);
            System.out.println(complaint);
        }
        
        Optional<Long> optionalId = StreamSupport
                .stream(complaintsJson.spliterator(), false)
                .map(c -> (JSONObject) c)
                .filter(c -> c.getString("status").equals("open"))
                .map(c -> c.getLong("id"))
                .findFirst();
        if (optionalId.isPresent()) {
            Long id = optionalId.get();
            String openComplaint = client.target("http://localhost:8080/Complaints/" +
                                                    "resources/complaints/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            System.out.println("Open complaint:");
            System.out.println(openComplaint);
            JSONObject openedComplaintJson = new JSONObject(openComplaint);
            openedComplaintJson.put("status", "close");
            client.target("http://localhost:8080/Complaints/resources/complaints/" + id)   
                    .request()
                    .put(Entity.json(openedComplaintJson.toString()));            
        }
        
        String openComplaints = client
                .target("http://localhost:8080/Complaints/resources/complaints?status=open")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);  
        JSONArray openComplaintsJson = new JSONArray(openComplaints);
        System.out.println("Opened complaints:");
        for (int i=0; i<openComplaintsJson.length(); i++) {
            JSONObject complaint = openComplaintsJson.getJSONObject(i);
            System.out.println(complaint);
        }       

        client.close();         
    }
    
}
