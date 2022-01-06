const ModalProcessoAdministrativoEdit = {
	        props: ['id', 'obj'],
	        data:function(){
	        	return{
	        		object: this.obj
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isCertificadoSetado: false,
		            isUploadAtivo: false,
		            file: null,
		            ambitos: [],
	        	}
	        },
	        methods:{
	        	getAmbitos(){
	        		crudService.findAllNoPagination(URL_BASE+"/ambito-processo", r => {
	        			console.log(r.data)
	        			this.ambitos = _.sortBy(r.data, ['nome'])
	        		})
	        	},
	        	save(){
	        		this.object.territorio = {id: this.id}
	        		axios
	        		  .put(`/sigequi/api/v1/processosadministrativos/${this.object.id}`, this.object)
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
			    	                this.$emit('buscar')
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
	        		this.$emit('buscar')
	        		this.$parent.close()
	        	}
	        	
		        
	        },
	        created() {	        	
	        	this.object.ambito = this.object.ambito.label
	        	this.getAmbitos()
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Editar Processo Administrativo
		                    	
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
							</template>
		                </section>
	                    <footer class="modal-card-foot">
	                        <b-button @click.native="save()"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}