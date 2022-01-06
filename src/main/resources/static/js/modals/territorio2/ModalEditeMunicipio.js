//const estadosURL = "http://localhost:8500/sigequi/api/v1/estados"
//const municipiosURL = "/sigequi/api/v1/municipios/";
const ModalEditeMunicipio = {
	        props: ['data', 'toEdite'],
	        data: function () {
	            return {
	            	object: this.toEdite,
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
	            update() {
	            
	            	crudService.update(municipiosURL+"/", this.object.id, this.object, r => {
	        			if(r.data.success){
	        				this.$buefy.toast.open({
	                            duration: 3000,
	                            message: "Município alterado com sucesso!",
	                            position: 'is-top',
	                            type: 'is-success'
	                        })
	                        
	                        this.$emit('reload-data')
	                
	                        this.$emit('desativa-edite-municipio', false, this.toEdite)
	        			}else{
	        				this.$buefy.toast.open({
	                            duration: 3000,
	                            message: "Erro ao realizar operação!",
	                            position: 'is-top',
	                            type: 'is-danger'
	                        })
	        			}
	        		})
	                

		        }
	        },
	        mounted() {
	        	this.object.estado = this.object.estado.sigla
	        	this.getEstados()
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-form" style="font-size: 30px;"></i> Editar Município 
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
								<b-button @click.native="update()"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
								<b-button @click="$parent.close()"       type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
					    	</template>
	                    </section>
	                    <footer class="modal-card-foot">
	                        
	                    </footer>
	                </div>
	        `
	}