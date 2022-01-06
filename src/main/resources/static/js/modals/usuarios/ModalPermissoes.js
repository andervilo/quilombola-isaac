
const URL_PERMISSOES = URL_BASE+"/security/permissoes/";
const URL_MODAL_PERMISSOES = URL_BASE+"/security/usuarios/";
const URL_MODAL_ADD_PERMISSAO = URL_BASE+"/security/usuarios/add-permissao";
const URL_MODAL_REMOVE_PERMISSAO = URL_BASE+"/security/usuarios/remove-permissao";
const ModalPermissoes = {
        props: ['id', 'nome'],
        data: function () {
            return {
            	name: '',
                selected: {nome:null},
                titulo:"",
                todasAsPermissoes: [],
                permissaoSelecionada: null,
                jaPossuiPermissao:false,
                object:{
                	codigo : 0,
                	nome: "",
                	email: "",
                	userName: "",
                	enabled: false,
                	permissoes: [],
                	codigo: ""
               },
               createObject:{
            	   usuarioId : null,
            	   permissaoId : null
               }
            }
        },
        
        methods:{        	
	        loadData(id){
	        	crudService.findById(URL_MODAL_PERMISSOES, id, r => {
	        		this.object = r.data
	        		this.data = this.object.permissoes
        		})
	        },
	        loadPermissoes(){
	        	crudService.findAll(URL_PERMISSOES, 0, 2000, "descricao", r => {
	        		this.todasAsPermissoes = r.data.content
        		})
	        },
	        verificaPossuiPermissao(codigo){
	        	var per = _.filter(this.object.permissoes, ['codigo', codigo]);
	        	this.jaPossuiPermissao = per[0] != undefined ? true: false
	        },
	        getPermissao(codigo){
	        	var per = _.filter(this.object.permissoes, ['codigo', codigo]);
	        	return per[0] != undefined ? per[0].descricao: ""
	        },
	        addPermissao(permissaoId){
	        	this.createObject.usuarioId = this.object.codigo
	        	this.createObject.permissaoId = permissaoId
	        	
	        	axios
	            .post(URL_MODAL_ADD_PERMISSAO, this.createObject, {headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}})
	            .then(response => {
	            	console.log(response)
	        		if(response.status == 200){	        			
	        			this.$buefy.toast.open({
		                    duration: 3000,
		                    message: "Permissão concedida com sucesso!",
		                    position: 'is-top',
		                    type: 'is-success'
		                })
		                this.loadData(this.object.codigo)
		                this.permissaoSelecionada = null
	        		}else if(response.status == 400){
						this.$buefy.toast.open({
		                    duration: 20000,
		                    message: response.data.message,
		                    position: 'is-top',
		                    type: 'is-danger'
		                })
 					}
	            })
	            .catch(error => {
	            	console.log(error.response)
	            })
	        },
	        removePermissao(permissaoId){
	        	this.createObject.usuarioId = this.object.codigo
	        	this.createObject.permissaoId = permissaoId
	        	
	        	this.$buefy.dialog.confirm({
                    title: 'Revogar Permissão!',
                    message: 'Deseja revogar a Permissão <b>'+this.getPermissao(permissaoId)+"</b> do usuário <b>"+this.object.nome+"</b>",
                    confirmText: 'Revogar Permissão',
                    cancelText: 'Cancelar',
                    type: 'is-danger',
                    hasIcon: true,
                    onConfirm: () => {
                    	    axios
                    	      .post(URL_MODAL_REMOVE_PERMISSAO, this.createObject, {headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}})
                    	      .then(response => {
                    	    	  if(response.status == 200){	        			
              	        			this.$buefy.toast.open({
              		                    duration: 3000,
              		                    message: "Permissão revogada com sucesso!",
              		                    position: 'is-top',
              		                    type: 'is-success'
              		                })
              		                this.loadData(this.object.codigo)
              	        		}else if(response.status == 400){
              						this.$buefy.toast.open({
              		                    duration: 20000,
              		                    message: response.data.message,
              		                    position: 'is-top',
              		                    type: 'is-danger'
              		                })
               						
               					}
                    	      })
                    	      .catch(error => {
                    	    	  console.log(error.response)
                    	      })
                    }
                })
	        	
	        	
	        },
        },
        watch:{
        	object:function(val){
        		this.object = val;
        	}
        },
        mounted () {
        	this.loadData(this.id)
        	this.loadPermissoes()
        	this.titulo="Permissões do Usuário: "+this.nome
       	},
	        template: `
	                <div class="modal-card">
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
	                        <i class="icon-interface-windows" style="font-size: 30px;"></i> {{titulo}}
	                        </p>
	                        
	                    </header>
	                    <section class="modal-card-body"> 
	                    	<table>
		                    	<tr>
			                    	<td>
					                    <b-field >
								            <b-select v-model="permissaoSelecionada" placeholder="Selecione uma Permissão para incluir" 
								            	@change.native="verificaPossuiPermissao(permissaoSelecionada)">
								                <option
								                    v-for="option in todasAsPermissoes"
								                    :value="option.codigo"
								                    :key="option.codigo">
								                    {{ option.descricao }}
								                </option>
								            </b-select>
								        </b-field>
							        </td>
							        <td>
							        	&nbsp;<button :disabled="jaPossuiPermissao || permissaoSelecionada == null"  class="button  is-success" type="button" @click="addPermissao(permissaoSelecionada)">Incluir Permissão</button>
				                    </td>
			                    </tr>
			                    
		                    </table>
		                    <div v-show="jaPossuiPermissao">
		                    	<b-tag type="is-danger">Usuário já possui a permissão selecionada!</b-tag>
		                    </div>
								<b-table
									:data="object == null ? [] : object.permissoes"		                        
									:striped="true"
									:narrowed="true"
									:hoverable="true"
									:mobile-cards="true"
								>
									<template slot-scope="props">
									
										<b-table-column field="codigo" label="ID" width="40" numeric>
											{{ props.row.codigo }}
										</b-table-column>
				
										<b-table-column field="descricao" label="Permissão">
											{{ props.row.descricao }}
										</b-table-column>
										
										<b-table-column field="" label="">
											<a v-show="props.row.descricao != 'ADMINISTRADOR' && props.row.descricao != 'MASTER'" title="Revogar Permissão" @click="removePermissao(props.row.codigo)" style="color: red"  type="is-text"><i class="fas fa-user-slash"></i></a>
										</b-table-column>
									</template>
									<template slot="empty">
										<section class="section">
											<div class="content has-text-grey has-text-centered">
												<p>
													<b-icon icon="emoticon-sad" size="is-large">
													</b-icon>
												</p>
												<p>Não há registros para exibir.</p>
											</div>
										</section>
									</template>
								</b-table>
		                    </section>
		                    
		                    
		                    
	                    <footer class="modal-card-foot">
	                        <button  class="button  is-danger" type="button" @click="$parent.close()">Fechar</button>
	                    </footer>
	                </div>
	        `
}