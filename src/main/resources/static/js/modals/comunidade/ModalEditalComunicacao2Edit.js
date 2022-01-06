const ModalEditalComunicacao2Edit = {
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
	        		this.object.territorio = {id: this.id}
	        		axios
	        		  .put(`/sigequi/api/v1/editaiscomunicacoes/${this.object.id}`, this.object)
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
	        	this.object.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital = 
					new Date(this.object.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital)
				this.object.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital = 
					new Date(this.object.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital)
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
						        <b-field label="Data de Publicação Primeiro Edital DOU/DOE ">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        :years-range="[-100,100]"
                                        v-model="object.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital">
                                    </b-datepicker>
                                </b-field>
                                
					        	<b-field label="Data de Publicação Segundo Edital DOU/DOE ">
						        	<b-datepicker
							        	placeholder="Selecione uma data"
							        	icon="calendar-today"
							        	:month-names="mesesNomes"
							        	:day-names="diasNomes"
							        	:years-range="[-100,100]"
							        	v-model="object.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital">
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
                                
					        	<b-field label="Número de Famílias">
						            <b-numberinput  v-model="object.numeroFamilias" controls-position="compact"></b-numberinput>
						        </b-field>
					        	
					        	<b-field label="Área Edital">
						            <b-numberinput step=".0001" v-model="object.areaHaEdital" controls-position="compact"></b-numberinput>
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