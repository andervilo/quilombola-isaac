//const pesquisaURL = "/sigequi/api/v1/comunidades/estado/"
const pesquisaURL = "/sigequi/api/v1/comunidades/search/";
const pesquisar = {
  data: function () {
    return {
      estados: [],
      estadoSelected: null,
      buscarPor: null,
      isModalViewComunidadeQuilombo: false,

      selected: null,
      perPage: 25,
      options: [
        { value: 5, text: "5 por página" },
        { value: 10, text: "10 por página" },
        { value: 15, text: "15 por página" },
        { value: 20, text: "20 por página" },
        { value: 25, text: "25 por página" },
        { value: 30, text: "30 por página" },
        { value: 40, text: "40 por página" },
        { value: 50, text: "50 por página" },
        { value: 75, text: "50 por página" },
        { value: 100, text: "50 por página" },
      ],
      currentPage: 1,
      fields: [
        {
          key: "id",
          sortable: true,
          label: "ID",
        },
        {
          key: "nome",
          sortable: true,
          label: "Comunidade",
        },
        {
          key: "certificada",
          sortable: false,
          label: "Certificada",
        },
        {
          key: "processo",
          sortable: false,
          label: "Processo",
        },
        {
          key: "edital",
          sortable: false,
          label: "Edital",
        },
        {
          key: "portaria",
          sortable: false,
          label: "Portaria",
        },
        {
          key: "decreto",
          sortable: false,
          label: "Decreto",
        },
        {
          key: "titulo",
          sortable: false,
          label: "Título",
        },
        {
          key: "view",
          sortable: false,
          label: "",
        },
      ],
      fieldsTerritorio: [
        {
          key: "id",
          sortable: true,
          label: "ID",
        },
        {
          key: "nome",
          sortable: true,
          label: "Território",
        },
        {
          key: "certificada",
          sortable: false,
          label: "Certificado",
        },
        {
          key: "processo",
          sortable: false,
          label: "Processo",
        },
        {
          key: "edital",
          sortable: false,
          label: "Edital",
        },
        {
          key: "portaria",
          sortable: false,
          label: "Portaria",
        },
        {
          key: "decreto",
          sortable: false,
          label: "Decreto",
        },
        {
          key: "titulo",
          sortable: false,
          label: "Título",
        },
        {
          key: "view",
          sortable: false,
          label: "",
        },
      ],

      items: [],
      modalShow: false,
      comunitySelected: null,
      textoBusca: null,
      itemsTerritorio: [],
      modalTerritorioShow: false,
      territorioSelected: null,
    };
  },
  computed: {
    rows() {
      return this.items.length;
    },
    rowsterritorio() {
      return this.itemsTerritorio.length;
    },
  },
  methods: {
    getComunidades() {
      this.items = [];

      if (this.textoBusca == null || this.textoBusca == "") {
        this.textoBusca = "";
      }

      if (this.estadoSelected == null) {
        this.estadoSelected = "";
      }

      axios
        .post(pesquisaURL + this.estadoSelected, { busca: this.textoBusca })
        .then((response) => {
        	console.log('array', response)
        	response.data.forEach((item) => {
            this.items.push({
              id: item.id,
              nome: item.nome,
              certificada:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.certificada != null
                  ? item.dadosAdicionais.certificada
                  : false,
              processo:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.processosReconhecimento != null
                  ? item.dadosAdicionais.processosReconhecimento
                  : false,
              edital:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.edital != null
                  ? item.dadosAdicionais.edital
                  : false,
              titulo:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.titulada != null
                  ? item.dadosAdicionais.titulada
                  : false,
              portaria:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.portaria != null
                  ? item.dadosAdicionais.portaria
                  : false,
              decreto:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.decreto != null
                  ? item.dadosAdicionais.decreto
                  : false,
              view: item,
            });
          });
        })
        .catch((error) => {
          console.log(error);
        });

      console.log(this.items);
    },
    getTerritorios() {
      this.itemsTerritorio = [];

      if (this.textoBusca == null || this.textoBusca == "") {
        this.textoBusca = "";
      }

      if (this.estadoSelected == null) {
        this.estadoSelected = "";
      }

      axios
        .post("/sigequi/api/v1/territorios/search/" + this.estadoSelected, {
          busca: this.textoBusca,
        })
        .then((response) => {
          response.data.forEach((item) => {
            this.itemsTerritorio.push({
              id: item.id,
              nome: item.nome,
              certificada:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.certificada != null
                  ? item.dadosAdicionais.certificada
                  : false,
              processo:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.processosReconhecimento != null
                  ? item.dadosAdicionais.processosReconhecimento
                  : false,
              edital:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.edital != null
                  ? item.dadosAdicionais.edital
                  : false,
              titulo:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.titulada != null
                  ? item.dadosAdicionais.titulada
                  : false,
              portaria:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.portaria != null
                  ? item.dadosAdicionais.portaria
                  : false,
              decreto:
                item.dadosAdicionais != null &&
                item.dadosAdicionais.decreto != null
                  ? item.dadosAdicionais.decreto
                  : false,
              view: item,
            });
          });
        })
        .catch((error) => {
          console.log(error);
        });

    },
    getEstados() {
      axios
        .get("/sigequi/api/v1/estados", {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        })
        .then((response) => {
          this.estados = _.sortBy(response.data, ["nome"]);
        })
        .catch((error) => fn(error.response));
    },
    limparFiltros() {
      this.textoBusca = null;
      this.estadoSelected = null;
      this.items = [];
      this.itemsTerritorio = [];
    },
    onRowSelect(item) {
      this.comunitySelected = item;
      this.modalShow = true;
    },
    onRowTerritorioSelect(item) {
    	this.territorioSelected = null;
    	console.log("item:", item)
        this.territorioSelected = item;
        console.log("territorio:",this.territorioSelected)
        if(this.territorioSelected != null){
        	this.modalTerritorioShow = true;
        }                
    },
    toLocalDate(date) {
      if (date == null) return "";
      return new Date(date).toLocaleDateString('pt-BR', { timeZone: 'America/Sao_Paulo' });
    },
  },
  mounted() {
    this.getEstados();
  },
  template: `
<div class="mt-3">
    <div class="row">
        <div class="col">
            <h2 class="text-center text-success">Pesquisa</h2>
        </div>
    </div>
    <hr>
    
    <div>
    <b-tabs content-class="mt-3" fill>
    <b-tab title="Comunidades" active>
    <!--inicio comuidade-->
    <div id="comunidade">
        <div class="row">

            <div class="col-md-2">
                <b-form-select v-model="perPage" :options="options"></b-form-select>
            </div>

            <div class="col-md-3">
                <b-form-select v-model="estadoSelected" @change="getComunidades">
                    <option :value="null">Selecione um Estado</option>
                    <option v-for="estado in estados" :value="estado.codigoIbge">{{ estado.nome }}</option>
                </b-form-select>

            </div>

            <div class="col-md-4">

                <div>
                    <input title="Buscar nome/parte do nome Comunidade" @keyup.enter="getComunidades"
                        placeholder="Buscar nome/parte do nome Comunidade" v-model="textoBusca"
                        type="text" class="form-control">
                </div>

                <div class="row" v-if="0==1">
                    <div class="col-md">
                        <b-form-radio v-model="buscarPor" value="1">Buscar por Comunidade</b-form-radio>
                    </div>
                    <div class="col-md">
                        <b-form-radio v-model="buscarPor" value="2">Buscar por Território</b-form-radio>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <b-button-group>
                    <b-button @click="getComunidades" variant="info">Buscar</b-button>
                    <b-button @click="limparFiltros" variant="danger">Limpar Filtros</b-button>
                </b-button-group>
            </div>
        </div>

        <div class="row">
            <div class="col-md">

                <b-table id="my-table" :items="items" :per-page="perPage" :current-page="currentPage" :fields="fields"
                    small>
                    <template v-slot:cell(certificada)="data">
                        <b-badge v-if="data.item.certificada" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.certificada" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(processo)="data">
                        <b-badge v-if="data.item.processo" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.processo" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(edital)="data">
                        <b-badge v-if="data.item.edital" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.edital" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(portaria)="data">
                        <b-badge v-if="data.item.portaria" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.portaria" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(decreto)="data">
                        <b-badge v-if="data.item.decreto" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.decreto" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(titulo)="data">
                        <b-badge v-if="data.item.titulo" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.titulo" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(view)="data">
                        <a class="button " title="Ver Informações da Comunidade!" @click="onRowSelect(data.item.view)">
                            <i style="color: #20bc56;" class="fas fa-eye"></i>
                        </a>
                    </template>


                </b-table>

                <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table"
                    align="center"></b-pagination>

                <div>
                    <b-modal size="xl" Large Modal scrollable v-model="modalShow" hide-footer>
                        <template v-slot:modal-title>
                            Comunidade: {{comunitySelected.nome}}
                        </template>

                        <div role="tablist">


                            <b-card no-body class="mb-1">
                                <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-1 variant="info">Municípios</b-button>
                                </b-card-header>
                                <b-collapse id="accordion-1" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                        <b-card-text>

                                            <table class="table" v-if="comunitySelected != null && comunitySelected.municipios.length > 0">
                                                <thead>
                                                    <tr>
                                                        <th>Nome</th>
                                                        <th>Cod.IBGE</th>
                                                        <th>Superintendência</h>
                                                        <th>Estado</th>
                                                        <th>Região</th>
                                                        <th>Amazônia Legal</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr v-if="comunitySelected != null" v-for="m in comunitySelected.municipios" :key="m.id">
                                                        <td>{{m.nome}}</td>
                                                        <td>{{m.codigoIbge}}</td>
                                                        <td>
                                                            <b-tag type="is-primary"
                                                                v-for="superintendencia in m.superintendencias"
                                                                :key="superintendencia.id" rounded>
                                                                {{superintendencia.nome}}
                                                            </b-tag>
                                                        </td>
                                                        <td>{{m.estado.nome}}</td>
                                                        <td>{{m.estado.regiao.nome}}</td>
                                                        <td>
                                                            <i style="color: green;" class="fa fa-check"
                                                                v-show="m.amazoniaLegal===true"></i>
                                                            <i style="color: red;" class="fa fa-times"
                                                                v-show="m.amazoniaLegal===false"></i>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                        </b-card-text>
                                    </b-card-body>
                                </b-collapse>
                            </b-card>


                            <b-card no-body class="mb-1">
                                <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-1 variant="info">Certificados</b-button>
                                </b-card-header>
                                <b-collapse id="accordion-1" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                        <b-card-text>
                                            <table class="table table-sm" v-if="comunitySelected != null && comunitySelected.certificados.length > 0">
                                                <tbody v-for="c in comunitySelected.certificados" :key="c.id">

                                                    <div>
                                                        <b-card :title="c.numeroProcessoFcp">
                                                            <table class="table table-sm">
                                                                <tr v-if="c.nomeDocumento != null">
                                                                    <td width="200"><b>Download Documento:</b></td>
                                                                    <td>
                                                                        <a :href="'/sigequi/certificados/'+c.id+'/documento'"
                                                                            style="color: #fff" class="btn btn-success"><i
                                                                                class="fa fa-download"></i>&nbsp;
                                                                            Download</a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Número Processo FCP:</b></td>
                                                                    <td>{{c.numeroProcessoFcp}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Data abertura processo:</b></td>
                                                                    <td>{{toLocalDate(c.dataAberturaProcessoCertificado)}}
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Livro:</b></td>
                                                                    <td>{{c.livro}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Folha:</b></td>
                                                                    <td>{{c.folha}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Número:</b></td>
                                                                    <td>{{c.numero}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Número Portaria:</b></td>
                                                                    <td>{{c.numeroPortaria}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Data da Portaria Portaria:</b></td>
                                                                    <td>{{toLocalDate(c.dataPortaria)}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Data Diário Oficial:</b></td>
                                                                    <td>{{toLocalDate(c.dataDiarioOficial)}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Diário Oficial:</b></td>
                                                                    <td>{{c.diarioOficial}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Seção Diário Oficial:</b></td>
                                                                    <td>{{c.secaoDO}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Folha Diário Oficial:</b></td>
                                                                    <td>{{c.folhaDO}}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Links publicação Diário Oficial:</b>
                                                                    </td>
                                                                    <td>
                                                                        <p><a target="_blank"
                                                                                v-bind:href="c.linkPublicacaoDO">{{c.linkPublicacaoDO}}</a>
                                                                        </p>
                                                                        <p><a target="_blank"
                                                                                v-bind:href="c.linkPublicacaoDO2">{{c.linkPublicacaoDO2}}</a>
                                                                        </p>
                                                                        <p><a target="_blank"
                                                                                v-bind:href="c.linkPublicacaoDO3">{{c.linkPublicacaoDO3}}</a>
                                                                        </p>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="200"><b>Retificações FCP/Observações:</b></td>
                                                                    <td>{{c.retificacaoFcpObservacao}}</td>
                                                                </tr>


                                                            </table>
                                                        </b-card>
                                                    </div><br>
                                                </tbody>

                                            </table>
                                        </b-card-text>
                                    </b-card-body>
                                </b-collapse>
                            </b-card>

                            <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-1 variant="info">Localização</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-1" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                      
                                          <div v-if="comunitySelected != null && comunitySelected.dadosAdicionais != null" class="columns" >
                                <div class="column">
                                    <table class="table">
                                        <tr>
                                            <th>Latitude:</th>
                                            <td>{{comunitySelected.dadosAdicionais.latitudeIbge}}</td>
                                        </tr>                                        
                                        <tr>
                                            <th>Longitude:</th>
                                            <td>{{comunitySelected.dadosAdicionais.longitudeIbge}}</td>
                                        </tr>
                                        <tr>
                                            <th>Localização:</th>
                                            <td>
                                                <a v-if="comunitySelected.dadosAdicionais.localizacaoIncra != null && comunitySelected.dadosAdicionais.localizacaoIncra != '' " target="_blank" v-bind:href="comunitySelected.dadosAdicionais.localizacaoIncra">Clique para ver a localização no mapa!</a>
                                            </td>
                                        </tr>
                                        
                                    </table>
                                </div>
                                
                            </div> 

                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>

                        </div>

                    </b-modal>
                </div>
            </div>
        </div>
    </div>
    <!--fim comunidade-->
    </b-tab>

    <b-tab title="Territórios">
    <!--inicio territorio-->
    <div id="territorio">
        <div class="row">

            <div class="col-md-2">
                <b-form-select v-model="perPage" :options="options"></b-form-select>
            </div>

            <div class="col-md-3">
                <b-form-select v-model="estadoSelected" @change="getTerritorios">
                    <option :value="null">Selecione um Estado</option>
                    <option v-for="estado in estados" :value="estado.codigoIbge">{{ estado.nome }}</option>
                </b-form-select>

            </div>

            <div class="col-md-4">

                <div>
                    <input title=" Buscar nome/parte do nome Território" @keyup.enter="getTerritorios"
                        placeholder="Buscar nome/parte do nome Território" v-model="textoBusca"
                        type="text" class="form-control">
                </div>

                <div class="row" v-if="0==1">
                    <div class="col-md">
                        <b-form-radio v-model="buscarPor" value="1">Buscar por Comunidade</b-form-radio>
                    </div>
                    <div class="col-md">
                        <b-form-radio v-model="buscarPor" value="2">Buscar por Território</b-form-radio>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <b-button-group>
                    <b-button @click="getTerritorios" variant="info">Buscar</b-button>
                    <b-button @click="limparFiltros" variant="danger">Limpar Filtros</b-button>
                </b-button-group>
            </div>
        </div>

        <div class="row">
            <div class="col-md">

                <b-table id="my-table" :items="itemsTerritorio" :per-page="perPage" :current-page="currentPage" :fields="fieldsTerritorio"
                    small>
                    <template v-slot:cell(certificada)="data">
                        <b-badge v-if="data.item.certificada" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.certificada" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(processo)="data">
                        <b-badge v-if="data.item.processo" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.processo" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(edital)="data">
                        <b-badge v-if="data.item.edital" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.edital" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(portaria)="data">
                        <b-badge v-if="data.item.portaria" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.portaria" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(decreto)="data">
                        <b-badge v-if="data.item.decreto" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.decreto" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(titulo)="data">
                        <b-badge v-if="data.item.titulo" variant="success">SIM</b-badge>
                        <b-badge v-if="!data.item.titulo" variant="danger">NÃO</b-badge>
                    </template>

                    <template v-slot:cell(view)="data">
                        <a class="button " title="Ver Informações do Território!" @click="onRowTerritorioSelect(data.item.view)">
                            <i style="color: #20bc56;" class="fas fa-eye"></i>
                        </a>
                    </template>


                </b-table>

                <b-pagination v-model="currentPage" :total-rows="rowsterritorio" :per-page="perPage" aria-controls="my-table"
                    align="center"></b-pagination>

                <div v-if="territorioSelected != null">
                    <b-modal size="xl" Large Modal scrollable v-model="modalTerritorioShow" hide-footer>
                        <template v-slot:modal-title>
                            Território: {{territorioSelected.nome}}
                        </template>

                        <div role="tablist">


                            <b-card no-body class="mb-1">
                                <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-1 variant="info">Municípios</b-button>
                                </b-card-header>
                                <b-collapse id="accordion-1" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                        <b-card-text>

                                            <table class="table" v-if="territorioSelected != null">
                                                <thead>
                                                    <tr>
                                                        <th>Nome</th>
                                                        <th>Cod.IBGE</th>
                                                        <th>Superintendência</h>
                                                        <th>Estado</th>
                                                        <th>Região</th>
                                                        <th>Amazônia Legal</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr v-if="territorioSelected != null && territorioSelected.municipioList.length > 0" v-for="m in territorioSelected.municipioList" :key="m.id">
                                                        <td>{{m.nome}}</td>
                                                        <td>{{m.codigoIbge}}</td>
                                                        <td>
                                                            <b-tag type="is-primary"
                                                                v-for="superintendencia in m.superintendencias"
                                                                :key="superintendencia.id" rounded>
                                                                {{superintendencia.nome}}
                                                            </b-tag>
                                                        </td>
                                                        <td>{{m.estado.nome}}</td>
                                                        <td>{{m.estado.regiao.nome}}</td>
                                                        <td>
                                                            <i style="color: green;" class="fa fa-check"
                                                                v-show="m.amazoniaLegal===true"></i>
                                                            <i style="color: red;" class="fa fa-times"
                                                                v-show="m.amazoniaLegal===false"></i>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                        </b-card-text>
                                    </b-card-body>
                                </b-collapse>
                            </b-card>


                            <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-3 variant="info">Processos Administrativos</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-3" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                          <table class="table" v-if="territorioSelected != null && territorioSelected.processosAdministrativos.length > 0">
                                          	<b-card  v-for="pa in territorioSelected.processosAdministrativos" :key="pa.id">
                                                <tbody>
                                                    <tr v-if="pa.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/processos/'+pa.id+'/documento'" style="color: #fff"  class="btn btn-success"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número Processo:</b></td>
                                                        <td>{{pa.numeroProcessoReconhecimento}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Âmbito:</b></td>
                                                        <td>{{pa.ambito != null ? pa.ambito.nome : ""}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Processo:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="pa.linkProcessoSei">{{pa.linkProcessoSei}}</a></p>
                                                            <p><a target="_blank" v-bind:href="pa.linkProcessoSei2">{{pa.linkProcessoSei2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="pa.linkProcessoSei3">{{pa.linkProcessoSei3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Localização Acervo Fundiário:</b></td>
                                                        <td>{{pa.localizacaoAcervoFundiario}}</td>
                                                    </tr>
                                                    
                                                </tbody>
                                                </b-card></br>
                                            </table>
                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>

                                <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-4 variant="info">Editais</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-4" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                          <table class="table" v-if="territorioSelected != null && (territorioSelected.editaisComunicacao.length > 0)">
                                          		<b-card v-for="ec in territorioSelected.editaisComunicacao" :key="ec.id">
                                                <tbody >
                                                    <tr v-if="ec.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/editais/'+ec.id+'/documento'" style="color: #fff"  class="btn btn-success"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Área HA Edital:</b></td>
                                                        <td>{{ec.areaHaEdital}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número de Famílias:</b></td>
                                                        <td>{{ec.numeroFamilias}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial I:</b></td>
                                                        <td>{{toLocalDate(ec.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial II:</b></td>
                                                        <td>{{toLocalDate(ec.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Edital:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="ec.linkPublicacao_1">{{ec.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="ec.linkPublicacao_2">{{ec.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="ec.linkPublicacao_3">{{ec.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                                </b-card>
                                            </table>
                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>

                                <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-11 variant="info">Portaria de Reconhecimento</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-11" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                      
                                          <table class="table" v-if="territorioSelected != null && territorioSelected.portariasReconhecimento.length > 0">
                                          	<b-card v-for="pr in territorioSelected.portariasReconhecimento" :key="pr.id">
                                              <tbody >
                                                    <tr v-if="pr.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/portarias/'+pr.id+'/documento'" style="color: #fff"  class="btn btn-success"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número:</b></td>
                                                        <td>{{pr.numeroPortariaReconhecimento}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Portaria:</b></td>
                                                        <td>{{toLocalDate(pr.dataPortariaReconhecimento)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(pr.portariaReconhecimentoDiarioOficialUniao)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="pr.linkPublicacao_1">{{pr.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="pr.linkPublicacao_2">{{pr.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="pr.linkPublicacao_3">{{pr.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                                </b-card>
                                          </table>
	  										
                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>

                                <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-6 variant="info">Decretos</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-6" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                      
                                          <table class="table" v-if="territorioSelected != null && territorioSelected.decretosDesapropriatorios.length > 0">
                                          		<b-card v-for="dd in territorioSelected.decretosDesapropriatorios" :key="dd.id">
                                                <tbody  >
                                                    <tr v-if="dd.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/decretos/'+dd.id+'/documento'" style="color: #fff"  class="btn btn-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número Decreto:</b></td>
                                                        <td>{{dd.numeroDecretoDesapropriacao}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data do Decreto:</b></td>
                                                        <td>{{toLocalDate(dd.dataDecretoDeaproriacaoa)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(dd.dataDecretoDesapropriacaoDiarioOficialUniao)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="dd.linkPublicacao_1">{{dd.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="dd.linkPublicacao_2">{{dd.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="dd.linkPublicacao_3">{{dd.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                                </b-card>
                                            </table>

                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>
                                
                                <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-7 variant="info">Títulos de Propriedade</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-7" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                      
                                          <table class="table" v-if="territorioSelected != null && territorioSelected.tituloPropriedade.length > 0">
                                          		<b-card v-for="tp in territorioSelected.tituloPropriedade" :key="tp.id">
                                                <tbody >
                                                    <tr v-if="tp.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/titulos/'+tp.id+'/documento'" style="color: #fff"  class="btn btn-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Área HA Título:</b></td>
                                                        <td>{{tp.areaHaTitulo}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>% área titulada:</b></td>
                                                        <td>{{tp.percentagemAreaTitulada}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número de Famílias:</b></td>
                                                        <td>{{tp.numeroFamiliaTitulos}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data do Título:</b></td>
                                                        <td>{{toLocalDate(tp.dataTitulo)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Órgão expedidor:</b></td>
                                                        <td>{{tp.orgaoExpedidor != null ? 
                                                            tp.orgaoExpedidor.nome: ""}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Título:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="tp.linkPublicacao_1">{{tp.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="tp.linkPublicacao_2">{{tp.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="tp.linkPublicacao_3">{{tp.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Observção:</b></td>
                                                        <td>{{tp.observacaoAreaTitulo}}</td>
                                                    </tr>
                                                    
                                                </tbody>
                                                </b-card>
                                            </table>

                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>

                                <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-8 variant="info">Assentamentos</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-8" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                      
                                          <table class="table" v-if="territorioSelected != null && territorioSelected.assentamentos.length > 0">
                                          		<b-card v-for="act in territorioSelected.assentamentos" :key="act.id">
                                               <tbody >
                                                    <tr v-if="act.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/assentamentos/'+act.id+'/documento'" style="color: #fff"  class="btn btn-success"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número:</b></td>
                                                        <td>{{act.numero}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Portaria:</b></td>
                                                        <td>{{toLocalDate(act.dataPortaria)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(act.dataDO)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Seção Diário Oficial:</b></td>
                                                        <td>{{act.secaoDO}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Folha Diário Oficial:</b></td>
                                                        <td>{{act.folhaDO}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data Decreto:</b></td>
                                                        <td>{{toLocalDate(act.dataDecreto)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Área:</b></td>
                                                        <td>{{act.area}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Tipo:</b></td>
                                                        <td>{{act.tipo != null ? act.tipo.nome : ""}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="act.linkPublicacaoDecreto_1">{{act.linkPublicacaoDecreto_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="act.linkPublicacaoDecreto_2">{{act.linkPublicacaoDecreto_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="act.linkPublicacaoDecreto_3">{{act.linkPublicacaoDecreto_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Links publicação Diário Oficial:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="act.linkPublicacaoDiarioUniaoDecreto_1">{{act.linkPublicacaoDiarioUniaoDecreto_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="act.linkPublicacaoDiarioUniaoDecreto_2">{{act.linkPublicacaoDiarioUniaoDecreto_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="act.linkPublicacaoDiarioUniaoDecreto_3">{{act.linkPublicacaoDiarioUniaoDecreto_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                                </b-card>
                                            </table>

                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>

                                <b-card no-body class="mb-1">
                                  <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-10 variant="info">Matrículas</b-button>
                                  </b-card-header>
                                  <b-collapse id="accordion-10" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                      <b-card-text>
                                      
                                          <table class="table" v-if="territorioSelected != null && territorioSelected.matriculas.length > 0">
                                          		<b-card v-for="mt in territorioSelected.matriculas" :key="mt.id">
                                               <tbody >
                                                    <tr v-if="mt.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/matriculas/'+mt.id+'/documento'" style="color: #fff"  class="btn btn-success"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Cartório:</b></td>
                                                        <td>{{mt.cartorioRegistroImoveis}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Livro:</b></td>
                                                        <td>{{mt.livroRegistroImoveis}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Folha:</b></td>
                                                        <td>{{mt.folhaRegistroImoveis}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Matrícula:</b></td>
                                                        <td>{{mt.matricula}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="mt.linkPublicacao_1">{{mt.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="mt.linkPublicacao_2">{{mt.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="mt.linkPublicacao_3">{{mt.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                                </b-card>
                                          </table>

                                      </b-card-text>
                                    </b-card-body>
                                  </b-collapse>
                                </b-card>

                                <b-card no-body class="mb-1">
                                <b-card-header header-tag="header" class="p-1" role="tab">
                                    <b-button block href="#" v-b-toggle.accordion-13 variant="info">Dados Adicionais
                                    </b-button>
                                </b-card-header>
                                <b-collapse id="accordion-13" accordion="my-accordion" role="tabpanel">
                                    <b-card-body>
                                        <b-card-text>
                                            <div v-if="territorioSelected != null">

                                                    <table class="table">
                                                        <tr>
                                                            <th width="300">Cadastro INCRA:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.cadastroIncra}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Cadastro SIGEF:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.cadastroSigef}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Cadastro CAR:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.cadastroCar}}</td>
                                                        </tr>
                                                        
                                                        <tr>
                                                            <th>Latitude IBGE:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.latitudeIbge}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Publicação Terra Quilombo:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.publicacaoTerraQuilombo}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Latitude INCRA:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.latitudeIncra}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Latitude SUL/NORTE Certificação:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.latitudeSNcertificacao}}</td>
                                                        </tr>
                                                        
                                                        <tr>
                                                            <th>Localização INCRA:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.localizacaoIncra}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Longitude IBGE:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.longitudeIbge}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Longitude INCRA:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.longitudeIncra}}</td>
                                                        </tr>
                                                        <tr>
                                                            <th>Longitude OESTE/LESTE:</th>
                                                            <td>{{territorioSelected.dadosAdicionais.longitudeWEcertificacao}}</td>
                                                        </tr>

                                                        <tr>
                                                            <th>Outros Processos Administrativos:</th>
                                                            <td>
	                                                            <p v-for="procadm in territorioSelected.dadosAdicionais.outrosProcessosAdministrativos" :key="procadm.link">
				                                            		<a target="_blank" v-bind:href=procadm.link>{{procadm.link}}</a>
				                                            	</p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>Processos Judiciais:</th>
                                                            <td>
	                                                            <p v-for="procjud in territorioSelected.dadosAdicionais.processosJudiciais" :key="procjud.link">
				                                            		<a target="_blank" v-bind:href=procjud.link>{{procjud.link}}</a>
				                                            	</p>
                                                            </td>
                                                        </tr>
                                                        
                                                        <tr>
                                                            <th>Observações:</th>
                                                            <td>
	                                                            <td>{{territorioSelected.dadosAdicionais.observacao}}</td>
                                                            </td>
                                                        </tr>
                                                    </table>
                                            </div>
                                        </b-card-text>
                                    </b-card-body>
                                </b-collapse>
                            </b-card>

                        </div>

                    </b-modal>
                </div>
            </div>
        </div>
    </div>
    <!--fim territorio-->
    </b-tab>
    </b-tabs>
    </div>
</div>
            `,
};
