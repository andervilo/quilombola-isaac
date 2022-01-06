const ModalDecretoAdd = {
	        props: ['id'],
	        data:function(){
	        	return{
	        		object:{
	        			id: null,
		            	numeroDecretoDesapropriacao: null,
		            	dataDecretoDeaproriacaoa: null,
		            	dataDecretoDesapropriacaoDiarioOficialUniao: null,
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
		            ambitos: [],
	        	}
	        },
	        methods:{
	        	
	        	save(){
	        		axios
	        		  .post(`/sigequi/api/v1/decretosdesapropriatorios`, this.object)
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
	        		this.$emit('buscar')
	        		this.$parent.close()
	        	},
	        	
	        	myClose(){
	        		this.$emit('buscar')
	        		this.$parent.close()
	        	}
	        	
		        
	        },
	        created() {
	        	this.getAmbitos()
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Adicionar Decreto
		                    	
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						       <b-field  label="Número do Decreto" >
						            <b-input v-model="object.numeroDecretoDesapropriacao"  placeholder="Número do Decreto" type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data do Decreto">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            :years-range="[-20,100]"
							            v-model="object.dataDecretoDeaproriacaoa">
							        </b-datepicker>
							    </b-field>
							    
						        <b-field label="Data de Publicação no DO">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        :years-range="[-20,100]"
                                        v-model="object.dataDecretoDesapropriacaoDiarioOficialUniao">
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
                                
							</template>
		                </section>
	                    <footer class="modal-card-foot">
	                        <b-button @click.native="save()"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}