package ar.com.nat.consumerhttp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import ar.com.nat.consumerhttp.consuming.JsonConsuming;
import ar.com.nat.consumerhttp.exceptions.ProcessException;
import junit.framework.TestCase;

public class apptest extends TestCase{
	
	public void testfuncion(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("COD_MONEDA", "Gs");
		map.put("FECHA_DESDE", "");
		map.put("FECHA_HASTA", "");
		try {
			JsonConsuming consuming = JsonConsuming.newbuilder().setMethodType(MediaType.APPLICATION_JSON).build();
			MonedaRequest request = new MonedaRequest("USD","","");
			GenericType<List<MonedasTest>> listmoneda = new GenericType<List<MonedasTest>>() {};
			List<MonedasTest> monedas = consuming.getHttptoList("http://192.168.1.42:8081/SolarEwanWS/json/Consultas/" + 
					"getMonedas",listmoneda,request);
			assertTrue(!monedas.isEmpty());
		} catch (ProcessException e) {
			assertTrue(false);
		}
	}

}
