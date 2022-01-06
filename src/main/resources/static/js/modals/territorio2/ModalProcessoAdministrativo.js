const ModalProcessoAdministrativo = {
	        props: ['data','territorio'],
	        data:function(){
	        	return{
	        		object:{
		            	id: null,
		            	ambito: null,
		            	numeroProcessoReconhecimento:null,
		            	linkProcessoSei:null,
		            	localizacaoAcervoFundiario: null
		            }
	        		,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
		            diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
		            isObjetoSetado: false,
		            ambitos: []
	        	}
	        },
	        methods:{
	        	getObjeto(){
	        		crudService.findById(URL_BASE+"/territorios/", this.territorio.id, r => {
	        			if(r.data.processoAdministrativo != null){
	        				this.isObjetoSetado = true
	        				this.object = r.data.processoAdministrativo
	        				this.object.ambito = r.data.processoAdministrativo.ambito.label
	        			}
	        		})
	        	},
	        	getAmbitos(){
	        		crudService.findAllNoPagination(URL_BASE+"/ambito-processo", r => {
	        			console.log(r.data)
	        			this.ambitos = _.sortBy(r.data, ['nome'])
	        		})
	        	},
                save(setado) {
	        	    console.log(this.object)
		        	if(setado){
		        		this.object.territorio = this.territorio.id
		        		crudService.update(URL_BASE+"/territorios/processo-administrativo", "", this.object, r => {
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
	            		crudService.create(URL_BASE+"/territorios/processo-administrativo", this.object, r => {
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
	        	this.getAmbitos()
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Processo Administrativo
		                    </p>
	                    </header>
	                    <section class="modal-card-body"> 
		                    <template>
						        <b-field label="Número Processo">
						            <b-input v-model="object.numeroProcessoReconhecimento" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Link Processo">
						            <b-input v-model="object.linkProcessoSei" required   type="text" ></b-input>
						        </b-field>
						        
						        <b-field label="Localização Acervo Fundiário">
						            <b-input v-model="object.localizacaoAcervoFundiario" required   type="text" ></b-input>
						        </b-field>
							    
					        	<b-field  label="Âmbito Processo" >
						            <b-select v-model="object.ambito" placeholder="Selecione um Âmbito!">
						                <option v-for="ambito in ambitos" :value="ambito.label">{{ambito.nome}}</option>
						            </b-select>
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