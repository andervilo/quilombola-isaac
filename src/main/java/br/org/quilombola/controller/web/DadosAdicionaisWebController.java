package br.org.quilombola.controller.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.org.quilombola.model.dto.DadosAdicionaisDTO;
import br.org.quilombola.model.entity.DadosAdicionaisComunidade;
import br.org.quilombola.model.entity.Territorio;
import br.org.quilombola.model.entity.jsonentity.ProcessoAdministrativoExtra;
import br.org.quilombola.model.entity.jsonentity.ProcessoJudicial;
import br.org.quilombola.model.repository.DadosAdicionaisRepository;
import br.org.quilombola.model.repository.TerritorioRepository;

@Controller
@RequestMapping("/dadosadicionais")
public class DadosAdicionaisWebController {

	@Autowired
	private DadosAdicionaisRepository dadosAdicionaisRepository;

	@Autowired
	private TerritorioRepository territorioRepository;

	private ModelMapper mapper = new ModelMapper();

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updatedadosAdicionais(@PathVariable Long id,
			@Valid @RequestBody DadosAdicionaisDTO dadosAdicionais) {
		System.out.println("Dto adm: " + dadosAdicionais);
		if (!dadosAdicionaisRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dados não encontrados!");
		}

		if (!territorioRepository.existsById(dadosAdicionais.getTerritorio())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Território não encontrado!");
		}

		Territorio territorio = territorioRepository.findById(dadosAdicionais.getTerritorio()).get();

		DadosAdicionaisComunidade dac = mapper.map(dadosAdicionais, DadosAdicionaisComunidade.class);

		List<ProcessoAdministrativoExtra> procAdmExt = mapper.map(dadosAdicionais.getOutrosProcessosAdministrativos(),
				new TypeToken<List<ProcessoAdministrativoExtra>>() {
				}.getType());

		List<ProcessoJudicial> procJud = mapper.map(dadosAdicionais.getProcessosJudiciais(),
				new TypeToken<List<ProcessoJudicial>>() {
				}.getType());

		dac.setOutrosProcessoasAdministrativos(procAdmExt);
		dac.setProcessosJudiciais(procJud);

		System.out.println("size: " + dac.getOutrosProcessosAdministrativos().size());

		dac.setTerritorio(territorio);

		dac = dadosAdicionaisRepository.save(dac);

		return ResponseEntity.ok().body(dac);
	}

	@PostMapping(value = "/{id}/documento", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<?> uploadDocumento(@PathVariable Long id, @Valid @RequestParam MultipartFile documento) {
		Map<Object, Object> map = new HashMap<Object, Object>();

		if (!documento.getContentType().equalsIgnoreCase(MediaType.APPLICATION_PDF_VALUE)
				&& !documento.getContentType().equalsIgnoreCase(MediaType.TEXT_PLAIN_VALUE)
				&& !documento.getContentType().equalsIgnoreCase("application/msword")
				&& !documento.getContentType().equalsIgnoreCase("application/octet-stream")
				&& !documento.getContentType().equalsIgnoreCase("application/vnd.ms-excel")
				&& !documento.getContentType()
						.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
				&& !documento.getContentType()
						.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Formato de arquivo inválido!<br>Apenas PDF/DOC/DOCX/XLS/XLSX/TXT são permitidos!");
		}
		if (!dadosAdicionaisRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dados não encontrados!");
		}
		DadosAdicionaisComunidade adicionaisComunidade = dadosAdicionaisRepository.findById(id).get();

		try {
			adicionaisComunidade.setNomeDocumento(documento.getOriginalFilename());
			adicionaisComunidade.setExtensaoDocumento(documento.getContentType());
			adicionaisComunidade.setBinarioDocumento(documento.getBytes());
			dadosAdicionaisRepository.saveAndFlush(adicionaisComunidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("message", "Documento adicionado com sucesso!");
		return ResponseEntity.ok().body(map);
	}

	@DeleteMapping(value = "/{id}/documento", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteDocumento(@PathVariable Long id) {
		Map<Object, Object> map = new HashMap<Object, Object>();

		if (!dadosAdicionaisRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dados não encontrado!");
		}
		DadosAdicionaisComunidade adicionaisComunidade = dadosAdicionaisRepository.findById(id).get();

		try {
			adicionaisComunidade.setNomeDocumento(null);
			adicionaisComunidade.setExtensaoDocumento(null);
			adicionaisComunidade.setBinarioDocumento(null);
			dadosAdicionaisRepository.saveAndFlush(adicionaisComunidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("message", "Documento adicionado com sucesso!");
		return ResponseEntity.ok().body(map);
	}

	@GetMapping(value = "/{id}/documento")
	public ResponseEntity<?> getDocumento(@PathVariable Long id) throws IOException {
		Map<Object, Object> map = new HashMap<Object, Object>();

		if (!dadosAdicionaisRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado não encontrado!");
		}
		DadosAdicionaisComunidade adicionaisComunidade = dadosAdicionaisRepository.findById(id).get();

		InputStream targetStream = new ByteArrayInputStream(adicionaisComunidade.getBinarioDocumento());

		ByteArrayResource byteArrayResource = new ByteArrayResource(adicionaisComunidade.getBinarioDocumento());
		targetStream.close();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + adicionaisComunidade.getNomeDocumento() + "\"")
				.body(byteArrayResource);

	}

}
