const estadosURL = URL_BASE+"/estados"
const municipiosURL = URL_BASE+"/municipios";
const comunidadesURL = URL_BASE+"/comunidades";
const territoriosURL = URL_BASE+"/territorios";
const superintendenciasURL = URL_BASE+"/superintendencias";
const ModalAddMunicipio = {
	        props: ['data'],
	        data: function () {
	            return {
	            	object:{
		            	id:null,
		            	nome: null,
		            	codigoIbge: null,
		            	estado: null,
		            	superintendencias: [],
		            	comunidades: [],
		            	territorioList: [],
		            	amazoniaLegal: false
		            },
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
	            save(idComunidade) {
//	            	this.object.comunidades.push({id: idComunidade})
	            	this.getComuni(this.object.comunidades)
                    this.getSuperints(this.object.superintendencias)
                    this.getTerrit(this.object.territorioList)
            		crudService.create(municipiosURL, this.object, r => {
            			if(r.status==200){
            				this.$buefy.toast.open({
			                    duration: 2000,
			                    message: "Município adicionado e vinculado com sucesso!",
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
		        	
	                this.$emit('reload-data')
	                this.$emit('desativa-novo-municipio')
		        }
	        },
	        mounted() {
	        	this.getEstados()
	        	this.getSuperintendencias()
	        	this.getTerritorios()
                this.getComunidades()
                this.object.territorioList.push(this.data)
	        },
	        components: {
    			Multiselect: window.VueMultiselect.default
  			},
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-form" style="font-size: 30px;"></i> Adicionar e Vincular Novo Município 
		                    </p>
	                    </header>
	                    <section class="modal-card-body">
	                        <template>
						        <b-field  label="Nome do Município"  width="70px">
						            <b-input v-model="object.nome" required   type="text" ></b-input>
						        </b-field>
						        <b-field  label="Código IBGE"  width="70px">
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
                                    <span slot="noResult">Comunidade não encontrada!.</span>
                                </multiselect><br>
                                
                                <label class="typo__label"><b>Superintendência(s)</b></label>
                                <multiselect 
                                    v-model="object.superintendencias" 
                                    :options="superintendencias"
                                    :multiple="true"
                                    track-by="id"
                                    label="nome"
                                    placeholder="Selecione uma ou mais Superintendências!"
                                    >
                                    <span slot="noResult">Superintendência não encontrada!.</span>
                                </multiselect><br>
                                
                                <label class="typo__label"><b>Território(s)</b></label>
                                <multiselect 
                                    v-model="object.territorioList" 
                                    :options="territorios"
                                    :multiple="true"
                                    track-by="id"
                                    label="nome"
                                    placeholder="Selecione uma ou mais Territórios!"
                                    >
                                    <span slot="noResult">Território não encontrado!.</span>
                                </multiselect><br>
						        
						        <b-field   >						         
						            <b-checkbox v-model="object.amazoniaLegal">
						                Amazônia Legal
						            </b-checkbox>
						        </b-field>
								<b-button @click.native="save(data.id)"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
								<button class="button  is-danger" type="button" @click="$parent.close()"><i class="fa fa-arrow-left"></i> Cancelar</button>
					    	</template>
	                    </section>
	                    <footer class="modal-card-foot">
	                        
	                    </footer>
	                </div>
	        `
	}