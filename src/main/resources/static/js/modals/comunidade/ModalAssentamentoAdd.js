const ModalAssentamentoAdd = {
	        props: ['id'],
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
						tipo: null,
						dataDecreto: null,
						linkPublicacaoDecreto_1: null,
						linkPublicacaoDecreto_2: null,
						linkPublicacaoDecreto_3: null,
						dataDiarioUniaoDecreto: null,
						linkPublicacaoDiarioUniaoDecreto_1: null,
						linkPublicacaoDiarioUniaoDecreto_2: null,
						linkPublicacaoDiarioUniaoDecreto_3: null,
		            	territorio: {
		            		id: this.id
		            	}
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            file: null,
		            tipos: []
	        	}
	        },
	        methods:{
	        	
	        	save(){
//	        		this.object.territorio = URL_BASEV2[0].concat("2/territorios/"+this.object.territorio.id)
	        		this.object.territorio = {id: this.id}
	        		axios
	        		  .post(`/sigequi/api/v1/assentamentos`, this.object)
		    	      .then(
		    	    		  response => {
				    	    	  console.log(response)
				    	    	  if(response.status >= 200 && response.status < 300){
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
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Adicionar Assentamento
		                    	
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						        <b-field  label="Tipo" >
                                    <b-select v-model="object.tipo" placeholder="Selecione um Tipo!">
                                    	<option value="FEDERAL">Federal</option>
                                    	<option value="ESTADUAL">Estadual</option>
                                    </b-select>
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
							</template>
		                </section>
	                    <footer class="modal-card-foot">
	                        <b-button @click.native="save()"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}