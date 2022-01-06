const ModalAssentamentoFederalList = {
        props: ['data', 'id'],
        data: function () {
            return {
            	data: this.data,
            	isShowListActive:true,
            	isCertificadoNovoActive:false,
            	municipios:[],
            	name: '',
                selected: {nome:null},
                titulo:""
            }
        },
        computed: {
            
        },
        methods:{
            
        },
        mounted() {
            this.titulo="Assentamento Federal"
       	},
	    template: `<div>
	                <div class="modal-card">
	                    <header class="modal-card-head">
	                        <p class="modal-card-title">
	                        <i class="icon-interface-windows" style="font-size: 30px;"></i> {{titulo}}
	                        </p>
	                        
	                    </header>
	                    <section class="modal-card-body"> 
		                    <!--
					        <button class="button is-info is-small "
												@click="isCertificadoNovoActive = true"><i class="fas fa-plus"></i>&nbsp;&nbsp;Adicionar Certificado</button>
					        -->
		                    <b-table
		                    	v-show="isShowListActive"
		                    	:data="data"		                        
		                        :striped="true"
		                        :narrowed="true"
		                        :hoverable="true"
		                        :mobile-cards="true"
		                    >
			                    <template slot-scope="props">
			                    
				                    <b-table-column field="id" label="ID" width="40" numeric>
				                        {{ props.row.id }}
				                    </b-table-column>
			
				                    <b-table-column field="numero" label="Número">
				                        {{ props.row.numero }}
				                    </b-table-column>
				                    
				                    <b-table-column field="numero" label="Tipo">
				                        {{ props.row.tipo.nome }}
				                    </b-table-column>
				                    
				                </template>
				                <template slot="empty">
			                    <section class="section">
			                        <div class="content has-text-grey has-text-centered">
			                            <p>
			                                <b-icon
			                                    icon="emoticon-sad"
			                                    size="is-large">
			                                </b-icon>
			                            </p>
			                            <p>Não há registros para exibir.</p>
			                        </div>
			                    </section>
			                </template>
				            </b-table>
		                    </section>
		                    
		                    
		                    
	                    <footer class="modal-card-foot">
	                        <button v-show="isShowListActive" class="button  is-danger" type="button" @click="$parent.close()">Fechar</button>
	                    </footer>
	                </div>
	                
					<b-modal can-cancel :active.sync="isCertificadoNovoActive" animation 
						:width="700" has-modal-card> 
						<modal-certificados-add  
							:id="id">
						</modal-certificados-add>
					</b-modal>
				</div>
	        `
}