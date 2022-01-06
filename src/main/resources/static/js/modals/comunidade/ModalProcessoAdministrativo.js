const ModalProcessoAdministrativo = {
	        props: ['data','territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	ambito: null,
		            	numeroProcessoReconhecimento:null,
		            	linkProcessoSei:null,
		            	linkProcessoSei2:null,
		            	linkProcessoSei3:null,
		            	localizacaoAcervoFundiario: null,
		            	secaoDO: null,
                        folhaDO: null,
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isObjetoSetado: false,
		            ambitos: [],
		            isUploadAtivo: false,
		            file: null
	        	}
	        },
	        methods:{
	        	getObjeto(){
	        		crudService.findById(URL_BASE+"/comunidades/", this.territorio.id, r => {
	        			if(r.data.processoAdministrativo != null){
	        				this.isObjetoSetado = true
	        				this.object = r.data.processoAdministrativo
	        				this.object.ambito = r.data.processoAdministrativo.ambito.label
	        			}
	        		})
	        	},
	        	getAmbitos(){
	        		crudService.findAllNoPagination(URL_BASE+"/ambito-processo", r => {
	        			console.log(r.data)
	        			this.ambitos = _.sortBy(r.data, ['nome'])
	        		})
	        	},
                save(setado) {
	        	    console.log(this.object)
		        	if(setado){
		        		this.object.comunidade = this.territorio.id
		        		crudService.update(URL_BASE+"/comunidades/processo-administrativo", "", this.object, r => {
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
	            		crudService.create(URL_BASE+"/comunidades/processo-administrativo", this.object, r => {
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
		    	      .post("/sigequi/processos/"+this.object.id+"/documento", formData, {headers: {'Content-Type': 'multipart/form-data', 'Accept': 'application/json'}})
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
		        	}
	        },
	        watch:{
	        	object:function(val){
	        		this.object = val;
	        	}
	        },
	        created() {
	        	this.getObjeto()
	        	this.getAmbitos()
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Processo Administrativo
		                    	<button v-if="isObjetoSetado" class="button is-primary is-medium"
						            @click="isUploadAtivo = true"><i class="fa fa-upload"></i>&nbsp; Upload
						        </button>
						        <a :href="'/sigequi/processos/'+object.id+'/documento'" style="color: #fff" v-if="object.nomeDocumento != null" class="button is-success is-medium"
						            ><i class="fa fa-download"></i>&nbsp; Download
						        </a>
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						        <b-field label="Número Processo">
						            <b-input v-model="object.numeroProcessoReconhecimento" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field  label="Âmbito Processo" >
                                    <b-select v-model="object.ambito" placeholder="Selecione um Âmbito!">
                                        <option v-for="ambito in ambitos" :value="ambito.label">{{ambito.nome}}</option>
                                    </b-select>
                                </b-field>
						        
						        <b-field label="Link Processo">
						            <b-input v-model="object.linkProcessoSei" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Link Processo 2">
						            <b-input v-model="object.linkProcessoSei2" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Link Processo 3">
						            <b-input v-model="object.linkProcessoSei3" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Localização Acervo Fundiário">
						            <b-input v-model="object.localizacaoAcervoFundiario" required   type="text" ></b-input>
						        </b-field>
							    
					        	<b-field  label="Seção Diário Oficial" >
                                    <b-input v-model="object.secaoDO"   placeholder="Seção Diário Oficial" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Folha Diário Oficial" >
                                    <b-input v-model="object.folhaDO"   placeholder="Folha Diário Oficial" type="text" ></b-input>
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