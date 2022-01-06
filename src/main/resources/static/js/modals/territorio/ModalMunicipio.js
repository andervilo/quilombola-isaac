const ModalMunicipio = {
        props: ['dataMunicipios'],
        data: function () {
            return {
            	data: this.dataMunicipios.municipioList,
            	isVincularMunicipioActive:false,
            	isListMunicipiosActive:true,
            	municipios:[],
            	name: '',
                selected: {nome:null},
                titulo:""
            }
        },
        computed: {
            filteredDataArray() {
                return this.municipios.filter((option) => {
                    return option.nome
                        .toString()
                        .toLowerCase()
                        .indexOf(this.name.toLowerCase()) >= 0
                })
            }
        },
        methods:{
        	chamaModalNovoMunicipio(){
        		this.$emit('ativa-novo-municipio')
			},
			desativaModalMunicipio(){
				this.$emit('desativa-modal-municipio')
			},
			ativaModalMunicipio(){
				this.$emit('ativa-modal-municipio')
			},
			chamaModalEditeMunicipio(municipio){
				this.$emit('ativa-editar-municipio', true, municipio)
			},
			ativaModalVincularMunicipio(){	
				this.titulo="Vincular Município ao Território"
				this.isVincularMunicipioActive = true
				this.isListMunicipiosActive = false
            },
			desativaModalVincularMunicipio(){	       
				this.isVincularMunicipioActive = false
				this.isListMunicipiosActive = true
            },
			
			desvincularMunicipio(idTerritorio, municipio) {
            	this.desativaModalMunicipio()   
				
        		crudService.update(URL_BASE+"/territorios/desvincular-territorio", "", {municipioId: municipio.id, territorioId: idTerritorio}, r => {
					console.log(r)
        			 if(r.status==200){
        			 	this.$buefy.toast.open({
                             duration: 3000,
                             message: r.data.message,
                             position: 'is-top',
                             type: 'is-success'
                         })
                         
        			 }else{
        			 	this.$buefy.dialog.alert({
		                    title: 'Erro',
		                    message: r.data.message,
		                    type: 'is-danger',
		                    hasIcon: true,
		                    icon: 'times-circle',
		                    iconPack: 'fa'
		                })
        			 }
        		}) 
        		this.ativaModalMunicipio()
	        },
	        vincular(){
        		this.desativaModalMunicipio()
	        	var objeto = this.selected
	        	objeto.estado = this.selected.estado.sigla	
	        	
	        	objeto.territorioList.push(this.dataMunicipios.id)
	        	
	        	this.dataMunicipios.municipioList.push({"id":this.selected.id})
	        	
	        	crudService.update(URL_BASE+"/territorios/vincular-territorio", "", {municipioId: this.selected.id, territorioId: this.dataMunicipios.id}, r => {
					console.log(r)
        			 if(r.status==200){
        			 	this.$buefy.toast.open({
                             duration: 3000,
                             message: r.data.message,
                             position: 'is-top',
                             type: 'is-success'
                         })
                         this.desativaModalVincularMunicipio()
                        this.loadTerritorio(this.dataMunicipios.id)
        			 }else{
        			 	this.$buefy.dialog.alert({
		                    title: 'Erro',
		                    message: r.data.message,
		                    type: 'is-danger',
		                    hasIcon: true,
		                    icon: 'times-circle',
		                    iconPack: 'fa'
		                })
        			 }
        		})

				this.ativaModalMunicipio()
	        },
	        loadTerritorio(idTerritorio){
	        	this.data=[]
	        	crudService.findById(URL, idTerritorio, r => {
	        		this.data =  _.sortBy(r.data.municipioList, ['nome'])
        		})
	        },
            getMunicipios() {
    			this.municipios = []
                crudService.findAll(municipiosURL, 0, 5000, "nome",  r => {	 
 					this.municipios = _.sortBy(r.data.content, ['nome'])
				})	                
	        }
        },
        mounted() {
        	this.loadTerritorio(this.dataMunicipios.id)
        	this.getMunicipios()
        	this.titulo="Relação de Municípios do Território"
       	},
	        template: `
	                <div class="modal-card">
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
	                        <i class="icon-interface-windows" style="font-size: 30px;"></i> {{titulo}}
	                        </p>
	                        
	                    </header>
	                    <section class="modal-card-body"> 
		                    <button class="button field is-info" @click="chamaModalNovoMunicipio">
		                    	<i class="fas fa-plus"></i>&nbsp;&nbsp;
		                    	<span>Adicionar Município</span>
					        </button>
					        <button class="button field is-info" 
					        v-show="isListMunicipiosActive"
					        @click="ativaModalVincularMunicipio"
					        >
		                    	<i class="fas fa-sign-in-alt"></i>&nbsp;&nbsp;
		                    	<span> Vincular Município</span>
					        </button>
					        
					        <div v-show="isVincularMunicipioActive">     
								        <p class="content"><b>Município selecionado:</b> {{ selected.nome }} </p>
								        <b-field label="Buscar por município">
								            <b-autocomplete
								                rounded
								                v-model="name"
								                :data="filteredDataArray"
								                placeholder="Digite o nome do Município que deseja vincular!"
								                icon="magnify"
								                :keep-first="false"
								                :open-on-focus="false"
								                :clear-on-select="true"
								                @select="option => selected = option">
								                <template slot-scope="props">
								                    <div class="media">
								                        <div class="media-content">
								                            <b>{{ props.option.nome }}</b>
								                            <br>
								                            <small>
								                                Estado: {{ props.option.estado.nome }},</br>
								                                Região: {{ props.option.estado.regiao.nome }}							                                
								                            </small>
								                        </div>
								                    </div>
								                </template>
								                <template slot="empty">Sem resultados para sua busca!</template>
								            </b-autocomplete>
								        </b-field>
								        <div>
	        								<button :disabled="selected.nome == null" class="button  is-info" type="button" @click="vincular"><i class="fas fa-save"></i>&nbsp;Vncular</button>
								        	<button class="button  is-danger" type="button" @click="desativaModalVincularMunicipio()"><i class="fa fa-arrow-left"></i>&nbsp;Cancelar</button>
	                    
								        </div>
								</div>
		                    <b-table
		                    	v-show="isListMunicipiosActive"
		                    	:data="data"		                        
		                        :striped="true"
		                        :narrowed="true"
		                        :hoverable="true"
		                        :mobile-cards="true"
		                    >
			                    <template slot-scope="props">
			                    
				                    <b-table-column field="id" label="ID" width="40" numeric>
				                        {{ props.row.id }}
				                    </b-table-column>
			
				                    <b-table-column field="nome" label="Município">
				                        {{ props.row.nome }}
				                    </b-table-column>
				                    
				                    <b-table-column field="codigoIbge" label="C.IBGE">
				                        {{ props.row.codigoIbge }}
				                    </b-table-column>
				                    
				                    <b-table-column field="estado.nome" label="Estado">
				                        {{ props.row.estado.nome }} ({{ props.row.estado.sigla }})
				                    </b-table-column>
				                    
				                    <b-table-column field="estado.regiao.nome" label="Região">
				                        {{ props.row.estado.regiao.nome }}
				                    </b-table-column>
				                    <b-table-column centered field="amazoniaLegal" label="Amz.Legal"> 
										<i title="Pertence à Amazônia Legal" style="color: green;" class="fa fa-check"   v-show="props.row.amazoniaLegal===true"></i>
										<i title="Não pertence à Amazônia Legal" style="color: red;" class="fa fa-times"   v-show="props.row.amazoniaLegal===false"></i>										
									</b-table-column>
									<b-table-column field="" label=""> 
										<a title="Desvincular Municipio" @click="desvincularMunicipio(dataMunicipios.id, props.row)"  type="is-text"><i class="fas fa-share-square"></i></a>
										<a title="Editar Municipio" @click="chamaModalEditeMunicipio(props.row)"  type="is-text"><i class="fas fa-edit"></i></a>
									</b-table-column>
				                </template>
				                <template slot="empty">
			                    <section class="section">
			                        <div class="content has-text-grey has-text-centered">
			                            <p>
			                                <b-icon
			                                    icon="emoticon-sad"
			                                    size="is-large">
			                                </b-icon>
			                            </p>
			                            <p>Não há registros para exibir.</p>
			                        </div>
			                    </section>
			                </template>
				            </b-table>
		                    </section>
		                    
		                    
		                    
	                    <footer class="modal-card-foot">
	                        <button v-show="isListMunicipiosActive" class="button  is-danger" type="button" @click="$parent.close()">Fechar</button>
	                    </footer>
	                </div>
	        `
}