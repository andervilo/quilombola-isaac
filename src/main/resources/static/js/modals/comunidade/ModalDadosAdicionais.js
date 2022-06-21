const ModalDadosAdicionais = {
	        props: ['comunidade'],
	        data:function(){
	        	return{
	        		object:{
	        		    certificada: false,
	        		    longitudeWEcertificacao: null,
	        		    latitudeSNcertificacao: null,
	        		    processosReconhecimento: false,
	        		    edital: false,
	        		    concessao: false,
	        		    ccdru: null,
	        		    titulada: false,
	        		    areahaTerritorial: 0,
	        		    observacao: null,
	        		    copiaTituloArquivoAutor: false,
	        		    publicacaoTerraQuilombo: false,
	        		    quilomboUrbano: false,
	        		    peqTeq: false,
	        		    qtdFamiliasPeqTeq: null,
	        		    areahaPeqTeq: null,
	        		    numeroPortariaPeqTeq: null,
	        		    dataPortariaPeqTeq: null,
	        		    dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq: null,
	        		    cadastroIncra: null,
	        		    cadastroSigef: null,
	        		    cadastroCar: null,
	        		    latitudeIbge: null,
	        		    longitudeIbge: null,
	        		    latitudeIncra: null,
	        		    longitudeIncra: null,
	        		    localizacaoIncra: null,
	        		    nomeDocumento: null,
	        		    portaria: false,
	        		    decreto: false,
	        		    assentamentos: false,
	        		    outrosProcessosAdministrativos: [],
	        		    processosJudiciais: [],
	        		    territorio: null
	        		    
	        		    
		            },
		            isObjetoSetado: false,
		            mesesNomes:["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
                    diasNomes:["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
                    isUploadAtivo: false,
                    file: null,
                    linkToAddPA: "",
                    linkToAddPJ: ""
	        	}
	        },
	        methods:{
	        	
	        	getObjeto(){
	        		crudService.findById(URL_BASE+"/territorios/", this.comunidade.id, r => {
	        			if(r.data.dadosAdicionais != null){
	        			    this.object = r.data.dadosAdicionais
	        			    this.object.dataPortariaPeqTeq = 
	        			        this.object.dataPortariaPeqTeq == null? "" : new Date(this.object.dataPortariaPeqTeq)
	        			    this.object.dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq = 
	        			        this.object.dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq == null? "" : new Date(this.object.dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq)
	        				this.isObjetoSetado = true
	        				this.object.territorio = r.data.id
	        			}
	        		})
	        	},
                save() {
	        		console.log('onsave: ', this.object);
	        		crudService.update('/sigequi/dadosadicionais/',this.object.id, this.object, r => {
	        			console.log(r)
	        			if(r.status == 200){
	        				this.object=r.data.data
	        				console.log(this.object)
	        				this.$buefy.toast.open({
	                            duration: 3000,
	                            message: 'message' in r.data ? r.data.message : 'Operação Realizada com sucesso!',
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
		        	
		        	this.$parent.close() 
		        },
		        saveDocumento(){
                    var formData = new FormData();
                    formData.append("documento", this.file, this.file.name)
                    
                    axios
                      .post("/sigequi/dadosadicionais/"+this.object.id+"/documento", formData, {headers: {'Content-Type': 'multipart/form-data', 'Accept': 'application/json'}})
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
                                        this.isUploadAtivo = false
                                        this.getObjeto()
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
                onClickAddProcAdm(linkAdd){
                	if(this.object.outrosProcessosAdministrativos === null ){
                		this.object.outrosProcessosAdministrativos = [];
                	}
                	if(linkAdd !== ""){
                		this.object.outrosProcessosAdministrativos.push({link: linkAdd});
                    	this.linkToAddPA = "";
                	}                	
                },
                onClickRemoveProcAdm(index){
                	alert(index)
            		this.object.outrosProcessosAdministrativos.splice(index, 1);               	
                },
                onClickAddProcJud(linkAdd){
                	if(this.object.processosJudiciais === null ){
                		this.object.processosJudiciais = [];
                	}
                	if(linkAdd !== ""){
                		this.object.processosJudiciais.push({link: linkAdd});
                    	this.linkToAddPJ = "";
                	}                	
                },
                onClickRemoveProcJud(index){
            		this.object.processosJudiciais.splice(index, 1);               	
                }
	        },
	        watch:{
	        },
	        created() {
	        	this.getObjeto()
	        	
    			 
	        },
	        template: `
	                <div class="modal-card" >
	                    
	                    <header class="modal-card-head">
                            <p class="modal-card-title">
                                <i class="icon-interface-windows" style="font-size: 30px;"></i> Dados Adicionais
                                <button v-if="isObjetoSetado" class="button is-primary is-medium"
                                    @click="isUploadAtivo = true"><i class="fa fa-upload"></i>&nbsp; Upload
                                </button>
                                <a :href="'/sigequi/dadosadicionais/'+object.id+'/documento'" style="color: #fff" v-if="object.nomeDocumento != null" class="button is-success is-medium"
                                    ><i class="fa fa-download"></i>&nbsp; Download
                                </a>
                            </p>
                        </header>
	                    
	                    <section class="modal-card-body"> 
	                        <b-field   >                                
                                <b-checkbox v-model="object.certificada">
                                    Certificada
                                </b-checkbox>
                            </b-field>
	                        <b-field   >                                
                                <b-checkbox v-model="object.processosReconhecimento">
                                    Processo de Reconhecimento
                                </b-checkbox>
                            </b-field>
	                        <b-field   >                                
                                <b-checkbox v-model="object.edital">
                                    Edital
                                </b-checkbox>
                            </b-field>
	                        <b-field   >                                
                                <b-checkbox v-model="object.concessao">
                                    Concessão
                                </b-checkbox>
                            </b-field>
	                        <b-field   >                                
                                <b-checkbox v-model="object.portaria">
                                    Portaria
                                </b-checkbox>
                            </b-field>
	                        <b-field   >                                
                                <b-checkbox v-model="object.decreto">
                                    Decreto
                                </b-checkbox>
                            </b-field>
	                        <b-field   >                                
                                <b-checkbox v-model="object.assentamentos">
                                    Possui Assentamentos(Federal ou Estadual)
                                </b-checkbox>
                            </b-field>
	                        <b-field   >                                
                                <b-checkbox v-model="object.titulada">
                                    Titulada
                                </b-checkbox>
                            </b-field>
                            
                            <b-field  label="CCDRU"  width="70px">
                                <b-input v-model="object.ccdru"    type="text" ></b-input>
                            </b-field>
                            
                            
                            <b-field  label="Cadastro INCRA"  width="70px">
                                <b-input v-model="object.cadastroIncra"    type="text" ></b-input>
                            </b-field>
                            <b-field  label="Cadastro SIGEF"  width="70px">
                                <b-input v-model="object.cadastroSigef"    type="text" ></b-input>
                            </b-field>
                            <b-field  label="Cadastro CAR"  width="70px">
                                <b-input v-model="object.cadastroCar"    type="text" ></b-input>
                            </b-field>
                            
                            <b-field  label="Latitude IBGE"  width="70px">
                                <b-input v-model="object.latitudeIbge"    type="text" ></b-input>
                            </b-field>
                            <b-field  label="Longitude IBGE"  width="70px">
                                <b-input v-model="object.longitudeIbge"    type="text" ></b-input>
                            </b-field>
                            <b-field  label="Latitude Incra"  width="70px">
                                <b-input v-model="object.latitudeIncra"    type="text" ></b-input>
                            </b-field>
                            <b-field  label="Longitude INCRA"  width="70px">
                                <b-input v-model="object.longitudeIncra"    type="text" ></b-input>
                            </b-field>
                            <b-field  label="Localização INCRA"  width="70px">
                                <b-input v-model="object.localizacaoIncra"    type="text" ></b-input>
                            </b-field>
                            
                            <b-field label="Área HA Territorial">
                                <b-numberinput step=".01" v-model="object.areahaTerritorial" controls-position="compact"></b-numberinput>
                            </b-field>
                            
                            <b-field   >                                
                                <b-checkbox v-model="object.copiaTituloArquivoAutor">
                                    Cópia Título Arquivo Autor
                                </b-checkbox>
                            </b-field>
                            
                            <b-field   >                                
                                <b-checkbox v-model="object.publicacaoTerraQuilombo">
                                    Publicação Terra Quilombo
                                </b-checkbox>
                            </b-field>
                            
                            <b-field   >                                
                                <b-checkbox v-model="object.peqTeq">
                                    PeqTeq
                                </b-checkbox>
                            </b-field>
                            
                            <b-field label="Quantidade Famílias PeqTeq" v-if="object.peqTeq">
                                <b-numberinput  v-model="object.qtdFamiliasPeqTeq" controls-position="compact"></b-numberinput>
                            </b-field>
                            
                            <b-field label="Área HA PeqTeq" v-if="object.peqTeq">
                                <b-numberinput step=".01" v-model="object.areahaPeqTeq" controls-position="compact"></b-numberinput>
                            </b-field>
                            
                            <b-field  label="Número Portaria PeqTeq"  width="70px" v-if="object.peqTeq">
                                <b-input v-model="object.numeroPortariaPeqTeq"  type="text" ></b-input>
                            </b-field>
                            
                            <b-field label="Data Portaria PeqTeq" v-if="object.peqTeq">
                                <b-datepicker
                                    placeholder="Selecione uma data"
                                    icon="calendar-today"
                                    :month-names="mesesNomes"
                                    :day-names="diasNomes"
                                    :years-range="[-20, 80]" 
                                    v-model="object.dataPortariaPeqTeq">
                                </b-datepicker>
                            </b-field>
                            
                            <b-field label="Data de Publicação DO PeqTeq" v-if="object.peqTeq">
                                <b-datepicker
                                    placeholder="Selecione uma data"
                                    icon="calendar-today"
                                    :month-names="mesesNomes"
                                    :day-names="diasNomes"
                                    :years-range="[-20, 80]"    
                                    v-model="object.dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq">
                                </b-datepicker>
                            </b-field>
                            
                            <b-field  label="Observacao"  >
                                <b-input v-model="object.observacao" placeholder="Observacao" type="textarea" ></b-input>
                            </b-field>
                            
                            <b-field  label="Outros Processos Administrativos"  >
                                <b-input v-model="linkToAddPA"    type="url" ></b-input>                                
                            </b-field>
                            <b-button @click.native="onClickAddProcAdm(linkToAddPA)"  type="is-info"><i class="fa fa-plus"></i> Adicionar</b-button>
                            <b-table
					            :data="object.outrosProcessosAdministrativos">
								<template slot-scope="props">
						            <b-table-column >
						                {{ props.row.link }}
						            </b-table-column>
						            
						            <b-table-column >
						                <b-button @click.native="onClickRemoveProcAdm(props.index)"  type="is-danger">X</b-button>
						            </b-table-column>
						            
						            
								</template>
						    </b-table>
						    
						    <b-field  label="Processos Judiciais"  >
                                <b-input v-model="linkToAddPJ"    type="url" ></b-input>                                
                            </b-field>
                            <b-button @click.native="onClickAddProcJud(linkToAddPJ)"  type="is-info"><i class="fa fa-plus"></i> Adicionar</b-button>
                            <b-table
					            :data="object.processosJudiciais">
								<template slot-scope="props">
						            <b-table-column >
						                {{ props.row.link }}
						            </b-table-column>
						            
						            <b-table-column >
						                <b-button @click.native="onClickRemoveProcJud(props.index)"  type="is-danger">X</b-button>
						            </b-table-column>
						            
						            
								</template>
						    </b-table>
                            
                            <b-modal :active.sync="isUploadAtivo" :width="640" scroll="keep">
                                <div class="card">
                                    <div class="card-content">
                                        <template>
                                            <b-field class="file">
                                                <b-upload name="file" v-model="file">
                                                    <a style="color: #fff;" class="button is-primary">
                                                        <b-icon icon="upload"></b-icon>
                                                        <span >Selecione o arquivo para upload</span>
                                                    </a>
                                                </b-upload>
                                                <span class="file-name" v-if="file">
                                                    {{ file.name }}
                                                </span>
                                            </b-field>
                                            <b-button type="is-primary" @click.native="saveDocumento">Enviar</b-button>
                                            <b-button @click="isUploadAtivo = false"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
                                        </template>
                                    </div>
                                </div>
                            </b-modal>
		                    
		                </section>
	                    <footer class="modal-card-foot">
	                        <b-button v-show="!isObjetoSetado" @click.native="save(isObjetoSetado)"  type="is-info"><i class="fa fa-save"></i> Salvar</b-button>
	        				<b-button v-show="isObjetoSetado" @click.native="save(isObjetoSetado)"  type="is-info"><i class="fa fa-edit"></i> Editar</b-button>
	        				<b-button @click="$parent.close()"  type="is-danger"><i class="fa fa-arrow-left"></i> Cancelar</b-button>
	                    </footer>
	                </div>
	        `
	}