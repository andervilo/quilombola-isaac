package br.org.quilombola.model.deserializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import br.org.quilombola.arquitetura.enums.Estado;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Territorio;
import br.org.quilombola.model.service.TerritorioService;

public class MunicipioDeserializer extends StdDeserializer<Municipio>{

	@Autowired
	private TerritorioService service;
	
	private static final long serialVersionUID = -6316095278614388116L;

	public MunicipioDeserializer() {
		this(null);
	}
	protected MunicipioDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Municipio deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
	 	String nome = node.get("nome").asText();
	 	String estado = node.get("estado").asText();
	 	Estado estadoEnum = Estado.valueOf(estado);
	 	int codigoIbge = (Integer) ((IntNode) node.get("codigoIbge")).numberValue();
	 	int sr = (Integer) ((IntNode) node.get("sr")).numberValue();
	 	ObjectMapper mapper = new ObjectMapper();
	 	List<Territorio> list = new ArrayList<>();
	 	Boolean amazoniaLegal = (Boolean) node.get("amazoniaLegal").asBoolean();

	 	Municipio municipio = new Municipio();
	 	municipio.setAmazoniaLegal(amazoniaLegal);
	 	municipio.setCodigoIbge(codigoIbge);	 	
	 	municipio.setEstado(estado);
	 	municipio.setNome(nome);

	 	try {
	 		list = mapper.readValue(node.get("territorioList").asText(), List.class);
	 		municipio.setTerritorioList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 		 	
	 	System.out.println(municipio);
		return municipio;
	}

}
