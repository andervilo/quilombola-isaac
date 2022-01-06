const ModalQuilombo = {
	        props: ['dataQuilombo'],
	        template: `
                <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title">
                    <i class="icon-interface-windows" style="font-size: 30px;"></i> Relação de Quilombos do Território
                    </p>
                    
                </header>
                <section class="modal-card-body"> 
                    <button class="button field is-danger" @click="selected = null"
                        :disabled="!selected">
                        <b-icon icon="close"></b-icon>
                        <span>Limpar Seleção</span>
                    </button>
                    <b-table
                    	:data="dataQuilombo"
                    	ref="table"
                        
                        :striped="true"
                        :narrowed="true"
                        :hoverable="true"
                        :mobile-cards="true"
                    	:selected.sync="selected"
                    >
	                    <template slot-scope="props">
		                    
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
                    <button  class="button  is-danger" type="button" @click="$parent.close()">Fechar</button>
                </footer>
            </div>
    `
}