const COMURL = "/sigequi/api/v1/relatorios/";
const comunidadeexportar = {
  data: function () {
	  return {
          estado:null,
          relatorio:null,
          estados:[
          	"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB",
          	"PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"],
          relatorios:[
          	{id:0, relatorio:"Assentamento"},
      		{id:1, relatorio:"CCDRU"},
      		{id:2, relatorio:"Decreto"},
  			{id:3, relatorio:"Edital"},
  			{id:4, relatorio:"Portaria"},
 				{id:5, relatorio:"Processo"},
 				{id:6, relatorio:"Título"}
          ]
      }
      
  },
  methods: {
	  gotToUrl(url) {
          window.location.href = url;
      },
      exportar() {
      	uri= this.estado != null ? COMURL+"comunidade.xls/estado/"+this.estado : COMURL+"comunidade.xls";
  		if(uri != null){
  			this.gotToUrl(uri);
  		}
      }
  },
  mounted() {
  },
  template: `
<div class="mt-3">
    <div class="row">
        <div class="col">
            <h2 class="text-center text-success">Exportar dados de Comunidades(Excel/XLSX)</h2>
        </div>
    </div>
    <hr>
    
    <div>
    
	  <div class="col-md-3">
		    <b-form-select v-model="estado">
		        <option :value="null">Brasil</option>
		        <option v-for="estado in estados" :value="estado">{{ estado }}</option>
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
