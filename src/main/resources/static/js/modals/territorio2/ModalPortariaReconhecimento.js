const ModalPortariaReconhecimento = {
	        props: ['data','territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	numeroPortariaReconhecimento: null,
		            	dataPortariaReconhecimento: null,
		            	portariaReconhecimentoDiarioOficialUniao: null
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isObjetoSetado: false
	        	}
	        },
	        methods:{
	        	getObjeto(){
	        		crudService.findById(URL_BASE+"/territorios/", this.territorio.id, r => {
	        			if(r.data.portariaReconhecimento != null){
	        				this.isObjetoSetado = true
	        				this.object = r.data.portariaReconhecimento	
	        				this.object.dataPortariaReconhecimento = new Date(this.object.dataPortariaReconhecimento)
	        				this.object.portariaReconhecimentoDiarioOficialUniao = new Date(this.object.portariaReconhecimentoDiarioOficialUniao)
	        			}
	        		})
	        	},
                save(setado) {
		        	if(setado){
		        		this.object.territorio = this.territorio.id
		        		crudService.update(URL_BASE+"/territorios/portaria-reconhecimento", "", this.object, r => {
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
	            		this.object.territorio = this.territorio.id
	            		crudService.create(URL_BASE+"/territorios/portariasreconhecimento", this.object, r => {
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
	        },
	        watch:{
	        	object:function(val){
	        		this.object = val;
	        	}
	        },
	        created() {
	        	this.getObjeto()
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Portaria de Reconhecimento
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						        <b-field  label="Número da Portaria de Reconhecimento" >
						            <b-input v-model="object.numeroPortariaReconhecimento"  placeholder="Número da Portaria de Reconhecimento" type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data da Portaria de Reconhecimento">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            v-model="object.dataPortariaReconhecimento">
							        </b-datepicker>
							    </b-field>
							    
						        <b-field label="Data de Publicação da Portaria de Reconhecimento no DOU">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        v-model="object.portariaReconhecimentoDiarioOficialUniao">
                                    </b-datepicker>
                                </b-field>
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