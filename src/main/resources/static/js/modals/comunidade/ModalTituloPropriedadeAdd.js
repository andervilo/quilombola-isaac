const ModalTituloPropriedadeAdd = {
	        props: ['id'],
	        data:function(){
	        	return{
	        		object:{
	        			id: null,
		            	areaHaTitulo: 0,
		            	dataTitulo:null,
		            	percentagemAreaTitulada:0,
		            	numeroFamiliaTitulos: 0,
		            	observacaoAreaTitulo: null,
		            	orgaoExpedidor: null,
		            	linkPublicacao_1: null,
                        linkPublicacao_2: null,
                        linkPublicacao_3: null,
                        secaoDO: null,
                        folhaDO: null,
		            	territorio: {
		            		id: this.id
		            	}
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            file: null,
		            orgaos: [],
	        	}
	        },
	        methods:{
	        	
	        	save(){
	        		this.object.orgaoExpedidor = URL_BASEV2[0].concat("2/orgaos/"+this.object.orgaoExpedidor)
	        		this.object.territorio = URL_BASEV2[0].concat("2/territorios/"+this.object.territorio.id)
	        		
	        		axios
	        		  .post(`/sigequi/api/v2/titulospropriedades`, this.object)
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
	        	
	        	getOrgaos(){
	        		crudService.findAllNoPagination(URL_BASE+"/orgaos/", r => {
	        			console.log(r.data)
	        			this.orgaos = _.sortBy(r.data.content, ['nome'])
	        		})
	        	},
	        	myClose(){
	        		this.$emit('buscar')
	        		this.$parent.close()
	        	}
	        	
		        
	        },
	        created() {
	        	this.getOrgaos()
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Adicionar Título de Propriedade
		                    	
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						       <b-field label="Área do Título">
						            <b-numberinput step=".0001"  v-model="object.areaHaTitulo" controls-position="compact"></b-numberinput>
						        </b-field>
						        
						        <b-field label="% Área Titulada">
						            <b-numberinput step=".01"  v-model="object.percentagemAreaTitulada" controls-position="compact"></b-numberinput>
						        </b-field>
						        
						        <b-field label="Número de famílias beneficiadas">
						            <b-numberinput v-model="object.numeroFamiliaTitulos" controls-position="compact"></b-numberinput>
						        </b-field>
							    
						        <b-field label="Data de Expedição">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        v-model="object.dataTitulo">
                                    </b-datepicker>
                                </b-field>
                                
					        	<b-field  label="Orgão Expedidor" >
						            <b-select v-model="object.orgaoExpedidor" placeholder="Selecione um Orgão!">
						                <option v-for="orgao in orgaos" :value="orgao.id">{{orgao.nome}}</option>
						            </b-select>
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
                                
                                <b-field  label="Seção Diário Oficial" >
                                    <b-input v-model="object.secaoDO"   placeholder="Seção Diário Oficial" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Folha Diário Oficial" >
                                    <b-input v-model="object.folhaDO"   placeholder="Folha Diário Oficial" type="text" ></b-input>
                                </b-field>
						        
						        <b-field  label="Observações"  >
						            <b-input v-model="object.observacaoAreaTitulo" placeholder="Observação área título" type="textarea" ></b-input>
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