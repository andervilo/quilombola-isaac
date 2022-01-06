const ModalVincularMunicipio = {
	        props: ['territorio', 'listMunicipios'],
	        data() {
	            return {
	                data: this.listMunicipios,
	                territorioToEdite: this.territorio,
	                name: '',
	                selected: {nome:null}
	            }
	        },
	        computed: {
	            filteredDataArray() {
	                return this.data.filter((option) => {
	                    return option.nome
	                        .toString()
	                        .toLowerCase()
	                        .indexOf(this.name.toLowerCase()) >= 0
	                })
	            }
	        },
	        methods:{
        		loadAsyncData() {
	                crudService.findById(URL, this.territorioToEdite.id,  r => {	
	                	ModalMunicipio.data().data = r.data.municipioList
	                	console.log(ModalMunicipio.data())
					})	                
		        },
		        desativaEsteModal(){
		        	this.loadAsyncData()
		        	this.$emit('desativa-modal-vincular-municipio')
		        },
		        vincular(){
		            alert("Chamou")
		        	var objeto = this.selected
		        	objeto.estado = this.selected.estado.sigla	
		        	
		        	objeto.territorioList.push(this.territorioToEdite.id)
		        	
		        	this.territorioToEdite.municipioList.push({"id":this.selected.id})

		        	
		        	crudService.update(BASE_URL+"/municipios/", objeto.id, objeto,  r => {	 
		        		if(r.data.success){
	        				this.$buefy.toast.open({
	                            duration: 3000,
	                            message: "Município vinculado com sucesso!",
	                            position: 'is-top',
	                            type: 'is-success'
	                        })
	                        this.desativaEsteModal()
	        			}else{
	        				this.$buefy.toast.open({
	                            duration: 3000,
	                            message: "Erro ao realizar operação!",
	                            position: 'is-top',
	                            type: 'is-danger'
	                        })	        			
	        			}
					})
		        }
	        },
	        mounted() {
           	},
	        template: `
	                <div class="modal-card" style="height:500px">
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
		                    	<i class="icon-interface-windows" style="font-size: 30px;"></i> Vincular Município ao Território
		                    </p>
	                    </header>
	                    <section class="modal-card-body">                     
	                        <template>
							        <p class="content"><b>Município selecionado:</b> {{ selected.nome }} </p>
							        <b-field label="Buscar por município">
							            <b-autocomplete
							                rounded
							                v-model="name"
							                :data="filteredDataArray"
							                placeholder="Digite o nome do Município que deseja vincular!"
							                icon="magnify"
							                :keep-first="false"
							                :open-on-focus="false"
							                :clear-on-select="true"
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
							</template>
	                    </section>
	                    <footer class="modal-card-foot">
	                        <button class="button  is-danger" type="button" @click="desativaEsteModal"><i class="fa fa-arrow-left"></i>&nbsp;Cancelar</button>
	        				<button :disabled="selected.nome == null" class="button  is-info" type="button" @click="vincular"><i class="fas fa-sign-in-alt"></i>&nbsp;Vincular Município</button>
	                    </footer>
	                </div>
	        `
	}