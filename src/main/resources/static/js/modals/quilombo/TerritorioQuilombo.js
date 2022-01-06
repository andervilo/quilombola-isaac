const TerritorioQuilombo = {
	        props: ['data'],
	        data: function () {
	            return {
	            	object:this.data,
	            	defaultOpenedDetails: [],
	                showDetailIcon: true
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
                }	        
	            
	        },
	        mounted() {
	        	
	        },
	        template: `
        	           <b-table
                            :data="data"
                            ref="table"
                            paginated
                            per-page="5"
                            :opened-detailed="defaultOpenedDetails"
                            detailed
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
                                
                                <b-table-column field="numeroComunidades" label="Número de Comunidades" > 
                                     {{ props.row.numeroComunidades}}
                                </b-table-column>
                            </template>
                
                            <template slot="detail" slot-scope="props" size="is-small">
                            	   <!--******************************-->
                            	   
                            	   <!--******************************-->
                                    <b-tabs type="is-toggle" expanded>
                                        <b-tab-item label="Municípios">
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th>Nome</th>
                                                        <th>Cod.IBGE</th>
                                                        <th>Superintendência</h>
                                                        <th>Estado</th>
                                                        <th>Região</th>
                                                        <th>Amazônia Legal</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr v-for="m in props.row.municipioList" :key="m.id">
                                                        <td>{{m.nome}}</td>
                                                        <td>{{m.codigoIbge}}</td>
                                                        <td>
                                                            <b-tag type="is-primary" v-for="superintendencia in m.superintendencias" rounded>
	                                                            {{superintendencia.nome}}
                                                            </b-tag>
                                                        </td>
                                                        <td>{{m.estado.nome}}</td>
                                                        <td>{{m.estado.regiao.nome}}</td>
                                                        <td>
                                                            <i style="color: green;" class="fa fa-check"   v-show="m.amazoniaLegal===true"></i>
                                                            <i style="color: red;" class="fa fa-times"   v-show="m.amazoniaLegal===false"></i>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                            
                                    </b-tabs>
                                     
                            </template>
                        </b-table>
                `
	}