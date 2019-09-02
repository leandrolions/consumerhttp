### Features

- aplicacion de soporte para consumier rests http y con respuesta de classes;
- version 1.0 soporta solamente requests en json ( gest and post)
- version 2.0 agregado build de la clase, ingreso de HTTP Headers, basic authenticator

Next versions:
SSL
XML response


Como utilizarlo (ejemplo get com retorno de lista):
	
	Generitype<List<T>> type = new Generitype<List<T>>();
	JsonConsuming jsonconsuming = new JsonConsuming();
    List<salida> salida = jsonconsuming.getHttptoList(parametros,type,classedeentrada);
    
    o
    
    JsonConsuming jsonconsuming = JsonConsuming.Builder().setBasicAuth(username,password).build();
    List<salida> salida = jsonconsuming.getHttptoList(parametros,type,classedeentrada);
    
    variantes
    setBasicAuth();
    setBasicAuthNoPreimptive();
    setUniversalAuth();
    setUniversalAuthMultiply();
    
*author :  leandro leao