package com.epf.cloud.ehealth_cloud.client.service;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

import com.epf.cloud.ehealth_cloud.common.ClientData;
import com.epf.cloud.ehealth_cloud.common.WelcomeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides a convenience wrapper around all REST calls to the remote server. Other classes in the project can use this
 * to perform remote procedure calls without worrying about the messy business of URL and String mangling. Under the
 * covers this class uses Spring's RestTemplate to simplify the task of talking to the server. For more information on
 * Spring's RestTemplate see: http://blog.springsource.com/2009/03/27/rest-in-spring-3-resttemplate/
 */

public class RestFulService {

    private Logger log;
    /**
     * This RestTemplate is a Spring helper class that simplifies the job of making remote calls REST onto the server.
     * It is marked as @Autowired, which means that Spring will magically wire in a RestTemplate at runtime when this
     * service is loaded from an ApplicationContext (see the SimpleRestAppFactory).
     */

    /**
     * This is a configuration property that allows us to define the base server URL to use at runtime. This is
     * ultimately loaded from the client.properties file but Spring allows us to hide a lot of this and we just deal
     * with a nice simple String here.
     * <p/>
     * By default the URL will point to 'http://localhost:8080/rest-server-server' which should hit your
     * local web server if you run it using the default settings of this project. When deploying to a real world
     * environment, you should change the properties file to match your actual server URL.
     */
    private String serverUrl;

    /**
     * Constructs a new instance of this service so that it will provide access to the REST methods made available by
     * the server at the URL provided.
     *
     * @param serverUrl the base URL of the server to access, should be in normal URL form along the lines of
     *                  'http://localhost:8080/rest-server'
     */
    public RestFulService(Logger log, String serverUrl) {
    	this.log = log;
        //log.info("RestFulService is using server URL " + serverUrl);
        this.serverUrl = serverUrl;
    }

    /**
     * Makes a remote REST call to the server, invoking the 'welcome' method. This handles the job of building the
     * appropriate server URL (passing the name as a parameter) and then unmarshaling the result. If there is any
     * problem connecting to the server or reading the response then an appropriate exception is thrown by this method.
     *
     * @param name the name of the person saying hello. This is passed to the server as part of the REST URL.
     * @return the response from the server. We could have gotten away with a simple String result here but it is more
     * interesting to show a regular Java object being returned.
     * @throws RestClientException 
     */
    public WelcomeMessage sayHello(String name){    	      
    	try {
    		URL url = new URL(serverUrl + "/welcome?name=" + name);
			log.info("calling service " + url.toString());
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setRequestProperty("Accept", "application/json");
    		
    		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED 
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK ) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
    		System.out.println("Output from Server .... \n");
    		ObjectMapper mapper = new ObjectMapper();
    		WelcomeMessage message = mapper.readValue(conn.getInputStream(), WelcomeMessage.class);				
    		conn.disconnect();	
    		System.out.println(message.getMessage());
    		
    		
    		
//    		   try {
//    				Class.forName("org.gjt.mm.mysql.Driver");
//    				String url1 = "jdbc:mysql://localhost/ehealth";
//    				String user = "appserver";
//    				String passwd = "123456";
//
//    				Connection conn1 = DriverManager.getConnection(url1, user, passwd);
//    				//System.out.println("Connection effective !");
//
//    		               //Création d'un objet Statement
//    				Statement state = conn1.createStatement();
//    				//L'objet ResultSet contient le résultat de la requête SQL
//    				ResultSet result = state.executeQuery("SELECT valeur FROM bpm");
//    			               result.close();
//    		                    state.close();
//    		                       
//    			} catch (Exception e) {
//    		           	//e.printStackTrace();
//    		               JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données", "Attention", JOptionPane.WARNING_MESSAGE);
//    			}
    		
    		
    		
    		return message;
    		
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//				(input)));
//			
//			String output;
//			System.out.println("Output from Server .... \n");
//			while ((output = br.readLine()) != null) {
//				System.out.println(output);
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public void callDB(){
     

        
    }   
    
    
    
	public WelcomeMessage SendClientData(ClientData data, String linkservice) {
		// TODO Auto-generated method stub
		try {				
			URL url = new URL(serverUrl + linkservice);
			log.info("calling service " + url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			ObjectMapper mapper = new ObjectMapper();
			
			String input = mapper.writeValueAsString(data); //"{\"qty\":100,\"name\":\"iPad 4\"}";
			log.info("Data sent to cloud " + input);
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED 
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK ) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
			WelcomeMessage message = mapper.readValue(conn.getInputStream(), WelcomeMessage.class);
			
			System.out.println("Output from Server .... " + message.getMessage());
			conn.disconnect();
			
			return message;
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		 }
		return null;
	}

	public WelcomeMessage getSqlData(String dataname) {
		// TODO Auto-generated method stub
		try {
    		URL url = new URL(serverUrl + "/getdata?name=" + dataname);
			log.info("calling service " + url.toString());
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setRequestProperty("Accept", "application/json");
    		
    		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED 
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK ) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
    		System.out.println("Output from Server .... \n");
    		ObjectMapper mapper = new ObjectMapper();
    		WelcomeMessage message = mapper.readValue(conn.getInputStream(), WelcomeMessage.class);				
    		conn.disconnect();	
    		System.out.println(message.getMessage());
    		
    		
    		
    		
    		
    		return message;
    		
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//				(input)));
//			
//			String output;
//			System.out.println("Output from Server .... \n");
//			while ((output = br.readLine()) != null) {
//				System.out.println(output);
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
	}
}
