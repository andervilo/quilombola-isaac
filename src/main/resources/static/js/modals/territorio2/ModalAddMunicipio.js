const estadosURL = URL_BASE+"/estados"
const municipiosURL = URL_BASE+"/municipios";
const ModalAddMunicipio = {
	        props: ['data'],
	        data: function () {
	            return {
	            	object:{
		            	id:null,
		            	nome: null,
		            	codigoIbge: null,
		            	sr: null,
		            	estado: null,
		            	amazoniaLegal: false,
		            	territorioList:[]
		            },
		            estados:[]
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
	            save(idTerritorio) {
	            	this.object.territorioList = [{id: idTerritorio}]
	            	this.object.territorioId = idTerritorio
	            	console.log(this.object)
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
						        <b-field  label="Código SR"  width="70px">
						            <b-input v-model="object.sr" required   type="text" ></b-input>
						        </b-field>
						        <b-field  label="Estado" >
						            <b-select v-model="object.estado" placeholder="Selecione um Estado!">
						                <option v-for="estado in estados" :value="estado.sigla">{{estado.nome}}</option>
						            </b-select>
						        </b-field>
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