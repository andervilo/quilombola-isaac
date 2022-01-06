const URL = "/sigequi/api/v1/relatorios/";
const territorioexportar = {
  data: function () {
    return {
      estado: null,
      relatorio: null,
      estados: [
        "AC",
        "AL",
        "AP",
        "AM",
        "BA",
        "CE",
        "DF",
        "ES",
        "GO",
        "MA",
        "MT",
        "MS",
        "MG",
        "PA",
        "PB",
        "PR",
        "PE",
        "PI",
        "RJ",
        "RN",
        "RS",
        "RO",
        "RR",
        "SC",
        "SP",
        "SE",
        "TO",
      ],
      relatorios: [
        { id: 0, relatorio: "Assentamento" },
        { id: 1, relatorio: "CCDRU" },
        { id: 2, relatorio: "Decreto" },
        { id: 3, relatorio: "Edital" },
        { id: 4, relatorio: "Portaria" },
        { id: 5, relatorio: "Processo" },
        { id: 6, relatorio: "Título" },
      ],
    };
  },
  methods: {
    gotToUrl(url) {
      window.location.href = url;
    },
    exportar() {
      uri = URL;
      console.log(this.relatorio);
      switch (this.relatorio) {
        case 0:
          uri =
            this.estado == null
              ? uri + "assentamento.xls"
              : uri + "assentamento.xls/estado/" + this.estado;
          break;

        case 1:
          uri =
            this.estado == null
              ? uri + "ccdru.xls"
              : uri + "ccdru.xls/estado/" + this.estado;
          break;

        case 2:
          uri =
            this.estado == null
              ? uri + "decreto.xls"
              : uri + "decreto.xls/estado/" + this.estado;
          break;

        case 3:
          uri =
            this.estado == null
              ? uri + "edital.xls"
              : uri + "edital.xls/estado/" + this.estado;
          break;

        case 4:
          uri =
            this.estado == null
              ? uri + "portaria.xls"
              : uri + "portaria.xls/estado/" + this.estado;
          break;

        case 5:
          uri = uri + "processo.xls";
          break;

        case 6:
          uri =
            this.estado == null
              ? uri + "titulo.xls"
              : uri + "titulo.xls/estado/" + this.estado;
          break;

        default:
          uri = null;
      }

      if (uri != null) {
        this.gotToUrl(uri);
      }
    },
  },
  mounted() {},
  template: `
		<div class="mt-3">
			<div class="row">
				<div class="col">
					<h2 class="text-center text-success">Exportar dados de Territórios(Excel/XLSX)</h2>
				</div>
			</div>
			<hr>
			
			<div>
			
			<div class="col-md-3">
					<b-form-select v-model="estado">
						<option :value="null">Brasil</option>
						<option v-for="estado in estados" :value="estado">{{ estado }}</option>
					</b-form-select>		
			</div></br>
			<div class="col-md-3">
					<b-form-select v-model="relatorio">
						<option :value="null">Selecione um Relatório</option>
						<option v-for="relat in relatorios" :value="relat.id">{{ relat.relatorio }}</option>
					</b-form-select>		
			</div>
			</br>
			<div class="col-md-3">
				<b-button @click="exportar" variant="info">Exportar</b-button>
			</div>
			
			</div>
		</div>
         `,
};
