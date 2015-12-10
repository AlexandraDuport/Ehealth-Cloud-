package com.epf.cloud.ehealth_cloud.server.controller;

import com.epf.cloud.ehealth_cloud.common.ClientData;
import com.epf.cloud.ehealth_cloud.common.WelcomeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

/**
 * SpringMVC Controller that lives on the server side and handles incoming HTTP requests. It is basically a servlet but
 * using the power of SpringMVC we can avoid a lot of the raw servlet and request/response mapping uglies that
 * servlets require and instead just deal with simple, clean Java Objects. For more information on SpringMVC see:
 * http://static.springsource.org/spring/docs/current/spring-framework-reference/html/mvc.html
 */
@Controller
public class WelcomeController {

    private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

    /**
     * This method is exposed as a REST service. The @RequestMapping parameter tells Spring that when a request comes in
     * to the server at the sub-url of '/welcome' (e.g. http://localhost:8080/ehealth-cloud-server/welcome)
     * it should be directed to this method.
     * <p/>
     * In normal SpringMVC you would typically handle the request, attach some data to the 'Model' and redirect to a
     * JSP for rendering. In our REST example however we want the result to be an XML response. Thanks to some Spring
     * magic we can just return our bean, annotate it with @ResponseBody and Spring will magically turn this into XML
     * for us.
     * <p/>
     * We really didn't need the whole WelcomeMessage object here and could just have easily returned a String. That
     * wouldn't have made a very good example though, so the WelcomeMessage is here to show how Spring turns objects
     * into XML and back again for easy REST calls. The 'date' parameter was added just to give it some spice.
     *
     * @param name the name of the person to say hello to. This is pulled from the input URL. In this case we use a
     *             request parameter (i.e. ?name=someone), but you could also map it directly into the URL if you
     *             prefer. See the very good SpringMVC documentation on this for more information.
     * @return
     */
    @RequestMapping("/welcome")
    public @ResponseBody WelcomeMessage sayHello(@RequestParam(required = false) String name) {

        log.info("Saying hello to '{}'", name);

        String message;
        if (name != null && name.trim().length() > 0) {
            message = "Hello " + name;
        } else {
            message = "Hello mysterious person";
        }
        return new WelcomeMessage(message, new Date());
    }
    
    @RequestMapping(value="/analyse", method = RequestMethod.POST)
    public @ResponseBody WelcomeMessage analyse(@RequestBody ClientData data) {        
    	String strData = data.getData();
        log.info("Server Received data " + strData );

        String message;
        if (strData != null && strData.trim().length() > 0) {            
            message = "Server has already received your data " + strData ;
        } else {
            message = "FHE-Cloud server received a mysterious data from client";
        }
        return new WelcomeMessage(message, Calendar.getInstance().getTime());
    }
    
    @RequestMapping(value="/getdata", method = RequestMethod.GET)
    public @ResponseBody WelcomeMessage getHealth(String name) {

    	System.out.println("getHealth getHealth to " + name);

        String message="";

		   try {
				Class.forName("org.gjt.mm.mysql.Driver");
//				String url1 = "jdbc:mysql://localhost/ehealth";
//				String user = "appserver";
//				String passwd = "123456";
//
//				Connection conn1 = DriverManager.getConnection(url1, user, passwd);
//				//System.out.println("Connection effective !");
//
//		               //Création d'un objet Statement
//				Statement state = conn1.createStatement();
//				//L'objet ResultSet contient le résultat de la requête SQL
//				ResultSet result = state.executeQuery("SELECT valeur FROM bpm");
//			               result.close();
//		                    state.close();
                System.out.println("data display here");
			} catch (Exception e) {
		           	//e.printStackTrace();
               System.out.println(
            		   "Impossible de se connecter à la base de données");
               message = e.toString();
			}
		
        return new WelcomeMessage(message, new Date());
    }
    
}
