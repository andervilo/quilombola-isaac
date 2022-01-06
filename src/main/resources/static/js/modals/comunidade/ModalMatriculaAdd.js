const ModalMatriculaAdd = {
	        props: ['id'],
	        data:function(){
	        	return{
	        		object:{
	        			id: null,
                        cartorioRegistroImoveis: null,
                        livroRegistroImoveis: null,
                        folhaRegistroImoveis: null,
                        matricula: null,
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
	        	}
	        },
	        methods:{
	        	
	        	save(){
	        		this.object.territorio = URL_BASEV2[0].concat("2/territorios/"+this.object.territorio.id)
	        		
	        		axios
	        		  .post(`/sigequi/api/v2/matriculas`, this.object)
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
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Adicionar Matrícula
		                    	
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						       <b-field  label="Cartório de Registro de Imóveis" >
                                    <b-input v-model="object.cartorioRegistroImoveis"   placeholder="Cartório de Registro de Imóveis" type="text" ></b-input>
                                </b-field>
                                
                                 <b-field  label="Livro de Registro de Imóveis" >
                                    <b-input v-model="object.livroRegistroImoveis"   placeholder="Livro de Registro de Imóveis" type="text" ></b-input>
                                </b-field>
                                
                                <b-field  label="Folha de Registro de Imóveis" >
                                    <b-input v-model="object.folhaRegistroImoveis"   placeholder="Folha de Registro de Imóveis" type="text" ></b-input>
                                </b-field>
                                
                                 <b-field  label="Matrícula de Registro de Imóveis" >
                                    <b-input v-model="object.matricula"   placeholder="Matrícula de Registro de Imóveis" type="text" ></b-input>
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
							</template>
		                </section>
	                    <footer class="modal-card-foot">
	                        <b-button @click.native="save()"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}