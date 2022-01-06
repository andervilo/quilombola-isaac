const ModalViewComunidadeQuilombo = {
	        props: ['data'],
	        data: function () {
	            return {
	            	object:this.data,
	            	activeTab: 0,
	                showBooks: false,
	            	comunidade_tb:this.data.comunidades,
	            	isProfileSuccess: true,
	            	
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
	                        	<p class="modal-card-title">Comunidade: {{data.nome}} </p>
	                            <div class="columns">
	                                <div class="column is-half">
	                                    <b-button type="is-danger" @click="desativaModalView">Fechar</b-button>
	                                </div>
	                            </div>
	                        </header>
                        <section class="modal-card-body">
                            <div class="columns" >
                                <div class="column">
                                    <b-taglist v-if="object.dadosAdicionais != null">
                                        <b-tag :type="{'is-success': object.dadosAdicionais.certificada, 'is-danger':!object.dadosAdicionais.certificada}" size="is-large">Certificado</b-tag>
                                        <b-tag :type="{'is-success': object.dadosAdicionais.processosReconhecimento, 'is-danger':!object.dadosAdicionais.processosReconhecimento}" size="is-large">Processo</b-tag>
                                        <b-tag :type="{'is-success': object.dadosAdicionais.edital, 'is-danger':!object.dadosAdicionais.edital}" size="is-large">Edital</b-tag>
                                        <b-tag :type="{'is-success': object.dadosAdicionais.portaria, 'is-danger':!object.dadosAdicionais.portaria}" size="is-large">Portaria</b-tag>
                                        <b-tag :type="{'is-success': object.dadosAdicionais.decreto, 'is-danger':!object.dadosAdicionais.decreto}" size="is-large">Decreto</b-tag>
                                        <b-tag :type="{'is-success': object.dadosAdicionais.titulada, 'is-danger':!object.dadosAdicionais.titulada}" size="is-large">Título</b-tag>
                                        <b-tag :type="{'is-success': object.dadosAdicionais.assentamentos, 'is-danger':!object.dadosAdicionais.assentamentos}" size="is-large">Assentamentos</b-tag>
                                    </b-taglist>
                                </div>
                            </div>
                            
                            <dvi class="columns">
                                <dvi class="column">
                                    <b-tabs v-model="activeTab" >
                                        <b-tab-item label="Municípios">
                                            <table class="table">
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
                                                    <tr v-for="m in object.municipios" :key="m.id">
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
                            
                                        <b-tab-item label="Certificado" v-if="object.certificado != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.certificado.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/certificados/'+object.certificado.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número Processo FCP:</b></td>
                                                        <td>{{object.certificado.numeroProcessoFcp}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data abertura processo:</b></td>
                                                        <td>{{toLocalDate(object.certificado.dataAberturaProcessoCertificado)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Livro:</b></td>
                                                        <td>{{object.certificado.livro}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Folha:</b></td>
                                                        <td>{{object.certificado.folha}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número:</b></td>
                                                        <td>{{object.certificado.numero}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número Portaria:</b></td>
                                                        <td>{{object.certificado.numeroPortaria}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data da Portaria Portaria:</b></td>
                                                        <td>{{toLocalDate(object.certificado.dataPortaria)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(object.certificado.dataDiarioOficial)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Diário Oficial:</b></td>
                                                        <td>{{object.certificado.diarioOficial}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Seção Diário Oficial:</b></td>
                                                        <td>{{object.certificado.secaoDO}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Folha Diário Oficial:</b></td>
                                                        <td>{{object.certificado.folhaDO}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Links publicação Diário Oficial:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.certificado.linkPublicacaoDO">{{object.certificado.linkPublicacaoDO}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.certificado.linkPublicacaoDO2">{{object.certificado.linkPublicacaoDO2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.certificado.linkPublicacaoDO3">{{object.certificado.linkPublicacaoDO3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Observação/Retificação:</b></td>
                                                        <td>{{object.certificado.retificacaoFcpObservacao}}</td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                            
                                        <b-tab-item label="Processo Administrativo" v-if="object.processoAdministrativo != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.processoAdministrativo.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/processos/'+object.processoAdministrativo.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número Processo:</b></td>
                                                        <td>{{object.processoAdministrativo.numeroProcessoReconhecimento}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Âmbito:</b></td>
                                                        <td>{{object.processoAdministrativo.ambito.nome}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Processo:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.processoAdministrativo.linkProcessoSei">{{object.processoAdministrativo.linkProcessoSei}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.processoAdministrativo.linkProcessoSei2">{{object.processoAdministrativo.linkProcessoSei2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.processoAdministrativo.linkProcessoSei3">{{object.processoAdministrativo.linkProcessoSei3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Localização Acervo Fundiário:</b></td>
                                                        <td>{{object.processoAdministrativo.localizacaoAcervoFundiario}}</td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                            
                                        <b-tab-item label="Edital" v-if="object.editalComunicacao != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.editalComunicacao.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/editais/'+object.editalComunicacao.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Área HA Edital:</b></td>
                                                        <td>{{object.editalComunicacao.areaHaEdital}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número de Famílias:</b></td>
                                                        <td>{{object.editalComunicacao.numeroFamilias}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial I:</b></td>
                                                        <td>{{toLocalDate(object.editalComunicacao.dataDiarioOficialUniaoDiarioOficialEstadoPrimeiroEdital)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial II:</b></td>
                                                        <td>{{toLocalDate(object.editalComunicacao.dataDiarioOficialUniaoDiarioOficialEstadoSegundoEdital)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Edital:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.editalComunicacao.linkPublicacao_1">{{object.editalComunicacao.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.editalComunicacao.linkPublicacao_2">{{object.editalComunicacao.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.editalComunicacao.linkPublicacao_3">{{object.editalComunicacao.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                                        
                                        <b-tab-item label="Decreto" v-if="object.decretoDesapropriatorio != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.decretoDesapropriatorio.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/decretos/'+object.decretoDesapropriatorio.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número Decreto:</b></td>
                                                        <td>{{object.decretoDesapropriatorio.numeroDecretoDesapropriacao}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data do Decreto:</b></td>
                                                        <td>{{toLocalDate(object.decretoDesapropriatorio.dataDecretoDeaproriacaoa)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(object.decretoDesapropriatorio.dataDecretoDesapropriacaoDiarioOficialUniao)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.decretoDesapropriatorio.linkPublicacao_1">{{object.decretoDesapropriatorio.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.decretoDesapropriatorio.linkPublicacao_2">{{object.decretoDesapropriatorio.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.decretoDesapropriatorio.linkPublicacao_3">{{object.decretoDesapropriatorio.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                                        
                                        <b-tab-item label="Titulo de Propriedade" v-if="object.tituloPropriedade != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.tituloPropriedade.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/titulos/'+object.tituloPropriedade.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Área HA Título:</b></td>
                                                        <td>{{object.tituloPropriedade.areaHaTitulo}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>% área titulada:</b></td>
                                                        <td>{{object.tituloPropriedade.percentagemAreaTitulada}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número de Famílias:</b></td>
                                                        <td>{{object.tituloPropriedade.numeroFamiliaTitulos}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data do Título:</b></td>
                                                        <td>{{toLocalDate(object.tituloPropriedade.dataTitulo)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Órgão expedidor:</b></td>
                                                        <td>{{object.tituloPropriedade.orgaoExpedidor.nome}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Título:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.tituloPropriedade.linkPublicacao_1">{{object.tituloPropriedade.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.tituloPropriedade.linkPublicacao_2">{{object.tituloPropriedade.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.tituloPropriedade.linkPublicacao_3">{{object.tituloPropriedade.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Observção:</b></td>
                                                        <td>{{object.tituloPropriedade.observacaoAreaTitulo}}</td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                                        
                                        <b-tab-item label="Assent. Estadual" v-if="object.assentamentoEstadual != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.assentamentoEstadual.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/assentamentos/'+object.assentamentoEstadual.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número:</b></td>
                                                        <td>{{object.assentamentoEstadual.numero}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Portaria:</b></td>
                                                        <td>{{toLocalDate(object.assentamentoEstadual.dataPortaria)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(object.assentamentoEstadual.dataDO)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Seção Diário Oficial:</b></td>
                                                        <td>{{object.assentamentoEstadual.secaoDO}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Folha Diário Oficial:</b></td>
                                                        <td>{{object.assentamentoEstadual.folhaDO}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data Decreto:</b></td>
                                                        <td>{{toLocalDate(object.assentamentoEstadual.dataDecreto)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Área:</b></td>
                                                        <td>{{object.assentamentoEstadual.area}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Tipo:</b></td>
                                                        <td>{{object.assentamentoEstadual.tipo.nome}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoEstadual.linkPublicacaoDecreto_1">{{object.assentamentoEstadual.linkPublicacaoDecreto_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoEstadual.linkPublicacaoDecreto_2">{{object.assentamentoEstadual.linkPublicacaoDecreto_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoEstadual.linkPublicacaoDecreto_3">{{object.assentamentoEstadual.linkPublicacaoDecreto_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Links publicação Diário Oficial:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoEstadual.linkPublicacaoDiarioUniaoDecreto_1">{{object.assentamentoEstadual.linkPublicacaoDiarioUniaoDecreto_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoEstadual.linkPublicacaoDiarioUniaoDecreto_2">{{object.assentamentoEstadual.linkPublicacaoDiarioUniaoDecreto_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoEstadual.linkPublicacaoDiarioUniaoDecreto_3">{{object.assentamentoEstadual.linkPublicacaoDiarioUniaoDecreto_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                    
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                                        
                                        <b-tab-item label="Assent. Federal" v-if="object.assentamentoFederal != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.assentamentoFederal.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/assentamentos/'+object.assentamentoFederal.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número:</b></td>
                                                        <td>{{object.assentamentoFederal.numero}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Portaria:</b></td>
                                                        <td>{{toLocalDate(object.assentamentoFederal.dataPortaria)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(object.assentamentoFederal.dataDO)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Seção Diário Oficial:</b></td>
                                                        <td>{{object.assentamentoFederal.secaoDO}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Folha Diário Oficial:</b></td>
                                                        <td>{{object.assentamentoFederal.folhaDO}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Data Decreto:</b></td>
                                                        <td>{{toLocalDate(object.assentamentoFederal.dataDecreto)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Área:</b></td>
                                                        <td>{{object.assentamentoFederal.area}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Tipo:</b></td>
                                                        <td>{{object.assentamentoFederal.tipo.nome}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoFederal.linkPublicacaoDecreto_1">{{object.assentamentoFederal.linkPublicacaoDecreto_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoFederal.linkPublicacaoDecreto_2">{{object.assentamentoFederal.linkPublicacaoDecreto_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoFederal.linkPublicacaoDecreto_3">{{object.assentamentoFederal.linkPublicacaoDecreto_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Links publicação Diário Oficial:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoFederal.linkPublicacaoDiarioUniaoDecreto_1">{{object.assentamentoFederal.linkPublicacaoDiarioUniaoDecreto_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoFederal.linkPublicacaoDiarioUniaoDecreto_2">{{object.assentamentoFederal.linkPublicacaoDiarioUniaoDecreto_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.assentamentoFederal.linkPublicacaoDiarioUniaoDecreto_3">{{object.assentamentoFederal.linkPublicacaoDiarioUniaoDecreto_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                                        
                                        <b-tab-item label="Matrícula" v-if="object.matricula != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.matricula.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/matriculas/'+object.matricula.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Cartório:</b></td>
                                                        <td>{{object.matricula.cartorioRegistroImoveis}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Livro:</b></td>
                                                        <td>{{object.matricula.livroRegistroImoveis}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Folha:</b></td>
                                                        <td>{{object.matricula.folhaRegistroImoveis}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Matrícula:</b></td>
                                                        <td>{{object.matricula.matricula}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.matricula.linkPublicacao_1">{{object.matricula.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.matricula.linkPublicacao_2">{{object.matricula.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.matricula.linkPublicacao_3">{{object.matricula.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                                        
                                        <b-tab-item label="Portaria de Recon." v-if="object.portariaReconhecimento != null">
                                            <table class="table">
                                                <tbody>
                                                    <tr v-if="object.portariaReconhecimento.nomeDocumento != null">
                                                        <td width="200"><b>Download Documento:</b></td>
                                                        <td>
                                                        <a :href="'/sigequi/portarias/'+object.portariaReconhecimento.id+'/documento'" style="color: #fff"  class="button is-success is-small"
                                                            ><i class="fa fa-download"></i>&nbsp; Download</a>
                                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Número:</b></td>
                                                        <td>{{object.portariaReconhecimento.numeroPortariaReconhecimento}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Portaria:</b></td>
                                                        <td>{{toLocalDate(object.portariaReconhecimento.dataPortariaReconhecimento)}}</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200"><b>Data Diário Oficial:</b></td>
                                                        <td>{{toLocalDate(object.portariaReconhecimento.portariaReconhecimentoDiarioOficialUniao)}}</td>
                                                    </tr>
                                                    
                                                    <tr>
                                                        <td width="200"><b>Links publicação Decreto:</b></td>
                                                        <td>
                                                            <p><a target="_blank" v-bind:href="object.portariaReconhecimento.linkPublicacao_1">{{object.portariaReconhecimento.linkPublicacao_1}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.portariaReconhecimento.linkPublicacao_2">{{object.portariaReconhecimento.linkPublicacao_2}}</a></p>
                                                            <p><a target="_blank" v-bind:href="object.portariaReconhecimento.linkPublicacao_3">{{object.portariaReconhecimento.linkPublicacao_3}}</a></p>
                                                        </td>
                                                    </tr>
                                                    
                                                </tbody>
                                            </table>
                                        </b-tab-item>
                                    </b-tabs>
                                </dvi>
                            </dvi>
                            
                            <div v-if="object.dadosAdicionais != null" class="columns" >
                                <div class="column">
                                    <table>
                                        <tr>
                                            <th>Cadastro INCRA:</th>
                                            <td>{{object.dadosAdicionais.cadastroIncra}}</td>
                                        </tr>
                                        <tr>
                                            <th>Cadastro SIGEF:</th>
                                            <td>{{object.dadosAdicionais.cadastroSigef}}</td>
                                        </tr>
                                        <tr>
                                            <th>Cadastro CAR:</th>
                                            <td>{{object.dadosAdicionais.cadastroCar}}</td>
                                        </tr>
                                        
                                        <tr>
                                            <th>Latitude IBGE:</th>
                                            <td>{{object.dadosAdicionais.latitudeIbge}}</td>
                                        </tr>                                        
                                        <tr>
                                            <th>Longitude IBGE:</th>
                                            <td>{{object.dadosAdicionais.longitudeIbge}}</td>
                                        </tr>
                                        
                                        <tr>
                                            <th>Latitude INCRA:</th>
                                            <td>{{object.dadosAdicionais.latitudeIncra}}</td>
                                        </tr><tr>
                                            <th>Longitude INCRA:</th>
                                            <td>{{object.dadosAdicionais.longitudeIncra}}</td>
                                        </tr>
                                        <tr>
                                            <th>Localização INCRA:</th>
                                            <td>{{object.dadosAdicionais.localizacaoIncra}}</td>
                                        </tr>
                                        
                                        <tr>
                                            <th>Área (HA) Territorial:</th>
                                            <td>{{object.dadosAdicionais.areahaTerritorial}}</td>
                                        </tr>
                                        <tr>
                                            <th>Cópia Título Arquivo Autor:</th>
                                            <td>
                                                <i style="color: green;" class="fa fa-check"   v-show="object.dadosAdicionais.copiaTituloArquivoAutor===true"></i>
                                                <i style="color: red;" class="fa fa-times"   v-show="object.dadosAdicionais.copiaTituloArquivoAutor===false || object.dadosAdicionais.copiaTituloArquivoAutor===null"></i>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Publicação Terra Quilombo:</th>
                                            <td>
                                                <i style="color: green;" class="fa fa-check"   v-show="object.dadosAdicionais.publicacaoTerraQuilombo===true"></i>
                                                <i style="color: red;" class="fa fa-times"   v-show="object.dadosAdicionais.publicacaoTerraQuilombo===false || object.dadosAdicionais.publicacaoTerraQuilombo===null"></i>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Quilombo Urbano:</th>
                                            <td>
                                                <i style="color: green;" class="fa fa-check"   v-show="object.dadosAdicionais.quilomboUrbano===true"></i>
                                                <i style="color: red;" class="fa fa-times"   v-show="object.dadosAdicionais.quilomboUrbano===false || object.dadosAdicionais.quilomboUrbano===null"></i>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Concessão:</th>
                                            <td>
                                                <i style="color: green;" class="fa fa-check"   v-show="object.dadosAdicionais.concessao===true"></i>
                                                <i style="color: red;" class="fa fa-times"   v-show="object.dadosAdicionais.concessao===false || object.dadosAdicionais.concessao===null"></i>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                
                                
                                <div class="column">
                                    <table>
                                        <tr>
                                            <th>PEQ TEQ:</th>
                                            <td>
                                                <i style="color: green;" class="fa fa-check"   v-show="object.dadosAdicionais.peqTeq===true"></i>
                                                <i style="color: red;" class="fa fa-times"   v-show="object.dadosAdicionais.peqTeq===false || object.dadosAdicionais.peqTeq===null"></i>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Quantidade Famílias PEQ TEQ :</th>
                                            <td>{{object.dadosAdicionais.qtdFamiliasPeqTeq}}</td>
                                        </tr>
                                        <tr>
                                            <th>Área (HA) PEQ TEQ:</th>
                                            <td>{{object.dadosAdicionais.areahaPeqTeq}}</td>
                                        </tr>
                                        <tr>
                                            <th>Número Portaria PEQ TEQ:</th>
                                            <td>{{object.dadosAdicionais.numeroPortariaPeqTeq}}</td>
                                        </tr>
                                        <tr>
                                            <th>Data Portaria PEQ TEQ:</th>
                                            <td>{{toLocalDate(object.dadosAdicionais.dataPortariaPeqTeq)}}</td>
                                        </tr>
                                        <tr>
                                            <th>Data Publicação DOU/DEO PEQ TEQ:</th>
                                            <td>{{toLocalDate(object.dadosAdicionais.dataDiarioOficialUniaoDiarioOficialEstadoPortariaPeqTeq)}}</td>
                                        </tr>
                                        <tr>
                                            <th>Outros Processos Administrativos:</th>
                                            <td>
                                            	<p v-for="procadm in object.dadosAdicionais.outrosProcessosAdministrativos" :key="procadm.link">
                                            		<a target="_blank" v-bind:href=procadm.link>{{procadm.link}}</a>
                                            	</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Processos Judiciais:</th>
                                            <td>
                                            	<p v-for="procjud in object.dadosAdicionais.processosJudiciais" :key="procjud.link">
                                            		<a target="_blank" v-bind:href=procjud.link>{{procjud.link}}</a>
                                            	</p>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                
                            </div>
                            
                            <div class="columns" >
                            </div>
                        </section
                    </div>
                `
	}