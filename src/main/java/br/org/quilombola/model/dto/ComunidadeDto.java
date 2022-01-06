package br.org.quilombola.model.dto;

import br.org.quilombola.model.entity.Certificado;
import br.org.quilombola.model.entity.DadosAdicionaisComunidade;
import br.org.quilombola.model.entity.Municipio;
import br.org.quilombola.model.entity.Quilombo;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class ComunidadeDto {

    private Long id;

    private String nome;

    private Integer numeroComunidades;

    private DadosAdicionaisComunidadeDto dadosAdicionais;

    private List<QuilomboDto> quilombolaList = new ArrayList<QuilomboDto>();

    private List<MunicipioDTO> municipios = new ArrayList<MunicipioDTO>();

    private List<CertificadoDTO> certificados = new ArrayList<CertificadoDTO>();
}
