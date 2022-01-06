package br.org.quilombola.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoAssentamentoEnum {
	FEDERAL(1, "Federal", "FEDERAL"),
	ESTADUAL(2, "Estadual", "ESTADUAL")
	;
	
	private final int codigo;
	private final String nome;
	private final String label;
	
	private TipoAssentamentoEnum(int codigo, String nome, String label) {
		this.codigo = codigo;
		this.nome = nome;
		this.label = label;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public String getLabel() {
		return label;
	}
	
        public TipoAssentamentoEnum getTipoAssentamentoEnumByCodigo(int codigo){
            List<TipoAssentamentoEnum> listAssentamento = Arrays.asList(TipoAssentamentoEnum.values());
            List<TipoAssentamentoEnum> listResult = listAssentamento.stream()
                    .filter(a -> a.getCodigo() == codigo).collect(Collectors.toList());
            
            return listResult.size() == 1 ? listResult.get(0) : null ;
        }
	
}
