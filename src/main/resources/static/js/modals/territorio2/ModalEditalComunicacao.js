const ModalEditalComunicacao = {
	        props: ['data','territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	tipoEdital:null,
		            	dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital: null,
		            	dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital: null,
		            	numeroFamilias: 0,
		            	areaHaEdital: 0,
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
	        			if(r.data.editalComunicacao != null){
	        				this.isObjetoSetado = true
	        				this.object = r.data.editalComunicacao	
	        				this.object.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital = new Date(this.object.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital)
	        				this.object.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital = new Date(this.object.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital)
	        			}
	        		})
	        	},
                save(setado) {
		        	if(setado){
		        		this.object.territorio = this.territorio.id
		        		crudService.update(URL_BASE+"/territorios/edital-comunicacao", "", this.object, r => {
		        			console.log(r.data)
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
	            		crudService.create(URL_BASE+"/territorios/edital-comunicacao", this.object, r => {
	            			console.log(r.data)
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
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Edital de Comunicação
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						        <b-field  label="Tipo de Edital" >
						            <b-input  v-model="object.tipoEdital"  placeholder="Tipo de Edital" type="text" ></b-input>
						        </b-field>
						        
						        
							    
						        <b-field label="Data de Publicação Primeiro Edital DOU/DOE ">
                                    <b-datepicker
                                        placeholder="Selecione uma data"
                                        icon="calendar-today"
                                        :month-names="mesesNomes"
                                        :day-names="diasNomes"
                                        v-model="object.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital">
                                    </b-datepicker>
                                </b-field>
                                
					        	<b-field label="Data de Publicação Segundo Edital DOU/DOE ">
						        	<b-datepicker
							        	placeholder="Selecione uma data"
							        	icon="calendar-today"
							        	:month-names="mesesNomes"
							        	:day-names="diasNomes"
							        	v-model="object.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital">
						        	</b-datepicker>
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
	                        <b-button v-show="!isObjetoSetado" @click.native="save(isObjetoSetado)"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button v-show="isObjetoSetado" @click.native="save(isObjetoSetado)"  type="is-info"><i class="fa fa-edit"></i> Editar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}