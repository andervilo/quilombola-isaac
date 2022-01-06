const ModalMatriculaList = {
        props: ['matricula', 'id'],
        data: function () {
            return {
            	data: this.matricula,
            	isShowListActive:true,
            	isNewActive:false,
            	isEditActive:false,
            	isUploadAtivo: false,
            	municipios:[],
            	name: '',
                selected: null,
                titulo:"",
                file: {nome:null}
            }
        },
        computed: {
            
        },
        methods:{
        	
        	buscar(){
        		axios
      		  .get(`/sigequi/api/v1/territorios/${this.id}`)
	    	      .then(
	    	    		  response => {
			    	    	  if(response.status == 200){
			    	    		  console.log(response.data)
			    	    		  this.data = response.data.matriculas
			    	    	  }
			    	      })
		    	     .catch(error => {
	    	                this.$buefy.dialog.alert({
			                    title: 'Erro',
			                    message: error.response.data.message,
			                    type: 'is-danger',
			                    hasIcon: true,
			                    icon: 'times-circle',
			                    iconPack: 'fa'
			                })
		    	      }
	    	      )
            },
            
        	confirmCustomDelete(id) {
            	this.$buefy.dialog.confirm({
                    title: 'Excluir Registro',
                    message: 'Deseja Excluir registro? Esta operação não poderá ser desfeita!',
                    confirmText: 'Excluir',
                    cancelText: "Cancelar",
                    type: 'is-danger',
                    hasIcon: true,
                    onConfirm: () => {
                    	axios
              		  .delete(`/sigequi/api/v1/matriculas/${id}`)
              		  .then(response =>{
              			  
              			  console.log(response)
              			if(response.data.success){
	 						this.data = []
	 						this.buscar()
	 					}
              			
              			this.$buefy.toast.open({
		                    duration: 5000,
		                    message: "Registro Excluído com sucesso!",
		                    position: 'is-top',
		                    type: 'is-success'
		                })
              		  }).catch(error => {
	    	                this.$buefy.dialog.alert({
			                    title: 'Erro',
			                    message: error.response.data.message,
			                    type: 'is-danger',
			                    hasIcon: true,
			                    icon: 'times-circle',
			                    iconPack: 'fa'
			                })
			    	      }
		    	      )	
                	}
                })
            },
            
        	editar(_selected){
            	this.selected = _selected
                this.isEditActive = true
            },
            
            uploadAtivo(_selected){
            	this.selected = _selected
                this.isUploadAtivo = true
            },
            
        	saveDocumento(){
	    		var formData = new FormData();
	    		formData.append("documento", this.file, this.file.name)
	    		
	    		axios
	    	      .post("/sigequi/matriculas/"+this.selected.id+"/documento", 
	    	    		  formData, {headers: {'Content-Type': 'multipart/form-data', 'Accept': 'application/json'}})
	    	      .then(
	    	    		  response => {
			    	    	  console.log(response)
			    	    	  if(response.status == 200){
			    	    		  this.$buefy.toast.open({
			    	                    duration: 2000,
			    	                    message: "Operação realizada com sucesso!",
			    	                    position: 'is-top',
			    	                    type: 'is-success'
			    	                })
			    	                this.isUploadAtivo = false
			    	                this.buscar()
			    	    	  }
			    	      })
		    	     .catch(error => {
	    	                this.$buefy.dialog.alert({
			                    title: 'Erro',
			                    message: error.response.data.message,
			                    type: 'is-danger',
			                    hasIcon: true,
			                    icon: 'times-circle',
			                    iconPack: 'fa'
			                })
		    	      }
	    	      )
	    	}
            
        },
        mounted() {
            this.titulo="Matrículas"
            this.buscar()
       	},
	    template: `<div>
	                <div class="modal-card">
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
	                        <i class="icon-interface-windows" style="font-size: 30px;"></i> {{titulo}}
	                        </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <button class="button is-info is-small "
												@click="isNewActive = true"><i class="fas fa-plus"></i>
												&nbsp;&nbsp;Adicionar Matrícula</button>
		                    <b-table
		                    	v-show="isShowListActive"
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
			
				                    <b-table-column field="cartorioRegistroImoveis" label="Cartório">
				                        {{ props.row.cartorioRegistroImoveis }}
				                    </b-table-column>
				                    
				                    <b-table-column field="matricula" label="Matrícula">
				                        {{ props.row.matricula }}
				                    </b-table-column>
				                    
				                    <b-table-column field="" label="">
				                    	<a :href="'/sigequi/matriculas/'+props.row.id+'/documento'" style="color: #fff" 
				                    		v-if="props.row.nomeDocumento != null" class="button is-info is-small"
								            ><i class="fa fa-download"></i>
								        </a>
				                    	<b-button title="UpLoad" @click.native="uploadAtivo(props.row)" size="is-small" 
				                    	type="is-success"><i style="color:#fff" class="fa fa-upload"></i></b-button>
				                    	
				                    	<b-button title="Editar" @click.native="editar(props.row)" size="is-small" 
				                    	type="is-warning"><i style="color:#fff" class="fa fa-edit"></i></b-button>
				                    	
				                        <b-button title="Excluir" @click.native="confirmCustomDelete(props.row.id)" 
				                        size="is-small" type="is-danger"><i class="fa fa-eraser"></i></b-button>
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
	                        <button v-show="isShowListActive" class="button  is-danger" type="button" @click="$parent.close()">Fechar</button>
	                    </footer>
	                </div>
	                
					<b-modal can-cancel :active.sync="isNewActive" animation 
						:width="700" has-modal-card> 
						<modal-matricula-add  
							:id="id"
							@buscar="buscar">>
						</modal-matricula-add>
					</b-modal>
					
					<b-modal can-cancel :active.sync="isEditActive" animation 
						:width="700" has-modal-card> 
						<modal-matricula-edit  
							:id="id"
							:obj="selected"
							@buscar="buscar">
						</modal-matricula-edit>
					</b-modal>
					
					<b-modal :active.sync="isUploadAtivo" :width="640" scroll="keep">
			            <div class="card">
			                <div class="card-content">
			                    <template>
								    <b-field class="file">
								        <b-upload name="file" v-model="file">
								            <a style="color: #fff;" class="button is-primary">
								                <b-icon icon="upload"></b-icon>
								                <span >Selecione o arquivo para upload</span>
								            </a>
								        </b-upload>
								        <span class="file-name" v-if="file != null">
								            {{ file.name }}
								        </span>
								    </b-field>
								    <b-button type="is-primary" @click.native="saveDocumento">Enviar</b-button>
								    <b-button @click="isUploadAtivo = false"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
								</template>
			                </div>
			            </div>
			        </b-modal>
				</div>
	        `
}