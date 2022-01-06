const ModalTituloPropriedade = {
	        props: ['data','territorio'],
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
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isObjetoSetado: false,
		            orgaos: []
	        	}
	        },
	        methods:{
	        	getObjeto(){
	        		crudService.findById(URL_BASE+"/territorios/", this.territorio.id, r => {
	        			if(r.data.tituloPropriedade != null){
	        				this.isObjetoSetado = true
	        				this.object = r.data.tituloPropriedade	
	        				this.object.dataTitulo = new Date(this.object.dataTitulo)
	        				console.log(r.data.tituloPropriedade)
	        			}
	        		})
	        	},
	        	getOrgaos(){
	        		crudService.findAllNoPagination(URL_BASE+"/orgaos/", r => {
	        			console.log(r.data)
	        			this.orgaos = _.sortBy(r.data.content, ['nome'])
	        		})
	        	},
                save(setado) {
		        	if(setado){
		        		this.object.territorio = this.territorio.id
		        		crudService.update(URL_BASE+"/territorios/titulo-propriedade", "", this.object, r => {
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
	            		crudService.create(URL_BASE+"/territorios/titulo-propriedade", this.object, r => {
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
		        isNumber(evt) {
		            evt = (evt) ? evt : window.event;
		            var charCode = (evt.which) ? evt.which : evt.keyCode;
		            if ((charCode > 31 && (charCode < 48 || charCode > 57)) && charCode !== 46) {
		              evt.preventDefault();;
		            } else {
		              return true;
		            }
		          },
		          onlyNumber ($event) {
		        	   //console.log($event.keyCode); //keyCodes value
		        	  
		        	   let keyCode = ($event.keyCode ? $event.keyCode : $event.which);
		        	   if ((keyCode < 48 || keyCode > 57) && keyCode !== 46) { // 46 is dot
		        	      $event.preventDefault();
		        	      alert($event.keyCode)
		        	   }
		        	}
	        },
	        watch:{
	        	object:function(val){
	        		this.object = val;
	        	}
	        },
	        created() {
	        	this.getObjeto()
	        	this.getOrgaos()
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Título de Propriedade
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
						        
						        <b-field  label="Observações"  >
						            <b-input v-model="object.observacaoAreaTitulo" placeholder="Observação área título" type="textarea" ></b-input>
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