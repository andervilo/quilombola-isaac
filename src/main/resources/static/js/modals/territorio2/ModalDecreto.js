const ModalDecreto = {
	        props: ['data','territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	numeroDecretoDesapropriacao: null,
		            	dataDecretoDeaproriacaoa: null,
		            	dataDecretoDesapropriacaoDiarioOficialUniao: null
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
	        			if(r.data.decretoDesapropriatorio != null){
	        				this.isObjetoSetado = true
	        				this.object = r.data.decretoDesapropriatorio	
	        				this.object.dataDecretoDeaproriacaoa = new Date(this.object.dataDecretoDeaproriacaoa)
	        				this.object.dataDecretoDesapropriacaoDiarioOficialUniao = new Date(this.object.dataDecretoDesapropriacaoDiarioOficialUniao)
	        			}
	        		})
	        	},
                save(setado) {
		        	if(setado){
		        		this.object.territorio = this.territorio.id
		        		crudService.update(URL_BASE+"/territorios/decretodesapropriatorio/", "", this.object, r => {
		        			console.log(r)
		        			if(r.status == 200){
		        				this.object=r.data.data
		        				console.log(this.object)
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
	            		crudService.create(URL_BASE+"/territorios/decretodesapropriatorio/", this.object, r => {
	            			console.log(r)
	            			if(r.status == 200){
	            				this.object=r.data.data
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
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Decreto Desapropriatório
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
							            v-model="object.dataDecretoDeaproriacaoa">
							        </b-datepicker>
							    </b-field>
							    
						        <b-field label="Data de Publicação no DOU">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        v-model="object.dataDecretoDesapropriacaoDiarioOficialUniao">
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