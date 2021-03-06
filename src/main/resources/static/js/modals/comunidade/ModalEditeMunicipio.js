//const estadosURL = "http://localhost:8500/sigequi/api/v1/estados"
//const municipiosURL = "/sigequi/api/v1/municipios/";
const ModalEditeMunicipio = {
	        props: ['data', 'toEdite'],
	        data: function () {
	            return {
	            	object: this.toEdite,
		            estados:[],
		            superintendencias: [],
		            territorios: [],
                    comunidades: [],
	            }
	        },
	        methods:{
	        	chamaModalNovoMunicipio(){
	        		this.$emit('ativa-novo-municipio')
				},
				getEstados(){
	            	crudService.findAllNoPagination(estadosURL, r => {
	 					this.estados = _.sortBy(r.data, ['nome'])
					})	
	            },
	            getSuperintendencias(){
	            	crudService.findAllNoPagination(superintendenciasURL, r => {
	 					this.superintendencias = _.sortBy(r.data.content, ['nome'])
					})	
	            },
                getTerritorios(){
                    crudService.findAllNoPagination(territoriosURL, r => {
                        this.territorios = _.sortBy(r.data.content, ['nome'])
                    })  
                },
                getComunidades(){
                    crudService.findAllNoPagination(comunidadesURL, r => {
                        this.comunidades = _.sortBy(r.data.content, ['nome'])
                    })  
                },
                getSuperints(idsSuper){
                    sup = []                    
                    idsSuper.forEach((item, index)=>{
                        sup.push({id:item.id})
                    })
                    this.object.superintendencias=[]
                    this.object.superintendencias=sup
                },
                getTerrit(ids){
                    sup = []                    
                    ids.forEach((item, index)=>{
                        sup.push({id:item.id})
                    })
                    this.object.territorioList=[]
                    this.object.territorioList=sup
                },
                getComuni(ids){
                    sup = []                    
                    ids.forEach((item, index)=>{
                        sup.push({id:item.id})
                    })
                    this.object.comunidades=[]
                    this.object.comunidades=sup
                },
                getObject(){
                    crudService.findById(municipiosURL+"/", this.object.id, r=>{
                        this.object = r.data
                        this.object.estado = this.object.estado.sigla
                    })
                },
	            update() {
	            	this.getComuni(this.object.comunidades)
                    this.getSuperints(this.object.superintendencias)
                    this.getTerrit(this.object.territorioList)
	            	crudService.update(municipiosURL+"/", this.object.id, this.object, r => {
	            	    if(r.status == 200){
                            this.$buefy.toast.open({
                                duration: 3000,
                                message: r.data.message,
                                position: 'is-top',
                                type: 'is-success'
                            })
                            this.$emit('reload-data')
                            this.$emit('desativa-edite-municipio', false, this.toEdite)
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
//	        			if(r.data.success){
//	        				this.$buefy.toast.open({
//	                            duration: 3000,
//	                            message: "Munic??pio alterado com sucesso!",
//	                            position: 'is-top',
//	                            type: 'is-success'
//	                        })
//	                        
//	                        this.$emit('reload-data')
//	                
//	                        this.$emit('desativa-edite-municipio', false, this.toEdite)
//	        			}else{
//	        				this.$buefy.toast.open({
//	                            duration: 3000,
//	                            message: "Erro ao realizar opera????o!",
//	                            position: 'is-top',
//	                            type: 'is-danger'
//	                        })
//	        			}
	        		})
	                

		        }
	        },
	        mounted() {
//	        	this.object.estado = this.object.estado.sigla
	            this.getObject()
	        	this.getEstados()
	        	this.getSuperintendencias()
                this.getTerritorios()
                this.getComunidades()
	        },
	        components: {
    			Multiselect: window.VueMultiselect.default
  			},
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-form" style="font-size: 30px;"></i> Editar Munic??pio 
		                    </p>
	                    </header>
	                    <section class="modal-card-body">  
	                        <template>
						        <b-field  label="Nome do Munic??pio"  width="70px">
						            <b-input v-model="object.nome" required   type="text" ></b-input>
						        </b-field>
						        <b-field  label="C??digo IBGE"  width="70px">
						            <b-input v-model="object.codigoIbge" required   type="text" ></b-input>
						        </b-field>
						        <b-field  label="Estado" >
						            <b-select v-model="object.estado" placeholder="Selecione um Estado!">
						                <option v-for="estado in estados" :value="estado.sigla">{{estado.nome}}</option>
						            </b-select>
						        </b-field>
						        
						        <label class="typo__label"><b>Comunidade(s)</b></label>
                                <multiselect 
                                    v-model="object.comunidades" 
                                    :options="comunidades"
                                    :multiple="true"
                                    track-by="id"
                                    label="nome"
                                    placeholder="Selecione uma ou mais Comunidades!"
                                    >
                                    <span slot="noResult">Comunidade n??o encontrada!.</span>
                                </multiselect><br>
                                
                                <label class="typo__label"><b>Superintend??ncia(s)</b></label>
                                <multiselect 
                                    v-model="object.superintendencias" 
                                    :options="superintendencias"
                                    :multiple="true"
                                    track-by="id"
                                    label="nome"
                                    placeholder="Selecione uma ou mais Superintend??ncias!"
                                    >
                                    <span slot="noResult">Superintend??ncia n??o encontrada!.</span>
                                </multiselect><br>
                                
                                <label class="typo__label"><b>Territ??rio(s)</b></label>
                                <multiselect 
                                    v-model="object.territorioList" 
                                    :options="territorios"
                                    :multiple="true"
                                    track-by="id"
                                    label="nome"
                                    placeholder="Selecione uma ou mais Territ??rios!"
                                    >
                                    <span slot="noResult">Territ??rio n??o encontrado!.</span>
                                </multiselect><br>
						        
						        <b-field   >						         
						            <b-checkbox v-model="object.amazoniaLegal">
						                Amaz??nia Legal
						            </b-checkbox>
						        </b-field>
								<b-button @click.native="update()"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
								<b-button @click="$parent.close()"       type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
					    	</template>
	                    </section>
	                    <footer class="modal-card-foot">
	                        
	                    </footer>
	                </div>
	        `
	}