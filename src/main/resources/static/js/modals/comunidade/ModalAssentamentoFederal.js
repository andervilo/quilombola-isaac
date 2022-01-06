const ModalAssentamentoFederal = {
	        props: ['territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	numero: null,
						dataPortaria: null,
						dataDO: null,
						secaoDO: null,
						folhaDO: null,
						area: null,
						tipo: "FEDERAL",
                        dataDecreto: null,
                        linkPublicacaoDecreto_1: null,
                        linkPublicacaoDecreto_2: null,
                        linkPublicacaoDecreto_3: null,
                        dataDiarioUniaoDecreto: null,
                        linkPublicacaoDiarioUniaoDecreto_1: null,
                        linkPublicacaoDiarioUniaoDecreto_2: null,
                        linkPublicacaoDiarioUniaoDecreto_3: null,
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isObjetoSetado: false,
		            ambitos: [],
		            isUploadAtivo: false,
	        	}
	        },
	        methods:{
	        	getObjeto(){
	        		axios
		      	      .get(URL_BASE+"/comunidades/"+this.territorio.id)
		      	      .then(response => {
		      	    	  if(response.data.assentamentoFederal != null){
		      	    		this.isObjetoSetado = true
	        				this.object = response.data.assentamentoFederal
	        				this.object.dataPortaria = new Date(this.object.dataPortaria)
	        				this.object.dataDecreto = new Date(this.object.dataDecreto)
	        				this.object.dataDiarioUniaoDecreto = new Date(this.object.dataDiarioUniaoDecreto)
	        				this.object.tipo = this.object.tipo.label
		      	    	  }
			      	  		
		      	      })
		      	      .catch(error => {
		      	    	  
		      	      })
	        	},
	        	getAmbitos(){
	        		crudService.findAllNoPagination(URL_BASE+"/ambito-processo", r => {
	        			this.ambitos = _.sortBy(r.data, ['nome'])
	        		})
	        	},
                save(setado) {
	        	    console.log(this.object)
		        	if(setado){
		        		crudService.update(URL_BASE+"/comunidades/"+this.territorio.id+"/assentamento-federal", "", this.object, r => {
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
	            		crudService.create(URL_BASE+"/comunidades/"+this.territorio.id+"/assentamento-federal", this.object, r => {
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
		        isNumber(evt) {
		            evt = (evt) ? evt : window.event;
		            var charCode = (evt.which) ? evt.which : evt.keyCode;
		            if ((charCode > 31 && (charCode < 48 || charCode > 57)) && charCode !== 46) {
		              evt.preventDefault();;
		            } else {
		              return true;
		            }
		          },
		          onlyNumber ($event) {
		        	   //console.log($event.keyCode); //keyCodes value
		        	  
		        	   let keyCode = ($event.keyCode ? $event.keyCode : $event.which);
		        	   if ((keyCode < 48 || keyCode > 57) && keyCode !== 46) { // 46 is dot
		        	      $event.preventDefault();
		        	      alert($event.keyCode)
		        	   }
	        	},
	        	saveDocumento(){
                    var formData = new FormData();
                    formData.append("documento", this.file, this.file.name)
                    
                    axios
                      .post("/sigequi/assentamentos/"+this.object.id+"/documento", formData, {headers: {'Content-Type': 'multipart/form-data', 'Accept': 'application/json'}})
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
                }
	        },
	        watch:{
	        	object:function(val){
	        		this.object = val;
	        	}
	        },
	        mounted() {
	        	this.getObjeto()
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Assentamento Federal
		                    	<button v-if="isObjetoSetado" class="button is-primary is-medium"
						            @click="isUploadAtivo = true"><i class="fa fa-upload"></i>&nbsp; Upload
						        </button>
						        <a :href="'/sigequi/assentamentos/'+object.id+'/documento'" style="color: #fff" v-if="object.nomeDocumento != null" class="button is-success is-medium"
						            ><i class="fa fa-download"></i>&nbsp; Download
						        </a>
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
		                        <b-field label="Tipo">
                                    <b-input v-model="object.tipo" required   type="text" disabled></b-input>
                                </b-field>
                                
						        <b-field label="Número">
						            <b-input v-model="object.numero" placeholder="Número"   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data da Portaria">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            v-model="object.dataPortaria">
							        </b-datepicker>
							    </b-field>
							    
							    <b-field label="Data Diário Oficial">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            v-model="object.dataDO">
							        </b-datepicker>
							    </b-field>
						        
						        <b-field label="Seção Diário Oficial">
						            <b-input v-model="object.secaoDO" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Folha Diário Oficial">
						            <b-input v-model="object.folhaDO" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data do Decreto">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        :years-range="[-20,100]"
                                        v-model="object.dataDecreto">
                                    </b-datepicker>
                                </b-field>
                                
                                <b-field  label="Link publicação Decreto 1" >
                                    <b-input v-model="object.linkPublicacaoDecreto_1"   placeholder="Link publicação Decreto 1" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link publicação Decreto 2" >
                                    <b-input v-model="object.linkPublicacaoDecreto_2"   placeholder="Link publicação Decreto 2" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link publicação Decreto 3" >
                                    <b-input v-model="object.linkPublicacaoDecreto_3"   placeholder="Link publicação Decreto 3" type="text" ></b-input>
                                </b-field>
                                
                                <b-field label="Data Diario Uniao do Decreto">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        :years-range="[-20,100]"
                                        v-model="object.dataDiarioUniaoDecreto">
                                    </b-datepicker>
                                </b-field>
                                
                                <b-field  label="Link publicação Decreto União 1" >
                                    <b-input v-model="object.linkPublicacaoDiarioUniaoDecreto_1"   placeholder="Link publicação Decreto União 1" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link publicação Decreto União 2" >
                                    <b-input v-model="object.linkPublicacaoDiarioUniaoDecreto_2"   placeholder="Link publicação Decreto União 2" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link publicação Decreto União 3" >
                                    <b-input v-model="object.linkPublicacaoDiarioUniaoDecreto_3"   placeholder="Link publicação Decreto União 3" type="text" ></b-input>
                                </b-field>
						        
						        <b-field label="Área do Título">
						            <b-numberinput step=".0001"  v-model="object.area" controls-position="compact"></b-numberinput>
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