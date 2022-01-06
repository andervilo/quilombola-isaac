const ComunidadeQuilombo = {
	        props: ['data'],
	        data: function () {
	            return {
	            	object:this.data,
	            	defaultOpenedDetails: [],
	                showDetailIcon: true,
	                isModalViewComunidadeQuilombo: false,
	                selected: null
		        }
	        },
	        methods:{
                toggle(row) {
                    this.$refs.table.toggleDetails(row)
                },
                toLocalDate(date){
                	if(date==null)
	                    return ""
                    return new Date(date).toLocaleDateString()
                },
                onRowSelect(_selected){
                    this.selected = _selected
                    this.isModalViewComunidadeQuilombo = true
                }
	            
	        },
	        mounted() {
	        	
	        },
	        template: `
	            <div>
        	           <b-table
                            :data="data"
                            ref="table"
                            paginated
                            per-page="5"
                            :opened-detailed="defaultOpenedDetails"
                            
                            detail-key="id"
                            :show-detail-icon="showDetailIcon"
                            aria-next-label="Next page"
                            aria-previous-label="Previous page"
                            aria-page-label="Page"
                            aria-current-label="Current page">
                
                            <template slot-scope="props">
                                <b-table-column field="id" label="ID" width="40" numeric>
                                    {{ props.row.id }} 
                                </b-table-column>
                
                                <b-table-column field="" label="Nome" sortable>
                                    <template v-if="showDetailIcon">
                                        {{ props.row.nome }}
                                    </template>
                                    <template v-else>
                                        <a @click="toggle(props.row)">
                                            {{ props.row.nome }}
                                        </a>
                                    </template>
                                </b-table-column>                
                                <b-table-column field="" label="" >
                                    <a class="button "  title="Ver Informações da Comunidade!" @click="onRowSelect(props.row)">                                        
                                        <i style="color: #20bc56;" class="fas fa-eye"></i>
                                    </a>
                                </b-table-column>                
                            </template>
                            
                            
                
                        </b-table>
                        
                        <div id="MODAIS_COMUNIDADES">
                            <b-modal :active.sync="isModalViewComunidadeQuilombo" animation
                                :width="700" has-modal-card full-screen> 
                                <modal-view-comunidade-quilombo :data="selected"></modal-view-comunidade-quilombo>
                            </b-modal>
                        </div>
                    </div>
                `
	}