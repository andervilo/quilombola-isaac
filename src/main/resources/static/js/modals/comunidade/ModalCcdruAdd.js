const ModalCcdruAdd = {
	        props: ['id'],
	        data:function(){
	        	return{
	        		object:{
	        			id: null,
	        			numeroPortaria: null,
	        			dataPortaria: null,
	        			dataPortariaDOU: null,
	        			secaoDOU: null,
	        			folhaDOU: null,
	        			linkDOU: null,
	        			linkDOU2: null,
	        			linkDOU3: null,
	        			numeroFamilias: null,
	        			nomeImovel: null,
	        			municipio: null,
	        			areaHa: null,
		            	territorio: {
		            		id: this.id
		            	}
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            file: null,
		            name: 'teste',
	                selected: {nome:null},
	                listMunicipios: [],
	                isFetching: false
	        	}
	        },
	        computed: {
	            
	        },
	        methods:{
	        	
	        	save(){
	        		this.object.territorio = URL_BASEV2[0].concat("2/territorios/"+this.object.territorio.id)
	        		this.object.municipio = URL_BASEV2[0].concat("2/municipios/"+this.selected.id)
	        		axios
	        		  .post(`/sigequi/api/v2/ccdrus`, this.object)
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
	        	},
	        		        	
	        	getAsyncData: _.debounce(function (name) {
	                if (!name.length) {
	                    this.data = []
	                    return
	                }
	                this.isFetching = true
	                axios
	        		  .get(`/sigequi/api/v1/municipios/toComboList/${name}`)
	                    .then(response => {
	    	    			  this.listMunicipios = []
	    	    			  this.listMunicipios = response.data
	    	    			  this.isFetching = false
			    	      })
	            }, 500)
	        	
		        
	        },
	        mounted() {
	        	console.log("minicipios", this.municipios)
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Adicionar CCDRU
		                    	
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
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
                                
					        	<b-field label="Data Portaria DO">
						        	<b-datepicker
							        	placeholder="Selecione uma data"
							        	icon="calendar-today"
							        	:month-names="mesesNomes"
							        	:day-names="diasNomes"
							        	:years-range="[-100,100]"
							        	v-model="object.dataPortariaDOU">
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
                                
                                <b-field label="Número de famílias beneficiadas">
						            <b-numberinput v-model="object.numeroFamilias" controls-position="compact"></b-numberinput>
						        </b-field>
						        
						        <b-field  label="Nome Imóvel" >
                                    <b-input v-model="object.nomeImovel"   placeholder="Nome Imóvel" type="text" ></b-input>
                                </b-field>
                                
                                <b-field label="Buscar por município">
							            <b-autocomplete
							                rounded
							                v-model="object.municipio"
							                :data="listMunicipios"
							                :loading="isFetching"
                							@typing="getAsyncData"
							                placeholder="Digite o nome do Município!"
							                icon="magnify"
							                field="nome"
							                @select="option => selected = option">
							                <template slot-scope="props">
							                    <div class="media">
							                        <div class="media-content">
							                            <b>{{ props.option.nome }}</b>
							                            <br>
							                            <small>
							                                Estado: {{ props.option.estado.nome }},</br>
							                                Região: {{ props.option.estado.regiao.nome }}							                                
							                            </small>
							                        </div>
							                    </div>
							                </template>
							                <template slot="empty">Sem resultados para sua busca!</template>
							            </b-autocomplete>
							        </b-field>
							        
							        <b-field label="Área">
	        							<b-numberinput step=".0001" v-model="object.areaHa" controls-position="compact"></b-numberinput>
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