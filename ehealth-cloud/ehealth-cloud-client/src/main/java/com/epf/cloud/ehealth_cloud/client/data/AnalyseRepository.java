package com.epf.cloud.ehealth_cloud.client.data;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.epf.cloud.ehealth_cloud.client.model.Analyse;
import com.epf.cloud.ehealth_cloud.client.service.RestFulService;
import com.epf.cloud.ehealth_cloud.client.util.Constants;
import com.epf.cloud.ehealth_cloud.client.util.Tools;
import com.epf.cloud.ehealth_cloud.common.ClientData;
import com.epf.cloud.ehealth_cloud.common.WelcomeMessage;

@ApplicationScoped
public class AnalyseRepository {

	@Inject
    private Logger log;		
	private byte [] request;
    
	public WelcomeMessage saveRequest(Analyse analyse) {
		// TODO Auto-generated method stub
		request = new byte[2];

        try {
            String data_as_string = "Ms"; // Flag.
            if(analyse.isUsrSex())
            	request[0] = 1;
            log.info("Sex data: " + data_as_string);   
            
            data_as_string = "no";
                        
            log.info("Age data: " + analyse.getUsrAge());
            request[1]=(byte)analyse.getUsrAge();                       
            
            return processRequest();

        } catch(Exception exception) {
            log.info("Something went wrong when parsing data: "+exception.toString());
        }
        return null;
	}
		
	private WelcomeMessage processRequest() {
		// TODO Auto-generated method stub
		String _clientData = Tools.byteArrayToString(request);
		/*We have to fix this insecure information late*/	
		ClientData data = new ClientData(_clientData, request.length);
		RestFulService service = new RestFulService(log, Constants.URL_SERVER); 
		
		WelcomeMessage message = service.SendClientData(data, "/analyse");
		log.info("Message from server : " + message.getMessage());
		return message;
	}	
	
	
	public WelcomeMessage checkingConnect() {
		// TODO Auto-generated method stub
		WelcomeMessage message = null; 
		log.info("Sending requete to checking connect server to retrieve data ");
		RestFulService service = new RestFulService(log, Constants.URL_SERVER); 
		message = service.sayHello("Your-namehere");			
		log.info("Retrieve response data " + message.getMessage());
		return message;
	}

	
	public WelcomeMessage checkingSqlConnect() {
		WelcomeMessage message = new WelcomeMessage(); 
		log.info("Sending requete to checking connect server to retrieve data ");
		RestFulService service = new RestFulService(log, Constants.URL_SERVER); 
		message = service.getSqlData("Your-database");			
		log.info("Retrieve response data " + message.getMessage());
		return message;
	}
}
