const certificadoURL = URL_BASE+"/territorios/certificado";

const ModalCertificado = {
	        props: ['data','territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	numeroProcessoFcp: null,
		            	dataAberturaProcessoCertificado: null,
		            	retificacaoFcpObservacao: null
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isCertificadoSetado: false
	        	}
	        },
	        methods:{
	        	getCertificado(){
	        		crudService.findById(URL_BASE+"/territorios/", this.territorio.id, r => {
	        			if(r.data.certificado != null){
	        				this.isCertificadoSetado = true
	        				this.object = r.data.certificado	
	        				this.object.dataAberturaProcessoCertificado = new Date(this.object.dataAberturaProcessoCertificado)
	        			}
	        		})
	        	},
	        	save(CertificadoSetado) {
		        	if(CertificadoSetado){
		        		this.object.territorio = this.territorio.id
		        		crudService.update(certificadoURL, "", this.object, r => {
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
	            		crudService.create(certificadoURL, this.object, r => {
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
	        	this.getCertificado()
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Certificado
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						        <b-field  label="Numero Processo FCP" >
						            <b-input v-model="object.numeroProcessoFcp" required  placeholder="Numero Processo" type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Data de Abertura do Processo">
							        <b-datepicker
							            placeholder="Selecione uma data"
							            icon="calendar-today"
							            :month-names="mesesNomes"
							            :day-names="diasNomes"
							            v-model="object.dataAberturaProcessoCertificado">
							        </b-datepicker>
							    </b-field>
							    
						        <b-field  label="Retificação/Observacao"  >
						            <b-input v-model="object.retificacaoFcpObservacao" placeholder="Retificação/Observacao" type="textarea" ></b-input>
						        </b-field>
							</template>
		                </section>
	                    <footer class="modal-card-foot">
	                        <b-button v-show="!isCertificadoSetado" @click.native="save(isCertificadoSetado)"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button v-show="isCertificadoSetado" @click.native="save(isCertificadoSetado)"  type="is-info"><i class="fa fa-edit"></i> Editar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}