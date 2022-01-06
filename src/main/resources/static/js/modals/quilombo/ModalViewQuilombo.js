const ModalViewQuilombo = {
	        props: ['data'],
	        data: function () {
	            return {
	            	object:this.data,
	            	activeTab: 0,
	                showBooks: false,
	            	comunidade_tb:this.data.comunidades,
	            	isProfileSuccess: true,
	            	comunidade_col: [
	            	                 {
            	                        field: 'id',
            	                        label: 'ID',
            	                        width: '40',
            	                        numeric: true
            	                    },
            	                    {
            	                        field: 'nome',
            	                        label: 'Nome',
            	                    },
            	                    {
            	                        field: 'numeroComunidades',
            	                        label: 'N° Comunidades',
            	                        centered: true
            	                    },
            	                    {
            	                        field: 'uf.sigla',
            	                        label: 'Estado',
            	                        centered: true
            	                    },
            	                    {
            	                        field: 'uf.regiao.nome',
            	                        label: 'Região',
            	                    }
            	                 ]
	            
		            }
	        },
	        methods:{
	            desativaModalView(){
	                this.$emit('desativa-modal-view')
	                this.$parent.close()
	            },
                toLocalDate(date){
	                if(date==null)
	                    return ""
                    return new Date(date).toLocaleDateString()
                }
	            
	        },
	        mounted() {
	        	
	        },
	        template: `
        	           <div class="modal-card" style="width: auto">
	                       <header class="modal-card-head">
	                        	<p class="modal-card-title">Quilombo: {{data.identificacaoQuilombola}} </p>
	                            <div class="columns">
	                                <div class="column is-half">
	                                    <b-button type="is-danger" @click="desativaModalView">Fechar</b-button>
	                                </div>
	                            </div>
	                        </header>
                        <section class="modal-card-body">
                            
                            
                            <div class="columns" >
                                <div id="tb-comunidades" class="column is-half">
                                    <div class="modal-card" style="width: auto">
                                        <header class="modal-card-head">
                                            <p class="modal-card-title">Comunidades</p>
                                        </header>
                                        <section class="modal-card-body">
                                            <comunidade-quilombo :data="object.comunidades"></comunidade-quilombo>
                                        </section>
                                    </div>
                                </div>
                                
                                <!-- Territórios -->
                                <div id="tb-territorios" class="column is-half">
                                    <div class="modal-card" style="width: auto">
                                        <header class="modal-card-head">
                                            <p class="modal-card-title">Territórios</p>
                                        </header>
                                        <section class="modal-card-body">
                                            <territorio-quilombo :data="object.territorioList"></territorio-quilombo>
                                        </section>
                                    </div>
                                </div>
                                <!-- Fim Territórios -->
                            </div>
                        </section
                    </div>
                `
	}