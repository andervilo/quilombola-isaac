const ModalCertificadosEdit = {
	        props: ['id', 'obj'],
	        data:function(){
	        	return{
	        		object: this.obj
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isCertificadoSetado: false,
		            isUploadAtivo: false,
		            file: null
	        	}
	        },
	        methods:{
	        	save(){
	        		this.object.comunidade = {id: this.id}
	        		axios
	        		  .put(`/sigequi/api/v1/certificados/${this.object.id}`, this.object)
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
			    	                this.$emit('buscar-certificados')
			    	                this.$parent.close()
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
	        	
	        	myClose(){
	        		this.$emit('buscar-certificados')
	        		this.$parent.close()
	        	}
	        	
		        
	        },
	        created() {	        	
	        	this.object.dataAberturaProcessoCertificado = new Date(this.object.dataAberturaProcessoCertificado)
	        	this.object.dataPortaria = new Date(this.object.dataPortaria)
				this.object.dataDiarioOficial = new Date(this.object.dataDiarioOficial)
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Editar Certificado
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template v-if="object != null">
						        <b-field  label="Numero Processo FCP" >
						            <b-input v-model="object.numeroProcessoFcp"   placeholder="Numero Processo" type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data de Abertura do Processo">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            :years-range="[-20,100]"
							            v-model="object.dataAberturaProcessoCertificado">
							        </b-datepicker>
							    </b-field>
							    
							    <b-field  label="Livro" >
						            <b-input v-model="object.livro"   placeholder="Livro" type="text" ></b-input>
						        </b-field>
						        
						        <b-field  label="Folha" >
						            <b-input v-model="object.folha"   placeholder="Folha" type="text" ></b-input>
						        </b-field>
						        
						        <b-field  label="Número" >
						            <b-input v-model="object.numero"   placeholder="Número" type="text" ></b-input>
						        </b-field>
						        
						        <b-field  label="Número Portaria" >
						            <b-input v-model="object.numeroPortaria"   placeholder="Número Portaria" type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data Portaria">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            :years-range="[-100,100]"
							            v-model="object.dataPortaria">
							        </b-datepicker>
							    </b-field>
							    
							    <b-field label="Data Diário Oficial">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            :years-range="[-100,100]"
							            v-model="object.dataDiarioOficial">
							        </b-datepicker>
							    </b-field>
							    
							    <b-field  label="Diário Oficial" >
						            <b-input v-model="object.diarioOficial"   placeholder="Diário Oficial" type="text" ></b-input>
						        </b-field>
						        
						        <b-field  label="Seção Diário Oficial" >
						            <b-input v-model="object.secaoDO"   placeholder="Seção Diário Oficial" type="text" ></b-input>
						        </b-field>
						        
						        <b-field  label="Folha Diário Oficial" >
						            <b-input v-model="object.folhaDO"   placeholder="Folha Diário Oficial" type="text" ></b-input>
						        </b-field>
						        
						        <b-field  label="Link Diário Oficial 1" >
                                    <b-input v-model="object.linkPublicacaoDO"   placeholder="Link Diário Oficial" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link Diário Oficial 2" >
                                    <b-input v-model="object.linkPublicacaoDO2"   placeholder="Link Diário Oficial" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Link Diário Oficial 3" >
                                    <b-input v-model="object.linkPublicacaoDO3"   placeholder="Link Diário Oficial" type="text" ></b-input>
                                </b-field>
							    
						        <b-field  label="Retificação/Observacao"  >
						            <b-input v-model="object.retificacaoFcpObservacao" placeholder="Retificação/Observacao" type="textarea" ></b-input>
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
	                        <b-button @click.native="save()"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button @click="myClose()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}