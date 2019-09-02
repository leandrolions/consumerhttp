package ar.com.nat.consumerhttp.consuming;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.google.common.base.Optional;

import ar.com.nat.consumerhttp.exceptions.ProcessException;

/**
 * 
 * @author lleao
 *
 */
public class JsonConsuming {
	
	private static int HTTP_COD_SUCESSO = 200;
	private Client client = ClientBuilder.newClient();
	private MultivaluedMap<String, Object> myHeaders;
	private String type = MediaType.APPLICATION_JSON;
	
	public JsonConsuming() {};

	private JsonConsuming(Builder builder) {
		this.client = builder.client;
		this.myHeaders = builder.myHeaders;
		this.type = builder.type;
	}
	
	private Response validResponseGet(WebTarget resource) throws ProcessException {
		try {	
			Response response;
			if(!Optional.fromNullable(myHeaders).isPresent()) {
				response= resource.request(type).get();
			}else {
				response= resource.request(type).headers(myHeaders).get();
			}
			if(response.getStatus() != HTTP_COD_SUCESSO) {
				throw new ProcessException("Respuesta fallida, codigo:"+response.getStatus());
			}
			return response;
		}catch (Exception e) {
				throw new ProcessException("no se pudo connectar al host",e);
		}
	}
	/**
	 * 
	 * @see este metodo devuleve una clase de la consulta HTTP get
	 * @param consumingURL = URL a ser consumida
	 * @param salida = clase de salida de la consulta HTTP
	 * @param params = clase con los parametros de la query param '?ex={}'
	 * @return
	 * @throws ProcessException
	 */
	public <T> T getHttptoEntity(String consumingURL,Class<T> salida, Object params) throws ProcessException {
		WebTarget resource = client.target(URI.create(consumingURL));
		Map<String,Object> mparams = new HashMap<String, Object>();
			try {
				for(Field field: params.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					mparams.put(field.getName(),field.get(params));
				}
			}catch (Exception e) {
				throw new ProcessException("falla en acceder a clase de parametros",e);
			}
			for (Map.Entry<String, Object> entry : mparams.entrySet()) {
				resource = resource.queryParam(entry.getKey(), entry.getValue());
			}
				Response response = validResponseGet(resource);
				T responseclass = response.readEntity(salida);
				return responseclass;

	}
	/**
	 * 
	 * @param <T>
	 * @param <T1>
	 * @param consumingURL
	 * @param salida
	 * @param mparams map con nombre de los atributos y valores para ellos
	 * @return salida
	 * @throws ProcessException
	 */
	public <T> T getHttptoEntity(String consumingURL,Class<T> salida, Map<String,Object> mparams) throws ProcessException {
		WebTarget resource = client.target(URI.create(consumingURL));
			for (Map.Entry<String, Object> entry : mparams.entrySet()) {
				resource = resource.queryParam(entry.getKey(), entry.getValue());
			}
			Response response = validResponseGet(resource);
			T responseclass = response.readEntity(salida);
			return responseclass;
	}
	/**
	 * @see metodo para consulta HTTP sin parametros de entrada
	 * @param <T>
	 * @param consumingURL = URL a ser consumida
	 * @param salida = clase de salida de la consulta
	 * @return
	 * @throws ProcessException
	 */
	
	public <T> T getHttptoEntity(String consumingURL,Class<T> salida) throws ProcessException {
		try {
				WebTarget resource = client.target(URI.create(consumingURL));
				Response response = validResponseGet(resource);
				T responseclass = response.readEntity(salida);
				return responseclass;
			}catch (Exception e) {
				throw new ProcessException("no se pudo connectar al host",e);
			}
	}

	/**
	 * @see este method consulta una URL con parametros POST y devuelve una clase
	 * @param <T>
	 * @param <E>
	 * @param consumingURL
	 * @param salida
	 * @param params
	 * @return
	 */
	public  <T> T postHttptoEntity(String consumingURL,Class<T> salida, Object params) {
		WebTarget resource = client.target(URI.create(consumingURL));
		return resource.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(params), salida);
	}

	/**
	 * 
	 * @see este metodo devuleve una clase post la consulta HTTP
	 * @param consumingURL = URL a ser consumida
	 * @param salida = clase de salida de la consulta HTTP
	 * @param params = clase con los parametros de la query param '?ex={}'
	 * @return
	 * @throws ProcessException
	 */
	public  <T> List<T> getHttptoList(String consumingURL,GenericType<List<T>> salida, Object params) throws ProcessException {
		WebTarget resource = client.target(URI.create(consumingURL));
		Map<String,Object> mparams = new HashMap<String, Object>();
			try {
				for(Field field: params.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					mparams.put(field.getName(),field.get(params));
				}
			}catch (Exception e) {
				throw new ProcessException("falla en acceder a clase de parametros",e);
			}
			for (Map.Entry<String, Object> entry : mparams.entrySet()) {
				resource = resource.queryParam(entry.getKey(), entry.getValue());
			}
			Response response = validResponseGet(resource);
			List<T> responseclass = response.readEntity(salida);
			return responseclass;
	}
	/**
	 * 
	 * @param <T>
	 * @param <T1>
	 * @param consumingURL
	 * @param salida
	 * @param mparams map con nombre de los atributos y valores para ellos
	 * @return salida
	 * @throws ProcessException
	 */
	public <T> List<T> getHttptoList(String consumingURL,GenericType<List<T>> salida, Map<String,Object> mparams) throws ProcessException {
		WebTarget resource = client.target(URI.create(consumingURL));
			for (Map.Entry<String, Object> entry : mparams.entrySet()) {
				resource = resource.queryParam(entry.getKey(), entry.getValue());
			}
			Response response = validResponseGet(resource);
			List<T> responseclass = response.readEntity(salida);
			return responseclass;
	}
	/**
	 * @see consuming y devuelve lista
	 * @param <T>
	 * @param consumingURL
	 * @param salida
	 * @param type
	 * @return
	 * @throws ProcessException
	 */
	public <T> List<T> getHttptoList(String consumingURL,GenericType<List<T>> salida) throws ProcessException {
			WebTarget resource = client.target(URI.create(consumingURL));
			Response response = validResponseGet(resource);
			List<T> responseclass = response.readEntity(salida);
			return responseclass;
	}

	/**
	 * 
	 * @see este method consulta una URL con parametros POST y devuelve una lista
	 * @param <T>
	 * @param <E>
	 * @param consumingURL
	 * @param salida
	 * @param params
	 * @return
	 */
	public <T> List<T> postHttptoList(String consumingURL,GenericType<List<T>> salida, Object params) {
		WebTarget resource = client.target(URI.create(consumingURL));
		return resource.request()
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(params),salida);
	}
	
	//securities methods
	
	public void setBasicAuth(String username,String password) {
		HttpAuthenticationFeature basic = HttpAuthenticationFeature.basic(username, password);
		client.register(basic);
	}
	public void setBasicAuthNoPreimptive(String username,String password) {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
                .nonPreemptive()
                .credentials(username, password)
                .build();
		client.register(feature);
	}
	public void setUniversalAuth(String username,String password) {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universal(username, password);
		client.register(feature);
	}
	public void setUniversalAuthMultiply(String username1,String username2,String password1,String password2) {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
                .credentialsForBasic(username1, password1)
                .credentials(username2, password2).build();
		client.register(feature);
	}
	
	public void setClient(Client builder) {
		this.client = builder;
	}
	public void setmyHeaders(MultivaluedMap<String, Object> Headers) {
		this.myHeaders = Headers;
	}
	public void setTypeMenthod(String methodtype) {
		this.type = methodtype;
	}
	/**
	 * Creates builder to build {@link JsonConsuming}.
	 * @return created builder
	 */
	public static Builder newbuilder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link JsonConsuming}.
	 */
	public static final class Builder {
		private Client client;
		private MultivaluedMap<String, Object> myHeaders;
		private String type;

		private Builder() {
		}
		public Builder setClient(Client client) {
			this.client = client;
			return this;
		}
		public Builder setHeaders(MultivaluedMap<String, Object> myHeaders) {
			this.myHeaders = myHeaders;
			return this;
		}
		public Builder setMethodType(String type) {
			this.type = type;
			return this;
		}
		public JsonConsuming build() {
			if(!Optional.fromNullable(client).isPresent()) {
				this.client = ClientBuilder.newClient();
			}
			return new JsonConsuming(this);
		}
	}
}
