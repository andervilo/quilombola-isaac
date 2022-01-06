const ModalPortariaReconhecimento = {
	        props: ['data','territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	numeroPortariaReconhecimento: null,
		            	dataPortariaReconhecimento: null,
		            	portariaReconhecimentoDiarioOficialUniao: null,
		            	linkPublicacao_1: null,
                        linkPublicacao_2: null,
                        linkPublicacao_3: null,
                        secaoDO: null,
                        folhaDO: null,
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isObjetoSetado: false,
		            isUploadAtivo: false,
		            file: null
	        	}
	        },
	        methods:{
	        	getObjeto(){
	        		crudService.findById(URL_BASE+"/comunidades/", this.territorio.id, r => {
	        			if(r.data.portariaReconhecimento != null){
	        				this.isObjetoSetado = true
	        				this.object = r.data.portariaReconhecimento	
	        				this.object.dataPortariaReconhecimento = new Date(this.object.dataPortariaReconhecimento)
	        				this.object.portariaReconhecimentoDiarioOficialUniao = 
	        					new Date(this.object.portariaReconhecimentoDiarioOficialUniao)
	        			}
	        		})
	        	},
                save(setado) {
		        	if(setado){
		        		this.object.comunidade = this.territorio.id
		        		crudService.update(URL_BASE+"/comunidades/portaria-reconhecimento", "", this.object, r => {
		        			console.log(r)
		        			if(r.status == 200){
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
	            	}else{
	            		this.object.comunidade = this.territorio.id
	            		crudService.create(URL_BASE+"/comunidades/portaria-reconhecimento", this.object, r => {
	            			console.log(r)
	            			if(r.status == 200){
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
	            	}
		        	
		        	this.$parent.close() 
		        },
		        saveDocumento(){
		    		var formData = new FormData();
		    		formData.append("documento", this.file, this.file.name)
		    		
		    		axios
		    	      .post("/sigequi/portarias/"+this.object.id+"/documento", formData, {headers: {'Content-Type': 'multipart/form-data', 'Accept': 'application/json'}})
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
				    	                this.getObjeto()
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
	        },
	        watch:{
	        	object:function(val){
	        		this.object = val;
	        	}
	        },
	        created() {
	        	this.getObjeto()
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Portaria de Reconhecimento
		                    	<button v-if="isObjetoSetado" class="button is-primary is-medium"
						            @click="isUploadAtivo = true"><i class="fa fa-upload"></i>&nbsp; Upload
						        </button>
						        <a :href="'/sigequi/portarias/'+object.id+'/documento'" style="color: #fff" v-if="object.nomeDocumento != null" class="button is-success is-medium"
						            ><i class="fa fa-download"></i>&nbsp; Download
						        </a>
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						        <b-field  label="Número da Portaria de Reconhecimento" >
						            <b-input v-model="object.numeroPortariaReconhecimento"  placeholder="Número da Portaria de Reconhecimento" type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data da Portaria de Reconhecimento">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            :years-range="[-20,100]"
							            v-model="object.dataPortariaReconhecimento">
							        </b-datepicker>
							    </b-field>
							    
						        <b-field label="Data de Publicação da Portaria de Reconhecimento no DO">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        :years-range="[-20,100]"
                                        v-model="object.portariaReconhecimentoDiarioOficialUniao">
                                    </b-datepicker>
                                </b-field>
                                
                                <b-field  label="Seção Diário Oficial" >
                                    <b-input v-model="object.secaoDO"   placeholder="Seção Diário Oficial" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Folha Diário Oficial" >
                                    <b-input v-model="object.folhaDO"   placeholder="Folha Diário Oficial" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link publicação 1" >
                                    <b-input v-model="object.linkPublicacao_1"   placeholder="Link publicação 1" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link publicação 2" >
                                    <b-input v-model="object.linkPublicacao_2"   placeholder="Link publicação 2" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link publicação 3" >
                                    <b-input v-model="object.linkPublicacao_3"   placeholder="Link publicação 3" type="text" ></b-input>
                                </b-field>
                                
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
											        <span class="file-name" v-if="file">
											            {{ file.name }}
											        </span>
											    </b-field>
											    <b-button type="is-primary" @click.native="saveDocumento">Enviar</b-button>
											    <b-button @click="isUploadAtivo = false"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
											</template>
						                </div>
						            </div>
						        </b-modal>
							</template>
		                </section>
	                    <footer class="modal-card-foot">
	                        <b-button v-show="!isObjetoSetado" @click.native="save(isObjetoSetado)"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button v-show="isObjetoSetado" @click.native="save(isObjetoSetado)"  type="is-info"><i class="fa fa-edit"></i> Editar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}